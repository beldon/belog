package me.beldon.belog.install.po;

import javax.persistence.*;

/**
 * 存储每个文章、链接和对应分类的关系
 *
 * @author Beldon
 */
@Entity(name = "t_term_relationships")
public class TermRelationships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应文章ID/链接ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "links_id")
    private Links links;


    /**
     * 对应分类方法ID
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_taxonomy_id", nullable = false)
    private TermTaxonomy termTaxonomy;
    /**
     * 排序
     */
    @Column(name = "term_order")
    private Integer termOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public TermTaxonomy getTermTaxonomy() {
        return termTaxonomy;
    }

    public void setTermTaxonomy(TermTaxonomy termTaxonomy) {
        this.termTaxonomy = termTaxonomy;
    }

    public Integer getTermOrder() {
        return termOrder;
    }

    public void setTermOrder(Integer termOrder) {
        this.termOrder = termOrder;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
