<#include "../common/_layout.ftl"/>
<@article_tag articleId=intVal>
    <#assign pageArticle=article>
</@article_tag>
<@html title= pageArticle.title+"-">
<article id="post-hello-world" class="article article-type-post" itemscope itemprop="blogPost">

    <div class="article-meta">
        <a href="${BASE_PATH}/article/details/${(pageArticle.id)!}.html" class="article-date">
            <time datetime="" itemprop="datePublished">${(article.creDate?string("yyyy-MM-dd"))!}</time>
        </a>
    </div>

    <div class="article-inner">
        <input type="hidden" class="isFancy"/>
        <header class="article-header">
            <h1 class="article-title" itemprop="name">
                ${(pageArticle.title)!}
            </h1>
        </header>

        <div class="article-info article-info-post">
            <div class="article-category tagcloud">
                <#list pageArticle.cats as cat>
                    <a class="article-category-link" href="${BASE_PATH}/cat/${(cat.id)!}.html">${(cat.name)!}</a>
                </#list>
            </div>

            <div class="article-tag tagcloud">
                <ul class="article-tag-list">
                    <#list pageArticle.tags as tag>
                        <li class="article-tag-list-item"><a class="article-tag-list-link" href="${BASE_PATH}/tag/${(tag.name)!}.html">${(tag.name)!}</a></li>
                    </#list>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="article-entry" itemprop="articleBody">
            ${(pageArticle.content)!}
        </div>
    </div>

    <div class="copyright">
        <p><span>本文标题:</span><a href="${BASE_PATH}/article/details/${(pageArticle.id)!}.html">${(pageArticle.title)!}</a></p>
        <p><span>文章作者:</span><a href="#" title="访问Beldon的个人博客">${(pageArticle.user.nickname)!}</a></p>
        <p><span>发布时间:</span>${(pageArticle.creDate?string("yyyy年MM月dd日 - HH时mm分"))!}</p>
        <p><span>最后更新:</span>${(pageArticle.creDate?string("yyyy年MM月dd日 - HH时mm分"))!}</p>
        <p>
            <span>原始链接:</span><a class="post-url" href="${BASE_PATH}/article/details/${(pageArticle.id)!}.html" title="${(pageArticle.title)!}">${BASE_PATH}/article/details/${(pageArticle.id)!}.html</a>
            <span class="copy-path" data-clipboard-text="标题: ${(pageArticle.title)!} 原文: ${BASE_PATH}/article/details/${(pageArticle.id)!}.html 作者: ${(pageArticle.user.nickname)!}"
                  title="点击复制文章链接"><i class="fa fa-clipboard"></i></span>
            <script src="${THEME_PATH}/js/clipboard.min.js"></script>
            <script> var clipboard = new Clipboard('.copy-path'); </script>
        </p>
        <p>
            <span>许可协议:</span><i class="fa fa-creative-commons"></i> <a rel="license"
                                                                        href="http://creativecommons.org/licenses/by-nc-sa/3.0/cn/"
                                                                        title="中国大陆 (CC BY-NC-SA 3.0 CN)"
                                                                        target="_blank">"署名-非商用-相同方式共享 3.0"</a>
            转载请保留原文链接及作者。
        </p>
    </div>


    <#--<nav id="article-nav">-->


        <#--<a href="/2016/01/16/mytest/" id="article-nav-older" class="article-nav-link-wrap">-->
            <#--<div class="article-nav-title">前端知识体系</div>-->
            <#--<strong class="article-nav-caption">></strong>-->
        <#--</a>-->

    <#--</nav>-->


</article>

<div id="toc" class="toc-article">
    <strong class="toc-title">文章目录</strong>

</div>
<style>
    .left-col .switch-btn {
        display: none;
    }

    .left-col .switch-area {
        display: none;
    }
</style>

<input type="button" id="tocButton" value="隐藏目录" title="点击按钮隐藏或者显示文章目录">

<script src="http://7.url.cn/edu/jslib/comb/require-2.1.6,jquery-1.9.1.min.js"></script>
<script>
    var valueHide = "隐藏目录";
    var valueShow = "显示目录";

    if ($(".left-col").is(":hidden")) {
        $("#tocButton").attr("value", valueShow);
    }

    $("#tocButton").click(function () {
        if ($("#toc").is(":hidden")) {
            $("#tocButton").attr("value", valueHide);
            $("#toc").slideDown(320);
            $(".switch-btn, .switch-area").fadeOut(300);
        }
        else {
            $("#tocButton").attr("value", valueShow);
            $("#toc").slideUp(350);
            $(".switch-btn, .switch-area").fadeIn(500);
        }
    })

    if ($(".toc").length < 1) {
        $("#toc, #tocButton").hide();
        $(".switch-btn, .switch-area").show();
    }
</script>


<div class="bdsharebuttonbox">
    <a href="#" class="fx fa-weibo bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a>
    <a href="#" class="fx fa-weixin bds_weixin" data-cmd="weixin" title="分享到微信"></a>
    <a href="#" class="fx fa-qq bds_sqq" data-cmd="sqq" title="分享到QQ好友"></a>
    <a href="#" class="fx fa-files-o bds_copy" data-cmd="copy" title="分享到复制网址"></a>
</div>
<script>window._bd_share_config = {
    "common": {
        "bdSnsKey": {},
        "bdText": "",
        "bdMini": "2",
        "bdMiniList": false,
        "bdPic": "",
        "bdStyle": "2",
        "bdSize": "24"
    }, "share": {}
};
with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];</script>

    <#if comment_tag?exists>
        <@comment_tag>
        ${(code)!}
        </@comment_tag>
    </#if>


<div class="scroll" id="post-nav-button">

    <a href="/" title="回到主页"><i class="fa fa-home"></i></a>

    <#--<a title="文章列表"><i class="fa fa-bars"></i><i class="fa fa-times"></i></a>-->

    <#--<a href="/2016/01/16/mytest/" title="下一篇: 前端知识体系">-->
        <#--<i class="fa fa-angle-right"></i>-->
    <#--</a>-->

</div>
<#--<ul class="post-list">-->
    <#--<li class="post-list-item"><a class="post-list-link" href="/2016/01/16/hello-world/">前端知识体系</a></li>-->
    <#--<li class="post-list-item"><a class="post-list-link" href="/2016/01/16/mytest/">前端知识体系</a></li>-->
<#--</ul>-->
<script>
    $(".post-list").addClass("toc-article");
    $(".post-list-item a").attr("target", "_blank");
    $("#post-nav-button > a:nth-child(2)").click(function () {
        $(".fa-bars, .fa-times").toggle();
        $(".post-list").toggle(300);
        if ($(".toc").length > 0) {
            $("#toc, #tocButton").toggle(200, function () {
                if ($(".switch-area").is(":visible")) {
                    $("#toc, .switch-btn, .switch-area").toggle();
                    $("#tocButton").attr("value", valueHide);
                }
            })
        }
        else {
            $(".switch-btn, .switch-area").fadeToggle(300);
        }
    })
</script>


<script>

</script>
</@html>