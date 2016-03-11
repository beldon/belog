package belog.service.impl;

import belog.pojo.po.Taxonomy;
import belog.pojo.vo.TagVo;
import belog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beldon
 */
@Service("TagService")
public class TagServiceImpl extends TaxonomyServiceImpl implements TagService {

    public void addTag(TagVo tagVo) {
        tagVo.setTaxonomy(TAG);
        tagVo.setSlug(TAG);
        taxonomyMapper.insertSelective(tagVo);
    }

    public List<TagVo> getAllTag() {
        List<TagVo> tagVos = new ArrayList<TagVo>();
        List<Taxonomy> taxonomies = findAll(TAG);
        for (Taxonomy taxonomy : taxonomies) {
            TagVo tagVo = new TagVo();
            BeanUtils.copyProperties(taxonomy, tagVo);
            tagVos.add(tagVo);
        }
        return tagVos;
    }

    public TagVo getTagById(long id) {
        TagVo tagVo = new TagVo();
        Taxonomy taxonomy = taxonomyMapper.selectByPrimaryKey(id);
        if (taxonomy != null) {
            BeanUtils.copyProperties(taxonomy, tagVo);
        }
        return tagVo;
    }

    public TagVo getOrAddTagByName(String tagName) {
        TagVo tagVo = new TagVo();
        Taxonomy taxonomy = findOneByName(tagName);
        if (tagName != null) {
            BeanUtils.copyProperties(taxonomy, tagVo);
        } else {
            tagVo.setName(tagName);
            addTag(tagVo);
        }
        return tagVo;
    }

}
