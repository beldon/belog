<#include "../common/_layout.ftl"/>
<@article_tag type='list' currentPage=currentPage pageSize='12'>
    <#assign pm=pm>
</@article_tag>
<#if pm.pageNo==1 >
    <#assign frontPage=1>
<#else >
    <#assign frontPage=pm.pageNo-1>
</#if>
<#if pm.pageNo==pm.totalPage >
    <#assign nextPage=pm.totalPage>
<#else >
    <#assign nextPage=pm.pageNo+1>
</#if>
<@html title="发现-">

<!-- BEGIN BERADCRUMB AND PAGE TITLE -->
<div class="page-title-wrap">
    <div class="container">
        <ol class="breadcrumb">
            <li><a href="${BASE_PATH}/index.html">首页</a></li>
            <li class="active">发现</li>
        </ol>
    </div><!-- /.container -->

</div><!-- /.page-title-wrap -->
<!-- END BERADCRUMB AND PAGE TITLE -->


<div class="section work-section">
    <div class="container">
        <div id="work-mixitup" class="work-content">
            <div class="row">
                <#list pm.results as article>
                    <!-- Begin work item -->
                    <div class="col-sm-4 col-md-3 col-xs-6 mix printing">
                        <div class="work-item">
                            <div class="hover-wrap">
                                <a href="${BASE_PATH}/article/details/${(article.id)!}.html">
                                    <i class="glyphicon glyphicon-plus icon-plus"></i>
                                </a>
                            </div><!-- /.hover-wrap -->
                            <#if article?exists && article.cover?exists && article.cover != ''>
                                <img src="${BASE_PATH}/${(article.cover)!}" onerror="javascript:this.src='${THEME_PATH}/img/work/2.jpg'" alt="文章封面图">
                            <#else>
                                <img src="${THEME_PATH}/img/work/2.jpg" alt="文章封面图">
                            </#if>
                            <div class="the-box no-border transparent no-margin">
                                <p class="project-name">${(article.title)!}</p>
                                <p class="project-category">
                                    <#if article.cats?exists>
                                        <#list article.cats as cat>
                                        ${(cat.name)}, &nbsp;
                                        </#list>
                                    </#if>
                                </p>
                            </div><!-- /.the-box no-border transparent -->
                        </div><!-- /.work-item -->
                    </div><!-- /.col-sm-4 col-md-3 col-xs-6 mix -->
                    <!-- End work item -->
                </#list>

            </div><!-- /.row -->
        </div><!-- /#work-mixitup -->

        <div class="clear"></div>
        <ul class="pagination info block-color separated pull-right">
            <li><a href="${BASE_PATH}/discover/index_${frontPage}.html">&laquo;</a></li>
            <li><a href="#">${(pm.pageNo)!}/${(pm.totalPage)!}</a></li>
            <li><a href="${BASE_PATH}/discover/index_${nextPage}.html">&raquo;</a></li>
        </ul>

    </div><!-- /.container -->
</div><!-- /.section -->

</@html>