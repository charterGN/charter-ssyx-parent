package ink.charter.ssyx.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ink.charter.ssyx.model.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单项信息 Mapper 接口
 * </p>
 *
 * @author charter
 * @since 2023-09-27
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {

}
