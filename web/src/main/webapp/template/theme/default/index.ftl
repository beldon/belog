<#include "common/_layout.ftl"/>
<@article_tag type='list' pageSize='4'>
    <#assign pagePm=pm>
</@article_tag>
<@html title="">

<!-- BEGIN HEADER FULL IMAGE SLIDE -->
<div class="full-slide-image" id="full-img-slide">
    <div class="slide-inner">
        <div class="slide-text-content">
            <div class="container-fluid">
                <div id="content-header-slide" class="owl-carousel">
                    <div class="item">
                        <h1>Belog</h1>
                        <div class="clear"></div>
                        <h3>
                            打造一个快速的，友好的，可以插件化博客程序<br />
                            学习是一个过程.
                        </h3>
                        <div class="clear"></div>
                        <button class="btn btn-lg btn-warning btn-learn-more btn-border-only">了解更多</button>
                        <a href="http://beldon.me" target="_blank" class="btn btn-lg btn-success btn-learn-more">Belog博客</a>
                    </div>
                    <div class="item">
                        <h1>分享才能学到更多</h1>
                        <div class="clear"></div>
                        <h3>
                            三人行，必有我师<br />
                            懂得分享，才会学到更多。
                        </h3>
                        <div class="clear"></div>
                        <button class="btn btn-lg btn-warning btn-learn-more btn-border-only">我看行</button>
                    </div>
                    <div class="item">
                        <h1>精益求精</h1>
                        <div class="clear"></div>
                        <h3>
                            没有事能一下子做得完美，但相信努力就会有好结果<br />
                            坚持才会有好成就.
                        </h3>
                        <div class="clear"></div>
                        <a href="http://beldon.me" target="_blank" class="btn btn-lg btn-success btn-learn-more">Beldon</a>
                    </div><!-- /.item -->
                </div><!-- /.#content-header-slide -->
            </div><!-- /.container -->
        </div><!-- /.slide-text-content -->
    </div><!-- /.slide-inner -->
</div><!-- /.full-slide-image -->
<!-- END HEADER FULL IMAGE SLIDE -->



<!-- BEGIN TEXT SECTION -->
<div class="section">
    <div class="container">
        <p class="text-center text-slogan">
            生活赋予我们一种巨大的和无限高贵的礼品，这就是青春：充满着力量，充满着期待志愿，充满着求知和斗争的志向，充满着希望信心和青春。<br />
            最糟糕的是人们在生活中经常受到错误志向的阻碍而不自知，真到摆脱了那些阻碍时才能明白过来。.<br />
            勇敢坚毅真正之才智乃刚毅之志向.
        </p>
    </div><!-- /.container -->
</div><!-- /.section -->
<!-- END TEXT SECTION -->



<!-- BEGIN FEATURE SECTION -->
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="the-box bg-danger no-border box-feature text-center">
                    <h4 class="heading">友好型</h4>
                    <i class="fa fa-css3 icon-lg"></i>
                    <div class="p-wrap">
                        <p>
                            希望能共同打造一个友好型的网站，
                        </p>
                    </div><!-- /.p-wrap -->
                    <div class="btn-wrap">
                        <button class="btn btn-danger btn-learn-more">了解更多</button>
                    </div><!-- /.btn-wrap -->
                </div><!-- /.the-box bg-danger no-border box-feature text-center -->
            </div><!-- /.col-sm-3 -->
            <div class="col-sm-3">
                <div class="the-box bg-warning no-border box-feature text-center">
                    <h4 class="heading">拓展性</h4>
                    <i class="fa fa-female icon-lg"></i>
                    <p>
                        拓展性强的程序乃是一个好程序
                    </p>
                    <div class="btn-wrap">
                        <button class="btn btn-warning btn-learn-more">了解更多</button>
                    </div><!-- /.btn-wrap -->
                </div><!-- /.the-box bg-warning no-border box-feature text-center -->
            </div><!-- /.col-sm-3 -->
            <div class="col-sm-3">
                <div class="the-box bg-success no-border box-feature text-center">
                    <h4 class="heading">模板</h4>
                    <i class="fa fa-circle-o-notch icon-lg"></i>
                    <p>
                        模板，自己DIY
                    </p>
                    <div class="btn-wrap">
                        <button class="btn btn-success btn-learn-more">了解更多</button>
                    </div><!-- /.btn-wrap -->
                </div><!-- /.the-box bg-success no-border box-feature text-center -->
            </div><!-- /.col-sm-3 -->
            <div class="col-sm-3">
                <div class="the-box bg-info no-border box-feature text-center">
                    <h4 class="heading">代码分享</h4>
                    <i class="fa fa-code icon-lg"></i>
                    <p>
                        分享，才会学到更加多
                    </p>
                    <div class="btn-wrap">
                        <button class="btn btn-info btn-learn-more">了解更多</button>
                    </div><!-- /.btn-wrap -->
                </div><!-- /.the-box bg-info no-border box-feature text-center -->
            </div><!-- /.col-sm-3 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</div><!-- /.section -->
<!-- END FEATURE SECTION -->

<!-- BEGIN LATEST WORK SECTION -->
<div class="section work-section">
    <div class="container">
        <div class="section-heading">
            <div class="inner-border"></div>
            <h3>最新发布</h3>
        </div><!-- /.section-heading -->
        <div id="work-mixitup" class="work-content">
            <div class="row">
                <#list pagePm.results as article>
                    <!-- Begin work item -->
                    <div class="col-sm-4 col-md-3 col-xs-6 mix printing">
                        <div class="work-item">
                            <div class="hover-wrap">
                                <a href="${BASE_PATH}/article/details/${(article.id)!}.html">
                                    <i class="glyphicon glyphicon-plus icon-plus"></i>
                                </a>
                            </div><!-- /.hover-wrap -->
                            <#if article?exists && article.cover?exists && article.cover != ''>
                                <img src="${BASE_PATH}/${(article.cover)!}" onerror="javascript:this.src='${THEME_PATH}/img/work/1.jpg'" alt="Img">
                            <#else>
                                <img src="${THEME_PATH}/img/work/1.jpg" alt="Img work">
                            </#if>

                            <div class="the-box no-border transparent no-margin">
                                <p class="project-name">${(article.title)!}</p>
                            <#--<p class="project-category">${(article.title)!}</p>-->
                            </div><!-- /.the-box no-border transparent -->
                        </div><!-- /.work-item -->
                    </div><!-- /.col-sm-4 col-md-3 col-xs-6 mix -->
                    <!-- End work item -->
                </#list>


            </div><!-- /.row -->
        </div><!-- /#work-mixitup -->

    </div><!-- /.container -->
</div><!-- /.section -->
<!-- END LATEST WORK SECTION -->
</@html>