<#macro header title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="Xenon Boostrap Admin Panel" />
    <meta name="author" content="" />

    <title>${title!""} - 后台管理</title>

<#--<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">-->
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/fonts/linecons/css/linecons.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/bootstrap.min.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/xenon-core.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/xenon-forms.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/xenon-components.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/xenon-skins.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/custom.css">


    <link rel="stylesheet" href="${BASE_PATH}/static/admin/js/artDialog/css/ui-dialog.css">


    <script src="${BASE_PATH}/static/admin/js/jquery-1.11.1.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${BASE_PATH}/static/admin/js/html5shiv.min.js"></script>
    <script src="${BASE_PATH}/static/admin/js/respond.min.js"></script>
    <![endif]-->


</head>
<body class="page-body">
<input type="hidden" id="basePath" value="${BASE_PATH}/">
<div class="page-container">
</#macro>