package belog.pojo;

import java.net.URLClassLoader;

/**
 * 插件内容
 * Created by Beldon
 */
public class PluginMsg {
    /**
     * 插件id，必填，整站插件唯一，命名和约定与安卓app包名一致，如 me.beldon.plugin
     */
    private String id;

    /**
     * 插件配置文件位置
     */
    private String configPath;

    /**
     * 插件名称
     */
    private String name;

    /**
     * 插件版本
     */
    private String version;

    /**
     * 插件作者
     */
    private String author;

    /**
     * 插件作者邮件
     */
    private String email;

    /**
     * 插件地址
     */
    private String url;

    private URLClassLoader urlClassLoader;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public URLClassLoader getUrlClassLoader() {
        return urlClassLoader;
    }

    public void setUrlClassLoader(URLClassLoader urlClassLoader) {
        this.urlClassLoader = urlClassLoader;
    }
}
