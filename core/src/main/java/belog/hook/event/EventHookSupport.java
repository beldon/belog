package belog.hook.event;

import belog.context.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;

/**
 * Event钩子支持类
 *
 * @author Beldon
 */
@Component
public abstract class EventHookSupport<T extends ApplicationEvent> implements EventHook<T> {
    @Autowired
    protected EventHookManager eventHookManager;

    private final Class<T> eventClass;

    @Autowired
    private AppContext appContext;

    protected Map<String, EventListener> plugins;

    public EventHookSupport() {
        this.eventClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 初始化，编写Event钩子时，应该编写此方法，方法应有以下内容
     * <p>
     * 如：this.plugins = getPlugins(FeedsEventListener.class);
     * <p>
     * 其中FeedsEventListener是继承EventListener的接口，拥有区分一类钩子下的所有插件
     */
//    @PostConstruct
    public abstract void init();

    /**
     * 程序加载实现钩子的插件
     */
    public void reload() {
        init();
    }

    /**
     * 钩子注销
     */
    @PreDestroy
    public void destroy() {
        eventHookManager.removeHook(this);
    }

    /**
     * 获取泛型中的类型，用于区分该钩子是处理哪个Event，顾编写钩子的时候理应用泛型
     * <p>
     * 如：public class FeedsEventHook extends EventHookSupport<FeedsEvent>
     *
     * @return
     */
    public Class getEventClass() {
        Class clazz = getClass();
        while (clazz != Object.class) {
            Type t = clazz.getGenericSuperclass();
            if (t instanceof ParameterizedType) {
                Type[] args = ((ParameterizedType) t).getActualTypeArguments();
                if (args[0] instanceof Class) {
                    clazz = (Class<T>) args[0];
                    break;
                }
            }
            clazz = clazz.getSuperclass();
        }
        return clazz;
    }

    /**
     * 获取此钩子下的所有插件,调用此方法时会获取所有Event钩子的插件
     *
     * @return
     */
    protected Map<String, EventListener> getPlugins() {
        return getPlugins(EventListener.class);
    }

    /**
     * 获取钩子
     *
     * @param clazz
     * @return
     */
    public Map<String, EventListener> getPlugins(Class clazz) {
        return appContext.getContexts().getBeansOfType(clazz);
    }

    protected void onEvent(ApplicationEvent event) {
        Iterator<Map.Entry<String, EventListener>> it = this.plugins.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().onEvent(event);
        }
    }

    /**
     * 插件接口
     *
     * @param <T>
     */
    public interface EventListener<T extends ApplicationEvent> {
        /**
         * Event触发后的方法
         *
         * @param event
         */
        void onEvent(T event);
    }
}
