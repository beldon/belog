package belog.service.impl;


import belog.dao.PostMetaDao;
import belog.dao.PostsDao;
import belog.dao.TermRelationshipsDao;
import belog.dao.UsersDao;
import belog.pojo.Msg;
import belog.pojo.PageModel;
import belog.pojo.event.ArticleEvent;
import belog.pojo.event.Event;
import belog.pojo.po.*;
import belog.pojo.vo.ArticleVo;
import belog.pojo.vo.CategoryVo;
import belog.pojo.vo.UserVo;
import belog.service.ArticleService;
import belog.service.CategoryService;
import belog.utils.MsgUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Beldon
 */
@Service
public class ArticleServiceImpl extends BaseService implements ArticleService {

    @Autowired
    private PostsDao postsDao;

    @Autowired
    private PostMetaDao postMetaDao;

    @Autowired
    private TermRelationshipsDao termRelationshipsDao;

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private CategoryService categoryService;

    /**
     * @param articleVo
     */
    public void addOrUpdate(ArticleVo articleVo) {
        if (articleVo.getId() == 0) { //新增文章
            appContext.getContexts().publishEvent(new ArticleEvent(articleVo, Event.Action.ADD));
            Posts posts = new Posts();
            BeanUtils.copyProperties(articleVo, posts);
            posts.setDate(new Date());
            posts.setStatus("publish");
            posts.setCommentStatus("open");
            posts.setPingStatues("open");
            posts.setType("article");

            Subject subject = SecurityUtils.getSubject();
            String loginName = subject.getPrincipal().toString();
            Users users = usersDao.findByLoginName(loginName);
//        Users users = usersDao.findByLoginName("beldon");
            posts.setUsers(users);

            postsDao.saveEntity(posts);

            if (!StringUtils.isEmpty(articleVo.getCover())) {
                PostMeta postMeta = new PostMeta();
                postMeta.setPosts(posts);
                postMeta.setMeteKey("cover");
                postMeta.setMetaValue(articleVo.getCover());
                postMetaDao.saveEntity(postMeta);
            }

            List<CategoryVo> cats = articleVo.getCats();
            if (cats != null && cats.size() > 0) {
                for (CategoryVo categoryVo : cats) {
                    TermRelationships termRelationships = new TermRelationships();
                    TermTaxonomy taxonomy = new TermTaxonomy();
                    taxonomy.setId(categoryVo.getId());
                    termRelationships.setPosts(posts);
                    termRelationships.setTermTaxonomy(taxonomy);
                    termRelationshipsDao.saveOrUpdate(termRelationships);
                    categoryService.countPlus(categoryVo.getId(), 1);
                }
            }
        } else { //更新文章
            appContext.getContexts().publishEvent(new ArticleEvent(articleVo, Event.Action.UPDATE));
            Posts posts = postsDao.findById(articleVo.getId());
            posts.setModified(new Date());
            posts.setTitle(articleVo.getTitle());
            posts.setContent(articleVo.getContent());
            postsDao.updateEntity(posts);

            Set<PostMeta> postMetas = posts.getPostMetas();
            PostMeta postMeta = null;
            if (postMetas != null && postMetas.size() > 0) {
                Iterator<PostMeta> it = postMetas.iterator();
                while (it.hasNext()) {
                    PostMeta meta = it.next();
                    if ("cover".equals(meta.getMeteKey())) {
                        postMeta = meta;
                    }
                }
            }

            if (!StringUtils.isEmpty(articleVo.getCover())) {//更改
                if (postMeta != null) {
                    postMeta.setMetaValue(articleVo.getCover());
                    postMetaDao.updateEntity(postMeta);
                } else {
                    postMeta = new PostMeta();
                    postMeta.setPosts(posts);
                    postMeta.setMeteKey("cover");
                    postMeta.setMetaValue(articleVo.getCover());
                    postMetaDao.saveEntity(postMeta);
                }

            } else {//删除
                if (postMeta != null) {
                    postMeta.setMetaValue("");
                    postMetaDao.updateEntity(postMeta);
                }
            }

            Set<TermRelationships> termRelationshipsSet = posts.getTermRelationships();
            for (TermRelationships termRelationships : termRelationshipsSet) {
                termRelationshipsDao.deleteEntity(termRelationships);
                categoryService.countMinus(termRelationships.getTermTaxonomy().getId(), 1);
            }

            List<CategoryVo> cats = articleVo.getCats();
            if (cats != null && cats.size() > 0) {
                for (CategoryVo categoryVo : cats) {
                    TermRelationships termRelationships = new TermRelationships();
                    termRelationships.setPosts(posts);
                    TermTaxonomy taxonomy = new TermTaxonomy();
                    taxonomy.setId(categoryVo.getId());
                    termRelationships.setTermTaxonomy(taxonomy);
                    termRelationshipsDao.saveOrUpdate(termRelationships);
                    categoryService.countPlus(categoryVo.getId(), 1);
                }
            }
        }


    }

