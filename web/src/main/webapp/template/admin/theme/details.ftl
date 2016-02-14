<#include "../public/header.ftl"/>
<body class="page-body">
<div class="page-container">

<#include "../public/aside.ftl"/>
    <div class="main-content">
    <#include "../public/content-nav.ftl"/>
        <div class="row">
            <div class="col-md-12">
                <!-- Default panel -->
                <div class="panel panel-default panel-border">
                    <div class="panel-heading">
                        ${(theme.name)!}详情
                    </div>

                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-7">
                                <img src="${(BASE_PATH)!}/static/theme/${(theme.directory)!}/${(theme.logo)!}" alt="主题logo" class="img-responsive">
                            </div>
                            <div class="col-md-5">
                                <h4>${(theme.name)!} <small>版本：${(theme.version)!}</small></h4>
                                由 <a target="_blank" href="${(theme.url)!}">${(theme.author)!}</a>创建
                                <div class="row"> &nbsp; </div>
                                <p>${(theme.description)!}</p>
                            </div>
                        </div>
                        <div class="row">
                            <#if theme.directory != currentTheme  >
                                <button id="js-save" class="btn btn-success btn-sm btn-icon col-md-offset-6" style="margin-top:5px;">
                                    <i class="fa-check"></i>
                                    <span>启用</span>
                                </button>
                            <#else >
                                <button class="btn btn-success btn-sm disabled btn-icon col-md-offset-6" style="margin-top:5px;">
                                    <i class="fa-check"></i>
                                    <span>已启用</span>
                                </button>
                            </#if>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    <#include "../public/content-footer.ftl"/>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function(){
        $("#js-save").click(function(){
            $.ajax({
                type: "POST",
                url: "addOrUpdate.json",
                data: {theme:"${(theme.directory)!}"},
                dataType: "json",
                success: function(data){
                    if(data.status===true){
                        window.location.reload();
                    }else{
                        alert("error");
                    }
                }
            });
        });
    });
</script>

<#include "../public/footer.ftl"/>