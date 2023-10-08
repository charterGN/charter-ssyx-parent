package ink.charter.ssyx.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.charter.ssyx.model.acl.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Charter
 * @create 2023-09-17 22:00
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
