package belog.tag;


import belog.plugin.TagPlugin;
import belog.pojo.Page;
import belog.pojo.PageModel;
import belog.pojo.vo.ArticleVo;
import belog.service.ArticleService;
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
 * 文章类标签
 * Created by Beldon
 */
@Service
public class ArticleTag extends TagPlugin {

    @Autowired
    private ArticleService articleService;

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String aId = SSUtils.nullToString(params.get("articleId"), "");
        String typeO = SSUtils.nullToString(params.get("type"), "");


        //根据ID获取单个文章内容
        if (StringUtils.hasText(aId)) {
            Long articleId = Long.parseLong(aId.toString());
            ArticleVo article = articleService.findById(articleId);
            env.setVariable("article", beansWrapper.wrap(article));
        } else {//批量获取文章内容
            int currentPage = 1;
            int pageSize = 8;
            Object currentPageO = params.get("pageNo");
            Object pageSizeO = params.get("pageSize");
            if (currentPageO != null) {
                currentPage = Integer.parseInt(currentPageO.toString());
            }
            if (pageSizeO != null) {
                pageSize = Integer.parseInt(pageSizeO.toString());
            }
            Page<ArticleVo> page = new Page<ArticleVo>();
            page.setPageNo(currentPage);
            page.setPageSize(pageSize);

            if ("list".equals(typeO)) { //普通列表
                page = articleService.findPage(page);
                env.setVariable("articlePage", beansWrapper.wrap(page));
            } else if ("cat".equals(typeO)) { //分类列表
                long catId = SSUtils.nullToLong(params.get("catId"), 1l);
                page = articleService.findPageByCatId(catId, page, "new");
                env.setVariable("articlePage", beansWrapper.wrap(page));
            } else if ("tag".equals(typeO)) {
                String tag = SSUtils.nullToString(params.get("tag"), "tag");
                page = articleService.findPageByTag(tag, page, "new");
                env.setVariable("articlePage", beansWrapper.wrap(page));
            }
        }

        body.render(env.getOut());
    }
}
