<#include "header.ftl"/>
<#include "aside.ftl"/>
<#include "content-nav.ftl"/>
<#include "content-footer.ftl"/>
<#include "footer.ftl"/>

<#macro html title>
<@header title = title/>
<@aside/>
    <div class="main-content">
    <@content_nav/>
<#--内容开始-->
    <#nested>
<#--内容结束-->
   <@content_footer/>
    </div>

</div>

<@footer/>
</#macro>
