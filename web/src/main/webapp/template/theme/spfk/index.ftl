<#include "common/_layout.ftl"/>
<@article_tag type='list' pageNo=pageNo pageSize = 8>
    <#assign articlePage=articlePage>
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

<@html title="">

    <#list articlePage.results as article>
    <article id="post-hello-world" class="article article-type-post" itemscope itemprop="blogPost">

        <div class="article-meta">
            <a href="${BASE_PATH}/article/details/${(article.id)!}.html" class="article-date">
                <time datetime="2016-01-16T05:58:41.000Z" itemprop="datePublished">${(article.creDate?string("yyyy-MM-dd"))!}</time>
            </a>
        </div>

        <div class="article-inner">
            <input type="hidden" class="isFancy" />
            <header class="article-header">
                <h1 itemprop="name">
                    <a class="article-title" href="${BASE_PATH}/article/details/${(article.id)!}.html">${(article.title)!}</a>
                </h1>
            </header>

            <div class="article-entry" itemprop="articleBody">
                dddd
            </div>

            <div class="article-info article-info-index">
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
                <p class="article-more-link">
                    <a  href="#">${(article.user.nickname)!} >></a>
                </p>

                <div class="clearfix"></div>
            </div>
        </div>
    </article>
    </#list>

<nav id="page-nav">
    <#if articlePage.pageNo !=1>
    <a class="extend prev" rel="prev" href="${BASE_PATH}/index_${frontPage}.html">« Prev</a>
    </#if>
    <#list 1 .. articlePage.totalPage as page>
        <#if page == articlePage.pageNo>
            <span class="page-number current">${page}</span>
        <#else>
            <a class="page-number" href="${BASE_PATH}/index_${page}.html">${page}</a>
        </#if>
    </#list>
    <#if articlePage.pageNo !=articlePage.totalPage>
    <a class="extend next" rel="next" href="${BASE_PATH}/index_${nextPage}.html">Next &raquo;</a>
    </#if>
</nav>
</@html>