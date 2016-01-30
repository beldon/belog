package belog.front.controller;


import belog.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * Created by Beldon
 */
@Controller("frontFrontBaseController")
public abstract class FrontBaseController {

    @Autowired
    protected ThemeService themeService;

    @PostConstruct
    private void init() {
        System.out.println("初始化：" + this.getClass().getName());
    }
}
