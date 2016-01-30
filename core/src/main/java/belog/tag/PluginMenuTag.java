package belog.tag;

import belog.plugin.TagPlugin;
import belog.pojo.PluginContent;
import belog.service.PluginService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 插件菜单
 * Created by Beldon
 */
@Service
public class PluginMenuTag extends TagPlugin {

    @Autowired
    private PluginService pluginService;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        List<PluginContent> list =  pluginService.getPluginsWithConfig();
        env.setVariable("menus", beansWrapper.wrap(list));
        body.render(env.getOut());
    }
}
