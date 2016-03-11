package belog.admin;


import belog.pojo.Msg;
import belog.pojo.Page;
import belog.pojo.PageModel;
import belog.pojo.vo.RoleVo;
import belog.pojo.vo.UserVo;
import belog.service.RoleService;
import belog.service.UserService;
import belog.utils.MsgUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
@Controller("adminUserController")
@RequestMapping("/admin/user")
public class UserController extends AdminBaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "8") int pageSize) {

        Page page = new Page();
        page.setPageNo(currentPage);
        page.setPageSize(pageSize);

        page = userService.findByPage(page);

        model.addAttribute("pm", page);

        return getTemplatePath("user/list");
    }

    @RequestMapping("/edit")
    public String edit(Model model, @RequestParam(defaultValue = "0") long id) {
        List<RoleVo> list = roleService.getAll();

        if (id != 0) {
            UserVo userVo = userService.findById(id);
            model.addAttribute("user", userVo);
        }

        model.addAttribute("roleList", list);
        return getTemplatePath("user/edit");
    }

    @RequestMapping("/profile")
    public String profile(Model model) {
        Subject subject = SecurityUtils.getSubject();
//        subject.getPreviousPrincipals().getPrimaryPrincipal();
        String loginName = subject.getPrincipal().toString();
        UserVo userVo = userService.findUserByLoginName(loginName);

        model.addAttribute("user", userVo);
        return getTemplatePath("user/profile");
    }

    @RequestMapping("/ajaxEdit.json")
    @ResponseBody
    public Msg ajaxEdit(@ModelAttribute(value = "user") UserVo user) {
        Msg msg = new Msg();

        if (user != null) {
//            user.setNickName(user.getLogin());
            user.setDisplayName(user.getNickname());
            userService.saveOrUpdate(user);
            msg.setErrCode(0);
            msg.setStatus(true);
            msg.setErrMsg("success");
        } else {
            msg.setErrCode(-1);
            msg.setErrMsg("error");
        }
        return msg;
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public Msg delete(@RequestParam("id") int id) {
        userService.delete(id);
        return MsgUtils.success();
    }
}
