package belog.service.impl;


import belog.dao.TermTaxonomyDao;
import belog.dao.TermsDao;
import belog.pojo.po.TermTaxonomy;
import belog.pojo.po.Terms;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.Categorys;
import belog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beldon.
 */
@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {

    @Autowired
    private TermsDao termsDao;

    @Autowired
    private TermTaxonomyDao termTaxonomyDao;

    public void saveOrUpdate(CategoryVo category) {
        Terms terms = new Terms();
        TermTaxonomy termTaxonomy = new TermTaxonomy();
        BeanUtils.copyProperties(category, terms);
        BeanUtils.copyProperties(category, termTaxonomy);
        termTaxonomy.setTaxonomy("category");

        if (terms.getId() == 0) {//新增
            termsDao.saveEntity(terms);
            termTaxonomy.setTerms(terms);
            termTaxonomyDao.saveEntity(termTaxonomy);
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

    public void delete(long id) {
        TermTaxonomy termTaxonomy = termTaxonomyDao.findById(id);
        Terms terms = termTaxonomy.getTerms();
        termTaxonomyDao.deleteEntity(termTaxonomy);
        termsDao.deleteEntity(terms);
    }

    public List<CategoryVo> findAll() {
        List<CategoryVo> list = new ArrayList<CategoryVo>();
        List<TermTaxonomy> taxonomies = termTaxonomyDao.findAll();
        for (TermTaxonomy termTaxonomy : taxonomies) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(termTaxonomy.getTerms(), categoryVo);
            BeanUtils.copyProperties(termTaxonomy, categoryVo);
            list.add(categoryVo);
        }
        return list;
    }

    public List<Categorys> findCat() {
        List<Categorys> categorysList = new ArrayList<Categorys>();

        TermTaxonomy termTaxonomy = new TermTaxonomy();
        termTaxonomy.setParent(0l);
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
        List<CategoryVo> categorysList = new ArrayList<CategoryVo>();
        TermTaxonomy termTaxonomy = new TermTaxonomy();
        termTaxonomy.setParent(0l);
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

    public void countPlus(long catId, long sum) {
        TermTaxonomy taxonomy = termTaxonomyDao.findById(catId);
        taxonomy.setCount(taxonomy.getCount() + sum);
        termTaxonomyDao.updateEntity(taxonomy);
    }

    public void countMinus(long catId, long sum) {
        TermTaxonomy taxonomy = termTaxonomyDao.findById(catId);
        long count = taxonomy.getCount();
        if (count >= sum) {
            taxonomy.setCount(taxonomy.getCount() - sum);
        }
        termTaxonomyDao.updateEntity(taxonomy);
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
