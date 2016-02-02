package belog.pojo.vo;


import java.util.Date;
import java.util.List;

/**
 * 文章vo类
 * Created by Beldon
 */
public class ArticleVo {

    private long id;
    /**
     * 发布时间
     */
    private Date date;
    /**
     * 正文
     */
    private String content;
    /**
     * 标题
     */
    private String title;
    /**
     * 摘录
     */
    private String excerpt;
    /**
     * 文章状态（publish/auto-draft/inherit等）
     */
    private String status;
    /**
     * 评论状态（open/closed）
     */
    private String commentStatus;
    /**
     * PING状态（open/closed）
     */
    private String pingStatues;
    /**
     * 文章密码
     */
    private String password;
    /**
     * 文章缩略名
     */
    private String name;

    private String toPing;
    /**
     * 已经PING过的链接
     */
    private String pinged;
    /**
     * 修改时间
     */
    private Date modified;

    private String contentFiltered;
    /**
     * 父文章，主要用于PAGE
     */
    private long parent;

    private String guid;
    /**
     * 排序ID
     */
    private int menuOrder;
    /**
     * 文章类型（post/page等）
     */
    private String type;
    /**
     * MIME类型
     */
    private String mimeType;
    /**
     * 评论总数
     */
    private long commentCount;

    /**
     * 封面URL
     */
    private String cover;

    /**
     * 分类
     */
    private List<CategoryVo> cats;

    /**
     * 标签
     */
    private List<TagVo> tagVos;

    /**
     * 文章对应的用户
     */
    private UserVo userVo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getParent() {
        return parent;
    }

    public void setParent(long parent) {
        this.parent = parent;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public int getMenuOrder() {
        return menuOrder;
    }

    public void setMenuOrder(int menuOrder) {
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

    public List<CategoryVo> getCats() {
        return cats;
    }

    public void setCats(List<CategoryVo> cats) {
        this.cats = cats;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<TagVo> getTagVos() {
        return tagVos;
    }

    public void setTagVos(List<TagVo> tagVos) {
        this.tagVos = tagVos;
    }
}
