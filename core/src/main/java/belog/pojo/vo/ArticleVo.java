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
    private List<TagVo> tagVos;

    /**
     * 文章对应的用户
     */
    private Users user;

    public List<CategoryVo> getCats() {
        return cats;
    }

    public void setCats(List<CategoryVo> cats) {
        this.cats = cats;
    }

    public List<TagVo> getTagVos() {
        return tagVos;
    }

    public void setTagVos(List<TagVo> tagVos) {
        this.tagVos = tagVos;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