    public void delete(long id) {
        this.postsDao.delete(id);
    }

    public ArticleVo findById(long id) {
        ArticleVo articleVo = new ArticleVo();
        Posts posts = postsDao.findById(id);
        copy(posts, articleVo);
        return articleVo;
    }

    public PageModel findPage(PageModel pageModel) {
        List<ArticleVo> articleVoList = new ArrayList<ArticleVo>();
//        PageModel pm = postsDao.findPage(pageModel);
        PageModel pm = postsDao.findPageByHql("from belog.pojo.po.Posts order by date desc", pageModel);
        List<Posts> postsList = pm.getList();
        for (Posts posts : postsList) {
            ArticleVo articleVo = new ArticleVo();
            copy(posts, articleVo);
            articleVoList.add(articleVo);
        }
        pm.setList(articleVoList);
        return pm;
    }

    public PageModel findPageByCatId(long catId, PageModel pageModel, String type) {
        Posts posts = new Posts();
        posts.setStatus("publish");
        posts.setType("article");
        TermRelationships termRelationships = new TermRelationships();
        TermTaxonomy taxonomy = new TermTaxonomy();
        taxonomy.setId(1l);
        termRelationships.setTermTaxonomy(taxonomy);
        Set<TermRelationships> termTaxonomies = new HashSet<TermRelationships>();
        termTaxonomies.add(termRelationships);
        posts.setTermRelationships(termTaxonomies);

        Order order = Order.desc("date");
        if (!StringUtils.isEmpty(type) && "hot".equals(type)) {
            order = Order.desc("commentCount");
        }
        PageModel pm = postsDao.findPageByExample(posts, pageModel, order);

        List<ArticleVo> list = new ArrayList<ArticleVo>();
        List<Posts> postsList = pm.getList();
        for (Posts p : postsList) {
            ArticleVo articleVo = new ArticleVo();
            copy(p, articleVo);
            list.add(articleVo);
        }
        pm.setList(list);
        return pm;
    }


    /**
     * 把 Posts 数据复制到ArticleVo
     *
     * @param articleVo
     * @param posts
     */
    private void copy(Posts posts, ArticleVo articleVo) {
        BeanUtils.copyProperties(posts, articleVo);

        //查询文章所属用户
        Users user = posts.getUsers();
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        articleVo.setUserVo(userVo);

        //查找封面图片
        Set<PostMeta> postMetas = posts.getPostMetas();
        if (postMetas != null && postMetas.size() > 0) {
            Iterator<PostMeta> it = postMetas.iterator();
            while (it.hasNext()) {
                PostMeta meta = it.next();
                if ("cover".equals(meta.getMeteKey())) {
                    articleVo.setCover(meta.getMetaValue());
                }
            }
        }

        //查询文章拥有的分类
        Set<TermRelationships> termRelationshipsSet = posts.getTermRelationships();
        if (termRelationshipsSet != null && termRelationshipsSet.size() > 0) {
            List<CategoryVo> categoryVoList = new ArrayList<CategoryVo>();
            for (TermRelationships termRelationships : termRelationshipsSet) {
                TermTaxonomy taxonomy = termRelationships.getTermTaxonomy();
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(taxonomy.getTerms(), categoryVo);
                BeanUtils.copyProperties(taxonomy, categoryVo);
                categoryVoList.add(categoryVo);
            }
            articleVo.setCats(categoryVoList);
        }
    }

    /**
     * 删除文章封面
     * @param id 文章id
     * @return
     */
    public Msg deleteCover(long id){
        List<PostMeta> list = postMetaDao.findByHql("from belog.pojo.po.PostMeta where meteKey = 'cover' and post_id = "+id);
        for (PostMeta meta : list) {
            postMetaDao.deleteEntity(meta);
        }
        return MsgUtils.success();
    }
}
