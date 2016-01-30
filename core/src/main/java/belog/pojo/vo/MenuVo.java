package belog.pojo.vo;


/**
 * 首页菜单
 * Created by Beldon
 */
public class MenuVo {
    private Long id;

    /**
     * 菜单名字
     */
    private String name;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 菜单排序
     */
    private Integer sort;
    /**
     * 父菜单
     */
    private Integer pid;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单类型(front-nav/auth-nav)
     */
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
