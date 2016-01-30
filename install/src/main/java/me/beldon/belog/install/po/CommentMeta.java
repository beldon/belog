package me.beldon.belog.install.po;

import javax.persistence.*;

/**
 * 存储评论的元数据
 *
 * @author Beldon
 */
@Entity(name = "t_commentmeta")
public class CommentMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meta_id", length = 20, nullable = false)
    private Long id;

    /**
     * 键名
     */
    @Column(name = "meta_key", length = 225)
    private String metaKey;

    /**
     * 键值
     */
    @Column(name = "meta_value")
    private String metaValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comments comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetaKey() {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getMetaValue() {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }

    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }
}
