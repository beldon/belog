package belog.service;

import belog.pojo.vo.ArticleVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Beldon.
 * Copyright (c) 2016, All Rights Reserved.
 * http://beldon.me
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-core.xml"})
public class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void testAddOrUpdate() throws Exception {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setTitle("title");
        articleVo.setContent("dd");
        articleVo.setCover("cover22");
        articleService.addOrUpdate(articleVo);
    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testFindById() throws Exception {

    }

    @Test
    public void testFindPage() throws Exception {

    }

    @Test
    public void testFindPageByCatId() throws Exception {

    }

    @Test
    public void testDeleteCover() throws Exception {

    }
}