package ink.charter.ssyx.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.charter.ssyx.model.product.Attr;
import ink.charter.ssyx.product.mapper.AttrMapper;
import ink.charter.ssyx.product.service.AttrService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.WeakHashMap;

/**
 * <p>
 * 商品属性 服务实现类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
@Service
public class AttrServiceImpl extends ServiceImpl<AttrMapper, Attr> implements AttrService {

    //根据平台属性分组id查询
    @Override
    public List<Attr> getAttrListByGroupId(Long groupId) {
        LambdaQueryWrapper<Attr> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Attr::getAttrGroupId,groupId);
        List<Attr> list = baseMapper.selectList(wrapper);
        return list;
    }
}
