package belog.base;


import belog.context.PluginContext;
import belog.pojo.PluginConfig;
import belog.pojo.PluginContent;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beldon
 */
public class PluginContentDefinitionParser extends AbstractSimpleBeanDefinitionParser {


    @Override
    protected Class<?> getBeanClass(Element element) {
        return PluginContent.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        super.doParse(element, builder);
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        builder.addPropertyValue(PluginContext.ID, getElementValue(element, PluginContext.ID));
        builder.addPropertyValue(PluginContext.NAME, getElementValue(element, PluginContext.NAME));
        builder.addPropertyValue(PluginContext.VERSION, getElementValue(element, PluginContext.VERSION));
        builder.addPropertyValue(PluginContext.AUTHOR, getElementValue(element, PluginContext.AUTHOR));
        builder.addPropertyValue(PluginContext.EMAIL, getElementValue(element, PluginContext.EMAIL));
        builder.addPropertyValue(PluginContext.URL, getElementValue(element, PluginContext.URL));
        builder.addPropertyValue(PluginContext.CONFIG, getConfigValue(element));
        super.doParse(element, parserContext, builder);
    }

    @Override
    protected String resolveId(Element element, AbstractBeanDefinition definition, ParserContext parserContext) throws BeanDefinitionStoreException {
        return getElementValue(element, PluginContext.ID);
    }

    /**
     * 获取Element指定标签值
     *
     * @param element   指定element
     * @param localName 标签名
     * @return
     */
    private String getElementValue(Element element, String localName) {
        NodeList nodeList = element.getElementsByTagNameNS(element.getNamespaceURI(), localName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }

    /**
     * 获取插件配置信息
     *
     * @param element
     * @return
     */
    private PluginConfig getConfigValue(Element element) {
        PluginConfig config = null;
        NodeList nodeList = element.getElementsByTagNameNS(element.getNamespaceURI(), PluginContext.CONFIG);
        if (nodeList.getLength() > 0) {
            config = new PluginConfig();
            List<PluginConfig.Element> elements = new ArrayList<PluginConfig.Element>();

            Element node = (Element) nodeList.item(0);
            NodeList childNodes = node.getElementsByTagNameNS(element.getNamespaceURI(), PluginContext.ELEMENT);
            for (int i = 0; i < childNodes.getLength(); i++) {
                PluginConfig.Element ele = new PluginConfig.Element();
                Element item = (Element) childNodes.item(i);
                String key = item.getAttribute(PluginContext.KEY);
                String type = item.getAttribute(PluginContext.TYPE);
                ele.setKey(key);
                ele.setType(type);
                if (item.hasChildNodes()) {
                    List<PluginConfig.MetaContent> metaContents = new ArrayList<PluginConfig.MetaContent>();
                    NodeList metas = item.getElementsByTagNameNS(element.getNamespaceURI(), PluginContext.META_CONTENT);
                    for (int j = 0;j<metas.getLength();j++) {
                        PluginConfig.MetaContent metaContent = new PluginConfig.MetaContent();
                        Element meta = (Element) metas.item(j);
                        String value = meta.getAttribute(PluginContext.VALUE);
                        String text = meta.getAttribute(PluginContext.TEXT);
                        metaContent.setText(text);
                        metaContent.setValue(value);
                        metaContents.add(metaContent);
                    }
                    ele.setMeta(metaContents);
                }

                elements.add(ele);
            }
            config.setElements(elements);
        }
        return config;
    }
}
