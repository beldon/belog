<#macro content_nav>
<!-- User Info, Notifications and Menu Bar -->
<nav class="navbar user-info-navbar" role="navigation">

    <!-- Left links for user info navbar -->
    <ul class="user-info-menu left-links list-inline list-unstyled">

        <li class="hidden-sm hidden-xs">
            <a href="#" data-toggle="sidebar">
                <i class="fa-bars"></i>
            </a>
        </li>

    </ul>

    <!-- Right links for user info navbar -->
    <ul class="user-info-menu right-links list-inline list-unstyled">

        <#--<li class="search-form"><!-- You can add "always-visible" to show make the search input visible &ndash;&gt;-->

            <#--<form method="get" action="extra-search.html">-->
                <#--<input type="text" name="s" class="form-control search-field" placeholder="输入您想查询的..."/>-->

                <#--<button type="submit" class="btn btn-link">-->
                    <#--<i class="linecons-search"></i>-->
                <#--</button>-->
            <#--</form>-->

        <#--</li>-->
        <li class="dropdown user-profile">
            <a href="#" data-toggle="dropdown">
                <img src="${BASE_PATH}/static/admin//images/user-4.png" alt="user-image"
                     class="img-circle img-inline userpic-32" width="28"/>
							<span>
								超级管理员
								<i class="fa-angle-down"></i>
							</span>
            </a>

            <ul class="dropdown-menu user-profile-menu list-unstyled">
                <li>
                    <a href="${BASE_PATH}/admin/article/edit.html">
                        <i class="fa-edit"></i>
                        写文章
                    </a>
                </li>
                <li>
                    <a href="${BASE_PATH}/admin/config/global.html">
                        <i class="fa-wrench"></i>
                        设置
                    </a>
                </li>
                <li>
                    <a href="${BASE_PATH}/admin/user/profile.html">
                        <i class="fa-user"></i>
                        个人中心
                    </a>
                </li>
                <li>
                    <a href="#help">
                        <i class="fa-info"></i>
                        帮助
                    </a>
                </li>
                <li class="last">
                    <a href="#logout" id="logout">
                        <i class="fa-lock"></i>
                        退出
                    </a>
                </li>
            </ul>
        </li>
    </ul>

</nav>
</#macro>