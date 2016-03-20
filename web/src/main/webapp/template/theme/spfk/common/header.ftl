<@tags_tag>
    <#assign tags=tags>
</@tags_tag>
<@links_tag type="list">
    <#assign linksPage=linksPage>
</@links_tag>
<#macro header title>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <title>${title!}Belog</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description">
    <meta property="og:type" content="website">
    <meta property="og:title" content="Hexo">
    <meta property="og:url" content="http://${BASE_PATH}/index.html">
    <meta property="og:site_name" content="Hexo">
    <meta property="og:description">
    <meta name="twitter:card" content="summary">
    <meta name="twitter:title" content="Hexo">
    <meta name="twitter:description">
    <link rel="alternative" href="${THEME_PATH}/atom.xml" title="Belog" type="application/atom+xml">
    <link rel="icon" href="${THEME_PATH}/img/favicon.png">
    <link rel="stylesheet" href="${THEME_PATH}/css/animate.min.css">
    <link rel="stylesheet" href="${THEME_PATH}/css/style.css">
    <link rel="stylesheet" href="${THEME_PATH}/font-awesome/css/font-awesome.min.css">
    <link rel="apple-touch-icon" href="${THEME_PATH}/img/apple-touch-icon.png">
    <link rel="stylesheet" href="${THEME_PATH}/fancybox/jquery.fancybox.css">

    <!-- 加载特效 -->
    <script src="${THEME_PATH}/js/pace.js"></script>
    <link href="${THEME_PATH}/css/pace/pace-theme-flash.css" rel="stylesheet" />
    <script>
        var yiliaConfig = {
            fancybox: true,
            animate: true,
            isHome: true,
            isPost: false,
            isArchive: false,
            isTag: false,
            isCategory: false,
            open_in_new: false
        }
    </script>

    ${css!}
</head>
<body>
<input type="hidden" id="themePath" value="${THEME_PATH}">
<div id="container">
    <div class="left-col">
        <div class="overlay"></div>
        <div class="intrude-less">
            <header id="header" class="inner">
                <a href="/" class="profilepic">
                    <img lazy-src="${THEME_PATH}/img/head.jpg" class="js-avatar">
                </a>
                <hgroup>
                    <h1 class="header-author"><a href="/">Beldon</a></h1>
                </hgroup>

                <div id="switch-btn" class="switch-btn">
                    <div class="icon">
                        <div class="icon-ctn">
                            <div class="icon-wrap icon-house" data-idx="0">
                                <div class="birdhouse"></div>
                                <div class="birdhouse_holes"></div>
                            </div>
                            <div class="icon-wrap icon-ribbon hide" data-idx="1">
                                <div class="ribbon"></div>
                            </div>

                            <div class="icon-wrap icon-link hide" data-idx="2">
                                <div class="loopback_l"></div>
                                <div class="loopback_r"></div>
                            </div>


                            <div class="icon-wrap icon-me hide" data-idx="3">
                                <div class="user"></div>
                                <div class="shoulder"></div>
                            </div>

                        </div>

                    </div>
                    <div class="tips-box hide">
                        <div class="tips-arrow"></div>
                        <ul class="tips-inner">
                            <li>菜单</li>
                            <li>标签</li>
                            <li>友情链接</li>
                            <li>关于我</li>
                        </ul>
                    </div>
                </div>

                <div id="switch-area" class="switch-area">
                    <div class="switch-wrap">
                        <section class="switch-part switch-part1">
                            <nav class="header-menu">
                                <ul>

                                    <li><a href="${BASE_PATH}/">博客首页</a></li>
                                    <#list currentMenus as menu>
                                        <li><a href="${menu.url}">${menu.name}</a></li>
                                    </#list>
                                </ul>
                            </nav>
                            <nav class="header-nav">
                                <ul class="social">
                                    <a class="fl mail" target="_blank" href="${spfk_email}" title="mail">mail</a>
                                    <a class="fl github" target="_blank" href="${spfk_github}" title="github">github</a>
                                    <a class="fl zhihu" target="_blank" href="${spfk_zhihu}" title="zhihu">zhihu</a>
                                    <a class="fl weibo" target="_blank" href="${spfk_weibo}" title="weibo">weibo</a>
                                    <#--<a class="fl weibo" target="_blank" href="http://weibo.com/beldon1" title="weibo">weibo</a>-->
                                    <#--<a class="fl rss" target="_blank" href="/atom.xml" title="rss">rss</a>-->
                                </ul>
                            </nav>
                        </section>
                        <section class="switch-part switch-part2">
                            <div class="widget tagcloud" id="js-tagcloud">
                                <#list tags as tag>
                                    <a href="${BASE_PATH}/tag/${(tag.name)!}.html" style="font-size: 20px;">${(tag.name)!}</a>
                                </#list>
                            </div>
                        </section>
                        <section class="switch-part switch-part3">
                            <div id="js-friends">
                                <a target="_blank" class="main-nav-link switch-friends-link" href="http://beldon.me">Beldon</a>
                                <#list linksPage.results as link>
                                    <a target="_blank" class="main-nav-link switch-friends-link" href="${(link.url)!}">${(link.name)!}22</a>
                                </#list>
                            </div>
                        </section>
                        <section class="switch-part switch-part4">
                            <#--<div id="js-aboutme">90后，水瓶座普通男青年！爱折腾，爱运动，更爱游离于错综复杂的编码与逻辑中，无法自拔。</div>-->
                            <div id="js-aboutme">${spfk_aboutme!}</div>
                        </section>
                    </div>
                </div>
            </header>
        </div>
    </div>
    <div class="mid-col">
</#macro>