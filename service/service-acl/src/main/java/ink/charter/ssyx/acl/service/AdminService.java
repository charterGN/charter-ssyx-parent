package ink.charter.ssyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.acl.Admin;
import ink.charter.ssyx.vo.acl.AdminQueryVo;

/**
 * @author Charter
 * @create 2023-09-17 22:13
 */
public interface AdminService extends IService<Admin> {

    //1 用户列表
    IPage<Admin> selectPageUser(Page<Admin> pageParam, AdminQueryVo adminQueryVo);
}
