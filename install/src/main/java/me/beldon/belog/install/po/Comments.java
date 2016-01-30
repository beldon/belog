package me.beldon.belog.install.po;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 存储评论
 *
 * @author Beldon
 */
@Entity(name = "t_comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_ID", length = 20, nullable = false)
    private Long id;

    /**
     * 评论者
     */
    @Column(name = "comment_author", nullable = false)
    private String author;

    /**
     * 评论者邮箱
     */
    @Column(name = "comment_author_email", length = 100, nullable = false)
    private String authorEmail;

    /**
     * 评论者网址
     */
    @Column(name = "comment_author_url", length = 200, nullable = false)
    private String authorUrl;

    /**
     * 评论者IP
     */
    @Column(name = "comment_author_IP", length = 100, nullable = false)
    private String authorIp;

    /**
     * 评论时间
     */
    @Column(name = "comment_date", nullable = false)
    private Date date;

    /**
     * 评论时间（GMT+0时间）
     */
    @Column(name = "comment_date_gmt", nullable = false)
    private Date dateGmt;

    /**
     * 评论正文
     */
    @Column(name = "comment_content", nullable = false)
    private String content;

    @Column(name = "comment_karma", nullable = false)
    private Integer karme;

    /**
     * 评论是否被批准
     */
    @Column(name = "comment_approved", length = 20, nullable = false)
    private String approved;

    /**
     * 评论者的USER AGENT
     */
    @Column(name = "comment_agent", length = 255, nullable = false)
    private String agent;

    /**
     * 评论类型(pingback/普通)
     */
    @Column(name = "comment_type", length = 20, nullable = false)
    private String type;

    /**
     * 父评论ID
     */
    @Column(name = "comment_parent", nullable = false)
    private Long parent;

    /**
     * 评论者用户ID（不一定存在）
     */

    @ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = true)
    private Users users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comments")
    private Set<CommentMeta> commentMetas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_post_ID", nullable = false)
    private Posts posts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public String getAuthorIp() {
        return authorIp;
    }

    public void setAuthorIp(String authorIp) {
        this.authorIp = authorIp;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateGmt() {
        return dateGmt;
    }

    public void setDateGmt(Date dateGmt) {
        this.dateGmt = dateGmt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getKarme() {
        return karme;
    }

    public void setKarme(Integer karme) {
        this.karme = karme;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Set<CommentMeta> getCommentMetas() {
        return commentMetas;
    }

    public void setCommentMetas(Set<CommentMeta> commentMetas) {
        this.commentMetas = commentMetas;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }
}
