package ink.charter.ssyx.activity.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import ink.charter.ssyx.activity.service.CouponInfoService;
import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.model.activity.CouponInfo;
import ink.charter.ssyx.vo.activity.CouponRuleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author charter
 * @since 2023-09-25
 */
@RestController
@RequestMapping("/admin/activity/couponInfo")
@CrossOrigin
public class CouponInfoController {

    @Autowired
    private CouponInfoService couponInfoService;

    //1 优惠卷分页查询
//    url: `${api_name}/${page}/${limit}`,
//    method: 'get'
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit) {
        IPage<CouponInfo> pageModel =
                couponInfoService.selectPageCouponInfo(page,limit);
        return Result.ok(pageModel);
    }

    //2 添加优惠卷
//    url: `${api_name}/save`,
//    method: 'post',
//    data: role
    @PostMapping("save")
    public Result save(@RequestBody CouponInfo couponInfo) {
        couponInfoService.save(couponInfo);
        return Result.ok();
    }

    //3 根据id查询优惠卷
//    url: `${api_name}/get/${id}`,
//    method: 'get'
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        CouponInfo couponInfo = couponInfoService.getCouponInfo(id);
        return Result.ok(couponInfo);
    }

    //4 根据优惠卷id查询规则数据
//    url: `${api_name}/findCouponRuleList/${id}`,
//    method: 'get'
    @GetMapping("findCouponRuleList/{id}")
    public Result findCouponRuleList(@PathVariable Long id) {
        Map<String,Object> map =
                couponInfoService.findCouponRuleList(id);
        return Result.ok(map);
    }

    //5 添加优惠卷规则数据
//    url: `${api_name}/saveCouponRule`,
//    method: 'post',
//    data: rule
    @PostMapping("saveCouponRule")
    public Result saveCouponRule(@RequestBody CouponRuleVo couponRuleVo) {
        couponInfoService.saveCouponRule(couponRuleVo);
        return Result.ok();
    }
}

