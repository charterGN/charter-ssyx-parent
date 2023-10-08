package ink.charter.ssyx.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ink.charter.ssyx.model.sys.Ware;
import ink.charter.ssyx.sys.mapper.WareMapper;
import ink.charter.ssyx.sys.service.WareService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author charter
 * @since 2023-09-18
 */
@Service
public class WareServiceImpl extends ServiceImpl<WareMapper, Ware> implements WareService {
}
