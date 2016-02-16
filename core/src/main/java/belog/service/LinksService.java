package belog.service;


import belog.pojo.PageModel;
import belog.pojo.vo.LinksVo;

import java.util.List;

/**
 * Created by Beldon
 */
public interface LinksService {
    /**
     * 添加或修改
     * @param links 链接
     */
    void saveOrUpdate(LinksVo links);

    /**
     * 删除
     * @param id
     */
    void delete(long id);

    LinksVo findById(long id);

    /**
     * 查找文章分页
     *
     * @param pageModel
     * @return
     */
    PageModel findPage(PageModel pageModel);

    /**
     * 根据分类ID查找分类
     *
     * @param catId     分类ID
     * @param pageModel
     * @return
     */
    PageModel findPageByCatId(long catId, PageModel pageModel);

    /**
     * 查找所有链接
     * @return
     */
    List<LinksVo> findAll();
}
