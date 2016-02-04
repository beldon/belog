<#include "../public/header.ftl"/>
<#if pm.currentPage==1 >
    <#assign frontPage=1>
<#else >
    <#assign frontPage=pm.currentPage-1>
</#if>
<#if pm.currentPage==pm.totalPage >
    <#assign nextPage=pm.totalPage>
<#else >
    <#assign nextPage=pm.currentPage+1>
</#if>
<body class="page-body">
<div class="page-container">

<#include "../public/aside.ftl"/>

    <div class="main-content">
    <#include "../public/content-nav.ftl"/>
        <!-- Removing search and results count filter -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">文章列表</h3>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <div class="alert alert-success alert-dismissible hide" role="alert" id="alert-success">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>success!</strong> 删除成功！
                    </div>
                    <table class="table table-model-2 table-hover">
                        <thead>
                        <tr>
                            <th width="40px"> <input type="checkbox"> </th>
                            <th>标题</th>
                            <th>作者</th>
                            <th>分类目录</th>
                            <th width="60px">评论</th>
                            <th width="170px">日期</th>
                            <th width="150px">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pm.list as article>
                        <tr>
                            <th> <input type="checkbox"> </th>
                            <th>${(article.title)!}</th>
                            <td>${(article.userVo.nickName)!}</td>
                            <td>
                                <#list (article.cats)! as cat>
                                    <#if cat_index == 0>
                                    ${(cat.name)!}
                                    <#else >
                                     | ${(cat.name)!}
                                    </#if>

                                </#list>
                            </td>
                            <td>${(article.commentCount?default(0))!}</td>
                            <td>${(article.date)!}</td>
                            <td>
                                <a href="edit.html?id=${(article.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">编辑</a>
                                <a href="#" aid="${(article.id)!}" class="btn btn-danger btn-sm btn-icon icon-left delete">删除</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                    <nav>
                        <ul class="pagination pull-right">
                            <li><a href="${(BASE_PATH)}/admin/article/list.html?currentPage=${(frontPage)!}">上一页</a></li>
                            <li><a href="#">${(pm.currentPage)!}/${(pm.totalPage)!}</a></li>
                            <li><a href="${(BASE_PATH)}/admin/article/list.html?currentPage=${(nextPage)!}">下一页</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>

    <#include "../public/content-footer.ftl"/>
    </div>

</div>
<!-- Imported styles on this page -->
<#include "../public/footer.ftl"/>

<script type="text/javascript">
    $(document).ready(function(){
        $(".delete").click(function(){
            var id = $(this).attr("aid");
            dialog({
                title: '警告',
                content: '你确定要删除该记录么？',
                okValue: '确 定',
                cancelValue: '取消',
                ok: function () {
                    $.ajax({
                        type: "POST",
                        url: "delete.json",
                        data: {id:id},
                        dataType: "json",
                        success: function(data){
                            if(data.status===true){
                                $("#alert-success").removeClass("hide");
                                setInterval(function(){
                                    window.location.reload();
                                },1000)
                            }else{
                                alert("error");
                            }
                        }
                    });
                },
                cancel: function () {}
            }).showModal();

        });
    });
</script>