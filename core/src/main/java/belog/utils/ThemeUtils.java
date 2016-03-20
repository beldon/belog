package belog.utils;

import belog.context.PluginContext;
import belog.plugin.Plugin;
import belog.pojo.PluginConfig;
import belog.pojo.ThemeConfig;
import belog.pojo.vo.ThemeVo;
import belog.service.ThemeService;
import org.springframework.beans.BeanUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.dc.pr.PRError;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * 主题工具类
 * Created by Beldon
 */
public class ThemeUtils {

    /**
     * 获取主题信息信息
     *
     * @param themePropertiesFile 主题配置文件
     * @return
     */
    public static Document getThemeContentDocument(File themePropertiesFile) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream is = new FileInputStream(themePropertiesFile);
            document = db.parse(is);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static ThemeVo getThemeContent(String themePropertiesFilePath) {
        File file = new File(themePropertiesFilePath);
        ThemeVo themeVo = new ThemeVo();
        if (file.exists()) {
            Document document = getThemeContentDocument(file);
            Element docElement = document.getDocumentElement();
            String name = getElementValue(docElement, ThemeService.NAME);
            String author = getElementValue(docElement, ThemeService.AUTHOR);
            String url = getElementValue(docElement, ThemeService.URL);
            String logo = getElementValue(docElement, ThemeService.LOGO);
            String email = getElementValue(docElement, ThemeService.EMAIL);
            String version = getElementValue(docElement, ThemeService.VERSION);
            String description = getElementValue(docElement, ThemeService.DESCRIPTION);

            PluginConfig config = PluginUtils.getConfigValue(docElement);
            if (config != null) {
                ThemeConfig themeConfig = new ThemeConfig();
                BeanUtils.copyProperties(config, themeConfig);
                themeVo.setConfig(themeConfig);
            }

            themeVo.setName(name);
            themeVo.setAuthor(author);
            themeVo.setUrl(url);
            themeVo.setLogo(logo);
            themeVo.setVersion(version);
            themeVo.setEmail(email);
            themeVo.setDescription(description);
        }
        return themeVo;
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
        return "";
    }
}
