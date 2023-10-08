package ink.charter.ssyx.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.product.Attr;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
public interface AttrService extends IService<Attr> {

    //根据平台属性分组id查询
    List<Attr> getAttrListByGroupId(Long groupId);
}
