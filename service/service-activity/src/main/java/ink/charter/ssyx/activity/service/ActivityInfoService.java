package ink.charter.ssyx.activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.activity.ActivityInfo;
import ink.charter.ssyx.model.activity.ActivityRule;
import ink.charter.ssyx.model.order.CartInfo;
import ink.charter.ssyx.model.product.SkuInfo;
import ink.charter.ssyx.vo.activity.ActivityRuleVo;
import ink.charter.ssyx.vo.order.CartInfoVo;
import ink.charter.ssyx.vo.order.OrderConfirmVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 活动表 服务类
 * </p>
 *
 * @author charter
 * @since 2023-09-25
 */
public interface ActivityInfoService extends IService<ActivityInfo> {

    //列表
    IPage<ActivityInfo> selectPage(Page<ActivityInfo> pageParam);

    //1 根据活动id获取活动规则数据
    Map<String, Object> findActivityRuleList(Long activityId);

    //2 在活动里面添加规则数据
    void saveActivityRule(ActivityRuleVo activityRuleVo);

    //3 根据关键字查询匹配sku信息
    List<SkuInfo> findSkuInfoByKeyword(String keyword);

    //根据skuId列表获取促销信息
    Map<Long, List<String>> findActivity(List<Long> skuIdList);

    //根据skuID获取营销数据和优惠卷
    Map<String, Object> findActivityAndCoupon(Long skuId, Long userId);

    //根据skuId获取活动规则数据
    List<ActivityRule> findActivityRuleBySkuId(Long skuId);

    //获取购物车里面满足条件优惠卷和活动的信息
    OrderConfirmVo findCartActivityAndCoupon(List<CartInfo> cartInfoList, Long userId);

    //获取购物车对应规则数据
    List<CartInfoVo> findCartActivityList(List<CartInfo> cartInfoList);
}
