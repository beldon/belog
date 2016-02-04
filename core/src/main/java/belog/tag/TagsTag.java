package belog.tag;

import belog.plugin.TagPlugin;
import belog.pojo.vo.TagVo;
import belog.service.TagService;
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
 * 文章标签类标签
 * Created by Beldon
 */
@Service
public class TagsTag extends TagPlugin{

    @Autowired
    private TagService tagService;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        List<TagVo> tagVos = tagService.getAllTag();
        env.setVariable("tags", beansWrapper.wrap(tagVos));
        body.render(env.getOut());
    }
}
