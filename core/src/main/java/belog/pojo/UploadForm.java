package belog.pojo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 文件上传辅助类
 * Created by Beldon
 */
public class UploadForm {
    /**
     * 文件名
     */
    private String name = null;
    private CommonsMultipartFile file = null;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }
}
