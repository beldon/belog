package belog.utils;


import belog.context.PluginContext;
import belog.pojo.PluginConfig;
import belog.pojo.PluginContent;
import belog.pojo.PluginMsg;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * 插件工具类
 * Created by Beldon
 */
public class PluginUtils {

    /**
     * 获取插件信息
     *
     * @param jarPath jar包路径
     * @return Properties
     */
    public static Properties getPluginMsgProp(String jarPath) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(jarPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getPluginMsgProp(jarFile);
    }

    /**
     * 获取插件信息
     *
     * @param jarFile jarFile文件
     * @return Properties
     */
    public static Properties getPluginMsgProp(JarFile jarFile) {
        Properties properties = null;
        try {
            ZipEntry zipEntry = jarFile.getEntry(PluginContext.PLUGIN_FILE);
            if (zipEntry != null) {
                properties = new Properties();
                InputStream is = jarFile.getInputStream(zipEntry);
                properties.load(new InputStreamReader(is, "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 获取插件信息
     *
     * @param file 插件文件
     * @return Properties
     */
    public static Properties getPluginMsgProp(File file) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getPluginMsgProp(jarFile);
    }

    /**
     * 获取插件信息
     *
     * @param jarFile 插件文件
     * @return PluginMsg
     */
    public static PluginMsg getPluginMsg(JarFile jarFile) {
        Properties properties = getPluginMsgProp(jarFile);
        return propToBean(properties);
    }

    /**
     * @param jarFile 插件文件
     * @return PluginMsg
     */
    public static PluginMsg getPluginMsg(File jarFile) {
        Properties properties = getPluginMsgProp(jarFile);
        return propToBean(properties);
    }

    /**
     * @param jarFilePath 插件文件
     * @return PluginMsg
     */
    public static PluginMsg getPluginMsg(String jarFilePath) {
        Properties properties = getPluginMsgProp(jarFilePath);
        return propToBean(properties);
    }


    /**
     * 插件 properties 转bean
     *
     * @param properties 插件内容
     * @return PluginMsg
     */
    private static PluginMsg propToBean(Properties properties) {
        PluginMsg pluginMsg = new PluginMsg();
        pluginMsg.setId(properties.getProperty(PluginContext.PLUGIN_UNIQUE_ID));
        pluginMsg.setName(properties.getProperty(PluginContext.PLUGIN_NAME));
        pluginMsg.setConfigPath(properties.getProperty(PluginContext.PLUGIN_CONFIG_PATH));
        pluginMsg.setVersion(properties.getProperty(PluginContext.PLUGIN_VERSION));
        pluginMsg.setAuthor(properties.getProperty(PluginContext.PLUGIN_AUTHOR));
        pluginMsg.setEmail(properties.getProperty(PluginContext.PLUGIN_EMAIL));
        pluginMsg.setUrl(properties.getProperty(PluginContext.PLUGIN_NAME));
        return pluginMsg;
    }


    /**
     * 获取插件信息
     * @param jarFile
     * @return
     */
    public static Document getPluginContentDocument(JarFile jarFile) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            ZipEntry zipEntry = jarFile.getEntry("plugin.xml");
            if (zipEntry != null) {
                InputStream is = jarFile.getInputStream(zipEntry);
                document = db.parse(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return document;
    }


    /**
     * 获取插件信息
     * @param file
     * @return
     */
    public static PluginContent getPluginContent(File file) {
        JarFile jarFile = null;
        try {
            jarFile = new JarFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getPluginContent(jarFile);
    }

    /**
     * 获取插件信息
     * @param jarFile
     * @return
     */
    public static PluginContent getPluginContent(JarFile jarFile){
        Document document = getPluginContentDocument(jarFile);
        Element docElement = document.getDocumentElement();
        PluginContent pluginContent = new PluginContent();
        String id = getElementValue(docElement, PluginContext.ID);
        String name = getElementValue(docElement, PluginContext.NAME);
        String configPath = getElementValue(docElement, PluginContext.CONFIG_PATH);
        String version = getElementValue(docElement, PluginContext.VERSION);
        String author = getElementValue(docElement, PluginContext.AUTHOR);
        String email = getElementValue(docElement, PluginContext.EMAIL);
        String url = getElementValue(docElement, PluginContext.URL);
        PluginConfig config = getConfigValue(docElement);
        pluginContent.setId(id);
        pluginContent.setName(name);
        pluginContent.setConfigPath(configPath);
        pluginContent.setVersion(version);
        pluginContent.setAuthor(author);
        pluginContent.setEmail(email);
        pluginContent.setUrl(url);
        pluginContent.setConfig(config);
        return pluginContent;
    }

    /**
     * 获取插件元素信息
     * @param element
     * @param tagName
     * @return
     */
    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
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
    private static PluginConfig getConfigValue(Element element) {
        PluginConfig config = null;
        NodeList nodeList = element.getElementsByTagName(PluginContext.CONFIG);
        if (nodeList.getLength() > 0) {
            config = new PluginConfig();
            List<PluginConfig.Element> elements = new ArrayList<PluginConfig.Element>();

            Element node = (Element) nodeList.item(0);
            NodeList childNodes = node.getElementsByTagName(PluginContext.ELEMENT);
            for (int i = 0; i < childNodes.getLength(); i++) {
                PluginConfig.Element ele = new PluginConfig.Element();
                Element item = (Element) childNodes.item(i);
                String key = item.getAttribute(PluginContext.KEY);
                String type = item.getAttribute(PluginContext.TYPE);
                String label = item.getAttribute(PluginContext.LABEL);
                ele.setKey(key);
                ele.setType(type);
                ele.setLabel(label);

                if (item.hasChildNodes()) {
                    List<PluginConfig.MetaContent> metaContents = new ArrayList<PluginConfig.MetaContent>();
                    NodeList metas = item.getElementsByTagName(PluginContext.META_CONTENT);
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
