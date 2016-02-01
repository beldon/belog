<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Belog安装程序</title>

    <!-- Bootstrap -->
    <link href="${BASE_PATH}/static/default/css/bootstrap.min.css" rel="stylesheet">

    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="col-md-10 col-md-offset-1">
        <ol class="breadcrumb">
            <li>首页</li>
            <li>数据库信息</li>
            <li class="active">网站信息</li>
            <li>安装</li>
        </ol>

        <form class="form-horizontal" action="javascript:return false;" id="actionForm">
            <div class="panel panel-default panel-border">
                <div class="panel-heading">
                    网站信息
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">网站名称</label>
                        <div class="col-sm-10">
                            <input type="text" value="" name="siteName" class="form-control"
                                   id="title" placeholder="网站名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">管理员账户</label>
                        <div class="col-sm-10">
                            <input type="text" value="" name="user" class="form-control"
                                   id="title" placeholder="管理员账户">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="text" value="" name="email" class="form-control"
                                   id="title" placeholder="管理员Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">管理密码</label>
                        <div class="col-sm-10">
                            <input type="text" value="" name="pass" class="form-control"
                                   id="title" placeholder="管理密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">确认密码</label>
                        <div class="col-sm-10">
                            <input type="text" value="" name="re_pass" class="form-control"
                                   id="title" placeholder="确认密码">
                        </div>
                    </div>
                </div>
            </div>
            <div class="btn-group pull-right" role="group" aria-label="belog安装程序">
                <a href="${BASE_PATH}/database.html" type="button" class="btn btn-default">上一步</a>
                <#--<a href="${BASE_PATH}/install.html" type="button" class="btn btn-default">安装</a>-->
                <button id="next" type="button" class="btn btn-default">安装</button>
            </div>
        </form>
    </div>
</div>

<script src="${BASE_PATH}/static/default/js/jquery-1.11.1.min.js"></script>
<script src="${BASE_PATH}/static/default/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        $("#next").click(function(){
            $.ajax({
                type: "POST",
                url: "user.json",
                data: $('#actionForm').serialize(),
                dataType: "json",
                success: function(data){
                    if(data.status===true){
                        if (data.errCode ==2) {
                            if(confirm("该用户已经存在，是否覆盖该数据")){
                                $.ajax({
                                    type: "POST",
                                    url: "cover.json",
                                    dataType: "json",
                                    success: function(data){
                                        if (data.status===true) {
                                            window.location.href = '${BASE_PATH}/install.html';
                                        }else{
                                            alert(data.errMsg);
                                        }
                                    }
                                })
                            }else{
                                window.location.href = '${BASE_PATH}/install.html';
                            }
                        }else{
                            window.location.href = '${BASE_PATH}/install.html';
                        }
                    }else{
                        alert("error");
                    }
                }
            });
        });
    });
</script>
</body>
</html>