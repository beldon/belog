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
            <li class="active">首页</li>
            <li>数据库信息</li>
            <li>用户信息</li>
            <li>安装</li>
        </ol>
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                Belog安装说明
            </div>
            <div class="panel-body">
                Belog安装说明
                <p>如果你准备好了，点击下一步进行数据库信息配置</p>
            </div>
        </div>
        <div class="btn-group pull-right" role="group" aria-label="belog安装程序">
            <button type="button" class="btn btn-default" disabled="disabled">上一步</button>
            <a href="${BASE_PATH}/database.html" type="button" class="btn btn-default">下一步</a>
        </div>
    </div>
</div>

<script src="${BASE_PATH}/static/default/js/jquery-1.11.1.min.js"></script>
<script src="${BASE_PATH}/static/default/js/bootstrap.min.js"></script>
</body>
</html>