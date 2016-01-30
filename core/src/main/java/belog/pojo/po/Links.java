package belog.pojo.po;

import javax.persistence.*;
import java.util.Date;

/**
 * 存储友情链接（Blogroll）
 *
 * @author Beldon
 */
@Entity(name = "t_links")
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id", length = 20, nullable = false)
    private Long id;

    /**
     * 链接URL
     */
    @Column(name = "link_url", length = 225, nullable = false)
    private String url;
    /**
     * 链接标题
     */
    @Column(name = "link_name", length = 225, nullable = false)
    private String name;
    /**
     * 链接图片
     */
    @Column(name = "link_image", length = 225, nullable = false)
    private String image;
    /**
     * 链接打开方式
     */
    @Column(name = "link_target", length = 25, nullable = false)
    private String targer;
    /**
     * 链接描述
     */
    @Column(name = "link_description", length = 225, nullable = false)
    private String description;
    /**
     * 是否可见（Y/N）
     */
    @Column(name = "link_visible", length = 20, nullable = false)
    private String visible;

    /**
     * 评分等级
     */
    @Column(name = "link_rating", nullable = false)
    private Integer rating;

    @Column(name = "link_updated", nullable = false)
    private Date updated;
    /**
     * XFN关系
     */
    @Column(name = "link_rel", length = 225, nullable = false)
    private String rel;
    /**
     * XFN注释
     */
    @Column(name = "link_notes", nullable = false)
    private String notes;
    /**
     * 链接RSS地址
     */
    @Column(name = "link_rss", length = 225, nullable = false)
    private String rss;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_owner", nullable = false)
    private Users users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getTarger() {
        return targer;
    }

    public void setTarger(String targer) {
        this.targer = targer;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
