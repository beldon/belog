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
    <style type="text/css">
        .cover {
            position: fixed;
            top: 0px;
            right: 0px;
            bottom: 0px;
            filter: alpha(opacity=60);
            background-color: #777;
            z-index: 1002;
            left: 0px;
            display: none;
            opacity: 0.5;
            -moz-opacity: 0.5;
        }
        .process {
            margin-top: 20%;
            width: 80%;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="col-md-10 col-md-offset-1">
        <ol class="breadcrumb">
            <li>首页</li>
            <li>数据库信息</li>
            <li>用户信息</li>
            <li class="active">安装</li>
        </ol>

        <div class="alert alert-danger alert-dismissible hide" role="alert" id="alert-danger">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span></button>
            <strong>错误!</strong> 安装失败，请检配置信息！
        </div>

        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                Belog安装
            </div>
            <div class="panel-body">
                <div id="content">
                    好了，一切都已经准备好了，点击安装进行安装吧~
                </div>
            </div>
        </div>
        <div class="btn-group pull-right" role="group" aria-label="belog安装程序">
            <a href="${BASE_PATH}/user.html" id="front" type="button" class="btn btn-default">上一步</a>
            <a href="${BASE_PATH}/index.html" id="index" type="button" class="btn btn-default" style="display: none">访问网站首页</a>
            <button id="install" type="button" class="btn btn-default">安装</button>
        </div>
    </div>
    <div id="cover" class="cover">
        <div class="progress process">
            <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100"
                 aria-valuemin="0" aria-valuemax="100" style="width: 100%">
                <span class="sr-only">正在安装</span>正在安装...
            </div>
        </div>
    </div>
</div>

<script src="${BASE_PATH}/static/default/js/jquery-1.11.1.min.js"></script>
<script src="${BASE_PATH}/static/default/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#install").click(function () {

            $('body').css("overflow", "hidden")
            $("#cover").show();

            $.ajax({
                type: "POST",
                url: "install.json",
                dataType: "json",
                success: function (data) {
                    if (data.status === true) {
                    <#--window.location.href = '${BASE_PATH}/user.html';-->
                        $("#content").html("好了，程序已经安装好，安装程序任务已经执行完毕，请等待系统的重启。。。");
                        $("#front").hide();
                        $("#install").hide();
                        $("#index").show();
                    } else {
                        $("#alert-danger").removeClass("hide");
                        setInterval(function () {
                            $("#alert-danger").hide();
                        }, 3000)
                    }
                    $('body').css("overflow", "auto")
                    $("#cover").hide();
//                    overflow:auto;
                }
            });
        });
    });
</script>

</body>
</html>