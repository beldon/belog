<#macro nav>
<nav id="mobile-nav">
    <div class="overlay">
        <div class="slider-trigger"></div>
        <h1 class="header-author js-mobile-header hide"><a href="/" title="回到主页">John Doe</a></h1>
    </div>
    <div class="intrude-less">
        <header id="header" class="inner">
            <a href="/" class="profilepic">

                <img lazy-src="${THEME_PATH}/img/head.jpg" class="js-avatar">

            </a>
            <hgroup>
                <h1 class="header-author"><a href="/" title="回到主页">John Doe</a></h1>
            </hgroup>

            <nav class="header-menu">
                <ul>

                    <li><a href="/Home">博客首页</a></li>

                    <#--<#list currentMenus as menu>-->
                        <#--<li><a href="${menu.url}">${menu.name}</a></li>-->
                    <#--</#list>-->
                    <div class="clearfix"></div>
                </ul>
            </nav>
            <nav class="header-nav">
                <div class="social">

                    <a class="mail" target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=PAkNDgsKBQ4MCnxNTRJfU1E" title="mail">mail</a>

                    <a class="github" target="_blank" href="https://github.com/luuman" title="github">github</a>

                    <a class="zhihu" target="_blank" href="https://www.zhihu.com/people/luuman" title="zhihu">zhihu</a>

                    <a class="weibo" target="_blank" href="http://weibo.com/lishangmou" title="weibo">weibo</a>

                    <a class="google" target="_blank" href="http://i.youku.com/shangmou" title="google">google</a>

                    <a class="twitter" target="_blank" href="http://twitter.com/luuman" title="twitter">twitter</a>

                    <a class="facebook" target="_blank" href="#" title="facebook">facebook</a>

                    <a class="rss" target="_blank" href="/atom.xml" title="rss">rss</a>

                </div>
            </nav>
        </header>
    </div>
</nav>
</#macro>