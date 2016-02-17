package me.beldon.belog.install.po;


import javax.persistence.*;
import java.util.Set;

/**
 * 存储每个目录、标签所对应的分类
 *
 * @author Beldon
 */
@Entity(name = "t_term_taxonomy")
public class TermTaxonomy {
    /**
     * 分类方法ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类方法(category/post_tag/link_category)
     */
    private String taxonomy;
    /**
     * 描述
     */
    private String description;

    /**
     * 所属父分类方法ID
     */
    private Long parent = new Long(0l);
    /**
     * 文章数统计
     */
    private Long count;

    /**
     * 分类对应的文章,关系表
     */
    @OneToMany(mappedBy = "termTaxonomy", fetch = FetchType.LAZY)
    private Set<TermRelationships> termRelationships;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    private Terms terms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Set<TermRelationships> getTermRelationships() {
        return termRelationships;
    }

    public void setTermRelationships(Set<TermRelationships> termRelationships) {
        this.termRelationships = termRelationships;
    }

    public Terms getTerms() {
        return terms;
    }

    public void setTerms(Terms terms) {
        this.terms = terms;
    }
}
