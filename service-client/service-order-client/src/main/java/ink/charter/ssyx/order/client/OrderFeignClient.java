package ink.charter.ssyx.order.client;

import ink.charter.ssyx.model.order.OrderInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Charter
 * @create 2023-10-04 9:52
 */
@FeignClient("service-order")
public interface OrderFeignClient {

    @GetMapping("/api/order/inner/getOrderInfo/{orderNo}")
    OrderInfo getOrderInfo(@PathVariable("orderNo") String orderNo);
}
