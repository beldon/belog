package me.beldon.belog.install.controller;

import org.springframework.stereotype.Controller;

/**
 * Created by Beldon
 */
@Controller
public class BaseController {

    public String getTemplatePath(String template){
        return "default/" + template;
    }
}
