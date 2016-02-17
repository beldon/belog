package me.beldon.belog.install.po;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 存储友情链接（Blogroll）
 *
 * @author Beldon
 */
@Entity(name = "t_links")
public class Links {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 链接URL
     */
    @Column(name = "link_url", length = 225)
    private String url;
    /**
     * 链接标题
     */
    @Column(name = "link_name", length = 225)
    private String name;
    /**
     * 链接图片
     */
    @Column(name = "link_image", length = 225)
    private String image;
    /**
     * 链接打开方式
     */
    @Column(name = "link_target", length = 25)
    private String target;
    /**
     * 链接描述
     */
    @Column(name = "link_description", length = 225)
    private String description;
    /**
     * 是否可见（Y/N）
     */
    @Column(name = "link_visible", length = 20)
    private String visible;

    /**
     * 排序
     */
    @Column(name = "link_sort")
    private Integer sort;

    @Column(name = "link_updated")
    private Date updated;
    /**
     * XFN关系
     */
    @Column(name = "link_rel", length = 225)
    private String rel;
    /**
     * XFN注释
     */
    @Column(name = "link_notes")
    private String notes;
    /**
     * 链接RSS地址
     */
    @Column(name = "link_rss", length = 225)
    private String rss;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_owner", nullable = false)
    private Users users;

    /**
     * 文章对应的分类
     */
    @OneToMany(mappedBy = "links", fetch = FetchType.LAZY)
    private Set<TermRelationships> termRelationships;

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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Set<TermRelationships> getTermRelationships() {
        return termRelationships;
    }

    public void setTermRelationships(Set<TermRelationships> termRelationships) {
        this.termRelationships = termRelationships;
    }
}
