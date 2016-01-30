package me.beldon.belog.install.controller;


import me.beldon.belog.install.InstallUtils;
import me.beldon.belog.install.bean.Database;
import me.beldon.belog.install.bean.Msg;
import me.beldon.belog.install.bean.WebMsg;
import me.beldon.boot.BootService;
import me.beldon.boot.IBoot;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by Beldon
 */
@Controller
public class IndexController extends BaseController implements IBoot {

    private BootService bootService;

    @Autowired
    protected ApplicationContext initContext;

    @PostConstruct
    public void init() {
        System.out.println("安装程序初始化成功...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("卸载...");
    }

    /**
     * 安装首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return getTemplatePath("index");
    }

    /**
     * 填写数据库信息
     *
     * @return
     */
    @RequestMapping("/database")
    public String dataBase() {
        return getTemplatePath("database");
    }

    /**
     * 数据库信息读取
     *
     * @param database 数据库信息
     * @param session  session
     * @return
     */
    @RequestMapping("/database.json")
    @ResponseBody
    public Msg dataBaseMsg(@ModelAttribute("database") Database database, HttpSession session) {
        Msg msg = new Msg();
        if (isAllNotEmtity(database.getDatabase(), database.getHost(), database.getDataUser(), database.getDataPass())) {
            msg = InstallUtils.checkDB(database);
            if (msg.getErrCode() == 0) {
                session.setAttribute("database", database);
            }
        } else {
            msg.setErrCode(-1);
            msg.setErrMsg("error");
        }

        return msg;
    }


    /**
     * 填写用户信息
     *
     * @return
     */
    @RequestMapping("/user")
    public String user() {
        return getTemplatePath("user");
    }

    @RequestMapping("/user.json")
    @ResponseBody
    public Msg userMsg(@ModelAttribute("webMsg") WebMsg webMsg, HttpSession session) {
        Msg msg = new Msg();
        if (isAllNotEmtity(webMsg.getSiteName(), webMsg.getUser(), webMsg.getEmail(), webMsg.getPass())) {
            msg.setStatus(true);
            msg.setErrCode(0);
            msg.setErrMsg("success");
            session.setAttribute("webMsg", webMsg);
        } else {
            msg.setErrCode(-1);
            msg.setErrMsg("error");
        }
        return msg;
    }

    /**
     * 安装
     *
     * @return
     */
    @RequestMapping("/install")
    public String install() {
        return getTemplatePath("install");
    }


    /**
     * 进行安装
     *
     * @param session
     * @return
     */
    @RequestMapping("/install.json")
    @ResponseBody
    public Msg doInstall(HttpSession session) {
        Msg msg = new Msg();

        Database database = (Database) session.getAttribute("database");
        WebMsg webMsg = (WebMsg) session.getAttribute("webMsg");
        if (database != null && webMsg != null) {
            InstallUtils.install(database, webMsg);
            InstallUtils.updateDB(database);
            msg.setStatus(true);
            msg.setErrMsg("success");
            msg.setErrCode(0);
            bootService.refreshContext();
        } else {
            msg.setErrMsg("请填写数据库信息");
            msg.setErrCode(-1);
        }

        return msg;
    }

    private boolean isAllNotEmtity(String... val) {
        for (String v : val) {
            if (StringUtils.isEmpty(v)) {
                return false;
            }
        }
        return true;
    }


    @RequestMapping("/ajax")
    public void ajax(long timed, HttpServletResponse response) throws Exception {
        PrintWriter writer = response.getWriter();
        Random rand = new Random();
        // 死循环 查询有无数据变化
        while (true) {
            Thread.sleep(300); // 休眠300毫秒，模拟处理业务等
            int i = rand.nextInt(100); // 产生一个0-100之间的随机数
            if (i > 20 && i < 56) { // 如果随机数在20-56之间就视为有效数据，模拟数据发生变化
                long responseTime = System.currentTimeMillis();
                // 返回数据信息，请求时间、返回数据时间、耗时
                writer.print("result: " + i + ", response time: " + responseTime + ", request time: " + timed + ", use time: " + (responseTime - timed));
                break; // 跳出循环，返回数据
            } else { // 模拟没有数据变化，将休眠 hold住连接
                Thread.sleep(1300);
            }
        }

    }

    public void setBootService(BootService bootService) {
        this.bootService = bootService;
    }
}
