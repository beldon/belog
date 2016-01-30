package belog.service;


import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.Categorys;

import java.util.List;

/**
 * Created by beldon.
 */
public interface CategoryService {
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

    /**
     * 增加分类 文章数统计
     *
     * @param catId 分类id
     * @param sum   增加数
     */
    void countPlus(long catId, long sum);

    /**
     * 减少分类 文章数统计 -1
     *
     * @param catId 分类id
     * @param sum   减少数量
     */
    void countMinus(long catId, long sum);
}
