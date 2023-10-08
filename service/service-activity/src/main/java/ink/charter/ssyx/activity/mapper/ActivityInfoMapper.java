package ink.charter.ssyx.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.charter.ssyx.model.activity.ActivityInfo;
import ink.charter.ssyx.model.activity.ActivityRule;
import ink.charter.ssyx.model.activity.ActivitySku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author charter
 * @since 2023-09-25
 */
@Mapper
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {

    // 如果之前参加过，活动正在进行中，排除商品
    List<Long> selectSkuIdListExist(@Param("skuIdList") List<Long> skuIdList);

    //根据skuId进行查询，查询sku对应活动里面规则列表
    List<ActivityRule> findActivityRule(@Param("skuId") Long skuId);

    //根据所有skuId列表获取参与活动
    List<ActivitySku> selectCartActivity(@Param("skuIdList") List<Long> skuIdList);
}
