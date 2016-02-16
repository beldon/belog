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
import belog.pojo.vo.TagVo;
import belog.pojo.vo.UserVo;
import belog.service.ArticleService;
import belog.service.CategoryService;
import belog.service.TagService;
import belog.utils.MsgUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("CategoryService")
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;


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

            //处理分类
            List<CategoryVo> cats = articleVo.getCats();
            addCat(posts, cats);

            //处理标签
            List<TagVo> tagVos = articleVo.getTagVos();
            addTags(posts, tagVos);

        } else { //更新文章
            appContext.getContexts().publishEvent(new ArticleEvent(articleVo, Event.Action.UPDATE));
            Posts posts = postsDao.findById(articleVo.getId());
            posts.setModified(new Date());
            posts.setTitle(articleVo.getTitle());
            posts.setContent(articleVo.getContent());

            Set<PostMeta> postMetas = posts.getPostMetas();
            PostMeta postMeta = null;
            if (postMetas != null && postMetas.size() > 0) {
                Iterator<PostMeta> it = postMetas.iterator();
                while (it.hasNext()) {
                    PostMeta meta = it.next();
                    if ("cover".equals(meta.getMeteKey())) {
                        postMeta = meta;
                        break;
                    }
                }
            }

            if (!StringUtils.isEmpty(articleVo.getCover())) {//更改或添加
                if (postMeta != null) {//更改
                    postMeta.setMetaValue(articleVo.getCover());
                } else {//添加
                    postMeta = new PostMeta();
                    postMeta.setPosts(posts);
                    postMeta.setMeteKey("cover");
                    postMeta.setMetaValue(articleVo.getCover());
                }

            } else {//删除
                if (postMeta != null) {
                    postMeta.setMetaValue("");
                }
            }

            postMetaDao.saveOrUpdate(postMeta);

            //更新标签
            Set<TermRelationships> termRelationshipsSet = posts.getTermRelationships();
            for (TermRelationships termRelationships : termRelationshipsSet) {//删除已有标签和分类
                termRelationshipsDao.deleteEntity(termRelationships);
                categoryService.countMinus(termRelationships.getTermTaxonomy().getId(), 1);
            }

            List<CategoryVo> cats = articleVo.getCats();

            //添加分类
            addCat(posts, cats);

            //添加标签
            addTags(posts, articleVo.getTagVos());
        }
    }

    /**
     * 删除文章
     *
     * @param id
     * @TODO 减去标签统计
     */
    public void delete(long id) {

        Posts posts = postsDao.findById(id);
        Set<PostMeta> postMetas = posts.getPostMetas();
        Set<TermRelationships> termRelationshipses = posts.getTermRelationships();

        //删除 postMeta
        if (postMetas != null && postMetas.size() > 0) {
            for (PostMeta postMeta : postMetas) {
                postMetaDao.deleteEntity(postMeta);
            }
        }

        //删除分类及标签
        if (termRelationshipses != null && termRelationshipses.size() > 0) {
            for (TermRelationships termRelationships : termRelationshipses) {
                termRelationshipsDao.deleteEntity(termRelationships);
                categoryService.countMinus(termRelationships.getTermTaxonomy().getId(), 1);
            }
        }

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
        StringBuilder hql = new StringBuilder(300);
        hql.append("SELECT post FROM belog.pojo.po.Posts post");
        hql.append(" JOIN post.termRelationships relation");
        hql.append(" where post.status = 'publish' and post.type = 'article' ");
        hql.append(" and relation.termTaxonomy.id = '" + catId + "'");
        if (!StringUtils.isEmpty(type) && "hot".equals(type)) {
            hql.append(" order by post.commentCount desc");
        }else{
            hql.append(" order by post.date desc");
        }
        PageModel pm = postsDao.findPageByHql(hql.toString(), pageModel);
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
            List<TagVo> tagVos = new ArrayList<TagVo>();
            for (TermRelationships termRelationships : termRelationshipsSet) {
                TermTaxonomy taxonomy = termRelationships.getTermTaxonomy();
                if (CategoryService.CATEGORY.equals(taxonomy.getTaxonomy())) {
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(taxonomy.getTerms(), categoryVo);
                    BeanUtils.copyProperties(taxonomy, categoryVo);
                    categoryVoList.add(categoryVo);
                } else if (TagService.TAG.equals(taxonomy.getTaxonomy())) {
                    TagVo tagVo = new TagVo();
                    Terms terms = taxonomy.getTerms();
                    tagVo.setId(taxonomy.getId());
                    tagVo.setName(terms.getName());
                    tagVo.setCount(taxonomy.getCount()==null?0:taxonomy.getCount());
                    tagVo.setTermGroup(terms.getTermGroup());
                    tagVos.add(tagVo);
                }
            }
            articleVo.setCats(categoryVoList);
            articleVo.setTagVos(tagVos);
        }
    }

    /**
     * 删除文章封面
     *
     * @param id 文章id
     * @return
     */
    public Msg deleteCover(long id) {
        List<PostMeta> list = postMetaDao.findByHql("from belog.pojo.po.PostMeta where meteKey = 'cover' and post_id = " + id);
        for (PostMeta meta : list) {
            postMetaDao.deleteEntity(meta);
        }
        return MsgUtils.success();
    }

    /**
     * 为文章添加分类
     * @param posts 文章
     * @param cats 分类
     */
    private void addCat(Posts posts,  List<CategoryVo> cats) {
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


    /**
     *  为文章添加标签
     * @param posts 文章
     * @param tagVos 标签
     */
    private void addTags(Posts posts, List<TagVo> tagVos) {
        if (tagVos != null && tagVos.size() > 0) {
            for (TagVo tagVo : tagVos) {
                TagVo tag = tagService.getOrAddTagByName(tagVo.getName());
                TermRelationships termRelationships = new TermRelationships();
                termRelationships.setPosts(posts);
                TermTaxonomy taxonomy = new TermTaxonomy();
                taxonomy.setId(tag.getId());
                termRelationships.setTermTaxonomy(taxonomy);
                termRelationshipsDao.saveOrUpdate(termRelationships);
                tagService.countPlus(tag.getId(), 1);
            }
        }
    }
}
