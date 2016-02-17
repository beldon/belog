package me.beldon.belog.install.po;


import javax.persistence.*;

/**
 * 存储每个目录、标签
 *
 * @author Beldon
 */
@Entity(name = "t_terms")
public class Terms {
    /**
     * 分类ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 分类名
     */
    private String name;
    /**
     * 缩略名
     */
    private String slug;
    /**
     * 分组
     */
    private Integer termGroup;

    @OneToOne(mappedBy = "terms",fetch = FetchType.LAZY)
    private TermTaxonomy termTaxonomy;

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getTermGroup() {
        return termGroup;
    }

    public void setTermGroup(Integer termGroup) {
        this.termGroup = termGroup;
    }

    public TermTaxonomy getTermTaxonomy() {
        return termTaxonomy;
    }

    public void setTermTaxonomy(TermTaxonomy termTaxonomy) {
        this.termTaxonomy = termTaxonomy;
    }
}
