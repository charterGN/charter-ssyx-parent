package ink.charter.ssyx.acl.controller;

import com.sun.xml.internal.bind.v2.TODO;
import ink.charter.ssyx.acl.service.PermissionService;
import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Charter
 * @create 2023-09-17 21:40
 */
@RestController
@RequestMapping("/admin/acl/permission")
@Api(tags = "菜单管理")
@CrossOrigin //跨域
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("查询所有菜单")
    @GetMapping
    public Result list() {
        List<Permission> list = permissionService.queryAllPermission();
        return Result.ok(list);
    }

    @ApiOperation("添加菜单")
    @PostMapping("save")
    public Result save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.ok(null);
    }

    @ApiOperation("修改菜单")
    @PutMapping("update")
    public Result update(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.ok(null);
    }

    @ApiOperation("递归删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        permissionService.removeChildById(id);
        return Result.ok(null);
    }

    //TODO 待实现
//    @ApiOperation("给角色分配权限")
//    @GetMapping("toAssign/{id}")
//    public Result toAssign(@PathVariable("id") Integer id){
//
//        return Result.ok();
//    }

}
