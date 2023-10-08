package ink.charter.ssyx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.product.SkuPoster;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
public interface SkuPosterService extends IService<SkuPoster> {

    //根据id查询商品海报列表
    List<SkuPoster> getPosterListBySkuId(Long id);
}
