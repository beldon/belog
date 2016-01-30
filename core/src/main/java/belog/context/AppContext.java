package belog.context;


import belog.pojo.PluginContent;
import belog.utils.ClassLoaderUtils;
import belog.utils.PluginUtils;
import me.beldon.boot.BootService;
import me.beldon.boot.IBoot;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Beldon
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AppContext implements IBoot {

    /**
     * 网站应用绝对路径获取的key
     */
    private static final String WEB_APP_ROOT_KEY = "belog.root";

    /**
     * 插件目录
     */
    String PLUGIN_ROOT = File.separator + "WEB-INF" + File.separator + "plugin" + File.separator;

    /**
     * 插件信息，key:插件id， value:插件信息
     */
    private Map<String, PluginContent> plugins = new HashMap<String, PluginContent>();

    private final Object lock = new Object();

//    @Autowired
    private BootService bootService;

    /**
     * 所有容器
     */
    @Autowired
    protected ApplicationContext context;

    /**
     * Web初始化时的容器
     */
    @Autowired
    protected ApplicationContext initContext;

    /**
     * 添加容器到容器链接中去
     *
     * @param applicationContext
     */
    public void addContext(ClassPathXmlApplicationContext applicationContext) {
        synchronized (lock) {
            if (context == null) {
                context = this.initContext;
            }
            applicationContext.setParent(context);
            applicationContext.refresh();

            context = applicationContext;
        }
    }

    /**
     * 根据容器的id获取容器
     */
    public ApplicationContext findContextById(String id) {
        ApplicationContext tempContext = context;
        while (true) {
            if (tempContext == initContext) {
                tempContext = null;
                break;
            }
            if (id.equals(context.getId())) {
                break;
            }
            tempContext = tempContext.getParent();
        }
        return tempContext;
    }


    /**
     * 根据ID移除Context
     *
     * @param id
     * @return
     */
    public ApplicationContext removeContextById(String id) {
        ApplicationContext tempContext = context;
        ApplicationContext frontContent = null;
        while (true) {
            if (tempContext == initContext) {
                tempContext = null;
                break;
            }
            if (id.equals(tempContext.getId())) { //找到要移除的Context
                if (frontContent == null) {//容器最尾端
                    context = context.getParent();
                    if (tempContext instanceof ClassPathXmlApplicationContext) {
                        ((ClassPathXmlApplicationContext) tempContext).destroy();
                    }
                    tempContext = null;
                } else {
                    if (tempContext instanceof ClassPathXmlApplicationContext) {
                        ClassPathXmlApplicationContext frontContextLink = (ClassPathXmlApplicationContext) frontContent;
                        frontContextLink.setParent(tempContext.getParent());
                        tempContext = null;
                        frontContextLink.refresh();
                        frontContextLink = null;
//                        contexts = frontContextLink;
                    }
                }
                break;
            }
            frontContent = tempContext;
            tempContext = tempContext.getParent();
        }
        return tempContext;
    }


    /**
     * 添加插件
     *
     * @param jarFile 插件文件
     * @return pluginId
     */
    public String addPlugin(File jarFile) {
        PluginContent pluginContent = PluginUtils.getPluginContent(jarFile);
        if (!StringUtils.isEmpty(pluginContent.getId()) && !StringUtils.isEmpty(pluginContent.getConfigPath())
                && !plugins.containsKey(pluginContent.getId().trim())) {
            synchronized (lock) {
                URL url = null;
                try {
                    url = jarFile.toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                URLClassLoader urlClassLoader = ClassLoaderUtils.loadUrlJar(url);
                ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
                context.setClassLoader(urlClassLoader);
                context.setConfigLocation(pluginContent.getConfigPath());
//                context.setConfigLocation("spring/spring-*.xml");
                context.setDisplayName(pluginContent.getName());
                context.setId(pluginContent.getId().trim());
                //添加容器
                this.addContext(context);
//                pluginMsg.setUrlClassLoader(urlClassLoader);
                plugins.put(pluginContent.getId(), pluginContent);
            }
        }
        return pluginContent.getId().trim();
    }

    /**
     * 移除插件
     *
     * @param pluginId
     */
    public void removePlugin(String pluginId) {
        plugins.remove(pluginId);
        this.removeContextById(pluginId);
    }

    /**
     * 判断插件是否加载
     *
     * @param pluginId
     * @return
     */
    public boolean isPluginLoad(String pluginId) {
        return plugins.containsKey(pluginId);
    }

    /**
     * 获取网站跟目录
     *
     * @return
     */
    public String getWebAppRoot() {
        return System.getProperty(WEB_APP_ROOT_KEY);
    }

    /**
     * 获取当前容器
     *
     * @return
     */
    public ApplicationContext getContexts() {
        if (context == null) {
            context = initContext;
        }
        return context;
    }

    public Object getBean(String name){
        Object bean = null;
        if (context == null) {
            context = initContext;
        }

        ApplicationContext temp = context;
        while (temp != null) {
            if (temp.containsBean(name)) {
                bean = temp.getBean(name);
                return bean;
            }
            temp = temp.getParent();
        }
        return bean;
    }

    /**
     * 获取初始化时的容器
     *
     * @return
     */
    public ApplicationContext getInitContext() {
        return initContext;
//        return bootService.getInitContext();
    }

    /**
     * 获取插件路径
     *
     * @return
     */
    public String getPluginRoot() {
        return System.getProperty(WEB_APP_ROOT_KEY) + PLUGIN_ROOT;
    }

    /**
     * 获取已经安装的插件
     * @return
     */
    public Map<String, PluginContent> getInstallPlugins(){
        return this.plugins;
    }

    /**
     * 根据插件id获取插件信息
     * @param pluginId 插件id
     * @return
     */
    public PluginContent getPluginById(String pluginId) {
        return this.plugins.get(pluginId);
    }

    public void setBootService(BootService bootService) {
//        this.bootService = bootService;
    }
}
