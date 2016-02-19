<#include "../common/_layout.ftl"/>
<@html title="网站设置">
<div class="row">
    <div class="col-md-12">
        <!-- Default panel -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                网站设置
            </div>
            <div class="alert alert-success alert-dismissible hide" role="alert" id="alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>success!</strong> 修改成功！
            </div>
            <div class="panel-body">
                <form class="form-horizontal" action="javascript:return false;" method="post" id="actionForm">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-1 control-label">网站名称</label>
                        <div class="col-sm-4">
                            <input type="text" name="siteName" value="${(siteName)!}" class="form-control" id="inputEmail3" placeholder="网站名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-1 control-label">网站网址</label>

                        <div class="col-sm-4">
                            <input type="text" name="domain" value="${(domain)!}" class="form-control" id="inputEmail3" placeholder="网站域名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3"class="col-sm-1 control-label">网站描述</label>

                        <div class="col-sm-4">
                            <input type="text" name="description" value="${(description)!}" class="form-control" id="inputEmail3" placeholder="网站描述">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-1 control-label">备案号</label>

                        <div class="col-sm-4">
                            <input type="text" name="icp_num" value="${(icp_num)!}" class="form-control" id="inputEmail3" placeholder="备案号">
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

        $('#actionForm').bootstrapValidator({
            message: 'This value is not valid',
            fields: {
                domain: {
                    validators: {
                        regexp: {
                            regexp: /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/,
                            message: '请输入合法的网址'
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
                        $("#alert-success").removeClass("hide");
                        setInterval(function(){
                            window.location.reload();
                        },2000)
                    }else{
                        alert("error");
                    }
                }
            });
        });
    });
</script>
</@html>