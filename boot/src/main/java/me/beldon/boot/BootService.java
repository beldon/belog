package me.beldon.boot;

import me.beldon.boot.context.BootContext;
import me.beldon.boot.util.ClassLoaderUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Properties;

/**
 * 开始引导Service
 * Created by Beldon
 */
@Service("bootService")
public class BootService extends BootContext implements BootServlet.RootServlet, BootFilterProxy.BootFilter {
    @Value("#{bootReader}")
    private Properties bootConfig;
    private static String WEB_APP_ID = "webAppId";
    private static String INSTALL_APP = "installAPP";

    public BootService() {
    }


    /**
     * Boot启动后其他
     */
    @PostConstruct
    private void init() {
        if ("true".equals(bootConfig.getProperty("boot"))) {
            String path = this.getClass().getClassLoader().getResource("/").getPath();
            File classPath = new File(path);
            String lockPath = classPath.getParent() + File.separator + "install.lock";
            File lockFile = new File(lockPath);

            if (lockFile.exists()) {
                System.out.println("系统开始加载Web组件...");
                String rootPath = classPath.getParent() + File.separator + bootConfig.getProperty("rootDir", "root");
                List<File> list = (List<File>) FileUtils.listFiles(new File(rootPath), new String[]{"jar"}, true);
                URLClassLoader urlClassLoader = ClassLoaderUtils.loadJar(list);
                String config = bootConfig.getProperty("rootConfig", "spring/applicationContext.xml");
                XmlWebApplicationContext context = new XmlWebApplicationContext();
                context.setClassLoader(urlClassLoader);
                context.setConfigLocation("classpath:" + config.trim());
                context.setDisplayName("webApp");
                context.setId(WEB_APP_ID);
                addWebContext(context);
                System.out.println("Web组件系统加载完毕...");
            } else {//安装程序
//                String installPath = classPath.getParent() + File.separator + bootConfig.getProperty("installDir", "install");
//                List<File> list = (List<File>) FileUtils.listFiles(new File(installPath), new String[]{"jar"}, true);
//                URLClassLoader classLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
//                for (File jarFile : list) {
//                    try {
//                        ClassLoaderUtils.addUrl(classLoader, jarFile.toURI().toURL());
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//                }

                System.out.println("系统安装程序启动中...");
                XmlWebApplicationContext installContent = new XmlWebApplicationContext();

                installContent.setClassLoader(Thread.currentThread().getContextClassLoader());

                String config = bootConfig.getProperty("installConfig", "spring/spring-install.xml");
                installContent.setConfigLocation("classpath:" + config.trim());
                installContent.setDisplayName("installApp");
                installContent.setId(INSTALL_APP);
                addWebContext(installContent);
                System.out.println("系统安装程序启动完毕...");
            }

        }
    }

    /**
     * 刷新容器
     */
    public void refreshContext() {
        destroyContext();
    }

    /**
     * 处理初始化时的容器外，其他容器全部destroy
     */
    public void destroyContext() {
        contexts = null;
        init();
    }


    @PreDestroy
    public void destroyAll() {
        ((XmlWebApplicationContext) initContext).destroy();
    }

    public void setBootServlet(BootServlet bootServlet) {
        this.bootServlet = bootServlet;
    }

    public void setBootFilter(BootFilterProxy bootFilterProxy) {
        this.bootFilterProxy = bootFilterProxy;
    }
}
