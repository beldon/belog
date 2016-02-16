<#include "../common/_layout.ftl"/>

<@html title="模板设置">
<div class="row">
    <div class="col-md-12">
        <!-- Default panel -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                模板设置
            </div>

            <div class="panel-body">
                <form class="form-horizontal" action="javascript:return false;" method="post" id="actionForm">
                    <div class="form-group">
                        <label for="" class="col-sm-1 control-label">主题选择</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="theme" id="themeSelect">
                                <option value="default">default</option>
                                <option value="beldon" <#if theme = 'beldon'>selected="selected"</#if>>beldon</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-4">
                            <button type="submit" id="js-save" class="btn btn-default">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $("#js-save").click(function(){
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
    });
</script>
</@html>