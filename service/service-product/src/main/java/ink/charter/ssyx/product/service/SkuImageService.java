package ink.charter.ssyx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.product.SkuImage;

import java.util.List;

/**
 * <p>
 * 商品图片 服务类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
public interface SkuImageService extends IService<SkuImage> {

    //根据id查询商品图片列表
    List<SkuImage> getImageListBySkuId(Long id);
}
