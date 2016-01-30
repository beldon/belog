package belog.pojo.po;

import javax.persistence.*;

/**
 * 用来记录博客的一些设置和选项
 *
 * @author Beldon
 */
@Entity(name = "t_options")
public class Options {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id", length = 20)
    private Long id;

    /**
     * 键名
     */
    @Column(name = "option_name", length = 64, unique = true, nullable = false)
    private String name;

    /**
     * 键值
     */
    @Column(name = "option_value", length = 65535, nullable = false)
    private String value;

    /**
     * 在博客载入时自动载入（yes/no）
     */
    @Column(name = "autoload", length = 20, nullable = false)
    private String autoLoad;

    /**
     * 类型。如：config全局配置
     */
    @Column(name = "type", length = 200, nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAutoLoad() {
        return autoLoad;
    }

    public void setAutoLoad(String autoLoad) {
        this.autoLoad = autoLoad;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
