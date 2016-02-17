package belog.service;

import belog.pojo.PageModel;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.LinksVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Beldon
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-core.xml")
public class LinksServiceTest {

//    @Autowired
    private LinksService linksService;

//    @Autowired
//    @Qualifier("CategoryService")
    private CategoryService categoryService;

//    @Test
    public void testSaveOrUpdate() throws Exception {
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setId(1);
        List<CategoryVo> cats = new ArrayList<CategoryVo>();
        cats.add(categoryVo);


        LinksVo linksVo = new LinksVo();
        linksVo.setId(2);
        linksVo.setUrl("url1");
        linksVo.setName("name1");
        linksVo.setCats(cats);

        linksService.saveOrUpdate(linksVo);

    }

//    @Test
    public void testDelete() throws Exception {
        linksService.delete(1);
    }

//    @Test
    public void testFindById() throws Exception {
        LinksVo linksVo = linksService.findById(2);
        System.out.println(linksVo);
    }

//    @Test
    public void testFindPage() throws Exception {
        PageModel pageModel = new PageModel();
        pageModel.setPageSize(10);
        pageModel.setCurrentPage(1);
        pageModel = linksService.findPage(pageModel);
        System.out.println(pageModel);
    }

//    @Test
    public void testFindPageByCatId() throws Exception {
        PageModel pageModel = new PageModel();
        pageModel.setPageSize(10);
        pageModel.setCurrentPage(1);
        pageModel = linksService.findPageByCatId(1,pageModel);
        System.out.println(pageModel);
    }

//    @Test
    public void testFindAll() throws Exception {
        List<LinksVo> linksVos = linksService.findAll();
        System.out.println(linksVos);
    }

//    @Test
    public void testAddCat() throws Exception {
        CategoryVo categoryVo = new CategoryVo();
        categoryVo.setName("测试");
        categoryVo.setSlug("slug");
        categoryVo.setTaxonomy(CategoryService.LINK_CATEGORY);
        categoryService.saveOrUpdate(categoryVo);
    }
}