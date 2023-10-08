package ink.charter.ssyx.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ink.charter.ssyx.model.sys.Region;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author charter
 * @since 2023-09-18
 */
public interface RegionService extends IService<Region> {

    //根据区域关键字查询区域列表信息
    List<Region> getRegionByKeyword(String keyword);
}
