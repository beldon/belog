package belog.service;


import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.Categorys;

import java.util.List;

/**
 * Created by beldon.
 */
public interface CategoryService extends TaxonomyService {

    /**
     * 保存或修改
     *
     * @param category 分类
     */
    void saveOrUpdate(CategoryVo category);


    /**
     * 获取所有分类信息，默认类型是category
     *
     * @return
     */
    List<CategoryVo> findAll();


    /**
     * 按等级获取信息，默认类型是category
     *
     * @return
     */
    List<Categorys> findCat();


    /**
     * 根据PID 查找分类，默认类型是category
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
