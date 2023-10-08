package ink.charter.ssyx.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.enums.user.User;
import ink.charter.ssyx.vo.user.LeaderAddressVo;
import ink.charter.ssyx.vo.user.UserLoginVo;

/**
 * @author Charter
 * @create 2023-09-30 09:59
 */
public interface UserService extends IService<User> {
    //// 判断是否是第一次使用微信授权登录：如何判断？openId
    User getUserByOpenId(String openid);

    //5 根据userId查询提货点和团长信息
    LeaderAddressVo getLeaderAddressByUserId(Long userId);

    //7 获取当前登录用户信息，
    UserLoginVo getUserLoginVo(Long id);
}
