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
        <div class="row">
            <div class="col-md-12">
                <!-- Bordered panel -->
                <div class="panel panel-default panel-border">
                    <div class="panel-heading">
                        标签列表
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
                                    <th width="40px"><input type="checkbox"></th>
                                    <th>名称</th>
                                    <th>文章总数</th>
                                    <th width="50px">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list pm.list as tag>
                                <tr>
                                    <th><input type="checkbox"></th>
                                    <th>${(tag.name)!}</th>
                                    <td>${(tag.count)!}</td>
                                    <td>
                                        <a href="#" tid="${(tag.id)!}" class="delete btn btn-danger btn-sm btn-icon icon-left">删除</a>
                                    </td>
                                </tr>
                                </#list>

                                </tbody>
                            </table>
                            <nav>
                                <ul class="pagination pull-right">
                                    <li><a href="${(BASE_PATH)}/admin/tag/list.html?currentPage=${(frontPage)!}">上一页</a></li>
                                    <li><a href="#">${(pm.currentPage)!}/${(pm.totalPage)!}</a></li>
                                    <li><a href="${(BASE_PATH)}/admin/tag/list.html?currentPage=${(nextPage)!}">下一页</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    <#include "../public/content-footer.ftl"/>
    </div>

</div>

<#include "../public/footer.ftl"/>

<script type="text/javascript">
    $(document).ready(function(){
        $(".delete").click(function(){
            var id = $(this).attr("tid");
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