package ink.charter.ssyx.sys.controller;


import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.model.sys.Region;
import ink.charter.ssyx.sys.service.RegionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author charter
 * @since 2023-09-18
 */
@RestController
@RequestMapping("/admin/sys/region")
@CrossOrigin
public class RegionController {

    @Autowired
    private RegionService regionService;

    //根据区域关键字查询区域列表信息
//    url: `${api_name}/findRegionByKeyword/${keyword}`,
//    method: 'get'
    @ApiOperation("根据区域关键字查询区域列表信息")
    @GetMapping("findRegionByKeyword/{keyword}")
    public Result findRegionByKeyword(@PathVariable("keyword") String keyword) {
        List<Region> list = regionService.getRegionByKeyword(keyword);
        return Result.ok(list);
    }
}

