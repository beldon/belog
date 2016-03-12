package belog.service;

import belog.pojo.Page;
import belog.pojo.po.Taxonomy;

import java.util.List;

/**
 * Created by Beldon
 */
public interface TaxonomyService {

    /**
     * 分类标记符
     */
    String CATEGORY = "category";

    /**
     * 标签标识
     */
    String TAG = "post_tag";

    /**
     * 链接分类标识
     */
    String LINK_CATEGORY = "link_category";

    /**
     * 增加分类或标签 文章数统计
     *
     * @param id  id
     * @param sum 增加数
     */
    void countPlus(long id, long sum);

    /**
     * 减少分类或标签 文章数统计 -1
     *
     * @param id  id
     * @param sum 减少数量
     */
    void countMinus(long id, long sum);


    /**
     * 根据id删除分类或标签
     *
     * @param id
     */
    void deleteById(long id);

    /**
     * 按类型查找分类信息
     *
     * @param type
     * @return
     */
    List<Taxonomy> findAll(String type);

    /**
     * 安pid查找分类信息
     *
     * @param pid 父id
     * @return
     */
    List<Taxonomy> findByPid(long pid);

    /**
     * 安父id和类型查找分类信息
     *
     * @param pid  父id
     * @param type 类型
     * @return
     */
    List<Taxonomy> findByPid(long pid, String type);

    Taxonomy findOneByName(String name);

    List<Taxonomy> findByObjectIdAndType(Long objectId, String type);

    List<Taxonomy> findByPage(String type, Page<Taxonomy> page);
}
