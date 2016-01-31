package belog.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Beldon
 */
@Controller
@RequestMapping("/discover")
public class DiscoverController extends FrontBaseController{
    @RequestMapping({"/", "/index.html", "/index_{page}.html"})
    public String index(Model model, @PathVariable @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("currentPage", page);
        return themeService.getTemplatePath("discover/index");
    }
}
