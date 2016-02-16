package belog.tag;

import belog.plugin.TagPlugin;
import belog.pojo.vo.ArticleVo;
import belog.pojo.vo.CategoryVo;
import belog.service.CategoryService;
import belog.utils.SSUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Beldon
 */
@Service
public class CategoryTag extends TagPlugin {

    @Autowired
    @Qualifier("CategoryService")
    private CategoryService categoryService;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String catIdO = SSUtils.nullToString(params.get("catId"), "");
        String typeO = SSUtils.nullToString(params.get("type"), "");
        //根据ID获取单个标签内容
        if (StringUtils.hasText(catIdO)) {
            Long catId = Long.parseLong(catIdO);
            CategoryVo categoryVo = categoryService.findById(catId);
            env.setVariable("category", beansWrapper.wrap(categoryVo));
        }else{
            List<CategoryVo> categoryVoList = categoryService.findCatByPid(0);
            env.setVariable("cats", beansWrapper.wrap(categoryVoList));
            body.render(env.getOut());
        }
    }
}
