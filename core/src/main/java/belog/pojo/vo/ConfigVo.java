package belog.pojo.vo;


/**
 * 网站配置
 *
 * @author Beldon
 */
public class ConfigVo {
    private long id;

    /**
     * 键名
     */
    private String name;

    /**
     * 键值
     */
    private String value;

    private String type;

    /**
     * 在博客载入时自动载入（yes/no）
     */
    private String autoLoad;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAutoLoad() {
        return autoLoad;
    }

    public void setAutoLoad(String autoLoad) {
        this.autoLoad = autoLoad;
    }
}
