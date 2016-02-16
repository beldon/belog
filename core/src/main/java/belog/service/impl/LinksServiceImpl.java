package belog.service.impl;

import belog.dao.LinksDao;
import belog.dao.TermRelationshipsDao;
import belog.dao.UsersDao;
import belog.pojo.PageModel;
import belog.pojo.event.ArticleEvent;
import belog.pojo.event.Event;
import belog.pojo.event.LinkEvent;
import belog.pojo.po.*;
import belog.pojo.vo.*;
import belog.service.CategoryService;
import belog.service.LinksService;
import belog.service.TagService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Beldon
 */
@Service("LinksService")
public class LinksServiceImpl extends BaseService implements LinksService {

    @Autowired
    private LinksDao linksDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private TermRelationshipsDao termRelationshipsDao;

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

//            Subject subject = SecurityUtils.getSubject();
//            String loginName = subject.getPrincipal().toString();
            String loginName = "beldon";
            Users users = usersDao.findByLoginName(loginName);
            links.setUsers(users);
            linksDao.saveEntity(links);

            //处理分类
            List<CategoryVo> cats = linksVo.getCats();
            addCat(links, cats);

        } else {//更新链接
            appContext.getContexts().publishEvent(new LinkEvent(linksVo, Event.Action.UPDATE));
            Links links = linksDao.findById(linksVo.getId());
            BeanUtils.copyProperties(linksVo, links);
            links.setUpdated(new Date());

            Set<TermRelationships> termRelationshipsSet = links.getTermRelationships();
            for (TermRelationships termRelationships : termRelationshipsSet) {//删除已有分类
                termRelationshipsDao.deleteEntity(termRelationships);
                categoryService.countMinus(termRelationships.getTermTaxonomy().getId(), 1);
            }

            //添加分类
            List<CategoryVo> cats = linksVo.getCats();
            addCat(links, cats);
        }
    }

    public void delete(long id) {
        Links links = linksDao.findById(id);
        Set<TermRelationships> termRelationshipsSet = links.getTermRelationships();
        for (TermRelationships termRelationships : termRelationshipsSet) {//删除已有分类
            termRelationshipsDao.deleteEntity(termRelationships);
            categoryService.countMinus(termRelationships.getTermTaxonomy().getId(), 1);
        }
        linksDao.deleteEntity(links);
    }

    public LinksVo findById(long id) {
        Links links = linksDao.findById(id);
        LinksVo linksVo = new LinksVo();
        copy(links, linksVo);
        return linksVo;
    }

    public PageModel findPage(PageModel pageModel) {
        List<LinksVo> linksVos = new ArrayList<LinksVo>();
        PageModel pm = linksDao.findPageByHql("from belog.pojo.po.Links order by sort desc", pageModel);
        List<Links> linksList = pm.getList();
        for (Links links : linksList) {
            LinksVo linksVo = new LinksVo();
            copy(links, linksVo);
            linksVos.add(linksVo);
        }
        pm.setList(linksVos);
        return pm;
    }

    public PageModel findPageByCatId(long catId, PageModel pageModel) {
        StringBuilder hql = new StringBuilder(300);
        hql.append("SELECT links FROM belog.pojo.po.Links links");
        hql.append(" JOIN links.termRelationships relation");
        hql.append(" where relation.termTaxonomy.id = '" + catId + "'");
        hql.append(" order by links.sort desc");

        PageModel pm = linksDao.findPageByHql(hql.toString(), pageModel);
        List<LinksVo> list = new ArrayList<LinksVo>();
        List<Links> linksList = pm.getList();
        for (Links links : linksList) {
            LinksVo linksVo = new LinksVo();
            copy(links, linksVo);
            list.add(linksVo);
        }
        pm.setList(list);
        return pm;
    }

    public List<LinksVo> findAll() {
        List<LinksVo> linksVos = new ArrayList<LinksVo>();
        List<Links> linkses = linksDao.findAll();
        for (Links links : linkses) {
            LinksVo linksVo = new LinksVo();
            copy(links, linksVo);
            linksVos.add(linksVo);
        }
        return linksVos;
    }


    /**
     * 为链接添加分类
     *
     * @param links 模板链接
     * @param cats  分类
     */
    private void addCat(Links links, List<CategoryVo> cats) {
        if (cats != null && cats.size() > 0) {
            for (CategoryVo categoryVo : cats) {
                TermRelationships termRelationships = new TermRelationships();
                termRelationships.setLinks(links);
                TermTaxonomy taxonomy = new TermTaxonomy();
                taxonomy.setId(categoryVo.getId());
                termRelationships.setTermTaxonomy(taxonomy);
                termRelationshipsDao.saveOrUpdate(termRelationships);
                categoryService.countPlus(categoryVo.getId(), 1);
            }
        }
    }

    private void copy(Links source, LinksVo target) {
        BeanUtils.copyProperties(source, target);

        //查询链接所属用户
        Users user = source.getUsers();
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        target.setUserVo(userVo);

        //查询文章拥有的分类
        Set<TermRelationships> termRelationshipsSet = source.getTermRelationships();
        if (termRelationshipsSet != null && termRelationshipsSet.size() > 0) {
            List<CategoryVo> categoryVoList = new ArrayList<CategoryVo>();
            for (TermRelationships termRelationships : termRelationshipsSet) {
                TermTaxonomy taxonomy = termRelationships.getTermTaxonomy();
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(taxonomy.getTerms(), categoryVo);
                BeanUtils.copyProperties(taxonomy, categoryVo);
                categoryVoList.add(categoryVo);
            }
            target.setCats(categoryVoList);
        }
    }
}
