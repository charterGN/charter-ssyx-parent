package ink.charter.ssyx.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.charter.ssyx.common.exception.SsyxException;
import ink.charter.ssyx.common.result.ResultCodeEnum;
import ink.charter.ssyx.enums.PaymentStatus;
import ink.charter.ssyx.enums.PaymentType;
import ink.charter.ssyx.model.order.OrderInfo;
import ink.charter.ssyx.model.order.PaymentInfo;
import ink.charter.ssyx.mq.constant.MqConst;
import ink.charter.ssyx.mq.service.RabbitService;
import ink.charter.ssyx.order.client.OrderFeignClient;
import ink.charter.ssyx.payment.mapper.PaymentInfoMappper;
import ink.charter.ssyx.payment.service.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @author Charter
 * @create 2023-09-28 10:08
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMappper, PaymentInfo> implements PaymentInfoService {

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private RabbitService rabbitService;

    @Override
    public PaymentInfo getPaymentInfoByOrderNo(String orderNo) {
        PaymentInfo paymentInfo = baseMapper.selectOne(
                new LambdaQueryWrapper<PaymentInfo>()
                        .eq(PaymentInfo::getOrderNo, orderNo)
        );
        return paymentInfo;
    }

    @Override
    public PaymentInfo savePaymentInfo(String orderNo) {
        //远程调用调用，根据orderNo查询订单信息
        OrderInfo orderInfo = orderFeignClient.getOrderInfo(orderNo);
        if(orderInfo == null) {
            throw new SsyxException(ResultCodeEnum.DATA_ERROR);
        }
        //封装到PaymentInfo对象
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setCreateTime(new Date());
        paymentInfo.setOrderId(orderInfo.getId());
        paymentInfo.setPaymentType(PaymentType.WEIXIN);
        paymentInfo.setUserId(orderInfo.getUserId());
        paymentInfo.setOrderNo(orderInfo.getOrderNo());
        paymentInfo.setPaymentStatus(PaymentStatus.UNPAID);
        String subject = "userID:"+orderInfo.getUserId()+"下订单";
        paymentInfo.setSubject(subject);
        //paymentInfo.setTotalAmount(orderInfo.getTotalAmount());
        //TODO 为了测试
        paymentInfo.setTotalAmount(new BigDecimal("0.01"));

        //调用方法实现添加
        baseMapper.insert(paymentInfo);
        return paymentInfo;
    }

    //3.1 支付成功，修改支付记录表状态：已经支付
    //3.2 支付成功，修改订单记录已经支付，库存扣减
    @Override
    public void paySuccess(String orderNo, Map<String, String> resultMap) {
        //1 查询当前订单支付记录表状态是否是已经支付
        PaymentInfo paymentInfo = baseMapper.selectOne(
                new LambdaQueryWrapper<PaymentInfo>()
                        .eq(PaymentInfo::getOrderNo, orderNo)
        );
        if(paymentInfo.getPaymentStatus() != PaymentStatus.UNPAID) {
            return;
        }

        //2 如果支付记录表支付状态没有支付，更新
        paymentInfo.setPaymentStatus(PaymentStatus.PAID);
        paymentInfo.setTradeNo(resultMap.get("transaction_id"));
        paymentInfo.setCallbackContent(resultMap.toString());
        baseMapper.updateById(paymentInfo);

        //3 整合RabbitMQ实现 修改订单记录已经支付，库存扣减
        rabbitService.sendMessage(MqConst.EXCHANGE_PAY_DIRECT,
                MqConst.ROUTING_PAY_SUCCESS,orderNo);
    }

}
