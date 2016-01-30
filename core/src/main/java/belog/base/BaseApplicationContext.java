package belog.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @TODO 添加容器插件的版本信息
 * Belog容器管理类
 * Created by Beldon
 */
public class BaseApplicationContext extends ClassPathXmlApplicationContext {
    public BaseApplicationContext() {
    }

    public BaseApplicationContext(ApplicationContext parent) {
        super(parent);
    }

    public BaseApplicationContext(String configLocation) throws BeansException {
        super(configLocation);
    }

    public BaseApplicationContext(String[] configLocations, ApplicationContext parent) throws BeansException {
        super(configLocations, parent);
    }
}
