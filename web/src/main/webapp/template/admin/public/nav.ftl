<ul id="main-menu" class="main-menu">
    <li>
        <a href="dashboard-1.html">
            <i class="fa-home"></i>
            <span class="title">仪表盘</span>
        </a>
        <ul>
            <li>
                <a href="${BASE_PATH}/admin/index.html">
                    <span class="title">首页</span>
                </a>
            </li>

            <li>
                <a href="#">
                    <span class="title">更新</span>
                </a>
            </li>
        </ul>
    </li>
    <li>
        <a href="#">
            <i class="fa-file-text-o"></i>
            <span class="title">文章</span>
        </a>
        <ul>
            <li>
                <a href="${BASE_PATH}/admin/article/list.html">
                    <span class="title">所有文章</span>
                </a>
            </li>
            <li>
                <a href="${BASE_PATH}/admin/article/edit.html">
                    <span class="title">写文章</span>
                </a>
            </li>
            <li>
                <a href="${BASE_PATH}/admin/category/list.html">
                    <span class="title">分类目录</span>
                </a>
            </li>
            <li>
                <a href="${BASE_PATH}/admin/tag/list.html">
                    <span class="title">标签</span>
                </a>
            </li>
        </ul>
    </li>
    <li>
        <a href="#">
            <i class="fa-user"></i>
            <span class="title">用户</span>
        </a>
        <ul>
            <li>
                <a href="${BASE_PATH}/admin/user/list.html">
                    <span class="title">用户列表</span>
                </a>
            </li>
            <li>
                <a href="${BASE_PATH}/admin/user/edit.html">
                    <span class="title">添加用户</span>
                </a>
            </li>
            <li>
                <a href="${BASE_PATH}/admin/user/profile.html">
                    <span class="title">我的个人资料</span>
                </a>
            </li>
        </ul>
    </li>
    <li>
        <a href="#">
            <i class="fa-paint-brush"></i>
            <span class="title">外观</span>
        </a>
        <ul>
            <li>
                <a href="${BASE_PATH}/admin/theme/show.html">
                    <span class="title">主题</span>
                </a>
            </li>
            <#--<li>-->
                <#--<a href="${BASE_PATH}/admin/menu/list.html">-->
                    <#--<span class="title">菜单</span>-->
                <#--</a>-->
            <#--</li>-->
        </ul>
    </li>
    <li>
        <a href="#">
            <i class="fa-plug"></i>
            <span class="title">插件</span>
        </a>
        <ul>
            <li>
                <a href="${BASE_PATH}/admin/plugin/list.html">
                    <span class="title">插件管理</span>
                </a>
            </li>
        <@plugin_menu_tag>
            <#list menus as menu>
                <li>
                    <a href="${BASE_PATH}/admin/plugin/plugin.html?pluginId=${(menu.id)!}">
                        <span class="title">${(menu.name)!}</span>
                    </a>
                </li>
            </#list>
        </@plugin_menu_tag>
        </ul>
    </li>
    <li>
        <a href="#">
            <i class="linecons-cog"></i>
            <span class="title">设置</span>
        </a>
        <ul>
            <li>
                <a href="${BASE_PATH}/admin/config/global.html">
                    <span class="title">站点设置</span>
                </a>
            </li>
        </ul>
    </li>
</ul>