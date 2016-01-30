package belog.admin;


import belog.pojo.Msg;
import belog.pojo.vo.MenuVo;
import belog.service.MenuService;
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
 * Created by Beldon
 */
@Controller("adminMenuController")
@RequestMapping("/admin/menu")
public class MenuController extends AdminBaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<MenuVo> menuVoList = menuService.findFrontMenu();
        model.addAttribute("menus", menuVoList);
        return getTemplatePath("menu/list");
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(defaultValue = "0", required = false) long id, Model model) {
        if (id != 0) {
            MenuVo menuVo = menuService.findById(id);
            model.addAttribute("menus", menuVo);
        }
        return getTemplatePath("menu/edit");
    }

    @RequestMapping("/addOrUpdate.json")
    @ResponseBody
    public Msg addOrUpdate(@ModelAttribute("menu") MenuVo menuVo) {
        menuService.addOrUpdateFront(menuVo);
        return MsgUtils.success();
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public Msg delete(@RequestParam("id") long id) {
        menuService.deleteMenuById(id);
        return MsgUtils.success();
    }
}
