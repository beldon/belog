package belog.tag;

import belog.plugin.TagPlugin;
import belog.service.UserService;
import belog.utils.SSUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Beldon
 */
@Service
public class UserTag extends TagPlugin {

    @Autowired
    private UserService userService;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String typeO = SSUtils.nullToString(params.get("type"), "");
        if (StringUtils.hasText(typeO)) {
            if ("username".endsWith(typeO)) {
                env.setVariable("username", beansWrapper.wrap(userService.getCurrentUserName()));
            }
        }else{//获取当前用户信息
            env.setVariable("user", beansWrapper.wrap(userService.getCurrentUser()));
        }
        body.render(env.getOut());
    }
}
