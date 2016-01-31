package me.beldon.boot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.HashMap;
import java.util.Map;

/**
 * 容器启动启动引导的Servlet，替换Spring的DispatcherServlet，配置如下:
 * <p>
 * <servlet>
 * <servlet-name>belog</servlet-name>
 * <servlet-class>me.beldon.boot.BootServlet</servlet-class>
 * <init-param>
 * <param-name>contextConfigLocation</param-name>
 * <param-value></param-value>
 * </init-param>
 * <load-on-startup>1</load-on-startup>
 * </servlet>
 * <servlet-mapping>
 * <servlet-name>belog</servlet-name>
 * <url-pattern>/</url-pattern>
 * </servlet-mapping>
 *
 * @author Beldon
 */
public class BootServlet extends DispatcherServlet {

    private XmlWebApplicationContext rootContext = null;


    /**
     * 刷新容器
     *
     * @param context，要刷新的容器，在web环境中，context应为
     * @see XmlWebApplicationContext
     * 对象
     */
    @Override
    public void onRefresh(ApplicationContext context) {
        if (rootContext != null) {
            rootContext.setParent(context);
            initStrategies(rootContext);
        }else{
            initStrategies(context);
        }
    }

    /**
     * 初始化
     * initMultipartResolver(context); //文件上传解析，如果请求类型是multipart将通过MultipartResolver进行文件上传解析；
     * initLocaleResolver(context);    //本地化解析
     * initThemeResolver(context);　　　//主题解析
     * initHandlerMappings(context);   //通过HandlerMapping，将请求映射到处理器
     * initHandlerAdapters(context);   //通过HandlerAdapter支持多种类型的处理器
     * initHandlerExceptionResolvers(context); //如果执行过程中遇到异常将交给HandlerExceptionResolver来解析
     * initRequestToViewNameTranslator(context); //直接解析请求到视图名
     * initViewResolvers(context);      //通过ViewResolver解析逻辑视图名到具体视图实现
     * initFlashMapManager(context);   //flash映射管理器
     *
     * @param context 要刷新的容器，在web环境中，context应为
     * @see XmlWebApplicationContext
     * 对象
     */
    @Override
    protected void initStrategies(ApplicationContext context) {
        if (rootContext == null) {
            rootContext = (XmlWebApplicationContext) context;
        }
        Map<String, RootServlet> map = new HashMap<String, RootServlet>();
        ApplicationContext tempContext = rootContext;
        while (tempContext.getParent() != null) {
            Map<String, RootServlet> tempMap = context.getBeansOfType(RootServlet.class);
            map.putAll(tempMap);
            tempContext = tempContext.getParent();
        }
        if (context.containsBean("bootService")) {
            RootServlet bootServlet = (RootServlet) context.getBean("bootService");
            bootServlet.setBootServlet(this);
        }

        for (Map.Entry<String, RootServlet> entry : map.entrySet()) {
            entry.getValue().setBootServlet(this);
        }
        super.initStrategies(context);
    }


    /**
     * 为获取BootServlet对象的接口，继承该接口的类会赋予 BootServlet对象
     *
     * @see BootServlet#initStrategies(ApplicationContext)
     */
    public interface RootServlet {
        void setBootServlet(BootServlet bootServlet);
    }
}
