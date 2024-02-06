package ink.charter.ssyx.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.charter.ssyx.enums.user.Leader;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Charter
 * @create 2023-09-30 09:52
 */
@Mapper
public interface LeaderMapper extends BaseMapper<Leader> {
}
