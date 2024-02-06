package ink.charter.ssyx.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.charter.ssyx.enums.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Charter
 * @create 2023-09-30 09:55
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
