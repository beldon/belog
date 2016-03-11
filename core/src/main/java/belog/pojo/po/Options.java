package belog.pojo.po;

public class Options {
    private Long id;

    private String autoLoad;

    private String name;

    private String type;

    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutoLoad() {
        return autoLoad;
    }

    public void setAutoLoad(String autoLoad) {
        this.autoLoad = autoLoad == null ? null : autoLoad.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}