package belog.pojo.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by Beldon
 */
public class LinksVo {
    private long id;

    /**
     * 链接URL
     */
    private String url;
    /**
     * 链接标题
     */
    private String name;
    /**
     * 链接图片
     */
    private String image;
    /**
     * 链接打开方式
     */
    private String target;
    /**
     * 链接描述
     */
    private String description;
    /**
     * 是否可见（Y/N）
     */
    private String visible;

    /**
     * 评分等级
     */
    private Integer sort;

    private Date updated;
    /**
     * XFN关系
     */
    private String rel;
    /**
     * XFN注释
     */
    private String notes;
    /**
     * 链接RSS地址
     */
    private String rss;

    /**
     * 分类
     */
    private List<CategoryVo> cats;

    /**
     * 链接对应创建的用户
     */
    private UserVo userVo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    public List<CategoryVo> getCats() {
        return cats;
    }

    public void setCats(List<CategoryVo> cats) {
        this.cats = cats;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }
}
