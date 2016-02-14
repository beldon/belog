package belog.admin;


import belog.pojo.Msg;
import belog.pojo.vo.ThemeVo;
import belog.service.ThemeService;
import belog.utils.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 主题管理
 * Created by Beldon
 */
@Controller("adminThemeController")
@RequestMapping("/admin/theme")
public class ThemeController extends AdminBaseController {

    @Autowired
    private ThemeService themeService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<ThemeVo> themes = themeService.getThemes();
        String currentTheme = themeService.getThemeName();
        model.addAttribute("currentTheme", currentTheme);
        model.addAttribute("themes", themes);
        return getTemplatePath("theme/list");
    }

    @RequestMapping("/details")
    public String detail(@RequestParam("theme") String theme, Model model) {
        ThemeVo themeVo = themeService.getThemeByDir(theme);
        String currentTheme = themeService.getThemeName();
        model.addAttribute("currentTheme", currentTheme);
        model.addAttribute("theme", themeVo);
        return getTemplatePath("theme/details");
    }

    @RequestMapping("/show")
    public String show(Model model) {
        String theme = themeService.getThemeName();
        model.addAttribute("theme", theme);
        return getTemplatePath("theme/show");
    }

    @RequestMapping("/addOrUpdate.json")
    @ResponseBody
    public Msg addOrUpdate(@RequestParam("theme") String theme) {
        themeService.setTheme(theme);
        return MsgUtils.success();
    }
}
