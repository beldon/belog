<#include "../inc/header.ftl"/>
<@category_tag type='list' currentPage=currentPage>
    <#assign cats=cats>
</@category_tag>
<@article_tag articleId=id>
    <#assign pageArticle=article>
</@article_tag>
<body class="tooltips">

<!--
===========================================================
BEGIN PAGE
===========================================================
-->
<#include "../inc/nav.ftl" />

<!-- BEGIN BERADCRUMB AND PAGE TITLE -->
<div class="page-title-wrap">
    <div class="container">
        <ol class="breadcrumb">
            <li><a href="${BASE_PATH}/index.html">首页</a></li>
            <li><a href="${BASE_PATH}/article/index.html">博客</a></li>
            <li class="active">${(pageArticle.title)!}</li>
        </ol>
    </div><!-- /.container -->

</div><!-- /.page-title-wrap -->
<!-- END BERADCRUMB AND PAGE TITLE -->

<div class="container">
    <div class="row">
        <div class="col-sm-8 col-md-9">

            <!-- BLOG DETAIL SECTION -->
            <div class="section blog-detail">
                 ${(pageArticle.content)!}
            </div><!-- /.section -->
            <!-- END BLOG DETAIL SECTION -->


            <!--评论开始-->
        <#if comment_tag?exists>
            <@comment_tag>
            ${(code)!}
            </@comment_tag>
        </#if>
            <!--评论结束-->
        </div><!-- /.col-sm-8 col-md-9 -->

        <div class="col-sm-4 col-md-3">

            <!-- BEGIN SIDEBAR -->
            <div class="section sidebar">

                <!-- Begin blog detail -->
                <div class="panel panel-square panel-success panel-no-border">
                    <div class="panel-heading lg">
                        <h3 class="panel-title"><strong>博文详情</strong></h3>
                    </div>
                    <!-- List group -->
                    <ul class="list-group success blog-detail-list square">
                        <li class="list-group-item">
                            <i class="fa fa-calendar icons"></i>
                            发布时间 : <a href="#fakelink">${(pageArticle.date?string("yyyy-MM-dd"))!}</a>
                        </li>
                        <li class="list-group-item">
                            <i class="fa fa-folder-o icons"></i>
                            分类 :
                            <#if pageArticle.cats?exists>
                                <#list pageArticle.cats as cat>
                                    <a href="#">${(cat.name)}</a>, &nbsp;
                                <#--<a href="#fakelink">Web design</a>-->
                                </#list>
                            </#if>
                        </li>
                        <li class="list-group-item">
                            <i class="fa fa-flask icons"></i>
                            作者: <a href="#fakelink">${(pageArticle.userVo.nickName)!}</a>
                        </li>
                    </ul>
                </div><!-- /.panel panel-default -->
                <!-- End blog detail -->


                <!-- Begin Categories -->
                <div class="panel panel-no-border panel-sidebar">
                    <div class="panel-heading">
                        <h3 class="panel-title">分类</h3>
                    </div>
                    <!-- List group -->
                    <div class="list-group">
                        <#list cats as cat>
                            <a href="#fakelink" class="list-group-item">${(cat.name)!}  (${(cat.count)!})</a>
                        </#list>
                    </div>
                </div><!-- /.panel panel-no-border panel-sidebar -->
                <!-- End Categories -->

            </div><!-- /.section -->
            <!-- END SIDEBAR -->

        </div><!-- /.col-sm-4 col-md-3 -->
    </div><!-- /.row -->
</div><!-- /.container -->


<#include "../inc/footer.ftl" />