<#include "../inc/header.ftl"/>
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


<body class="tooltips">

<!--
===========================================================
BEGIN PAGE
===========================================================
-->
<#include "../inc/nav.ftl"/>


<!-- BEGIN BERADCRUMB AND PAGE TITLE -->
<div class="page-title-wrap">
    <div class="container">
        <ol class="breadcrumb">
            <li><a href="${BASE_PATH}/index.html">首页</a></li>
            <li class="active">博客</li>
            <li class="active">${category.name!}</li>
        </ol>
    </div><!-- /.container -->

</div><!-- /.page-title-wrap -->
<!-- END BERADCRUMB AND PAGE TITLE -->

<div class="container">
    <div class="row">
        <div class="col-sm-8 col-md-9">

            <!-- BLOG LIST SECTION -->
            <div class="section">
                <ul class="media-list blog-list">

                    <#list pm.list as article>
                        <li class="media">
                            <a class="pull-left" href="#fakelink">
                                <#if article?exists && article.cover?exists && article.cover != ''>
                                    <img class="media-object img-post" src="${BASE_PATH}/${(article.cover)!}" alt="Image" onerror="javascript:this.src='${THEME_PATH}/img/photo/small/img-small5.jpg'">
                                <#else>
                                    <img class="media-object img-post" src="${THEME_PATH}/img/photo/small/img-small5.jpg" alt="Image">
                                </#if>
                            </a>
                            <div class="media-body">
                                <h4 class="media-heading">
                                    <a href="${BASE_PATH}/article/details/${(article.id)!}.html">
                                     ${(article.title)!}
                                    </a>
                                </h4>
                                <p class="small">
                                    Posted on <a href="#fakelink">${(article.date?string("yyyy-MM-dd"))!}</a> by <a href="#fakelink">${(article.userVo.nickName)!}</a>
                                </p>
                                <p>
                                    <#assign content=article.content?replace("<.*?>","","r")>
                                    <#if content?length gt 250 >
                                    ${(content?substring(0,250))!}...
                                    <#else >
                                    ${(content)!}
                                    </#if>
                                </p>
                            </div><!-- /.media-body -->
                        </li>
                    </#list>
                </ul>


                <!-- Begin pagination -->
                <ul class="pagination info block-color separated pull-right">
                    <#--<li class="disabled"><a href="#fakelink">&lsaquo;</a></li>-->
                    <#--<li class="active"><a href="#fakelink">1</a></li>-->
                    <#--<li><a href="#fakelink">2</a></li>-->
                    <#--<li><a href="#fakelink">...</a></li>-->
                    <#--<li><a href="#fakelink">10</a></li>-->
                    <#--<li><a href="#fakelink">&rsaquo;</a></li>-->
                    <li><a href="${BASE_PATH}/article/cat/${frontPage}/${id}.html">&laquo;</a></li>
                    <li><a href="#">${(pm.currentPage)!}/${(pm.totalPage)!}</a></li>
                    <li><a href="${BASE_PATH}/article/cat/${nextPage}/${id}.html">&raquo;</a></li>
                </ul>
                <!-- End pagination -->
            </div><!-- /.section -->
            <!-- END BLOG LIST SECTION -->


        </div><!-- /.col-sm-8 col-md-9 -->




        <div class="col-sm-4 col-md-3">

            <!-- BEGIN SIDEBAR -->
            <div class="section sidebar">
                <!-- Begin Categories -->
                <div class="panel panel-no-border panel-sidebar">
                    <div class="panel-heading">
                        <h3 class="panel-title">分类</h3>
                    </div>
                    <!-- List group -->
                    <div class="list-group">
                        <#list cats as cat>
                            <a href="${BASE_PATH}/article/cat/${(cat.id)!}.html" class="list-group-item">${(cat.name)!}  (${(cat.count)!})</a>
                        </#list>
                    </div>
                </div><!-- /.panel panel-no-border panel-sidebar -->
                <!-- End Categories -->

            </div><!-- /.section -->
            <!-- END SIDEBAR -->

        </div><!-- /.col-sm-4 col-md-3 -->
    </div><!-- /.row -->
</div><!-- /.container -->


<#include "../inc/footer.ftl"/>