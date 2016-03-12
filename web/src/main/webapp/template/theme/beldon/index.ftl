<@article_tag type='list' pageSize='4'>
    <#assign pagePm=pm>
</@article_tag>
<#include "inc/header.ftl" />
<div class="smooth-overflow frontend">

    <!--Navigation-->
        <#include "inc/nav.ftl" />
    <!--/Navigation-->

    <!--Carousel-->
    <div class="main-carousel">

        <!--Slider -->
        <div id="carousel-example-generic" class="carousel" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <!--First Item-->
                <div class="item item1 active">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="caption">
                                    <h2 class="animated bounceInRight">Welcome to <b>Beldon</b></h2>
                                    <p class="animated fadeInUpBig"> Beldon &#8212; 博客程序. </p>
                                </div>
                            </div>
                            <div class="col-md-4 visible-lg visible-md"> <img class="animated fadeInRightBig" src="${THEME_PATH}/images/slider/1.png" alt="ORB"> </div>
                        </div>
                    </div>
                </div>
                <!--/First Item-->
                <!--Second Item-->
                <div class="item item2">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="caption">
                                    <h2 class="animated lightSpeedIn"><b>Fully</b> Responsive!</h2>
                                    <p class="animated bounceInDown"> 做好的博客程序.</p>
                                </div>
                            </div>
                            <div class="col-md-4 visible-lg visible-md"> <img class="animated fadeInRightBig" src="${THEME_PATH}/images/slider/2.png" alt="ORB"> </div>
                        </div>
                    </div>
                </div>
                <!--/Second Item-->
                <!--Third Item-->
                <div class="item item3">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="caption">
                                    <h2 class="animated fadeInDownBig">Beldon博客程序</b></h2>
                                    <p class="animated bounceInDown"> Beldon &#8212; 博客</p>
                                </div>
                            </div>
                            <div class="col-md-4 visible-lg visible-md"> <img class="animated fadeInRightBig" src="${THEME_PATH}/images/slider/3.png" alt="img"> </div>
                        </div>
                    </div>
                </div>
                <!--/Third Item-->
            </div>
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left"></span> </a> <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right"></span> </a> </div>
    </div>
    <!--/Carousel-->

    <div class="container frontend">
        <div class="row">
            <div class="clearfix"></div>

            <!--Facts-->

            <div class="factage-block block">
                <h2>网站目标</h2>
                <!--Fact-->
                <div class="col-md-4">
                    <div class="factage">
                        <div class="factage-text-block">
                            <p class="numbers">1</p>
                            <h3>快速的</h3>
                            尽力打造一个快速的JavaWeb博客程序</div>
                        <div class="more-icon-wrap more-icon-hover more-icon-hover"> <a href="#" data-toggle="tooltip" data-placement="top" title="Know More!" class="more-icon more-icon-more tooltiped">Mobile</a> </div>
                    </div>
                </div>
                <!--/Fact-->
                <!--Fact-->
                <div class="col-md-4">
                    <div class="factage">
                        <div class="factage-text-block">
                            <p class="numbers">2</p>
                            <h3>友好的</h3>
                            做到更加友好</div>
                        <div class="more-icon-wrap more-icon-hover more-icon-hover"> <a href="#" data-toggle="tooltip" data-placement="top" title="Know More!" class="more-icon more-icon-more tooltiped">Mobile</a> </div>
                    </div>
                </div>
                <!--/Fact-->
                <!--Fact-->
                <div class="col-md-4">
                    <div class="factage">
                        <div class="factage-text-block">
                            <p class="numbers">3</p>
                            <h3>可拓展的</h3>
                             提供更多接口，方便更多的插件开发</div>
                        <div class="more-icon-wrap more-icon-hover more-icon-hover"> <a href="#" data-toggle="tooltip" data-placement="top" title="Know More!" class="more-icon more-icon-more tooltiped">Mobile</a> </div>
                    </div>
                </div>
                <!--/Fact-->
            </div>
            <div class="clearfix"></div>

            <!--/Facts-->

            <!--Latest Blog-->
            <div class="main-page-blog-posts block">
                <h2>最新发布</h2>

                <#list pagePm.results as article>
                    <!--Blog Post-->
                    <div class="col-md-3 col-sm-6">
                        <div class="blog-post-short">
                            <a href="${BASE_PATH}/article/details/${(article.id)!}.html">
                                <#if article?exists && article.cover?exists && article.cover != ''>
                                    <img class="img-responsive" style="height: 175px;" src="${BASE_PATH}/${(article.cover)!}" alt="blog" />
                                <#else>
                                    <img class="img-responsive" style="height: 175px;" src="${THEME_PATH}/images/photos/7.jpg" alt="blog" />
                                </#if>
                            </a>
                            <div class="blog-header">
                                <!--Big Date-->
                                <div class="blog-date"> <span class="month">${(article.date?string("MMMM"))!}</span> <span class="day">${(article.date?string("dd"))!}</span> <span class="year">${(article.date?string("yyyy"))!}</span> </div>
                                <!--/Big Date-->
                                <div class="page-header">
                                    <h3><a href="${BASE_PATH}/article/${(article.id)!}.html">${(article.title)!}</a></h3>
                                </div>
                                <div class="blog-info clearfix"> <a href="#">
                                    <#if article.cats?exists>
                                        <#list article.cats as cat>
                                            <a href="#">${(cat.name)}</a>
                                        </#list>
                                    <#else>
                                        <a href="#">默认分类2</a>
                                    </#if>
                                </a> By: <a href="#">${(article.userVo.nickName)!}</a> </div>
                            </div>
                        </div>
                    </div>
                    <!--/Blog Post-->
                </#list>
                <div class="clearfix"></div>
            </div>
            <!--/Latest Blog-->

        </div>
    </div>
    <!-- Page Ends -->
<#include "inc/footer.ftl" />