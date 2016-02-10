package belog.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Beldon
 */
@Controller("frontIndexController")
public class IndexController extends FrontBaseController {

    /**
     * 首页地址
     *
     * @return
     */
    @RequestMapping({"/", "/index.html"})
    public String index() {
        return themeService.getTemplatePath("index");
    }

    @RequestMapping({"/index_{page}.html"})
    public String index(Model model, @PathVariable int page) {
        model.addAttribute("currentPage", page);
        return themeService.getTemplatePath("index");
    }

    @RequestMapping({"/{file}.html"})
    public String one(@PathVariable("file") String file) {
        return themeService.getTemplatePath(file);
    }

    @RequestMapping({"/{folder}/","/{folder}/index.html"})
    public String folder(@PathVariable("folder") String folder) {
        return themeService.getTemplatePath(folder+"/index");
    }

    @RequestMapping({"/{folder}/index_{page}.html"})
    public String folder(@PathVariable("folder") String folder, @PathVariable int page, Model model) {
        model.addAttribute("currentPage", page);
        return themeService.getTemplatePath(folder+"/index");
    }

    @RequestMapping({"/{folder}/{file}.html"})
    public String folder(@PathVariable("folder") String folder, @PathVariable String file) {
        return themeService.getTemplatePath(folder + "/" + file);
    }

    @RequestMapping({"/{folder}/{file}/{id}.html"})
    public String folder(@PathVariable("folder") String folder, @PathVariable String file, @PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return themeService.getTemplatePath(folder + "/" + file);
    }

    @RequestMapping({"/{folder}/{file}/{page}/{id}.html"})
    public String folder(@PathVariable("folder") String folder, @PathVariable String file, @PathVariable int page, @PathVariable int id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("page", page);
        return themeService.getTemplatePath(folder + "/" + file);
    }

}
