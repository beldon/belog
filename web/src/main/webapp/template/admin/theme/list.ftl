<#include "../common/_layout.ftl"/>

<@html title="模板列表">
<div class="row">
    <div class="col-md-12">
        <!-- Default panel -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                模板列表
            </div>
            <div class="panel-body">
                <div class="row">
                    <#list themes as theme>
                        <div class="col-md-4">
                            <!-- Bordered + shadow panel -->
                            <div class="panel panel-default panel-border panel-shadow"><!-- Add class "collapsed" to minimize the panel -->
                                <div class="panel-heading">
                                    <h3 class="panel-title">${(theme.name)!}</h3>

                                    <div class="panel-options">
                                        <a href="${(BASE_PATH)!}/admin/theme/details.html?theme=${(theme.directory)!}">
                                            <i class="linecons-cog"></i>
                                        </a>

                                        <a href="#" data-toggle="remove" class="js-remove">
                                            ×
                                        </a>
                                    </div>
                                </div>

                                <div class="panel-body">
                                    <div>${(theme.description)!}</div>
                                    <a href="${(BASE_PATH)!}/admin/theme/details.html?theme=${(theme.directory)!}">
                                        <img src="${(BASE_PATH)!}/static/theme/${(theme.directory)!}/${(theme.logo)!}" alt="" class="img-responsive">
                                    </a>
                                    <div class="row">
                                        <#if theme.directory != currentTheme  >
                                            <button class="btn btn-success btn-xs btn-icon col-md-offset-5 js-save" theme="${(theme.directory)!}" style="margin-top:5px;">
                                                <i class="fa-check"></i>
                                                <span>启用</span>
                                            </button>
                                        <#else >
                                            <button class="btn btn-success btn-xs disabled btn-icon col-md-offset-5" style="margin-top:5px;">
                                                <i class="fa-check"></i>
                                                <span>已启用</span>
                                            </button>
                                        </#if>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </#list>
                </div>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $(".js-remove").click(function(){
            alert("dd");
            return false;
        });

        $(".js-save").click(function(){
            var theme = $(this).attr("theme");
            $.ajax({
                type: "POST",
                url: "addOrUpdate.json",
                data: {theme:theme},
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
</@html>