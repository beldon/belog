<#include "../inc/header.ftl" />
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
                    <li class="active">发现</li>
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
            <div class="page-header">
                <h1>发现<small>我们的生活</small></h1>
            </div>
            <div class="col-md-12">
                <div id="portfolio-filtering">
                <#list pm.results as article>
                    <!--Portfolio Item-->
                    <div class="col-md-3 col-sm-6 col-xs-12 cat1">
                        <div class="portfolio-item cs-style-5">
                            <figure>
                                <#if article?exists && article.cover?exists && article.cover != ''>
                                    <img style="height: 175px;" src="${BASE_PATH}/${(article.cover)!}" alt="文章封面图" onerror="javascript:this.src='${THEME_PATH}/images/photos/15.jpg'">
                                <#else>
                                    <img style="height: 175px;" src="${THEME_PATH}/images/photos/15.jpg" alt="文章封面图">
                                </#if>

                                    <#assign content=article.content?replace("<.*?>","","r")>

                                <figcaption>
                                    <h4>${(article.title)!}</h4>
                                    <#if content?length gt 50 >
                                        <span> ${(content?substring(0,50))!}...</span>
                                    <#else >
                                        <span>${(content)!}</span>
                                    </#if>
                                    <p><a class="btn btn-info btn-sm" href="${BASE_PATH}/article/details/${(article.id)!}.html">浏览更多</a></p>
                                </figcaption>
                            </figure>
                        </div>
                    </div>
                    <!--/Portfolio Item-->
                </#list>
                    <!--分页-->
                    <ul class="pagination pull-right">
                        <li><a href="${BASE_PATH}/discover/index_${frontPage}.html">&laquo;</a></li>
                        <li><a href="#">${(pm.currentPage)!}/${(pm.totalPage)!}</a></li>
                        <li><a href="${BASE_PATH}/discover/index_${nextPage}.html">&raquo;</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!-- Page Ends -->
<#include "../inc/footer.ftl" />