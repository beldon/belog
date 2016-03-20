package belog.pojo.vo;

import belog.pojo.ThemeConfig;

/**
 * 模板信息
 * Created by Beldon
 */
public class ThemeVo {

    /**
     * 模板文件夹名称
     */
    private String directory;

    /**
     * 模板名称
     */
    private String name;
    /**
     * 模板作者
     */
    private String author;
    /**
     * 作者url
     */
    private String url;
    /**
     * 模板logo
     */
    private String logo;
    /**
     * 作者email
     */
    private String email;
    /**
     * 模板版本
     */
    private String version;

    /**
     * 描述
     */
    private String description;

    private ThemeConfig config;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ThemeConfig getConfig() {
        return config;
    }

    public void setConfig(ThemeConfig config) {
        this.config = config;
    }
}
