package belog.tag;


import belog.plugin.TagPlugin;
import belog.pojo.PageModel;
import belog.pojo.vo.ArticleVo;
import belog.service.ArticleService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * 文章类标签
 * Created by Beldon
 */
@Service
public class ArticleTag extends TagPlugin {

    @Autowired
    private ArticleService articleService;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        Object aId = params.get("articleId");
        Object typeO = params.get("type");

        //根据ID获取单个文章内容
        if (aId != null) {
            Long articleId = Long.parseLong(aId.toString());
            singleArticle(articleId, env);
        }

        //分页列表文章列表
        if (typeO != null && "list".equals(typeO.toString())) {
            int currentPage = 1;
            int pageSize = 8;
            Object currentPageO = params.get("currentPage");
            Object pageSizeO = params.get("pageSize");
            if (currentPageO != null) {
                currentPage = Integer.parseInt(currentPageO.toString());
            }

            if (pageSizeO !=null) {
                pageSize = Integer.parseInt(pageSizeO.toString());
            }

            String type = typeO.toString();
            if ("list".equals(type)) { //普通列表
                articleList(currentPage, pageSize, env);
            } else if ("cat".equals(type)) { //分类列表
                PageModel pageModel = new PageModel();
                pageModel.setCurrentPage(currentPage);
                pageModel.setPageSize(pageSize);
                pageModel = articleService.findPageByCatId(1,pageModel,"new");
                env.setVariable("pm", beansWrapper.wrap(pageModel));
            }
        }

        body.render(env.getOut());
    }


    /**
     * 显示文章列表
     *
     * @param pageModel
     * @param env
     */
    private void articleList(PageModel pageModel, Environment env) throws TemplateModelException {
        pageModel = articleService.findPage(pageModel);
        env.setVariable("pm", beansWrapper.wrap(pageModel));
    }

    private void articleList(int currentPage, int pageSize, Environment environment) throws TemplateModelException {
        PageModel pageModel = new PageModel();
        pageModel.setCurrentPage(currentPage);
        pageModel.setPageSize(pageSize);
        articleList(pageModel, environment);
    }

    /**
     * 显示单个文章内容列表
     *
     * @param id
     * @param environment
     */
    private void singleArticle(long id, Environment environment) throws TemplateModelException {
        ArticleVo article = articleService.findById(id);
        environment.setVariable("article", beansWrapper.wrap(article));
    }
}
