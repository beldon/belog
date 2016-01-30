package belog.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

/**
 * @author Beldon
 */
@Controller("adminAdminBaseController")
public abstract class AdminBaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init() {
        logger.info("Controller::" + this.getClass().getName() + "初始化成功");
    }

    public String getTemplatePath(String template) {
        return "admin/" + template;
    }
}
