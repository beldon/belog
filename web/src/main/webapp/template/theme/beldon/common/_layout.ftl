<#include "header.ftl" />
<#include "nav.ftl" />
<#include "footer.ftl"/>
<#macro html title>
<@header title= title/>
<@nav/>
<#--内容开始-->
    <#nested>
<#--内容结束-->
<@footer/>
</#macro>