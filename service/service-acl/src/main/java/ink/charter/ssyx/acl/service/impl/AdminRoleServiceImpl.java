package ink.charter.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.charter.ssyx.acl.mapper.AdminRoleMapper;
import ink.charter.ssyx.acl.service.AdminRoleService;
import ink.charter.ssyx.model.acl.AdminRole;
import org.springframework.stereotype.Service;

@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
}
