package belog.pojo.vo;


import java.util.List;

/**
 * 保存分类等级信息
 * Created by beldon.
 */
public class Categorys {

    /**
     * 自身分类信息
     */
    private CategoryVo category;

    /**
     * 子类信息
     */
    private List<Categorys> subs;

    public CategoryVo getCategory() {
        return category;
    }

    public void setCategory(CategoryVo category) {
        this.category = category;
    }

    public List<Categorys> getSubs() {
        return subs;
    }

    public void setSubs(List<Categorys> subs) {
        this.subs = subs;
    }
}
