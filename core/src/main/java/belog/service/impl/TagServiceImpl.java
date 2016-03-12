package belog.service.impl;

import belog.pojo.Page;
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
        taxonomyMapper.insertSelective(tagVo);
    }

    public List<TagVo> getAllTag() {
        List<TagVo> tagVos = new ArrayList<TagVo>();
        List<Taxonomy> taxonomies = findAll(TAG);
        copy(taxonomies, tagVos);
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
        if (taxonomy != null) {
            BeanUtils.copyProperties(taxonomy, tagVo);
        } else {
            tagVo.setName(tagName);
            addTag(tagVo);
        }
        return tagVo;
    }

    public List<TagVo> findByObjectId(Long objectId, String type) {
        List<TagVo> tagVos = new ArrayList<TagVo>();
        List<Taxonomy> list = super.findByObjectIdAndType(objectId, type);
        copy(list, tagVos);
        return tagVos;
    }

    public Page<TagVo> findPage(Page<TagVo> page) {
        Page<Taxonomy> taxonomyPage = new Page<Taxonomy>();
        taxonomyPage.setPageNo(page.getPageNo());
        taxonomyPage.setPageSize(page.getPageSize());
        List<Taxonomy> list = super.findByPage(TAG, taxonomyPage);
        List<TagVo> tagVos = new ArrayList<TagVo>();
        copy(list, tagVos);
        page.setTotalRecord(taxonomyPage.getTotalRecord());
        page.setTotalPage(taxonomyPage.getTotalPage());
        page.setResults(tagVos);
        return page;
    }

    public void copy(List<Taxonomy> source, List<TagVo> target) {
        for (Taxonomy taxonomy : source) {
            TagVo tagVo = new TagVo();
            BeanUtils.copyProperties(taxonomy, tagVo);
            target.add(tagVo);
        }
    }

}
