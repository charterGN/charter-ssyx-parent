package ink.charter.ssyx.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.charter.ssyx.model.activity.ActivityRule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 优惠规则 Mapper 接口
 * </p>
 *
 * @author charter
 * @since 2023-09-25
 */
@Mapper
public interface ActivityRuleMapper extends BaseMapper<ActivityRule> {

}
