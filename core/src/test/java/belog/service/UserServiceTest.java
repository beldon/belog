package belog.service;

import belog.pojo.Page;
import belog.pojo.vo.UserVo;
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
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSaveOrUpdate() throws Exception {
        UserVo userVo = new UserVo();
        userVo.setId(2l);
        userVo.setLogin("calder");
        userVo.setNickname("calder");
        userVo.setPass("111111");
        userVo.setEmail("calder@qq.com");
        userVo.setDisplayName("calder");
        userService.saveOrUpdate(userVo);
    }

    @Test
    public void testDelete() throws Exception {
        userService.delete(4);
    }

    @Test
    public void testFindByPage() throws Exception {
        Page<UserVo> page = new Page<UserVo>();
        page.setPageSize(2);
        page = userService.findByPage(page);

        System.out.println(page.getTotalPage());
        System.out.println(page.getTotalRecord());
        System.out.println(page.getPageNo());
        System.out.println(page.getPageSize());
    }

    @Test
    public void testFindById() throws Exception {
        UserVo userVo = userService.findById(1);
        System.out.println(userVo);
    }

    @Test
    public void testLogin() throws Exception {

    }

    @Test
    public void testFindUserByLoginName() throws Exception {
        UserVo userVo = userService.findUserByLoginName("beldon");
        System.out.println(userVo);
    }

    @Test
    public void testGetCurrentUserName() throws Exception {

    }

    @Test
    public void testGetCurrentUser() throws Exception {

    }
}