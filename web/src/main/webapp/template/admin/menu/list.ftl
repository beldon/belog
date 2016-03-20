<#include "../common/_layout.ftl"/>
<@html title="添加菜单">
<div class="row">
    <div class="col-md-4">
        <!-- Default panel -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                添加菜单
            </div>

            <div class="panel-body">
                <form class="form-horizontal" action="javascript:return false;" id="actionForm">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">名称</label>
                        <div class="col-sm-12">
                            <input type="text" name="name" class="form-control" id="name" placeholder="菜单名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="url" class="col-sm-2 control-label">URL</label>
                        <div class="col-sm-12">
                            <input type="text" name="url" class="form-control" id="url" placeholder="菜单地址">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sort" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-12">
                            <input type="text" name="sort" class="form-control" id="sort" placeholder="菜单排序">
                        </div>
                    </div>
                    <#--<div class="form-group">-->
                        <#--<label for="inputPassword3" class="col-sm-3 control-label">父菜单</label>-->

                        <#--<div class="col-sm-12">-->
                            <#--<select class="form-control" name="parent">-->
                                <#--<option value="0">无</option>-->
                            <#--</select>-->
                        <#--</div>-->
                    <#--</div>-->
                    <div class="form-group">
                        <div class="col-sm-offset-6 col-sm-10">
                            <button type="submit" id="saveMenu" class="btn btn-default">添加新菜单</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>

    <div class="col-md-8">
        <!-- Bordered panel -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                首页菜单
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
                            <th>url</th>
                            <th width="70px">排序</th>
                            <th width="150px">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list menuList as menu>
                            <tr>
                                <th><input type="checkbox"></th>
                                <th>${(menu.name)!}</th>
                                <td>${(menu.url)!}</td>
                                <td>${(menu.sort)!}</td>
                                <td>
                                    <a href="edit.html?id=${(menu.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">编辑</a>
                                    <a href="#" catId="${(menu.id)!}" class="delete btn btn-danger btn-sm btn-icon icon-left">删除</a>
                                </td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $("#saveMenu").click(function(){
            $.ajax({
                type: "POST",
                url: "addOrUpdate.json",
                data: $('#actionForm').serialize(),
                dataType: "json",
                success: function(data){
                    if(data.status===true){
                        window.location.reload();
                    }else{
                        alert("error");
                    }
                }
            });
        });
        $(".delete").click(function(){
            var id = $(this).attr("catId");
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
        });
    });
</script>
</@html>