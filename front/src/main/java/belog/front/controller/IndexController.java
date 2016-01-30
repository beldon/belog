package belog.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Beldon
 */
@Controller("frontIndexController")
public class IndexController extends FrontBaseController {

    @RequestMapping("/index")
    public String index() {
        return themeService.getTemplatePath("index");
    }

    @RequestMapping("/contact")
    public String contact(){
        return themeService.getTemplatePath("contact");
    }


    @RequestMapping("/404.html")
    public String error404(){
        return themeService.getTemplatePath("404");
    }
}
