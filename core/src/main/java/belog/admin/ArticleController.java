package belog.admin;


import belog.pojo.Msg;
import belog.pojo.PageModel;
import belog.pojo.vo.ArticleVo;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.Categorys;
import belog.pojo.vo.TagVo;
import belog.service.ArticleService;
import belog.service.CategoryService;
import belog.utils.MsgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Beldon
 */
@Controller("adminArticleController")
@RequestMapping("/admin/article")
public class ArticleController extends AdminBaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("pageModel") PageModel pageModel, Model model) {
        PageModel pm = articleService.findPage(pageModel);
        model.addAttribute("pm", pm);
        return getTemplatePath("article/list");
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(defaultValue = "0", required = false) long id, Model model) {
        if (id != 0) {
            ArticleVo articleVo = articleService.findById(id);
            model.addAttribute("article", articleVo);
        }
        List<Categorys> cats = categoryService.findCat();
        model.addAttribute("cats", cats);
        return getTemplatePath("article/edit");
    }

    @RequestMapping("/ajaxEdit.json")
    @ResponseBody
    public Msg ajaxEdit(@ModelAttribute("article") ArticleVo articleVo, @RequestParam(required = false) String tags, @RequestParam(required = false) long[] catId) {
        List<CategoryVo> cats = new ArrayList<CategoryVo>();
        List<TagVo> tagVos = new ArrayList<TagVo>();

        //分类
        if (catId != null) {
            for (long id : catId) {
                CategoryVo categoryVo = new CategoryVo();
                categoryVo.setId(id);
                cats.add(categoryVo);
            }
        }
        //标签
        if (StringUtils.hasText(tags)) {
            String[] ts = tags.split(",");
            for (String tag : ts) {
                if (StringUtils.hasText(tag)) {
                    TagVo tagVo = new TagVo();
                    tagVo.setName(tag.trim());
                    tagVos.add(tagVo);
                }
            }
        }
        articleVo.setCats(cats);
        articleVo.setTagVos(tagVos);
        articleService.addOrUpdate(articleVo);
        return MsgUtils.success();
    }

    @RequestMapping("/delete.json")
    @ResponseBody
    public Msg delete(long id) {
        if (0 == id) {
            return MsgUtils.error("请填入文章内容");
        }
        articleService.delete(id);
        return MsgUtils.success();
    }


    /**
     * 删除封面
     *
     * @param id 目标文章id
     * @return
     */
    @RequestMapping("/deleteCover.json")
    @ResponseBody
    public Msg deleteCover(long id) {
        if (id != 0) {
            return articleService.deleteCover(id);
        }
        return MsgUtils.error();
    }
}
