package belog.tag;

import belog.plugin.TagPlugin;
import belog.pojo.vo.CategoryVo;
import belog.service.CategoryService;
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
 * Created by Beldon
 */
@Service
public class CategoryTag extends TagPlugin {

    @Autowired
    private CategoryService categoryService;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        List<CategoryVo> categoryVoList = categoryService.findCatByPid(0);
        env.setVariable("cats", beansWrapper.wrap(categoryVoList));

        body.render(env.getOut());
    }
}
