package belog.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * Created by Beldon
 */
@Controller("frontIndexController")
public class IndexController extends FrontBaseController {

//    @RequestMapping("/*")
//    public String index() {
//        return super.themeService.getTemplatePath("index");
//    }

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
        model.addAttribute("pageNo", page);
        return themeService.getTemplatePath("index");
    }

    @RequestMapping({"/{file}.html"})
    public String one(Model model, @PathVariable("file") String file) {
        if (!isFileExist(file)) {
            model.addAttribute("key", file);
            return themeService.getTemplatePath("index");
        }
        return themeService.getTemplatePath(file);
    }

    @RequestMapping({"/{folder}/", "/{folder}/index.html"})
    public String folderIndex(@PathVariable("folder") String folder, Model model) {
        if (!ifFolderExist(folder)) {
            model.addAttribute("key", folder);
            return themeService.getTemplatePath("index");
        }
        return themeService.getTemplatePath(folder + "/index");
    }

    @RequestMapping({"/{folder}/index_{page}.html"})
    public String folderIndex(@PathVariable("folder") String folder, @PathVariable int page, Model model) {
        model.addAttribute("pageNo", page);
        if (!ifFolderExist(folder)) {
            model.addAttribute("key", folder);
            return themeService.getTemplatePath("index");
        }
        return themeService.getTemplatePath(folder + "/index");
    }


    @RequestMapping({"/{folder}/{file}.html"})
    public String folder(@PathVariable("folder") String folder, @PathVariable String file, Model model) {
        String template = folder + "/" + file;
        if (!isFileExist(template)) {
            model.addAttribute("key", file);
            return themeService.getTemplatePath(folder + "/" + "index");
        }
        return themeService.getTemplatePath(folder + "/" + file);
    }

    @RequestMapping({"/{folder}/{file}/{intVal}.html"})
    public String folder(@PathVariable("folder") String folder, @PathVariable("file") String file, @PathVariable("intVal") int intVal, Model model) {
        String template = folder + "/" + file;
        model.addAttribute("intVal", intVal);

        if (!isFileExist(template)) {
            model.addAttribute("key", file);
            return themeService.getTemplatePath(folder + "/" + "index");
        }

        return themeService.getTemplatePath(folder + "/" + file);
    }

    @RequestMapping({"/{folder}/{file}/{page}/{id}.html"})
    public String folder(@PathVariable("folder") String folder, @PathVariable String file, @PathVariable int page, @PathVariable int id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("page", page);
        return themeService.getTemplatePath(folder + "/" + file);
    }

}
