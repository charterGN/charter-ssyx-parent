package ink.charter.ssyx.order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.order.OrderInfo;
import ink.charter.ssyx.vo.order.OrderConfirmVo;
import ink.charter.ssyx.vo.order.OrderSubmitVo;
import ink.charter.ssyx.vo.order.OrderUserQueryVo;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author charter
 * @since 2023-09-27
 */
public interface OrderInfoService extends IService<OrderInfo> {

    //确认订单
    OrderConfirmVo confirmOrder();

    //生成订单
    Long submitOrder(OrderSubmitVo orderParamVo);

    //订单详情
    OrderInfo getOrderInfoById(Long orderId);

    //根据orderNo查询订单信息
    OrderInfo getOrderInfoByOrderNo(String orderNo);

    //订单支付成功，更新订单状态，扣减库存
    void orderPay(String orderNo);

    //订单查询
    IPage<OrderInfo> getOrderInfoByUserIdPage(Page<OrderInfo> pageParam, OrderUserQueryVo orderUserQueryVo);
}
