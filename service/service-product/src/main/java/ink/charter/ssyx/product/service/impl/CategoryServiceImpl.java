package ink.charter.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.charter.ssyx.model.product.Category;
import ink.charter.ssyx.product.mapper.CategoryMapper;
import ink.charter.ssyx.product.service.CategoryService;
import ink.charter.ssyx.vo.product.CategoryQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 商品三级分类 服务实现类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    //商品分类列表
    @Override
    public IPage<Category> selectPageCategory(Page<Category> pageParam,
                                              CategoryQueryVo categoryQueryVo) {
        String name = categoryQueryVo.getName();
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(name)) {
            wrapper.like(Category::getName,name);
        }
        IPage<Category> categoryPage = baseMapper.selectPage(pageParam, wrapper);
        return categoryPage;
    }
}
