package belog.pojo;

/**
 * Created by Beldon
 */
public class PluginContent {
    private String id;
    private String name;
    private String version;
    private String author;
    private String email;
    private String url;
    private String configPath;
    private PluginConfig config;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public PluginConfig getConfig() {
        return config;
    }

    public void setConfig(PluginConfig config) {
        this.config = config;
    }
}
