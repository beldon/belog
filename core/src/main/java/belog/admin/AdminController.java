package belog.admin;


import belog.pojo.Msg;
import belog.service.SystemService;
import belog.service.UserService;
import belog.utils.MsgUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author Beldon
 */
@Controller("adminAdminController")
@RequestMapping("/admin")
public class AdminController extends AdminBaseController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public String login() {
        return getTemplatePath("login");
    }


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login.json")
    @ResponseBody
    public Msg doLogin(@RequestParam("username") String username, @RequestParam("password") String password) {
        Msg msg = new Msg();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            msg.setErrMsg("用户名或密码不能为空");
            msg.setErrCode(-1);
            return msg;
        }

        userService.login(username.trim(), password.trim());
        msg.setStatus(true);
        return msg;
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("/logout.json")
    @ResponseBody
    public Msg logOut() {
        SecurityUtils.getSubject().logout();
        return MsgUtils.success();
    }


    @RequestMapping("/index")
    public String index(Model model) {
        Map<String, String> map = systemService.getSystemMsg();
        model.addAllAttributes(map);
        return getTemplatePath("index");
    }
}
