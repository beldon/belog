package belog.plugin;

import belog.utils.SSUtils;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModelException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public abstract class TagPlugin extends ApplicationObjectSupport implements TemplateDirectiveModel, Plugin {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected BeansWrapperBuilder beansWrapperBuilder;

    protected BeansWrapper beansWrapper;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    /**
     * 初始化标签
     *
     * @throws TemplateModelException
     */
    @PostConstruct
    public void init() throws TemplateModelException {
        setTag(this);
        logger.info("创建【" + getTagName() + "】标签");
    }

    /**
     * 卸载标签
     *
     * @throws TemplateModelException
     */
    @PreDestroy
    private void destroy() throws TemplateModelException {
        setTag(null);
        logger.info("卸载【" + getTagName() + "】标签");
    }

    /**
     * 返回标签名称
     *
     * @return tagName
     */
    private String getTagName() {
        String className = this.getClass().getSimpleName();
        String beanName = StringUtils.uncapitalize(className);
        String tagName = SSUtils.toUnderline(beanName);
        return tagName;
    }

    /**
     * 设置标签
     *
     * @param object
     * @throws TemplateModelException
     */
    private void setTag(Object object) throws TemplateModelException {
        String tagName = getTagName();
        freeMarkerConfigurer.getConfiguration().setSharedVariable(tagName, object);
        freeMarkerConfigurer.getConfiguration().removeAutoInclude(tagName);
        beansWrapperBuilder = new BeansWrapperBuilder(Configuration.VERSION_2_3_22);
        beansWrapper = beansWrapperBuilder.build();
    }

}
