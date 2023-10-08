package ink.charter.ssyx.home.service;

import java.util.Map;

/**
 * @author Charter
 * @create 2023-09-25 16:11
 */
public interface HomeService {

    //首页数据显示接口
    Map<String, Object> homeData(Long userId);
}
