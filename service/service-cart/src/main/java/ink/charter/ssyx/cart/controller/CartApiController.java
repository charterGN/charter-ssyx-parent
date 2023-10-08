package ink.charter.ssyx.cart.controller;

import ink.charter.ssyx.activity.client.ActivityFeignClient;
import ink.charter.ssyx.cart.service.CartInfoService;
import ink.charter.ssyx.common.auth.AuthContextHolder;
import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.model.order.CartInfo;
import ink.charter.ssyx.vo.order.OrderConfirmVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Charter
 * @create 2023-09-26 10:20
 */
@RestController
@RequestMapping("/api/cart")
public class CartApiController {

    @Autowired
    private CartInfoService cartInfoService;
    
    @Autowired
    private ActivityFeignClient activityFeignClient;

    //1 根据skuId选中
    @GetMapping("checkCart/{skuId}/{isChecked}")
    public Result checkCart(@PathVariable Long skuId,
                            @PathVariable Integer isChecked) {
        //获取userId
        Long userId = AuthContextHolder.getUserId();
        //调用方法
        cartInfoService.checkCart(userId,skuId,isChecked);
        return Result.ok();
    }

    //2 全选
    @GetMapping("checkAllCart/{isChecked}")
    public Result checkAllCart(@PathVariable Integer isChecked) {
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.checkAllCart(userId,isChecked);
        return Result.ok();
    }

    //3 批量选中
    @PostMapping("batchCheckCart/{isChecked}")
    public Result batchCheckCart(@RequestBody List<Long> skuIdList,
                                 @PathVariable Integer isChecked) {
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.batchCheckCart(skuIdList,userId,isChecked);
        return Result.ok();
    }

    /**
     * 查询带优惠卷的购物车
     *
     * @return
     */
    @GetMapping("activityCartList")
    public Result activityCartList() {
        // 获取用户Id
        Long userId = AuthContextHolder.getUserId();
        List<CartInfo> cartInfoList = cartInfoService.getCartList(userId);

        OrderConfirmVo orderTradeVo = activityFeignClient.findCartActivityAndCoupon(cartInfoList, userId);
        return Result.ok(orderTradeVo);
    }

    //购物车列表
    @GetMapping("cartList")
    public Result cartList() {
        //获取userId
        Long userId = AuthContextHolder.getUserId();
        List<CartInfo> cartInfoList = cartInfoService.getCartList(userId);
        return Result.ok(cartInfoList);
    }

    //添加商品到购物车
    //添加内容：当前登录用户id，skuId，商品数量
    @GetMapping("addToCart/{skuId}/{skuNum}")
    public Result addToCart(@PathVariable("skuId") Long skuId,
                            @PathVariable("skuNum") Integer skuNum) {
        //获取当前登录用户Id
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.addToCart(userId,skuId,skuNum);
        return Result.ok();
    }

    //根据skuId删除购物车
    @DeleteMapping("deleteCart/{skuId}")
    public Result deleteCart(@PathVariable("skuId") Long skuId) {
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.deleteCart(skuId,userId);
        return Result.ok();
    }

    //清空购物车
    @DeleteMapping("deleteAllCart")
    public Result deleteAllCart() {
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.deleteAllCart(userId);
        return Result.ok();
    }

    //批量删除购物车 多个skuId
    @DeleteMapping("batchDeleteCart")
    public Result batchDeleteCart(@RequestBody List<Long> skuIdList) {
        Long userId = AuthContextHolder.getUserId();
        cartInfoService.batchDeleteCart(skuIdList,userId);
        return Result.ok();
    }

    //获取当前用户购物车选中购物项
    /**
     * 根据用户Id 查询购物车列表
     *
     * @param userId
     * @return
     */
    @GetMapping("inner/getCartCheckedList/{userId}")
    public List<CartInfo> getCartCheckedList(@PathVariable("userId") Long userId) {
        return cartInfoService.getCartCheckedList(userId);
    }

}
