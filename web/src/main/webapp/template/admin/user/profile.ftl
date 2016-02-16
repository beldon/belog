<#include "../common/_layout.ftl"/>

<@html title="个人信息">
<div class="row">
    <div class="col-md-12">
        <!-- Default panel -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                个人信息
            </div>

            <div class="panel-body">
                <form class="form-horizontal" action="javascript:return false;" id="actionForm">
                    <input type="hidden" name="id" value="${(user.id)?default("0")!}">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-1 control-label">登陆名</label>
                        <div class="col-sm-4">
                            <input type="text" name="login" value="${(user.login)!}" disabled="disabled" class="form-control" id="inputEmail3" placeholder="登陆名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-1 control-label">用户名</label>
                        <div class="col-sm-4">
                            <input type="text" name="login" value="${(user.nickName)!}" class="form-control" id="inputEmail3" placeholder="用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-1 control-label">电子邮件</label>

                        <div class="col-sm-4">
                            <input type="text" name="email" value="${(user.email)!}" class="form-control" id="inputEmail3" placeholder="电子邮件">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-1 control-label">密码</label>

                        <div class="col-sm-4">
                            <input type="password" name="pass" value="" class="form-control" id="inputEmail3" placeholder="您的密码">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-4">
                            <button type="submit" id="js-save" class="btn btn-default">修改</button>
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
                url: "ajaxEdit.json",
                data: $('#actionForm').serialize(),
                dataType: "json",
                success: function(data){
                    if(data.status===true){
                        window.location.href = "list.html";
                    }else{
                        alert("error");
                    }
                }
            });
        });
    });
</script>
</@html>