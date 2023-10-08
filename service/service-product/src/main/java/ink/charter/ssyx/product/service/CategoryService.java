package ink.charter.ssyx.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.product.Category;
import ink.charter.ssyx.vo.product.CategoryQueryVo;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
public interface CategoryService extends IService<Category> {

    //商品分类列表
    IPage<Category> selectPageCategory(Page<Category> pageParam, CategoryQueryVo categoryQueryVo);
}
