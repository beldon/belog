package belog.pojo.vo;

import belog.pojo.po.Links;

import java.util.Date;
import java.util.List;

/**
 * Created by Beldon
 */
public class LinksVo extends Links {

    /**
     * 分类
     */
    private List<CategoryVo> cats;

    public List<CategoryVo> getCats() {
        return cats;
    }

    public void setCats(List<CategoryVo> cats) {
        this.cats = cats;
    }
}
