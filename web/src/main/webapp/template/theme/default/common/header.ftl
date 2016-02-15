<#macro header title>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="${(description?default("Beldon"))!}">
    <meta name="keywords" content="${(keywords?default("Beldon"))!}">
    <meta name="author" content="Beldon">
    <title>${title!""}${(siteName?default("Beldon"))!}-这不仅仅是个博客</title>
    <!-- BOOTSTRAP CSS (REQUIRED ALL PAGE)-->
    <link href="${THEME_PATH}/css/bootstrap.min.css" rel="stylesheet">
    <!-- PLUGINS CSS-->
    <link href="${THEME_PATH}/plugins/magnific-popup/magnific-popup.css" rel="stylesheet">
    <link href="${THEME_PATH}/plugins/owl-carousel/owl.carousel.css" rel="stylesheet">
    <link href="${THEME_PATH}/plugins/owl-carousel/owl.theme.css" rel="stylesheet">
    <link href="${THEME_PATH}/plugins/owl-carousel/owl.transitions.css" rel="stylesheet">
    <!-- MAIN CSS (REQUIRED ALL PAGE)-->
    <link href="${THEME_PATH}/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${THEME_PATH}/css/style.css" rel="stylesheet">
    <link href="${THEME_PATH}/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="tooltips">
</#macro>