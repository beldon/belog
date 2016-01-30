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
                    ${(plugin.name)?default("未知插件")!}
                    </div>
                    <div class="alert alert-success alert-dismissible hide" role="alert" id="alert-success">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <strong>success!</strong> 操作成功！
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" action="javascript:return false;" method="post" id="loginForm">
                            <input type="hidden" name="pluginId" value="${(plugin.id)!}">
                        <#if (plugin.config?exists)!>
                            <#list (plugin.config.elements)! as element>
                                <div class="form-group">
                                    <label class="col-sm-1 control-label">${(element.label)?default("标签")!}</label>
                                    <div class="col-sm-4">
                                        <#if element.type == 'text'>
                                            <input type="text" name="${(element.key)!}" value="${(element.value)!}"
                                                   class="form-control" placeholder="网站名称">
                                        <#elseif element.type == 'text'>
                                        <#elseif element.type == 'radio'>
                                        <#elseif element.type == 'textarea'>
                                            <textarea rows="5" name="${(element.key)!}" class="form-control">${(element.value)!}</textarea>
                                        <#elseif element.type == 'checkbox'>
                                        <#elseif element.type == 'hidden'>
                                        <#elseif element.type == 'select'>
                                            <select class="form-control" name="${(element.key)!}">
                                                <#if element.meta?exists>
                                                    <#list element.meta as meta>
                                                        <#if (meta.value == element.value)!>
                                                            <option selected="selected" value="${(meta.value)!}">${(meta.text)!}</option>
                                                        <#else>
                                                            <option value="${(meta.value)!}">${(meta.text)!}</option>
                                                        </#if>

                                                    </#list>
                                                </#if>
                                            </select>
                                        </#if>
                                    </div>
                                </div>
                            </#list>
                        </#if>
                            <div class="form-group">
                                <div class="col-sm-offset-4 col-sm-4">
                                    <button type="submit" id="js-save" class="btn btn-default">保存</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    <#include "../public/content-footer.ftl"/>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        $("#js-save").click(function () {
            $.ajax({
                type: "POST",
                url: "ajaxEdit.json",
                data: $('#loginForm').serialize(),
                dataType: "json",
                success: function (data) {
                    if (data.status === true) {
                        $("#alert-success").removeClass("hide");
                        setInterval(function(){
                            window.location.reload();
                        },1000)
                    } else {
                        alert("error");
                    }
                }
            });
        });
    });
</script>

</body><#include "../public/footer.ftl"/>