package belog.service;

import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.Categorys;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Beldon.
 * Copyright (c) 2016, All Rights Reserved.
 * http://beldon.me
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-core.xml"})
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void testSaveOrUpdate() throws Exception {
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setId(1l);
        categoryVo.setName("cat2");
        categoryVo.setSlug("slug2");
        categoryVo.setTaxonomy("cat2");
        categoryVo.setDescription("描述2");
        categoryVo.setParent(0l);
        categoryService.saveOrUpdate(categoryVo);
    }

    @Test
    public void testFindAll() throws Exception {
        List<CategoryVo> list = categoryService.findAll();
        System.out.println(list.size());
    }

    @Test
    public void testFindCat() throws Exception {
        List<Categorys> categorysList = categoryService.findCat();
        System.out.println(categorysList);
    }

    @Test
    public void testFindCatByPid() throws Exception {

    }

    @Test
    public void testFindById() throws Exception {
        CategoryVo categoryVo = categoryService.findById(1l);
        System.out.println(categoryVo.getName());
    }

    @Test
    public void testM2() throws Exception {
        categoryService.countMinus(1, 4);
    }
}