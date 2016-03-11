package belog.service;


import belog.pojo.Msg;
import belog.pojo.Page;
import belog.pojo.PageModel;
import belog.pojo.vo.ArticleVo;

/**
 * Created by Beldon
 */
public interface ArticleService {
    String COVER = "cover";


    /**
     * 添加或删除文章
     *
     * @param articleVo
     */
    void addOrUpdate(ArticleVo articleVo);

    /**
     * 删除文章
     *
     * @param id
     */
    void delete(long id);

    /**
     * 根据文章id查找文章
     *
     * @param id
     * @return
     */
    ArticleVo findById(long id);

    /**
     * 查找文章分页
     *
     * @param page
     * @return
     */
    Page<ArticleVo> findPage(Page<ArticleVo> page);

    /**
     * 根据分类ID查找分类
     *
     * @param catId     分类ID
     * @param page
     * @param type      类似-hot or new , hot:最热的文章，new，最新的文章， default new
     * @return
     */
    Page<ArticleVo> findPageByCatId(long catId, Page<ArticleVo> page, String type);

    /**
     * 删除文章封面
     * @param id 文章id
     * @return
     */
    Msg deleteCover(long id);
}
