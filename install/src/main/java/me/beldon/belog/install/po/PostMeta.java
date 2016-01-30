package me.beldon.belog.install.po;

import javax.persistence.*;

/**
 * 存储文章（包括页面、上传文件、修订）的元数据
 *
 * @author Beldon
 */
@Entity(name = "t_postmeta")
public class PostMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_id")
    private Long id;

    @Column(name = "meta_key", length = 255)
    private String meteKey;

    @Column(name = "meta_value")
    private String metaValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Posts posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeteKey() {
        return meteKey;
    }

    public void setMeteKey(String meteKey) {
        this.meteKey = meteKey;
    }

    public String getMetaValue() {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }
}
