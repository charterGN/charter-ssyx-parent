package ink.charter.ssyx.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.charter.ssyx.model.product.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商品三级分类 Mapper 接口
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
