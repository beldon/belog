<!-- BEGIN TOP NAVBAR -->
<div class="top-navbar dark-color">
    <div class="container">

        <!-- Begin logo -->
        <div class="logo">
            <a href="${BASE_PATH}/index.html"><img src="${THEME_PATH}/img/logo3.png" alt="Logo"></a>
        </div><!-- /.logo -->
        <!-- End logo -->

        <!-- Begin button toggle nav -->
        <div class="btn-toggle-nav" id="btn-toggle-nav"><i class="fa fa-bars"></i></div>
        <div class="btn-toggle-search" id="btn-icon-search"><i class="fa fa-search"></i></div>
        <div class="btn-toggle-phone" id="btn-icon-phone"><i class="fa fa-phone"></i></div>
        <!-- End button toggle nav -->

        <!-- Begin visible phone and search nav -->
        <div id="search-sub" class="nav-right-info">
            <i class="fa fa-times times-icon" id="close-search-nav"></i>
            <form role="form">
                <input type="text" class="form-control" placeholder="搜索一下...">
            </form>
        </div>
        <!-- End visible phone and search nav -->

        <!-- Begin nav menu -->
        <ul class="menus">
            <li class="parent">
                <a href="${BASE_PATH}/index.html">首页</a>
            </li>
            <li class="parent">
                <a href="${BASE_PATH}/discover/">发现</a>
            </li>
            <li class="parent">
                <a href="${BASE_PATH}/article/">博客</a>
            </li>

            <#--<!-- Begin right icon &ndash;&gt;-->
            <#--<li class="parent right-icon">-->
                <#--<i class="fa fa-search" id="nav-icon-search"></i>-->
            <#--</li>-->
            <#--<!-- End right icon &ndash;&gt;-->
        </ul>
        <!-- End nav menu -->
    </div><!-- /.container -->
</div><!-- /.top-navbar -->
<!-- END TOP NAVBAR -->