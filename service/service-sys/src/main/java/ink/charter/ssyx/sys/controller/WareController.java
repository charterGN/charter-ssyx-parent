package ink.charter.ssyx.sys.controller;


import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.model.sys.Ware;
import ink.charter.ssyx.sys.service.WareService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author charter
 * @since 2023-09-18
 */
@RestController
@RequestMapping("/admin/sys/ware")
@CrossOrigin
public class WareController {

    @Autowired
    private WareService wareService;

    //查询所有仓库列表
//    url: `${api_name}/findAllList`,
//    method: 'get'
    @ApiOperation("查询所有仓库列表")
    @GetMapping("findAllList")
    public Result findAllList() {
        List<Ware> list = wareService.list();
        return Result.ok(list);
    }
}

