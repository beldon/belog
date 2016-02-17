package belog.service;

import belog.dao.LinksDao;
import belog.pojo.po.Links;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Beldon
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-core.xml")
public class PoTestService {

//    @Autowired
    private LinksService linksService;

//    @Test
    public void addTest() throws Exception{
        Links links = new Links();
        links.setName("d");
        links.setUrl("url");
//        linksService.saveOrUpdate(links);
    }
}
