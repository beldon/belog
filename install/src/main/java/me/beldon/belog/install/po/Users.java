package me.beldon.belog.install.po;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 用户表
 *
 * @author Beldon
 */
@Entity(name = "t_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 登录名
     */
    @Column(name = "login", length = 60, nullable = false)
    private String login;
    /***
     * 密码
     */
    @Column(name = "pass", length = 64, nullable = false)
    private String pass;
    /**
     * 昵称
     */
    @Column(name = "nickname", length = 50, nullable = false)
    private String nickName;
    /**
     * 邮箱
     */
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    /**
     * 网址
     */
    @Column(name = "url", length = 100, nullable = true)
    private String url;
    /**
     * 注册时间
     */
    private Date registered;
    /**
     * 激活码
     */
    @Column(name = "activation_key", length = 60, nullable = true)
    private String activationKey;
    /**
     * 显示状态,0为正常，1为禁用
     */
    private Integer status;
    /**
     * 显示名称
     */
    @Column(name = "display_name", length = 250, nullable = false)
    private String displayName;

    /**
     * 用户的元数据
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<UserMeta> userMetas;


    /**
     * 用户发表的存储文章（包括页面、上传文件、修订）
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Posts> postses;


    /**
     * 用户存储的友情链接（Blogroll）
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Links> linkses;

    /**
     * 用户的所有评论
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
    private Set<Comments> commentses;

    /**
     * 用户角色
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_users_roles",
            joinColumns = {@JoinColumn(name = "u_id")},
            inverseJoinColumns = {@JoinColumn(name = "r_id")}
    )
    private Set<Role> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<UserMeta> getUserMetas() {
        return userMetas;
    }

    public void setUserMetas(Set<UserMeta> userMetas) {
        this.userMetas = userMetas;
    }

    public Set<Posts> getPostses() {
        return postses;
    }

    public void setPostses(Set<Posts> postses) {
        this.postses = postses;
    }

    public Set<Links> getLinkses() {
        return linkses;
    }

    public void setLinkses(Set<Links> linkses) {
        this.linkses = linkses;
    }

    public Set<Comments> getCommentses() {
        return commentses;
    }

    public void setCommentses(Set<Comments> commentses) {
        this.commentses = commentses;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
