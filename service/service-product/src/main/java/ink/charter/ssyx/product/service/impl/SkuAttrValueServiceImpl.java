package ink.charter.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.charter.ssyx.model.product.SkuAttrValue;
import ink.charter.ssyx.product.mapper.SkuAttrValueMapper;
import ink.charter.ssyx.product.service.SkuAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * spu属性值 服务实现类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValue> implements SkuAttrValueService {

    //根据id查询商品属性信息列表
    @Override
    public List<SkuAttrValue> getAttrValueListBySkuId(Long id) {
        LambdaQueryWrapper<SkuAttrValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuAttrValue::getSkuId,id);
        return baseMapper.selectList(wrapper);
    }
}
