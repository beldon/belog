package belog.service;


import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.Categorys;

import java.util.List;

/**
 * Created by beldon.
 */
public interface CategoryService {
    /**
     * 分类标记符
     */
    String CATEGORY = "category";

    /**
     * 保存或修改
     *
     * @param category
     */
    void saveOrUpdate(CategoryVo category);

    /**
     * 删除
     *
     * @param id
     */
    void delete(long id);

    /**
     * 获取所有分类信息
     *
     * @return
     */
    List<CategoryVo> findAll();

    /**
     * 安等级获取信息
     *
     * @return
     */
    List<Categorys> findCat();

    /**
     * 根据PID 查找分类
     *
     * @param pid
     * @return
     */
    List<CategoryVo> findCatByPid(long pid);

    /**
     * 根据ID去查询
     *
     * @param id
     * @return
     */
    CategoryVo findById(long id);

}
