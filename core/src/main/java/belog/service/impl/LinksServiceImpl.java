package belog.service.impl;

import belog.dao.LinksMapper;
import belog.dao.TaxonomyRelationshipsMapper;
import belog.pojo.Page;
import belog.pojo.event.Event;
import belog.pojo.event.LinkEvent;
import belog.pojo.po.Links;
import belog.pojo.po.TaxonomyRelationships;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.LinksVo;
import belog.service.CategoryService;
import belog.service.LinksService;
import belog.service.TaxonomyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Beldon
 */
@Service("LinksService")
public class LinksServiceImpl extends BaseService implements LinksService {

    @Autowired
    private LinksMapper linksMapper;

    @Autowired
    private TaxonomyRelationshipsMapper taxonomyRelationshipsMapper;

    @Autowired
    @Qualifier("CategoryService")
    private CategoryService categoryService;

    public void saveOrUpdate(LinksVo linksVo) {
        if (linksVo.getId() == null || linksVo.getId() == 0) {//添加链接
            appContext.getContexts().publishEvent(new LinkEvent(linksVo, Event.Action.ADD));
            Links links = new Links();
            BeanUtils.copyProperties(linksVo, links);
            links.setVisible("Y");
            links.setUpdated(new Date());
//            links.setOwner(1);

            linksMapper.insertSelective(links);

            //处理分类
            List<CategoryVo> cats = linksVo.getCats();
            addCat(links.getId(), cats);

        } else {//更新链接
            appContext.getContexts().publishEvent(new LinkEvent(linksVo, Event.Action.UPDATE));
            Links links = linksMapper.selectByPrimaryKey(linksVo.getId());
            BeanUtils.copyProperties(linksVo, links);
            links.setUpdated(new Date());
            linksMapper.updateByPrimaryKeySelective(links);

            //添加分类
            List<CategoryVo> cats = linksVo.getCats();
            addCat(links.getId(), cats);
        }
    }

    public void delete(long id) {
        linksMapper.deleteByPrimaryKey(id);

        //删除分类
        taxonomyRelationshipsMapper.deleteByTypeAndObjectId(TaxonomyService.LINK_CATEGORY, id);
    }

    public LinksVo findById(long id) {
        Links links = linksMapper.selectByPrimaryKey(id);
        LinksVo linksVo = new LinksVo();
        copy(links, linksVo);
        return linksVo;
    }

    public Page<LinksVo> findPage(Page<LinksVo> page) {
        Page<Links> linksPage = new Page<Links>();
        linksPage.setPageNo(page.getPageNo());
        linksPage.setPageSize(page.getPageSize());
        List<Links> list = linksMapper.findPage(linksPage);
        List<LinksVo> linksVos = new ArrayList<LinksVo>();
        copy(list, linksVos);
        page.setTotalPage(linksPage.getTotalPage());
        page.setTotalRecord(linksPage.getTotalRecord());
        page.setResults(linksVos);
        return page;
    }

    public Page<LinksVo> findPageByCatId(long catId, Page<LinksVo> page) {
        return null;
    }


    public List<LinksVo> findAll() {
        List<LinksVo> linksVos = new ArrayList<LinksVo>();
        List<Links> linkses = linksMapper.findAll();
        copy(linkses, linksVos);
        return linksVos;
    }


    /**
     * 为链接添加分类
     *
     * @param linksId
     * @param cats
     */
    private void addCat(Long linksId, List<CategoryVo> cats) {
        for (CategoryVo categoryVo : cats) {
            TaxonomyRelationships taxonomyRelationships = new TaxonomyRelationships();
            taxonomyRelationships.setType(TaxonomyService.LINK_CATEGORY);
            taxonomyRelationships.setObjectId(linksId);
            taxonomyRelationships.setTaxonomyId(categoryVo.getId());
            taxonomyRelationshipsMapper.insertSelective(taxonomyRelationships);
        }
    }

    private void copy(List<Links> source, List<LinksVo> target) {
        for (Links links : source) {
            LinksVo linksVo = new LinksVo();
            copy(links, linksVo);
            target.add(linksVo);
        }
    }

    private void copy(Links source, LinksVo target) {
        BeanUtils.copyProperties(source, target);
        List<CategoryVo> categoryVoList = categoryService.findByObjectId(source.getId(), TaxonomyService.LINK_CATEGORY);
        target.setCats(categoryVoList);
    }


}
