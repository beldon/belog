package belog.service.impl;


import belog.dao.TermTaxonomyDao;
import belog.dao.TermsDao;
import belog.pojo.po.TermTaxonomy;
import belog.pojo.po.Terms;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.Categorys;
import belog.service.CategoryService;
import belog.service.TermTaxonomyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beldon.
 */
@Service("CategoryService")
public class CategoryServiceImpl extends TermTaxonomyServiceImpl implements CategoryService {

    @Autowired
    private TermsDao termsDao;

    @Autowired
    private TermTaxonomyDao termTaxonomyDao;

    public void saveOrUpdate(CategoryVo category) {
        if (category.getId() == 0) {//新增
            Terms terms = new Terms();
            TermTaxonomy termTaxonomy = new TermTaxonomy();
            BeanUtils.copyProperties(category, terms);
            BeanUtils.copyProperties(category, termTaxonomy);
            if (StringUtils.hasText(category.getTaxonomy())) {
                termTaxonomy.setTaxonomy(category.getTaxonomy());
            }else{
                termTaxonomy.setTaxonomy(CATEGORY);
            }
            termsDao.saveEntity(terms);
            termTaxonomy.setTerms(terms);
            termTaxonomyDao.saveEntity(termTaxonomy);
            category.setId(termTaxonomy.getId());
        } else {//更新
            TermTaxonomy taxonomy = termTaxonomyDao.findById(category.getId());

            taxonomy.setDescription(category.getDescription());
            taxonomy.setParent(category.getParent());
            Terms t = taxonomy.getTerms();
            t.setName(category.getName());
            t.setSlug(category.getSlug());
            t.setTermGroup(category.getTermGroup());
            termTaxonomyDao.updateEntity(taxonomy);

        }
    }

    public List<CategoryVo> findAll() {
        return findAll(CATEGORY);
    }

    public List<CategoryVo> findAll(String type) {
        List<CategoryVo> list = new ArrayList<CategoryVo>();
        TermTaxonomy taxonomy = new TermTaxonomy();
        taxonomy.setTaxonomy(type);
        List<TermTaxonomy> taxonomies = termTaxonomyDao.findByExample(taxonomy);
        for (TermTaxonomy termTaxonomy : taxonomies) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(termTaxonomy.getTerms(), categoryVo);
            BeanUtils.copyProperties(termTaxonomy, categoryVo);
            list.add(categoryVo);
        }
        return list;
    }

    public List<Categorys> findCat() {
        return findCat(CATEGORY);
    }

    public List<Categorys> findCat(String type) {
        List<Categorys> categorysList = new ArrayList<Categorys>();

        TermTaxonomy termTaxonomy = new TermTaxonomy();
        termTaxonomy.setParent(0l);
        termTaxonomy.setTaxonomy(type);

        List<TermTaxonomy> list = termTaxonomyDao.findByExample(termTaxonomy);
        for (TermTaxonomy taxonomy : list) {
            Categorys categorys = new Categorys();
            CategoryVo categoryVo = new CategoryVo();
            copy(taxonomy, categoryVo);
            categorys.setCategory(categoryVo);
            getChilds(categorys);
            categorysList.add(categorys);
        }
        return categorysList;
    }

    public List<CategoryVo> findCatByPid(long pid) {
        return findCatByPid(pid,CATEGORY);
    }

    public List<CategoryVo> findCatByPid(long pid, String type) {
        List<CategoryVo> categorysList = new ArrayList<CategoryVo>();
        TermTaxonomy termTaxonomy = new TermTaxonomy();
        termTaxonomy.setParent(pid);
        termTaxonomy.setTaxonomy(CATEGORY);
        List<TermTaxonomy> list = termTaxonomyDao.findByExample(termTaxonomy);

        for (TermTaxonomy taxonomy : list) {
            CategoryVo categoryVo = new CategoryVo();
            copy(taxonomy, categoryVo);
            categorysList.add(categoryVo);
        }
        return categorysList;
    }

    public CategoryVo findById(long id) {
        TermTaxonomy taxonomy = termTaxonomyDao.findById(id);
        Terms terms = taxonomy.getTerms();
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(terms, categoryVo);
        BeanUtils.copyProperties(taxonomy, categoryVo);
        return categoryVo;
    }

    /**
     * 获取下级菜单
     *
     * @param categorys
     */
    private void getChilds(Categorys categorys) {
        List<Categorys> subs = new ArrayList<Categorys>();
        TermTaxonomy termTaxonomy = new TermTaxonomy();
        termTaxonomy.setParent(categorys.getCategory().getId());
        List<TermTaxonomy> list = termTaxonomyDao.findByExample(termTaxonomy);
        for (TermTaxonomy taxonomy : list) {
            Categorys sub = new Categorys();
            CategoryVo categoryVo = new CategoryVo();
            copy(taxonomy, categoryVo);

            sub.setCategory(categoryVo);
            subs.add(sub);
            getChilds(sub);
        }
        categorys.setSubs(subs);
    }

    /**
     * 把TermTaxonomy对象数据复制到CategoryVo对象中去
     *
     * @param taxonomy
     * @param categoryVo
     */
    private void copy(TermTaxonomy taxonomy, CategoryVo categoryVo) {
        Terms terms = taxonomy.getTerms();
        BeanUtils.copyProperties(terms, categoryVo);
        BeanUtils.copyProperties(taxonomy, categoryVo);
    }
}
