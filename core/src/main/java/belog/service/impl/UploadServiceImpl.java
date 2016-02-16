package belog.service.impl;


import belog.service.UploadService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Beldon
 */
@Service("UploadService")
public class UploadServiceImpl implements UploadService {
    private static final String SAVE_PATH = "/upload/";
    private static final String SAVE_IMAGE_PATH = SAVE_PATH + "image/";

    public String saveImage(MultipartFile file) {
        String root = System.getProperty("belog.root");
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //获得文件类型（可以判断如果不是图片，禁止上传）
        String contentType = file.getContentType();
        String dateFormat = DateFormatUtils.format(new Date(), "yyyy/MM/dd/");
        //获得文件后缀名称
        String imageName = contentType.substring(contentType.indexOf("/") + 1);
        String fileName = uuid + "." + imageName;

        String savePath = root + SAVE_IMAGE_PATH + dateFormat;
        File saveFileDir = new File(savePath);
        if (!saveFileDir.exists()) {
            saveFileDir.mkdirs();
        }

        try {
            file.transferTo(new File(savePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SAVE_IMAGE_PATH + dateFormat + fileName;
    }

    public String saveThumbs(MultipartFile file) {
        String root = System.getProperty("belog.root");
        String imagePath = root + saveImage(file);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String dateFormat = DateFormatUtils.format(new Date(), "yyyy/MM/dd/");
        String contentType = file.getContentType();
        String imageName = contentType.substring(contentType.indexOf("/") + 1);
        String fileName = uuid + "." + imageName;

        String savePath = root + SAVE_IMAGE_PATH + "thumbs/" + dateFormat;
        File saveFileDir = new File(savePath);
        if (!saveFileDir.exists()) {
            saveFileDir.mkdirs();
        }
        try {
            Thumbnails.of(imagePath).forceSize(400, 280).toFile(savePath + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File image = new File(imagePath);
        if (image.exists()) {
            image.delete();
        }

        return SAVE_IMAGE_PATH + "thumbs/" + dateFormat + fileName;
    }
}
