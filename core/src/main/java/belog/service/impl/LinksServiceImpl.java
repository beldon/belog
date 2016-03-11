package belog.service.impl;

import belog.dao.LinksMapper;
import belog.dao.TaxonomyRelationshipsMapper;
import belog.pojo.Page;
import belog.pojo.event.Event;
import belog.pojo.event.LinkEvent;
import belog.pojo.po.Links;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.LinksVo;
import belog.service.CategoryService;
import belog.service.LinksService;
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
        if (linksVo.getId() == 0) {//添加链接
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

        //TODO 删除分类
    }

    public LinksVo findById(long id) {
        Links links = linksMapper.selectByPrimaryKey(id);
        LinksVo linksVo = new LinksVo();
        BeanUtils.copyProperties(links, linksVo);
        return linksVo;
    }

    public Page<LinksVo> findPage(Page<LinksVo> page) {
        return null;
    }

    public Page<LinksVo> findPageByCatId(long catId, Page<LinksVo> page) {
        return null;
    }


    public List<LinksVo> findAll() {
        List<LinksVo> linksVos = new ArrayList<LinksVo>();
        List<Links> linkses = linksMapper.findAll();
        for (Links links : linkses) {
            LinksVo linksVo = new LinksVo();
            BeanUtils.copyProperties(links, linksVo);
            linksVos.add(linksVo);
        }
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

        }
    }


}
