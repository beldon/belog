package belog.service.impl;


import belog.pojo.po.Taxonomy;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.Categorys;
import belog.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by beldon.
 */
@Service("CategoryService")
public class CategoryServiceImpl extends TaxonomyServiceImpl implements CategoryService {

    public void saveOrUpdate(CategoryVo category) {
        if (category.getId() == null || category.getId() == 0) {//添加
            taxonomyMapper.insertSelective(category);
        }else{//更新
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
        List<Categorys> categorysList = new CopyOnWriteArrayList<Categorys>();
        List<CategoryVo> parent = findCatByPid(0);
        for (CategoryVo categoryVo : parent) {
            Categorys categorys = new Categorys();
            List<Categorys> subs = new ArrayList<Categorys>();
            List<CategoryVo> categoryVoList =  findCatByPid(categoryVo.getId());
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
        List<CategoryVo> list = new ArrayList<CategoryVo>();
        List<Taxonomy> taxonomies = super.findByPid(pid, CATEGORY);
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


    private void copy(List<Taxonomy> source, List<CategoryVo> target) {
        for (Taxonomy taxonomy : source) {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(taxonomy, categoryVo);
            target.add(categoryVo);
        }
    }
}
