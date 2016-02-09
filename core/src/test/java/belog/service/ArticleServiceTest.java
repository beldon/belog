package belog.service;

import belog.pojo.PageModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Beldon
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-core.xml")
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

//    @Test
    public void testFindPageByCatId() throws Exception {
        PageModel pageModel = new PageModel();
        pageModel.setCurrentPage(1);
        pageModel.setPageSize(2);
        PageModel pm = articleService.findPageByCatId(33, pageModel, "");
        System.out.println(pm);
    }
}