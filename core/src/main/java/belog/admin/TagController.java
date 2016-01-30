package belog.admin;

import belog.pojo.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Beldon
 */
@Controller("adminTagController")
@RequestMapping("/admin/tag")
public class TagController extends AdminBaseController {
    @RequestMapping("/list")
    public String list() {
        return getTemplatePath("tag/list");
    }

    @RequestMapping("/edit")
    public String edit() {
        return getTemplatePath("tag/edit");
    }

    @RequestMapping("/ajaxEdit.json")
    public Msg ajaxEdit() {
        Msg msg = new Msg();
        return msg;
    }

    @RequestMapping("/delete.json")
    public Msg delete() {
        Msg msg = new Msg();
        return msg;
    }
}
