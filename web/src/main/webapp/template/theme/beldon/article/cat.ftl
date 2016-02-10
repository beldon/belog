<#include "../inc/header.ftl" />
<#if !page?exists>
    <#assign page=1>
</#if>
<@category_tag type='list' currentPage=currentPage>
    <#assign cats=cats>
</@category_tag>
<@category_tag catId = id>
    <#assign category=category>
</@category_tag>
<@article_tag type='cat'catId=id currentPage = page>
    <#assign pm=pm>
</@article_tag>
<#if pm.currentPage==1 >
    <#assign frontPage=1>
<#else >
    <#assign frontPage=pm.currentPage-1>
</#if>
<#if pm.currentPage==pm.totalPage >
    <#assign nextPage=pm.totalPage>
<#else >
    <#assign nextPage=pm.currentPage+1>
</#if>
<div class="smooth-overflow frontend">

    <!--Navigation-->
        <#include "../inc/nav.ftl" />
    <!--/Navigation-->

    <!--Breadcrumb-->
    <div class="container">
        <div class="row">
            <div class="breadcrumb clearfix">
                <ul>
                    <li><a href="${BASE_PATH}/index.html"><i class="fa fa-home"></i></a></li>
                    <li class="active">博客</li>
                </ul>

                <!--Search-->
                <#--
                <div class="site-search pull-right">
                    <form action="#" id="inline-search">
                        <i class="fa fa-search"></i>
                        <input type="search" placeholder="Search">
                    </form>
                </div>
                -->
            </div>
        </div>
    </div>
    <!--/Breadcrumb-->

    <div class="container frontend">
        <div class="row">
            <div class="col-md-9">
                    <#list pm.list as article>
                        <!-- Blog Post Starts -->
                        <div class="blog-post">
                            <div class="blog-header">
                                <!--Big Date-->
                                <div class="blog-date"> <span class="month">${(article.date?string("MMMM"))!}</span> <span class="day">${(article.date?string("dd"))!}</span> <span class="year">${(article.date?string("yyyy"))!}</span> </div>
                                <!--/Big Date-->
                                <div class="page-header">
                                    <a href="${BASE_PATH}/article/details/${(article.id)!}.html"><h1 style="margin-left: 10px;">${(article.title)!}</h1></a>
                                </div>
                                <div class="blog-info clearfix">
                                    <div class="pull-left"><i class="fa fa-folder-open"></i>: <a href="#">默认分类</a> By: <a
                                            href="#">${(article.userVo.nickName)!}</a> <i class="fa fa-comment"></i>: 251</div>
                                    <button class="btn btn-default btn-xs pull-right hidden-xs"><span><i class="fa fa-heart"></i></span> 0</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-8">
                                    <p style="text-indent: 2em;margin-top: 25px;">
                                        <#assign content=article.content?replace("<.*?>","","r")>
                                        <#if content?length gt 250 >
                                        ${(content?substring(0,250))!}...
                                        <#else >
                                        ${(content)!}
                                        </#if>
                                    </p>
                                </div>
                                <div class="main-blog-img col-md-4">
                                    <#if article?exists && article.cover?exists && article.cover != ''>
                                        <img class="img-responsive img-rounded" src="${BASE_PATH}/${(article.cover)!}" alt="Blog Picture" onerror="javascript:this.src='${THEME_PATH}/images/photos/12.jpg'"/>
                                    <#else>
                                        <img class="img-responsive img-rounded" src="${THEME_PATH}/images/photos/12.jpg" alt="Blog Picture" />
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <!-- Blog Post Ends -->
                    </#list>

                <ul class="pagination pull-right">
                    <#--<li><a href="${BASE_PATH}/article/index.html?page=${frontPage}">&laquo;</a></li>-->
                    <li><a href="${BASE_PATH}/article/cat/${frontPage}/${id}.html">&laquo;</a></li>
                    <li><a href="#">${(pm.currentPage)!}/${(pm.totalPage)!}</a></li>
                    <li><a href="${BASE_PATH}/article/cat/${nextPage}/${id}.html">&raquo;</a></li>
                    <#--<li><a href="${BASE_PATH}/article/index.html?page=${nextPage}">&raquo;</a></li>-->
                </ul>
            </div>
            <!-- /Left Side-->

            <!--Block Right-->
            <div class="col-md-3 margin-top">
                <#include "../inc/sidebar.ftl"/>
            </div>
            <!-- /Row Block Right-->
        </div>
    </div>
    <!-- Page Ends -->
<#include "../inc/footer.ftl" />