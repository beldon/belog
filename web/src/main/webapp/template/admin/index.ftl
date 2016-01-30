<#include "public/header.ftl"/>
<body class="page-body">
<div class="page-container">

<#include "public/aside.ftl"/>

    <div class="main-content">
    <#include "public/content-nav.ftl"/>
        <div class="row">
            <div class="col-sm-3">
                <div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="1000" data-to="${(loaded)!}" data-duration="1" data-easing="true">
                    <div class="xe-icon">
                        <i class="linecons-database"></i>
                    </div>
                    <div class="xe-label">
                        <strong class="num">${(loaded)!}</strong>
                        <span>当前虚拟机类数量</span>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                    <div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="100" data-to="${(totalLoaded)!}" data-duration="1" data-easing="true">
                        <div class="xe-icon">
                            <i class="linecons-database"></i>
                        </div>
                        <div class="xe-label">
                            <strong class="num">${(totalLoaded)!}</strong>
                            <span>当前虚拟机已加载类数量</span>
                        </div>
                    </div>
            </div>
            <div class="col-sm-3">
                    <div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="1000" data-to="${(unLoaded)!}" data-duration="1" data-easing="true">
                        <div class="xe-icon">
                            <i class="linecons-database"></i>
                        </div>
                        <div class="xe-label">
                            <strong class="num">${(unLoaded)!}</strong>
                            <span>当前虚拟机已卸载类数量</span>
                        </div>
                    </div>
                </div>
        </div>
        <div class="row">
            <div class="col-sm-3">
                <div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="10" data-to="${(heapInit)!}" data-suffix="M" data-duration="2" data-easing="true">
                    <div class="xe-icon">
                        <i class="linecons-database"></i>
                    </div>
                    <div class="xe-label">
                        <strong class="num">${(heapInit)!}</strong>
                        <span>虚拟机初始时堆内存</span>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                    <div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="10" data-to="${(heapMax)!}" data-suffix="M" data-duration="2" data-easing="true">
                        <div class="xe-icon">
                            <i class="linecons-database"></i>
                        </div>
                        <div class="xe-label">
                            <strong class="num">${(heapMax)!}</strong>
                            <span>虚拟机允许最大堆内存</span>
                        </div>
                    </div>
            </div>
            <div class="col-sm-3">
                    <div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="10" data-to="${(heapUsed)!}" data-suffix="M" data-duration="2" data-easing="true">
                        <div class="xe-icon">
                            <i class="linecons-database"></i>
                        </div>
                        <div class="xe-label">
                            <strong class="num">${(heapUsed)!}</strong>
                            <span>虚拟机当前堆内存</span>
                        </div>
                    </div>
                </div>
        </div>
        <div class="row">
            <div class="col-sm-3">
                <div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="10" data-to="${(notHeapInit)!}" data-suffix="M" data-duration="2" data-easing="true">
                    <div class="xe-icon">
                        <i class="linecons-database"></i>
                    </div>
                    <div class="xe-label">
                        <strong class="num">${(notHeapInit)!}</strong>
                        <span>虚拟机初始时非堆内存</span>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                    <div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="10" data-to="${(notHeapMax)!}" data-suffix="M" data-duration="2" data-easing="true">
                        <div class="xe-icon">
                            <i class="linecons-database"></i>
                        </div>
                        <div class="xe-label">
                            <strong class="num">${(notHeapMax)!}</strong>
                            <span>虚拟机允许最大非堆内存</span>
                        </div>
                    </div>
            </div>
            <div class="col-sm-3">
                    <div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="10" data-to="${(notHeapUsed)!}" data-suffix="M" data-duration="2" data-easing="true">
                        <div class="xe-icon">
                            <i class="linecons-database"></i>
                        </div>
                        <div class="xe-label">
                            <strong class="num">${(notHeapUsed)!}</strong>
                            <span>虚拟机当前非堆内存</span>
                        </div>
                    </div>
                </div>
        </div>
    <#include "public/content-footer.ftl"/>
    </div>

</div>

<#include "public/footer.ftl"/>
<script src="${BASE_PATH}/static/admin/js/xenon-widgets.js"></script>
<script src="${BASE_PATH}/static/admin/js/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${BASE_PATH}/static/admin/js/jvectormap/regions/jquery-jvectormap-world-mill-en.js"></script>
