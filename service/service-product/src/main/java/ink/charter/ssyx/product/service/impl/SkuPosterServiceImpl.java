package ink.charter.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.charter.ssyx.model.product.SkuPoster;
import ink.charter.ssyx.product.mapper.SkuPosterMapper;
import ink.charter.ssyx.product.service.SkuPosterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务实现类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
@Service
public class SkuPosterServiceImpl extends ServiceImpl<SkuPosterMapper, SkuPoster> implements SkuPosterService {

    //根据id查询商品海报列表
    @Override
    public List<SkuPoster> getPosterListBySkuId(Long id) {
        LambdaQueryWrapper<SkuPoster> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SkuPoster::getSkuId,id);
        return baseMapper.selectList(wrapper);
    }
}
