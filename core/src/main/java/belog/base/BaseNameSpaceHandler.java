package belog.base;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Beldon
 */
public class BaseNameSpaceHandler extends NamespaceHandlerSupport {
    public void init() {
        registerBeanDefinitionParser("plugin", new PluginContentDefinitionParser());
    }
}
