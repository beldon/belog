package belog.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Beldon
 */
public interface UploadService {

    /**
     * 保存图片，并返回地址
     * @param file
     * @return
     */
    String saveImage(MultipartFile file);


    /**
     * 保存缩列图
     * @param file
     * @return
     */
    String saveThumbs(MultipartFile file);
}
