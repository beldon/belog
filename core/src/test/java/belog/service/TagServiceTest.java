package belog.service;

import belog.pojo.vo.TagVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Beldon
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/spring-core.xml")
public class TagServiceTest {

    @Autowired
    private TagService tagService;

//    @Test
    public void testAddTag() throws Exception {
        TagVo tagVo = new TagVo();
        tagVo.setName("标签三");
        tagService.addTag(tagVo);
    }

//    @Test
    public void testGetAllTag() throws Exception {
        List<TagVo> list = tagService.getAllTag();
        System.out.println(list.size());
    }

//    @Test
    public void testGetTagById() throws Exception {
        TagVo tagVo = tagService.getTagById(4);
        System.out.println(tagVo);
    }

//    @Test
    public void testGetTagByName() throws Exception {
        TagVo tagVo = tagService.getOrAddTagByName("标签4");
        System.out.println(tagVo);
    }

//    @Test
    public void testCountPlus() throws Exception {
        tagService.countPlus(7,2);
    }

//    @Test
    public void testCountMinus() throws Exception {
        tagService.countMinus(7,1);
    }

//    @Test
    public void testDeleteById() throws Exception {
        tagService.deleteById(7);
    }
}