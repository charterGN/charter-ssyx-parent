package ink.charter.ssyx.home.controller;

import ink.charter.ssyx.common.auth.AuthContextHolder;
import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.home.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Charter
 * @create 2023-09-25 16:23
 */
@Api(tags = "首页接口")
@RestController
@RequestMapping("api/home")
public class HomeApiController {

    @Autowired
    private HomeService homeService;

    @ApiOperation("首页数据显示接口")
    @GetMapping("index")
    public Result index(HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId();
        Map<String,Object> map = homeService.homeData(userId);
        return Result.ok(map);
    }

}
