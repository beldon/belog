package belog.pojo.vo;


import belog.pojo.po.Posts;
import belog.pojo.po.Users;

import java.util.Date;
import java.util.List;

/**
 * 文章vo类
 * Created by Beldon
 */
public class ArticleVo extends Posts {

    /**
     * 封面
     */
    private String cover;

    private List<CategoryVo> cats;

    /**
     * 标签
     */
    private List<TagVo> tags;

    /**
     * 文章对应的用户
     */
    private UserVo user;

    public List<CategoryVo> getCats() {
        return cats;
    }

    public void setCats(List<CategoryVo> cats) {
        this.cats = cats;
    }

    public List<TagVo> getTags() {
        return tags;
    }

    public void setTags(List<TagVo> tags) {
        this.tags = tags;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
