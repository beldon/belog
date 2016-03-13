<@article_tag type='tag' pageNo=pageNo pageSize = 8 tag=key>
    <#assign articlePage=articlePage>
</@article_tag>
<#include "../common/_layout.ftl"/>
<#assign javascript>
<script>
    $(document).ready(function() {
        $("button").click(function() {
            $(".article-info").toggle(200);
        });
    });
</script>
</#assign>
<@html title="">
<section class="archives-wrap">
    <div class="archive-year-wrap">
        <a href="/archives/2016" class="archive-year">${key!}</a>
    </div>
    <div class="archives">
        <#list articlePage.results as article>
            <article class="archive-article archive-type-post">
                <div class="archive-article-inner">
                    <header class="archive-article-header">
                        <div class="article-meta">
                            <a href="/2016/01/16/hello-world/" class="archive-article-date">
                                <time datetime="2016-01-16T05:58:41.000Z" itemprop="datePublished">${(article.creDate?string("yyyy-MM-dd"))!}</time>
                            </a>
                        </div>
                        <h1 itemprop="name">
                            <a class="archive-article-title" href="/2016/01/16/hello-world/">${(article.title)!}</a>
                        </h1>
                        <div class="article-info info-on-archive">
                            <div class="article-category tagcloud">
                                <#list article.cats as cat>
                                    <a class="article-category-link" href="/categories/HTML-书籍/">${(cat.name)!}</a>
                                </#list>
                            </div>
                            <div class="article-tag tagcloud">
                                <ul class="article-tag-list">
                                    <#list article.tags as tag>
                                        <li class="article-tag-list-item"><a class="article-tag-list-link" href="${BASE_PATH}/tag/${(tag.name)!}.html">${(tag.name)!}</a></li>
                                    </#list>
                                </ul>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </header>
                </div>
            </article>
        </#list>

    </div>
</section>
<button class="hide-labels">TAG 开关</button>
</@html>