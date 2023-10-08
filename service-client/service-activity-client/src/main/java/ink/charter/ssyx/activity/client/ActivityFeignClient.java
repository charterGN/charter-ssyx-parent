package ink.charter.ssyx.activity.client;

import ink.charter.ssyx.model.activity.CouponInfo;
import ink.charter.ssyx.model.order.CartInfo;
import ink.charter.ssyx.vo.order.CartInfoVo;
import ink.charter.ssyx.vo.order.OrderConfirmVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author Charter
 * @create 2023-09-26 10:41
 */
@FeignClient("service-activity")
public interface ActivityFeignClient {

    //获取购物车里面满足条件优惠卷和活动的信息
    @PostMapping("/api/activity/inner/findCartActivityAndCoupon/{userId}")
    OrderConfirmVo findCartActivityAndCoupon(@RequestBody List<CartInfo> cartInfoList,
                                                    @PathVariable("userId") Long userId);

    @ApiOperation("根据skuID获取营销数据和优惠卷")
    @GetMapping("/api/activity/inner/findActivityAndCoupon/{skuId}/{userId}")
    Map<String,Object> findActivityAndCoupon(@PathVariable("skuId") Long skuId,
                                                    @PathVariable("userId") Long userId);

    @PostMapping("/api/activity/inner/findActivity")
    Map<Long, List<String>> findActivity(@RequestBody List<Long> skuIdList);

    @PostMapping("/api/activity/inner/findRangeSkuIdList/{couponId}")
    CouponInfo findRangeSkuIdList(@RequestBody List<CartInfo> cartInfoList,
                                         @PathVariable("couponId") Long couponId);

    @GetMapping("/api/activity/inner/updateCouponInfoUseStatus/{couponId}/{userId}/{orderId}")
    Boolean updateCouponInfoUseStatus(@PathVariable("couponId") Long couponId,
                                             @PathVariable("userId") Long userId,
                                             @PathVariable("orderId") Long orderId);

    //获取购物车对应规则数据
    @PostMapping("/api/activity/inner/findCartActivityList")
    List<CartInfoVo> findCartActivityList(@RequestBody List<CartInfo> cartInfoList);
}

