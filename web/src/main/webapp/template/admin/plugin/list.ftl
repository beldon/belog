<#include "../common/_layout.ftl"/>
<@html title="插件列表">
<div class="row">
    <div class="col-md-12">
        <!-- Default panel -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                插件列表
            </div>
            <div class="alert alert-success alert-dismissible hide" role="alert" id="alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>success!</strong> 操作成功！
            </div>
            <div class="panel-body">
                <table class="table table-model-2 table-hover">
                    <thead>
                    <tr>
                        <th width="50px">#</th>
                        <th>插件名称</th>
                        <th>插件作者</th>
                        <th>插件描述</th>
                        <th width="150px">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                        <#list list as plugin>
                        <tr id="${(plugin.id)!}">
                            <td>${(plugin_index+1)!}</td>
                            <td>${(plugin.name)!}</td>
                            <td>${(plugin.author)!}</td>
                            <td>插件描述</td>
                            <td>
                                <#if plugin.load>
                                    <button class="btn btn-white disable">禁用</button>
                                <#else >
                                    <button class="btn btn-white enable">启用</button>
                                </#if>
                                <button class="btn btn-warning delete">删除</button>
                            </td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {

        $(".enable ,.disable").click(function(){
            var id = $(this).parent().parent().attr("id");
            $.ajax({
                type: "POST",
                url: "able.json",
                data: {pluginId:id},
                dataType: "json",
                success: function (data) {
                    if (data.status === true) {
                        $("#alert-success").removeClass("hide");
                        setInterval(function(){
                            window.location.reload();
                        },1000)
                    } else {
                        alert("error");
                    }
                }
            });
        });
        $(".delete").click(function(){
            var id = $(this).parent().parent().attr("id");
            alert(id);
        });

        $("#js-save").click(function () {
            $.ajax({
                type: "POST",
                url: "ajaxEdit.json",
                data: $('#loginForm').serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.status === true) {
                        window.location.reload();
                    } else {
                        alert("error");
                    }
                }
            });
        });
    });
</script>
</@html>