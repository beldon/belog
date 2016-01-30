package belog.admin;


import belog.pojo.Msg;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.Categorys;
import belog.service.CategoryService;
import belog.utils.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Beldon
 */
@Controller("adminCategoryController")
@RequestMapping("/admin/category")
public class CategoryController extends AdminBaseController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Categorys> categorysList = categoryService.findCat();
        model.addAttribute("cats", categorysList);
        return getTemplatePath("category/list");
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id") long id,Model model) {
        CategoryVo cat = categoryService.findById(id);
        List<Categorys> categorysList = categoryService.findCat();
        model.addAttribute("cats", categorysList);
        model.addAttribute("cat", cat);
        return getTemplatePath("category/edit");
    }

    @RequestMapping("/ajaxEdit.json")
    @ResponseBody
    public Msg ajaxEdit(@ModelAttribute CategoryVo categoryVo) {
        categoryService.saveOrUpdate(categoryVo);
        return MsgUtils.success();
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public Msg delete(@RequestParam("id") long id) {
        categoryService.delete(id);
        return MsgUtils.success();
    }
}
