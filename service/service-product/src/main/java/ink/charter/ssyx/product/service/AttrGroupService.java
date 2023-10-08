package ink.charter.ssyx.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.product.AttrGroup;
import ink.charter.ssyx.vo.product.AttrGroupQueryVo;

import java.util.List;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
public interface AttrGroupService extends IService<AttrGroup> {

    //平台属性分组列表
    IPage<AttrGroup> selectPageAttrGroup(Page<AttrGroup> pageParam, AttrGroupQueryVo attrGroupQueryVo);

    //查询所有平台属性分组列表
    List<AttrGroup> findAllListAttrGroup();
}
