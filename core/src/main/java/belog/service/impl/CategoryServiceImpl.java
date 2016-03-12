package belog.service.impl;


import belog.pojo.po.Taxonomy;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.Categorys;
import belog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by beldon.
 */
@Service("CategoryService")
public class CategoryServiceImpl extends TaxonomyServiceImpl implements CategoryService {

    @Transactional
    public void saveOrUpdate(CategoryVo category) {
        if (category.getParent() == null) {
            category.setParent(0l);
        }

        if (category.getId() == null || category.getId() == 0) {//添加
            category.setCount(0l);
            taxonomyMapper.insertSelective(category);
        } else {//更新
            taxonomyMapper.updateByPrimaryKeySelective(category);
        }
    }

    public List<CategoryVo> findAll() {
        List<CategoryVo> list = new ArrayList<CategoryVo>();
        List<Taxonomy> taxonomies = super.findAll(CATEGORY);
        copy(taxonomies, list);
        return list;
    }

    public List<Categorys> findCat() {
        return findCat(CATEGORY);
    }

    public List<Categorys> findCat(String type) {
        List<Categorys> categorysList = new ArrayList<Categorys>();
        List<CategoryVo> parent = findCatByPid(0, type);
        for (CategoryVo categoryVo : parent) {
            Categorys categorys = new Categorys();
            List<Categorys> subs = new ArrayList<Categorys>();
            List<CategoryVo> categoryVoList = findCatByPid(categoryVo.getId(), type);
            for (CategoryVo subCat : categoryVoList) {
                Categorys subCategorys = new Categorys();
                subCategorys.setCategory(subCat);
                subs.add(subCategorys);
            }
            categorys.setCategory(categoryVo);
            categorys.setSubs(subs);
            categorysList.add(categorys);
        }
        return categorysList;
    }


    public List<CategoryVo> findCatByPid(long pid) {
        return findCatByPid(pid, CATEGORY);
    }

    public List<CategoryVo> findCatByPid(long pid, String type) {
        List<CategoryVo> list = new ArrayList<CategoryVo>();
        List<Taxonomy> taxonomies = super.findByPid(pid, type);
        copy(taxonomies, list);
        return list;
    }

    public CategoryVo findById(long id) {
        CategoryVo categoryVo = new CategoryVo();
        Taxonomy taxonomy = taxonomyMapper.selectByPrimaryKey(id);
        if (taxonomy != null) {
            BeanUtils.copyProperties(taxonomy, categoryVo);
        }
        return categoryVo;
    }

    public List<CategoryVo> findByObjectId(Long objectId, String type) {
        List<CategoryVo> categoryVoList = new ArrayList<CategoryVo>();
        List<Taxonomy> taxonomies = super.findByObjectIdAndType(objectId, type);
        copy(taxonomies, categoryVoList);
        return categoryVoList;
    }


    private void copy(List<Taxonomy> source, List<CategoryVo> target) {
        for (Taxonomy taxonomy : source) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(taxonomy, categoryVo);
            target.add(categoryVo);
        }
    }
}
