package belog.service;

/**
 * Created by Beldon
 */
public interface TermTaxonomyService {

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
     * @param id id
     * @param sum   增加数
     */
    void countPlus(long id, long sum);

    /**
     * 减少分类或标签 文章数统计 -1
     *
     * @param id id
     * @param sum   减少数量
     */
    void countMinus(long id, long sum);


    /**
     * 根据id删除分类或标签
     * @param id
     */
    void deleteById(long id);


}
