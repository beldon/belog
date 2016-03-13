<#include "header.ftl" />
<#include "nav.ftl" />
<#include "footer.ftl"/>
<#macro html title>
    <@header title= title/>
    <@nav/>
    <div class="body-wrap">
    <#--内容开始-->
        <#nested>
    <#--内容结束-->

    </div>
    <@footer/>
</#macro>