package ink.charter.ssyx.cart.client;

import ink.charter.ssyx.model.order.CartInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Charter
 * @create 2023-10-04 9:50
 */
@FeignClient("service-cart")
public interface CartFeignClient {

    //获取当前用户购物车选中购物项
    /**
     * 根据用户Id 查询购物车列表
     *
     * @param userId
     * @return
     */
    @GetMapping("/api/cart/inner/getCartCheckedList/{userId}")
    List<CartInfo> getCartCheckedList(@PathVariable("userId") Long userId);
}
