package belog.service;

/**
 * Created by Beldon
 */
public interface TermTaxonomyService {
    /**
     * 增加分类或标签 文章数统计
     *
     * @param catId id
     * @param sum   增加数
     */
    void countPlus(long catId, long sum);

    /**
     * 减少分类或标签 文章数统计 -1
     *
     * @param catId id
     * @param sum   减少数量
     */
    void countMinus(long catId, long sum);
}
