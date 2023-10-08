package ink.charter.ssyx.product.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件上传 服务类
 * </p>
 *
 * @author charter
 * @since 2023-09-19
 */
public interface FileUploadService {

    //图片上传的方法
    String uploadFile(MultipartFile file);
}
