package belog.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 文章Controller
 * <p>
 * Created by Beldon
 */
@Controller("frontArticleController")
@RequestMapping("/article")
public class ArticleController extends FrontBaseController {

    @RequestMapping({"/", "/index.html", "/index_{page}.html"})
    public String index(Model model, @PathVariable @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
//        model.addAttribute("articleId", "1");
        model.addAttribute("currentPage", page);
        return themeService.getTemplatePath("/article/index");
    }

    @RequestMapping("/{articleId}")
    public String details(@PathVariable("articleId") long articleId, Model model) {
        model.addAttribute("articleId", articleId);
        return themeService.getTemplatePath("/article/details");
    }


}
