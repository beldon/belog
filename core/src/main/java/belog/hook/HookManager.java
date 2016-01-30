package belog.hook;

import belog.context.AppContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * 钩子管理类
 * Created by Beldon
 */
public abstract class HookManager<T> {

    @Autowired
    protected AppContext appContext;

    /**
     * 钩子内容
     */
    protected Map<String, Set<T>> hookMap = new HashMap<String, Set<T>>();

    /**
     * 初始化，获取实现钩子的类
     */
//    @PostConstruct
    protected abstract void init();


    /**
     * 重新加载实现钩子的类
     */
    public void reload() {
        this.hookMap.clear();
        this.init();
    }


    /**
     * 添加一个钩子到钩子管理器管理中
     *
     * @param t 钩子
     */
    public void addHook(T t) {
        String key = t.getClass().getName();
        addHook(key, t);
    }

    /**
     * 添加一个钩子到钩子管理器中
     *
     * @param key key
     * @param t   钩子
     */
    public void addHook(String key, T t) {
        if (!this.hookMap.containsKey(key)) {
            Set<T> hooks = new HashSet<T>();
            hooks.add(t);
            this.hookMap.put(key, hooks);
        } else {
            this.hookMap.get(key).add(t);
        }
    }

    /**
     * 移除一个钩子
     *
     * @param key 钩子对应的key
     */
    public void removeHook(String key) {
//        Iterator<Map.Entry<String, Set<T>>> it = hookMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, Set<T>> entry = it.next();
//            entry.getValue().remove(key);
//        }
    }

    /**
     * 移除一个钩子
     *
     * @param t 钩子类
     */
    public void removeHook(T t) {
        Iterator<Map.Entry<String, Set<T>>> it = hookMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Set<T>> entry = it.next();
            entry.getValue().remove(t);
        }
    }

}
