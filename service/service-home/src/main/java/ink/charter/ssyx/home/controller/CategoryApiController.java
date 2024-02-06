package ink.charter.ssyx.home.controller;

import ink.charter.ssyx.client.product.ProductFeignClient;
import ink.charter.ssyx.common.result.Result;
import ink.charter.ssyx.model.product.Category;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Charter
 * @create 2023-09-25 16:11
 */
@Api(tags = "商品分类")
@RestController
@RequestMapping("api/home")
public class CategoryApiController {

    @Autowired
    private ProductFeignClient productFeignClient;

    //查询所有分类
    @GetMapping("category")
    public Result categoryList() {
        List<Category> categoryList = productFeignClient.findAllCategoryList();
        return Result.ok(categoryList);
    }

}
