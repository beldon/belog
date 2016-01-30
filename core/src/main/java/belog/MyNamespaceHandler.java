package belog;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Beldon
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
        registerBeanDefinitionParser("config", new PeopleBeanDefinitionParser());
    }
}
