package belog.service.impl;

import belog.dao.TermTaxonomyDao;
import belog.dao.TermsDao;
import belog.pojo.PageModel;
import belog.pojo.po.TermTaxonomy;
import belog.pojo.po.Terms;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.TagVo;
import belog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beldon
 */
@Service("TagService")
public class TagServiceImpl extends CategoryServiceImpl implements TagService {

    @Autowired
    private TermsDao termsDao;

    @Autowired
    private TermTaxonomyDao termTaxonomyDao;


    public void addTag(TagVo tagVo) {
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setName(tagVo.getName());
        categoryVo.setTermGroup(tagVo.getTermGroup());
        categoryVo.setTaxonomy(TAG);
        super.saveOrUpdate(categoryVo);
        tagVo.setId(categoryVo.getId());
    }

    public List<TagVo> getAllTag() {
        List<TagVo> tagVos = new ArrayList<TagVo>();
        List<CategoryVo> list = findAll(TAG);
        for (CategoryVo categoryVo : list) {
            TagVo tagVo = new TagVo();
            copy(categoryVo, tagVo);
            tagVos.add(tagVo);
        }
        return tagVos;
    }

    public TagVo getTagById(long id) {
        CategoryVo categoryVo = findById(id);
        TagVo tagVo = new TagVo();
        copy(categoryVo, tagVo);
        return tagVo;
    }

    public TagVo getOrAddTagByName(String tagName) {
        TagVo tagVo = new TagVo();
//        Terms terms = new Terms();
//        terms.setName(tagName.trim());
//        terms = termsDao.findOneByExample(terms);
        Terms terms = termsDao.findOneByNameAndTaxonomy(tagName.trim(), TagService.TAG);
        if (terms == null) {
            tagVo.setName(tagName.trim());
            addTag(tagVo);
        }else{
            TermTaxonomy termTaxonomy = terms.getTermTaxonomy();
            tagVo.setName(terms.getName());
            tagVo.setId(termTaxonomy.getId());
            tagVo.setCount(termTaxonomy.getCount());
            tagVo.setTermGroup(terms.getTermGroup());
        }
        return tagVo;
    }

    public PageModel findPage(PageModel pageModel) {
        TermTaxonomy taxonomy = new TermTaxonomy();
        taxonomy.setTaxonomy(TAG);
        pageModel = termTaxonomyDao.findPageByExample(taxonomy,pageModel);
        List<TagVo> tagVos = new ArrayList<TagVo>();
        List<TermTaxonomy> list = pageModel.getList();
        for (TermTaxonomy termTaxonomy : list) {
            TagVo tagVo = new TagVo();
            copy(termTaxonomy, tagVo);
            tagVos.add(tagVo);
        }
        pageModel.setList(tagVos);
        return pageModel;
    }

    /**
     * 把TermTaxonomy对象数据复制到Tag对象中去
     *
     * @param source
     * @param target
     */
    private void copy(TermTaxonomy source, TagVo target) {
        Terms terms = source.getTerms();
        target.setId(source.getId());
        target.setName(terms.getName());
        target.setCount(source.getCount()==null?0:source.getCount());
        target.setTermGroup(terms.getTermGroup());
    }

    /**
     * 把TagVo对象数据复制到TermTaxonomy对象中去
     *
     * @param source Tag
     * @param target TermTaxonomy
     */
    private void copy(TagVo source, TermTaxonomy target) {
        Terms terms = new Terms();
        terms.setName(source.getName());
        terms.setTermGroup(source.getTermGroup());
        target.setTaxonomy(TAG);
        target.setTerms(terms);
        target.setId(source.getId());
        target.setCount(source.getCount());
    }

    private void copy(CategoryVo source, TagVo target) {
        target.setId(source.getId());
        target.setName(source.getName());
        target.setCount(source.getCount());
        target.setTermGroup(source.getTermGroup());
    }
}
