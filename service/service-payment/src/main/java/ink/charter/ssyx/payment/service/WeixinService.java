package ink.charter.ssyx.payment.service;

import java.util.Map;

/**
 * @author Charter
 * @create 2023-09-28 09:53
 */
public interface WeixinService {

    //调用微信支付系统生成预付单
    Map<String, String> createJsapi(String orderNo);

    //1 调用微信支付系统接口查询订单支付状态
    Map<String, String> queryPayStatus(String orderNo);
}
