package ink.charter.ssyx.product.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.model.product.SkuInfo;
import ink.charter.ssyx.product.service.SkuInfoService;
import ink.charter.ssyx.vo.product.SkuInfoQueryVo;
import ink.charter.ssyx.vo.product.SkuInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * sku信息 前端控制器
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
@RestController
@RequestMapping("/admin/product/skuInfo")
//@CrossOrigin
public class SkuInfoController {

    @Autowired
    private SkuInfoService skuInfoService;

    //sku列表
//    url: `${api_name}/${page}/${limit}`,
//    method: 'get',
//    params: searchObj
    @ApiOperation("sku列表")
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       SkuInfoQueryVo skuInfoQueryVo) {
        Page<SkuInfo> pageParam = new Page<>(page,limit);
        IPage<SkuInfo> pageModel =
                skuInfoService.selectPageSkuInfo(pageParam,skuInfoQueryVo);
        return Result.ok(pageModel);
    }

    //添加商品sku信息
//    url: `${api_name}/save`,
//    method: 'post',
//    data: role
    @ApiOperation("添加商品sku信息")
    @PostMapping("save")
    public Result save(@RequestBody SkuInfoVo skuInfoVo) {
        skuInfoService.saveSkuInfo(skuInfoVo);
        return Result.ok();
    }

//    url: `${api_name}/get/${id}`,
//    method: 'get'
    @ApiOperation("获取sku信息")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SkuInfoVo skuInfoVo = skuInfoService.getSkuInfo(id);
        return Result.ok(skuInfoVo);
    }

//    url: `${api_name}/update`,
//    method: 'put',
//    data: role
    @ApiOperation("修改sku")
    @PutMapping("update")
    public Result update(@RequestBody SkuInfoVo skuInfoVo) {
        skuInfoService.updateSkuInfo(skuInfoVo);
        return Result.ok();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        SkuInfo skuInfo = skuInfoService.getById(id);
        if(skuInfo.getPublishStatus() == 1) {
            skuInfoService.publish(id, 0);//商品下架
        }

        skuInfoService.removeById(id);
        return Result.ok();
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        List<SkuInfo> skuInfoList = skuInfoService.listByIds(idList);
        for (SkuInfo skuInfo : skuInfoList) {
            if(skuInfo.getPublishStatus() == 1) {
                skuInfoService.publish(skuInfo.getId(), 0);//商品下架
            }
        }

        skuInfoService.removeByIds(idList);
        return Result.ok();
    }

//    url: `${api_name}/check/${id}/${status}`,
//    method: 'get'
    @ApiOperation("商品审核")
    @GetMapping("check/{skuId}/{status}")
    public Result check(@PathVariable Long skuId,
                        @PathVariable Integer status) {
        skuInfoService.check(skuId,status);
        return Result.ok();
    }

//    url: `${api_name}/publish/${id}/${status}`,
//    method: 'get'
    @ApiOperation("商品上下架")
    @GetMapping("publish/{skuId}/{status}")
    public Result publish(@PathVariable Long skuId,
                          @PathVariable Integer status) {
        skuInfoService.publish(skuId,status);
        return Result.ok();
    }

    //新人专享
    @GetMapping("isNewPerson/{skuId}/{status}")
    public Result isNewPerson(@PathVariable("skuId") Long skuId,
                              @PathVariable("status") Integer status) {
        skuInfoService.isNewPerson(skuId, status);
        return Result.ok();
    }

}

