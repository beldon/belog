package belog.pojo.vo;

/**
 * 分类VO
 * Created by beldon.
 */
public class CategoryVo {
    private long id;

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
    private int termGroup;

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
    private long parent;
    /**
     * 文章数统计
     */
    private long count;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getTermGroup() {
        return termGroup;
    }

    public void setTermGroup(int termGroup) {
        this.termGroup = termGroup;
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

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
