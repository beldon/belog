package belog.tag;

import belog.plugin.TagPlugin;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * @author Beldon
 */
@Service
public class TestTag extends TagPlugin {
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
//        // 获取页面的参数
//        Integer pid = Integer.parseInt(params.get("pid").toString());
//        try {
//            List<Menu> menuList = menuService.findMenuByPid(pid);
//            env.setVariable("tag_sys_menu_list", beansWrapperBuilder.build().wrap(menuList));
//        } catch (Exception e) {
//            env.setVariable("tag_sys_menu_list", beansWrapperBuilder.build().wrap(null));
//        }
//
//        body.render(env.getOut());
    }
}
