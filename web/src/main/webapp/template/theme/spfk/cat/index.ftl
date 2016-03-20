<@article_tag type='cat' pageNo=page pageSize = 8 tag=key>
    <#assign articlePage=articlePage>
    <#assign category=category>
</@article_tag>
<#if articlePage.pageNo==1 >
    <#assign frontPage=1>
<#else >
    <#assign frontPage=articlePage.pageNo-1>
</#if>
<#if articlePage.pageNo==articlePage.totalPage >
    <#assign nextPage=articlePage.totalPage>
<#else >
    <#assign nextPage=articlePage.pageNo+1>
</#if>
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
<@html title= category.name+"-分类-">
<section class="archives-wrap">
    <div class="archive-year-wrap">
        <a href="/archives/2016" class="archive-year">${(category.name)!}</a>
    </div>
    <div class="archives">
        <#list articlePage.results as article>
            <article class="archive-article archive-type-post">
                <div class="archive-article-inner">
                    <header class="archive-article-header">
                        <div class="article-meta">
                            <a href="${BASE_PATH}/article/details/${(article.id)!}.html" class="archive-article-date">
                                <time datetime="2016-01-16T05:58:41.000Z" itemprop="datePublished">${(article.creDate?string("yyyy-MM-dd"))!}</time>
                            </a>
                        </div>
                        <h1 itemprop="name">
                            <a class="archive-article-title" href="${BASE_PATH}/article/details/${(article.id)!}.html">${(article.title)!}</a>
                        </h1>
                        <div class="article-info info-on-archive">
                            <div class="article-category tagcloud">
                                <#list article.cats as cat>
                                    <a class="article-category-link" href="${BASE_PATH}/cat/${(cat.id)!}.html">${(cat.name)!}</a>
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
        <nav id="page-nav">
            <#if articlePage.pageNo !=1>
                <a class="extend prev" rel="prev" href="${BASE_PATH}/cat/${key!}/${frontPage}.html">« Prev</a>
            </#if>
            <#list 1 .. articlePage.totalPage as page>
                <#if page == articlePage.pageNo>
                    <span class="page-number current">${page}</span>
                <#else>
                    <a class="page-number" href="${BASE_PATH}/cat/${key!}/${page}.html">${page}</a>
                </#if>
            </#list>
            <#if articlePage.pageNo !=articlePage.totalPage>
                <a class="extend next" rel="next" href="${BASE_PATH}/cat/${key!}/${nextPage}.html">Next &raquo;</a>
            </#if>
        </nav>
    </div>
</section>
<button class="hide-labels">CATEGORY 开关</button>
</@html>