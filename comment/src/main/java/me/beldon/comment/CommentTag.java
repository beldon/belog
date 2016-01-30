package me.beldon.comment;

import belog.plugin.TagPlugin;
import belog.pojo.vo.ConfigVo;
import belog.service.ConfigService;
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
@Service("commentTag")
public class CommentTag extends TagPlugin {

    @Autowired(required = false)
    private ConfigService configService;


    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        if (configService != null) {
            ConfigVo switchVo = configService.findByName(Config.SWITCH);
            ConfigVo codeVo = configService.findByName(Config.CODE);

            if (StringUtils.hasText(switchVo.getValue()) && Config.ON.equals(switchVo.getValue())) {
                String code = codeVo.getValue();
                env.setVariable("code", beansWrapper.wrap(code));
            }
            body.render(env.getOut());
        }
    }
}
