package ink.charter.ssyx.payment.controller;

import com.baomidou.mybatisplus.extension.api.R;
import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.common.result.ResultCodeEnum;
import ink.charter.ssyx.payment.service.PaymentInfoService;
import ink.charter.ssyx.payment.service.WeixinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Charter
 * @create 2023-09-28 08:50
 */
@Api(tags = "微信支付接口")
@RestController
@RequestMapping("/api/payment/weixin")
public class WeixinController {

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private PaymentInfoService paymentInfoService;

    //调用微信支付系统生成预付单
    @GetMapping("/createJsapi/{orderNo}")
    public Result createJsapi(@PathVariable("orderNo") String orderNo) {
        Map<String,String> map = weixinService.createJsapi(orderNo);
        return Result.ok(map);
    }

    //查询订单支付状态
    @GetMapping("/queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable("orderNo") String orderNo) {
        //1 调用微信支付系统接口查询订单支付状态
        Map<String,String> resultMap = weixinService.queryPayStatus(orderNo);

        //2 微信支付系统返回值为null，支付失败
        if(resultMap == null) {
            return Result.build(null, ResultCodeEnum.PAYMENT_FAIL);
        }

        //3 如果微信支付系统返回值，判断支付成功
        if("SUCCESS".equals(resultMap.get("trade_state"))) {
            String out_trade_no = resultMap.get("out_trade_no");
            paymentInfoService.paySuccess(out_trade_no,resultMap);
            return Result.ok();
        }

        //4 支付中，等待
        return Result.build(null,ResultCodeEnum.PAYMENT_WAITING);
    }

}
