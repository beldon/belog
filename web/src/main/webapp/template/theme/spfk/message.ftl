<#include "common/_layout.ftl"/>
<@html title= "留言-">
<article id="post-hello-world" class="article article-type-post" itemscope itemprop="blogPost">

    <div class="article-meta">
        <a href="#" class="article-date">
            <time datetime="2016-01-16T05:58:41.000Z" itemprop="datePublished">2016-01-16</time>
        </a>
    </div>

    <div class="article-inner">
        <input type="hidden" class="isFancy"/>
        <header class="article-header">
            <h1 class="article-title" itemprop="name">
             留言
            </h1>
        </header>


        <div class="article-entry" itemprop="articleBody">
             若对本博客或本博客程序有好的意见或建议，欢迎留言
        </div>
    </div>

    <div class="copyright">
        <p><span>本文标题:</span><a href="${BASE_PATH}/message.html">留言</a></p>
        <p><span>文章作者:</span><a href="#" title="访问Beldon的个人博客">Beldon</a></p>
        <p><span>发布时间:</span>2016-3-20 21:00:00</p>
        <p>
            <span>原始链接:</span><a class="post-url" href="${BASE_PATH}/message.html" title="前端知识体系">${BASE_PATH}/message.html</a>
            <span class="copy-path" data-clipboard-text="标题: 留言 原文: ${BASE_PATH}/message.html 作者: Beldon"
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

<script src="${THEME_PATH}/js/jquery-1.9.1.min.js"></script>
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

</div>



<script>

</script>
</@html>