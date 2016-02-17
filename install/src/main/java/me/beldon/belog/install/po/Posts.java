package me.beldon.belog.install.po;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 存储文章（包括页面、上传文件、修订）
 *
 * @author Beldon
 */
@Entity(name = "t_posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 发布时间
     */
    @Column(name = "post_date", nullable = false)
    private Date date;

    /**
     * 正文
     * <p>
     * Text是有65535字节限制,MedumnText最大16777215字节,LongText最大2147483647字节
     * </p>
     */
    @Column(name = "post_content", nullable = false, length = 2147483647)
    private String content;
    /**
     * 标题
     */
    @Column(name = "post_title", nullable = false)
    private String title;
    /**
     * 摘录
     */
    @Column(name = "post_excerpt", nullable = true)
    private String excerpt;
    /**
     * 文章状态（publish/auto-draft/inherit等）
     */
    @Column(name = "post_status", length = 20, nullable = false)
    private String status;
    /**
     * 评论状态（open/closed）
     */
    @Column(name = "comment_status", length = 20, nullable = false)
    private String commentStatus;
    /**
     * PING状态（open/closed）
     */
    @Column(name = "ping_status", length = 20, nullable = false)
    private String pingStatues;
    /**
     * 文章密码
     */
    @Column(name = "post_password", length = 20, nullable = true)
    private String password;
    /**
     * 文章缩略名
     */
    @Column(name = "post_name", length = 200, nullable = true)
    private String name;

    @Column(name = "to_ping", nullable = true)
    private String toPing;
    /**
     * 已经PING过的链接
     */
    @Column(name = "pinged", nullable = true)
    private String pinged;
    /**
     * 修改时间
     */
    @Column(name = "post_modified", nullable = true)
    private Date modified;

    @Column(name = "post_content_filtered", nullable = true)
    private String contentFiltered;
    /**
     * 父文章，主要用于PAGE
     */
    @Column(name = "post_parent", length = 20, nullable = true)
    private Long parent;

    @Column(name = "guid", length = 255, nullable = true)
    private String guid;
    /**
     * 排序ID
     */
    @Column(name = "menu_order", length = 60, nullable = true)
    private Integer menuOrder;
    /**
     * 文章类型（article/post/page等）
     */
    @Column(name = "post_type", length = 20, nullable = false)
    private String type;
    /**
     * MIME类型
     */
    @Column(name = "post_mime_type", length = 100, nullable = true)
    private String mimeType;
    /**
     * 评论总数
     */
    @Column(name = "comment_count", length = 20, nullable = true)
    private long commentCount;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    private Set<PostMeta> postMetas;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "posts")
    private Set<Comments> commentses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_author", nullable = false)
    private Users users;

    /**
     * 文章对应的分类
     */
    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY)
    private Set<TermRelationships> termRelationships;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getPingStatues() {
        return pingStatues;
    }

    public void setPingStatues(String pingStatues) {
        this.pingStatues = pingStatues;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToPing() {
        return toPing;
    }

    public void setToPing(String toPing) {
        this.toPing = toPing;
    }

    public String getPinged() {
        return pinged;
    }

    public void setPinged(String pinged) {
        this.pinged = pinged;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getContentFiltered() {
        return contentFiltered;
    }

    public void setContentFiltered(String contentFiltered) {
        this.contentFiltered = contentFiltered;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Integer getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public Set<PostMeta> getPostMetas() {
        return postMetas;
    }

    public void setPostMetas(Set<PostMeta> postMetas) {
        this.postMetas = postMetas;
    }

    public Set<Comments> getCommentses() {
        return commentses;
    }

    public void setCommentses(Set<Comments> commentses) {
        this.commentses = commentses;
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
