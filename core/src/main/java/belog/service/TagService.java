package belog.service;

import belog.pojo.PageModel;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.TagVo;

import java.util.List;

/**
 * 标签管理Service
 * Created by Beldon
 */
public interface TagService extends TaxonomyService {

    /**
     * 添加标签
     *
     * @param tagVo
     */
    void addTag(TagVo tagVo);

    /**
     * 获取所有标签
     *
     * @return
     */
    List<TagVo> getAllTag();

    /**
     * 根据标签id获取标签
     *
     * @param id
     * @return
     */
    TagVo getTagById(long id);

    /**
     * 根据标签名称获取标签，若没有则自动生成
     *
     * @param tagName
     * @return
     */
    TagVo getOrAddTagByName(String tagName);


}
