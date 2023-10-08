package ink.charter.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.charter.ssyx.acl.mapper.RoleMapper;
import ink.charter.ssyx.acl.service.AdminRoleService;
import ink.charter.ssyx.acl.service.RoleService;
import ink.charter.ssyx.model.acl.AdminRole;
import ink.charter.ssyx.model.acl.Role;
import ink.charter.ssyx.vo.acl.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Charter
 * @create 2023-09-17 21:55
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper,Role> implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;

    //角色分页列表
    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        //获取条件值：角色名称
        String roleName = roleQueryVo.getRoleName();
        //创建条件构造器对象
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        //判断条件值是否为空
        if(!StringUtils.isEmpty(roleName)) {
            //封装条件
            wrapper.like(Role::getRoleName,roleName);
        }
        //调用mapper方法实现条件分页查询
        IPage<Role> pageModel = baseMapper.selectPage(pageParam, wrapper);
        return pageModel;
    }

    //获取所有角色，和根据用户id查询用户分配角色列表
    @Override
    public Map<String, Object> getRoleByAdminId(Long adminId) {
        //1 查询所有角色
        List<Role> allRolesList = baseMapper.selectList(null);

        //2 根据用户id查询用户分配角色列表
        //2.1 根据用户id查询 用户角色关系表 admin_role 查询用户分配角色id列表
        // List<AdminRole>
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        //设置查询条件，根据用户id adminId
        wrapper.eq(AdminRole::getAdminId,adminId);
        List<AdminRole> adminRoleList = adminRoleService.list(wrapper);

        //2.2 通过第一步返回集合，获取所有角色id的列表List<AdminRole> -- List<Long>
        List<Long> roleIdsList =
                adminRoleList.stream()
                        .map(item -> item.getRoleId())
                        .collect(Collectors.toList());

        //2.3 创建新的list集合，用于存储用户配置角色
        List<Role> assignRoleList = new ArrayList<>();

        //2.4 遍历所有角色列表 allRolesList，得到每个角色
        //判断所有角色里面是否包含已经分配角色id，封装到2.3里面新的list集合
        for (Role role:allRolesList) {
            //判断
            if(roleIdsList.contains(role.getId())) {
                assignRoleList.add(role);
            }
        }

        //封装到map，返回
        Map<String, Object> result = new HashMap<>();
        //所有角色列表
        result.put("allRolesList",allRolesList);
        //用户分配角色列表
        result.put("assignRoles",assignRoleList);
        return result;
    }

    //为用户进行分配
    @Override
    public void saveAdminRole(Long adminId, Long[] roleIds) {
        //1 删除用户已经分配过的角色数据
        //根据用户id删除admin_role表里面对应数据
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId,adminId);
        adminRoleService.remove(wrapper);

        //2 重新分配
        //adminId:1   roleId: 2 3
        //遍历多个角色id，得到每个角色id，拿着每个角色id + 用户id添加用户角色关系表
//        for (Long roleId:roleIds) {
//            AdminRole adminRole = new AdminRole();
//            adminRole.setAdminId(adminId);
//            adminRole.setRoleId(roleId);
//            adminRoleService.save(adminRole);
//        }
        List<AdminRole> list = new ArrayList<>();
        for (Long roleId: roleIds) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId);
            //放到list集合
            list.add(adminRole);
        }
        //调用方法添加
        adminRoleService.saveBatch(list);
    }

}
