<#include "../common/_layout.ftl"/>
<@html title="">
<div class="row">
    <div class="col-md-5">
        <!-- Default panel -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                编辑菜单
            </div>

            <div class="panel-body">
                <form class="form-horizontal" action="javascript:return false;" id="actionForm">
                    <input type="hidden" name="id" value="${(menu.id)!}">
                    <div class="form-group">
                        <label for="inputEmail3" class=" control-label">菜单名称</label>

                        <div class="col-sm-12">
                            <input type="text" name="name" value="${(menu.name)!}" class="form-control" id="inputEmail3" placeholder="分类名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="control-label">URL</label>

                        <div class="col-sm-12">
                            <input type="text" name="url" value="${(menu.url)!}" class="form-control" id="inputEmail3" placeholder="分类别名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="control-label">排序</label>

                        <div class="col-sm-12">
                            <input type="text" name="sort" value="${(menu.sort)!}" class="form-control" id="inputEmail3" placeholder="分类别名">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-8 col-sm-10">
                            <button type="submit" id="saveCat" class="btn btn-default">更新菜单</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>

</div>

<script type="text/javascript">
    $(document).ready(function(){
        $("#saveCat").click(function(){
            $.ajax({
                type: "POST",
                url: "addOrUpdate.json",
                data: $('#actionForm').serialize(),
                dataType: "json",
                success: function(data){
                    if(data.status===true){
                        window.location.href = 'list.html';
                    }else{
                        alert("error");
                    }
                }
            });
        });
    });
</script>
</@html>