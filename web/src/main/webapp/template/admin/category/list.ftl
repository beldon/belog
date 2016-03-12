<#include "../common/_layout.ftl"/>
<#--<@html title="分类列表">-->
<@html title="分类列表">
<div class="row">
    <div class="col-md-4">
        <!-- Default panel -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                添加新分类目录
            </div>

            <div class="panel-body">
                <form class="form-horizontal" action="javascript:return false;" id="actionForm">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">名称</label>

                        <div class="col-sm-12">
                            <input type="text" name="name" class="form-control" id="inputEmail3" placeholder="分类名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">别名</label>

                        <div class="col-sm-12">
                            <input type="text" name="slug" class="form-control" id="inputEmail3" placeholder="分类别名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">父节</label>

                        <div class="col-sm-12">
                            <select class="form-control" name="parent">
                                <option value="0">无</option>
                                <#list cats as cat>
                                    <option value="${(cat.category.id)!}">${(cat.category.name)!}</option>
                                    <#list cat.subs as sub>
                                        <option value="${(sub.category.id)!}">${(sub.category.name)!}</option>
                                    </#list>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-6 col-sm-10">
                            <button type="submit" id="saveCat" class="btn btn-default">添加新分类目录</button>
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
                分类目录
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-model-2 table-hover">
                        <thead>
                        <tr>
                            <th width="40px"><input type="checkbox"></th>
                            <th>名称</th>
                            <th>别名</th>
                            <th>总数</th>
                            <th width="150px">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list cats as cat>
                            <tr>
                                <th><input type="checkbox"></th>
                                <th>${(cat.category.name)!}</th>
                                <td>${(cat.category.slug)!}</td>
                                <td>${(cat.category.count)!}</td>
                                <td>
                                    <a href="edit.html?id=${(cat.category.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">编辑</a>
                                    <a href="#" catId="${(cat.category.id)!}" class="delete btn btn-danger btn-sm btn-icon icon-left">删除</a>
                                </td>
                            </tr>

                                <#list cat.subs as sub>
                                <tr>
                                    <th><input type="checkbox"></th>
                                    <th>|-- &nbsp;${(sub.category.name)!}</th>
                                    <td>${(sub.category.slug)!}</td>
                                    <td>${(sub.category.count)!}</td>
                                    <td>
                                        <a href="edit.html?id=${(sub.category.id)!}" class="btn btn-secondary btn-sm btn-icon icon-left">编辑</a>
                                        <a href="#" catId="${(sub.category.id)!}" class="delete btn btn-danger btn-sm btn-icon icon-left">删除</a>
                                    </td>
                                </tr>
                                </#list>
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

        $('#actionForm').bootstrapValidator({
            message: 'This value is not valid',
            fields: {
                name: {
                    message: '名称不能为空',
                    validators: {
                        notEmpty: {
                            message: '名称不能为空'
                        }
                    }
                },
                slug: {
                    message: '别名必须为数字或字母',
                    validators: {
                        regexp: {
                            regexp: /^[a-zA-Z0-9_]+$/,
                            message: '别名必须为数字或字母'
                        }
                    }
                }
            }
        }).on('success.form.bv', function(e) {
            $.ajax({
                type: "POST",
                url: "ajaxEdit.json",
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
                        window.location.reload();
                    }else{
                        alert("error");
                    }
                }
            });
        });
    });
</script>
</@html>