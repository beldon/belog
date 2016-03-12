package belog.admin;

import belog.pojo.Msg;
import belog.pojo.Page;
import belog.pojo.PageModel;
import belog.service.TagService;
import belog.utils.MsgUtils;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Beldon
 */
@Controller("adminTagController")
@RequestMapping("/admin/tag")
public class TagController extends AdminBaseController {

    @Autowired
    private TagService tagService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("page") Page page, Model model) {
        Page pm = tagService.findPage(page);
        model.addAttribute("pm", pm);
        return getTemplatePath("tag/list");
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public Msg delete(int id) {
        if (0 == id) {
            return MsgUtils.error("请填入文章内容");
        }
        tagService.deleteById(id);
        return MsgUtils.success();
    }
}
