package ink.charter.ssyx.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.charter.ssyx.model.activity.CouponRange;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 优惠券范围表 Mapper 接口
 * </p>
 *
 * @author charter
 * @since 2023-09-25
 */
@Mapper
public interface CouponRangeMapper extends BaseMapper<CouponRange> {

}
