package ink.charter.ssyx.client.user;

import ink.charter.ssyx.vo.user.LeaderAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Charter
 * @create 2023-10-04 9:56
 */
@FeignClient("service-user")
public interface UserFeignClient {

    //根据userId查询提货点和团长信息
    @GetMapping("/api/user/leader/inner/getUserAddressByUserId/{userId}")
    LeaderAddressVo getUserAddressByUserId(@PathVariable("userId") Long userId);
}
