package ink.charter.ssyx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.product.SkuAttrValue;

import java.util.List;

/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
public interface SkuAttrValueService extends IService<SkuAttrValue> {

    //根据id查询商品属性信息列表
    List<SkuAttrValue> getAttrValueListBySkuId(Long id);
}
