package belog.front;


import belog.context.AppContext;
import belog.service.ThemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by Beldon
 */
@Controller("frontFrontBaseController")
public abstract class FrontBaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected ThemeService themeService;

    @Autowired
    protected AppContext appContext;

    @PostConstruct
    private void init() {
        System.out.println("初始化：" + this.getClass().getName());
    }

    protected Boolean isExist(String theme) {
        String themePath = "/template/theme/"
                + themeService.getThemeName()
                + "/" + theme + ".ftl";
        File file = new File(appContext.getWebAppRoot() + themePath);
        if (file.exists()) {
            logger.info("尝试使用模板：" + themePath+"【存在】");
            return true;
        } else {
            logger.info("尝试使用模板：" + themePath+"【不存在】");
            return false;
        }

    }


}
