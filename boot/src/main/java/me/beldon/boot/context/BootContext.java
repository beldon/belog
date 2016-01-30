package me.beldon.boot.context;

import me.beldon.boot.BootFilterProxy;
import me.beldon.boot.BootService;
import me.beldon.boot.BootServlet;
import me.beldon.boot.IBoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.ServletException;
import java.util.Map;

/**
 * @author Beldon
 */
public class BootContext {

    protected BootServlet bootServlet;

    protected BootFilterProxy bootFilterProxy;

    private final Object lock = new Object();

    /**
     * 程序所有容器
     */
    protected ApplicationContext contexts;

    /**
     * Spring初始化时的容器
     */
    @Autowired
    protected ApplicationContext initContext;


    protected void addWebContext(XmlWebApplicationContext applicationContext) {
        synchronized (lock) {
            XmlWebApplicationContext webApplicationContext = ((XmlWebApplicationContext) initContext);
            if (contexts == null) {
                contexts = this.initContext;
            }
            BootService bootService = (BootService) webApplicationContext.getBean("bootService");
            XmlWebApplicationContext parent = (XmlWebApplicationContext) webApplicationContext.getParent();
            if (parent != null) {
                applicationContext.setServletContext(parent.getServletContext());
                applicationContext.afterPropertiesSet();
                parent.destroy();
            } else {
                applicationContext.setServletContext(webApplicationContext.getServletContext());
                applicationContext.afterPropertiesSet();
            }
            Map<String, IBoot> map = applicationContext.getBeansOfType(IBoot.class);
            for (Map.Entry<String, IBoot> entry : map.entrySet()) {
                entry.getValue().setBootService(bootService);
            }
            webApplicationContext.setParent(applicationContext);
            if ("webAppId".equals(webApplicationContext.getParent().getId()) && bootServlet != null) {
                this.bootServlet.onRefresh(webApplicationContext);
                try {
                    this.bootFilterProxy.initFilterBean();
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            } else {
                webApplicationContext.afterPropertiesSet();
            }

            contexts = applicationContext;

        }
    }


    /**
     * 获取当前容器
     *
     * @return
     */
    public ApplicationContext getContexts() {
        if (contexts == null) {
            contexts = initContext;
        }
        return contexts;
    }

    /**
     * 获取初始化时的容器
     *
     * @return
     */
    public ApplicationContext getInitContext() {
        return initContext;
    }


}
