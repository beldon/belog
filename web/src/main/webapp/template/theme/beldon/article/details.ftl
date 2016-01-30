<@article_tag articleId=articleId>
    <#assign pageArticle=article>
</@article_tag>
<#include "../inc/header.ftl" />
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
                    <li><a href="${BASE_PATH}/article/index.html">博客</a></li>
                    <li class="active">${(pageArticle.title)!}</li>
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

                <!-- Blog Post Starts -->
                <div class="blog-post">
                    <div class="blog-header">
                        <!--Big Date-->
                        <div class="blog-date"> <span class="month">${(pageArticle.date?string("MMMM"))!}</span> <span class="day">${(pageArticle.date?string("dd"))!}</span> <span class="year">${(pageArticle.date?string("yyyy"))!}</span> </div>
                        <!--/Big Date-->
                        <div class="page-header">
                            <h1>${(pageArticle.title)!}</h1>
                        </div>
                        <div class="blog-info clearfix">
                            <div class="pull-left"><i class="fa fa-folder-open"></i>:
                            <#if pageArticle.cats?exists>
                                <#list pageArticle.cats as cat>
                                    <a href="#">${(cat.name)}</a>
                                </#list>
                            <#else>
                                <a href="#">默认分类</a>
                            </#if> By: <a
                                    href="#">${(pageArticle.userVo.nickName)!}</a> <i class="fa fa-comment"></i>: 0</div>
                            <button class="btn btn-default btn-xs pull-right"><span><i class="fa fa-heart"></i></span> 0</button>
                        </div>
                    </div>
                        <!-- Blog DETAIL Starts -->
                    ${(pageArticle.content)!}
                        <!-- Blog DETAIL Ends -->
                    <#--
                    <div class="blog-tags padding">Tags: <a href="#" rel="tag">CSS3</a> <a href="#" rel="tag">HTML5</a> <a href="#"
                                                                                                                    rel="tag">jQuery</a> <a href="#" rel="tag">Premium Theme</a> <a href="#"
                                                                                                                                                                                   rel="tag">Design</a> </div>
                    -->
                </div>

                <!-- Blog Post Ends -->

                <!-- Post Author -->
                <div class="blog-author"> <img src="${THEME_PATH}/images/users/g.gif" alt="Arthur" class="img-responsive"/>
                    <h4>Beldon</h4>
                    <p>修身心，做善事，养厚德，不缺德；尊老人，爱儿童，常律己，结志友；讲诚信，做实事，求上进，思进取；常学习，常锻炼，平常心，常行善。</p>

                </div>
                <!--/Post Author-->

                <!-- Comments -->

                <#if comment_tag?exists>
                    <@comment_tag>
                    ${(code)!}
                    </@comment_tag>
                </#if>
                <!-- /Comment Form-->
            </div>
            <!-- /Left Side-->

            <!--Block Right-->
            <div class="col-md-3 margin-top">
                <#include "../inc/sidebar.ftl" />
            </div>
            <!-- /Row Block Right-->
        </div>
    </div>
    <!-- Page Ends -->

    <#include "../inc/footer.ftl" />