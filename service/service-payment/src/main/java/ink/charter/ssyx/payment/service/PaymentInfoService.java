package ink.charter.ssyx.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.order.PaymentInfo;

import java.util.Map;

/**
 * @author Charter
 * @create 2023-09-28 09:41
 */
public interface PaymentInfoService extends IService<PaymentInfo> {

    //根据orderNo查询支付记录
    PaymentInfo getPaymentInfoByOrderNo(String orderNo);

    //添加支付记录
    PaymentInfo savePaymentInfo(String orderNo);

    //3.1 支付成功，修改支付记录表状态：已经支付
    //3.2 支付成功，修改订单记录已经支付，库存扣减
    void paySuccess(String out_trade_no, Map<String, String> resultMap);
}
