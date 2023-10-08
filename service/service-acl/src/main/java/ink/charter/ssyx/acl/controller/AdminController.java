package ink.charter.ssyx.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.charter.ssyx.acl.service.AdminService;
import ink.charter.ssyx.acl.service.RoleService;
import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.common.utils.MD5;
import ink.charter.ssyx.model.acl.Admin;
import ink.charter.ssyx.vo.acl.AdminQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Charter
 * @create 2023-09-17 22:11
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/admin/acl/user")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @ApiOperation("为用户进行角色分配")
    @PostMapping("doAssign")
    public Result doAssign(@RequestParam Long adminId,
                           @RequestParam Long[] roleId) {
        roleService.saveAdminRole(adminId,roleId);
        return Result.ok();
    }


    @ApiOperation("获取用户角色")
    @GetMapping("toAssign/{adminId}")
    public Result toAssign(@PathVariable Long adminId) {
        //返回map集合包含两部分数据：所有角色 和 为用户分配角色列表
        Map<String,Object> map  = roleService.getRoleByAdminId(adminId);
        return Result.ok(map);
    }

    //1 用户列表
    @ApiOperation("用户列表")
    @GetMapping("{current}/{limit}")
    public Result list(@PathVariable Long current,
                       @PathVariable Long limit,
                       AdminQueryVo adminQueryVo) {
        Page<Admin> pageParam = new Page<Admin>(current,limit);
        IPage<Admin> pageModel = adminService.selectPageUser(pageParam,adminQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation("根据id查询")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return Result.ok(admin);
    }

    @ApiOperation("添加用户")
    @PostMapping("save")
    public Result save(@RequestBody Admin admin) {
        //获取输入的密码
        String password = admin.getPassword();

        //对输入密码进行加密 MD5
        String passwordMD5 = MD5.encrypt(password);

        //设置到admin对象里面
        admin.setPassword(passwordMD5);

        //调用方法添加
        adminService.save(admin);
        return Result.ok();
    }

    @ApiOperation("修改用户")
    @PutMapping("update")
    public Result update(@RequestBody Admin admin) {
        adminService.updateById(admin);
        return Result.ok();
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        adminService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        adminService.removeByIds(idList);
        return Result.ok();
    }

}