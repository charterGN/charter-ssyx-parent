package ink.charter.ssyx.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.acl.Permission;

import java.util.List;

/**
 * @author Charter
 * @create 2023-09-17 22:10
 */
public interface PermissionService extends IService<Permission> {

    //查询所有菜单
    List<Permission> queryAllPermission();

    //递归删除菜单
    void removeChildById(Long id);
}
