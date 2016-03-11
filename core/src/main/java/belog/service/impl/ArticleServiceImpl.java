package belog.service.impl;


import belog.dao.PostsMapper;
import belog.dao.PostsMetaMapper;
import belog.dao.TaxonomyRelationshipsMapper;
import belog.dao.UsersMapper;
import belog.pojo.Msg;
import belog.pojo.Page;
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
    private PostsMapper postsMapper;

    @Autowired
    private PostsMetaMapper postsMetaMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private TaxonomyRelationshipsMapper taxonomyRelationshipsMapper;


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
            posts.setCreDate(new Date());
            posts.setStatus("publish");
            posts.setCommentStatus("open");
            posts.setPingStatus("open");
            posts.setType("article");

            Subject subject = SecurityUtils.getSubject();
            String loginName = subject.getPrincipal().toString();
            Users users = usersMapper.findByLoginName(loginName);
            posts.setUserId(users.getId());

            postsMapper.insertSelective(posts);

            if (!StringUtils.isEmpty(articleVo.getCover())) {
                PostsMeta postsMeta = new PostsMeta();
                postsMeta.setPostId(posts.getId());
                postsMeta.setKey("cover");
                postsMeta.setValue(articleVo.getCover());
                postsMetaMapper.insertSelective(postsMeta);
            }

            //处理分类
            List<CategoryVo> cats = articleVo.getCats();
            addCat(posts.getId(), cats);

            //处理标签
            List<TagVo> tagVos = articleVo.getTagVos();
            addTags(posts.getId(), tagVos);

        } else { //更新文章
            appContext.getContexts().publishEvent(new ArticleEvent(articleVo, Event.Action.UPDATE));
            Posts posts = postsMapper.selectByPrimaryKey(articleVo.getId());
            posts.setModified(new Date());
            posts.setTitle(articleVo.getTitle());
            posts.setContent(articleVo.getContent());

            PostsMeta postsMeta = postsMetaMapper.findOneByKey(posts.getId(), "cover");


            if (!StringUtils.isEmpty(articleVo.getCover())) {//更改或添加
                if (postsMeta != null) {//更改
                    postsMeta.setValue(articleVo.getCover());
                } else {//添加
                    postsMeta = new PostsMeta();
                    postsMeta.setPostId(posts.getId());
                    postsMeta.setKey("cover");
                    postsMeta.setValue(articleVo.getCover());
                }
            } else {//删除
                if (postsMeta != null) {
                    postsMeta.setValue("");
                }
            }

            if (postsMeta != null) {
                postsMetaMapper.updateByPrimaryKeySelective(postsMeta);
            }

            //添加分类
            addCat(posts.getId(), articleVo.getCats());

            //添加标签
            addTags(posts.getId(), articleVo.getTagVos());
        }
    }

    /**
     * 删除文章
     *
     * @param id
     * @TODO 减去标签统计
     */
    public void delete(long id) {
        Posts posts = postsMapper.selectByPrimaryKey(id);
        if (posts != null) {
            //删除 postMeta
            postsMetaMapper.deleteByPostId(id);
            //删除分类及标签
            taxonomyRelationshipsMapper.deleteByTypeAndObjectId("post_cat", id);
            taxonomyRelationshipsMapper.deleteByTypeAndObjectId("post_tag", id);
            //删除文章
            postsMapper.deleteByPrimaryKey(id);
        }
    }

    public ArticleVo findById(long id) {
        ArticleVo articleVo = new ArticleVo();
        Posts posts = postsMapper.selectByPrimaryKey(id);
        copy(posts, articleVo);
        return articleVo;
    }

    public Page<ArticleVo> findPage(Page<ArticleVo> page) {
        Page<Posts> postPage = new Page<Posts>();
        postPage.setPageNo(page.getPageNo());
        postPage.setPageSize(page.getPageSize());
        postPage.setTotalRecord(page.getTotalRecord());

        List<Posts> list = postsMapper.findPage(postPage);
        List<ArticleVo> articleVoList = new ArrayList<ArticleVo>();

        for (Posts posts : list) {
            //@TODO 复制属性
        }
        page.setResults(articleVoList);
        page.setTotalRecord(postPage.getTotalRecord());
        return page;
    }

    public Page<ArticleVo> findPageByCatId(long catId, Page<ArticleVo> page, String type) {
        Page<Posts> postPage = new Page<Posts>();
        postPage.setPageNo(page.getPageNo());
        postPage.setPageSize(page.getPageSize());
        postPage.setTotalRecord(page.getTotalRecord());

        List<Posts> list = postsMapper.findPageCat(catId, postPage);
        List<ArticleVo> articleVoList = new ArrayList<ArticleVo>();

        for (Posts posts : list) {
            //@TODO 复制属性
        }

        page.setResults(articleVoList);
        page.setTotalRecord(postPage.getTotalRecord());
        return page;
    }


    /**
     * 把 Posts 数据复制到ArticleVo
     *
     * @param articleVo
     * @param posts
     */
    private void copy(Posts posts, ArticleVo articleVo) {
        BeanUtils.copyProperties(posts, articleVo);

        //文章用户
        Users users = usersMapper.selectByPrimaryKey(posts.getUserId());
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(users, userVo);
        articleVo.setUser(userVo);

        //文章封面
        PostsMeta postsMeta = postsMetaMapper.findOneByKey(posts.getId(), "cover");
        if (postsMeta != null) {
            articleVo.setCover(postsMeta.getValue());
        }

        //文章标签


        //文章分类

    }

    /**
     * 删除文章封面
     *
     * @param id 文章id
     * @return
     */
    public Msg deleteCover(long id) {
        postsMetaMapper.deleteByPostIdAndKey(id, "cover");
        return MsgUtils.success();
    }

    /**
     * 为文章添加分类
     *
     * @param postsId 文章Id
     * @param cats    分类
     */
    private void addCat(long postsId, List<CategoryVo> cats) {
        //删除所有分类
        taxonomyRelationshipsMapper.deleteByTypeAndObjectId("post_cat", postsId);

        //添加分类
        if (cats != null && cats.size() > 0) {
            for (CategoryVo categoryVo : cats) {
                TaxonomyRelationships taxonomyRelationships = new TaxonomyRelationships();
                taxonomyRelationships.setType("post_cat");
                taxonomyRelationships.setObjectId(postsId);
                taxonomyRelationships.setTaxonomyId(categoryVo.getId());
                taxonomyRelationshipsMapper.insertSelective(taxonomyRelationships);
            }
        }

        //@TODO 统计分类数量
    }


    /**
     * 为文章添加标签
     *
     * @param postsId 文章 Id
     * @param tagVos  标签
     */
    private void addTags(long postsId, List<TagVo> tagVos) {
        if (tagVos != null && tagVos.size() > 0) {
            for (TagVo tagVo : tagVos) {
                TagVo tag = tagService.getOrAddTagByName(tagVo.getName());
                TaxonomyRelationships taxonomyRelationships = new TaxonomyRelationships();
                taxonomyRelationships.setType("post_tag");
                taxonomyRelationships.setObjectId(postsId);
                taxonomyRelationships.setTaxonomyId(tag.getId());
                taxonomyRelationshipsMapper.insertSelective(taxonomyRelationships);
            }
        }

        //TODO 统计标签文章数量
    }
}
