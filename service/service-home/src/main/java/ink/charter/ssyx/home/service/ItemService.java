package ink.charter.ssyx.home.service;

import java.util.Map;

/**
 * @author Charter
 * @create 2023-09-25 16:12
 */
public interface ItemService {

    //详情
    Map<String, Object> item(Long id, Long userId);
}
