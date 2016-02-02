<#include "../public/header.ftl"/>
<body class="page-body">
<div class="page-container">

<#include "../public/aside.ftl"/>

    <div class="main-content">
    <#include "../public/content-nav.ftl"/>
        <!-- Removing search and results count filter -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                <h3 class="panel-title">用户列表</h3>
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
                            <th>用户名</th>
                            <th>姓名</th>
                            <th>电子邮件</th>
                            <th>角色</th>
                            <th>文章</th>
                            <th width="150px">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list pm.list as user>
                            <tr>
                                <th> <input type="checkbox"> </th>
                                <th>${(user.login)!}</th>
                                <td>${(user.nickName)!}</td>
                                <td>${(user.email)!}</td>
                                <td>${(user.roleName?default("默认"))!}</td>
                                <td>${(user.postCount?default("0"))!}</td>
                                <td>
                                    <a href="${BASE_PATH}/admin/user/edit.html?id=${(user.id)?default("0")!}" class="btn btn-secondary btn-sm btn-icon icon-left">编辑</a>
                                    <a href="#" uid="${(user.id)!}" class="btn btn-danger btn-sm btn-icon icon-left delete">删除</a>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    <#include "../public/content-footer.ftl"/>
    </div>

</div>
<!-- Imported styles on this page -->
<link rel="stylesheet" href="${BASE_PATH}/static/admin//js/datatables/dataTables.bootstrap.css">
<#include "../public/footer.ftl"/>

<script type="text/javascript">
    $(document).ready(function(){
        $(".delete").click(function(){
            var id = $(this).attr("uid");
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