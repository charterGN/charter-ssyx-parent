package ink.charter.ssyx.sys.api;

import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.model.sys.Region;
import ink.charter.ssyx.sys.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Charter
 * @create 2023-10-08 22:43
 */
@RestController
@RequestMapping("/api/sys/region")
public class RegionApiController {

    @Autowired
    private RegionService regionService;

    //   GET /api/sys/region/findAllList
    @GetMapping("/findAllList")
    public Result findAllList(){
        List<Region> regions = regionService.list();
        return Result.ok(regions);
    }

}
