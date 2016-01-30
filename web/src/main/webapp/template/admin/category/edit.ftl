<#include "../public/header.ftl"/>
<body class="page-body">
<div class="page-container">

<#include "../public/aside.ftl"/>

    <div class="main-content">
    <#include "../public/content-nav.ftl"/>
        <div class="row">
            <div class="col-md-5">
                <!-- Default panel -->
                <div class="panel panel-default panel-border">
                    <div class="panel-heading">
                        编辑分类目录
                    </div>

                    <div class="panel-body">
                        <form class="form-horizontal" action="javascript:return false;" id="actionForm">
                            <input type="hidden" name="id" value="${(cat.id)!}">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">名称</label>

                                <div class="col-sm-12">
                                    <input type="text" name="name" value="${(cat.name)!}" class="form-control" id="inputEmail3" placeholder="分类名称">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-2 control-label">别名</label>

                                <div class="col-sm-12">
                                    <input type="text" name="slug" value="${(cat.slug)!}" class="form-control" id="inputEmail3" placeholder="分类别名">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-2 control-label">父节</label>

                                <div class="col-sm-12">
                                    <select class="form-control" name="parent">
                                        <option value="0">无</option>
                                    <#list cats as c>
                                    <#if c.category.id !=cat.id>
                                        <option <#if c.category.id = cat.parent>selected</#if> value="${(c.category.id)!}">${(c.category.name)!}</option>
                                    </#if>
                                        <#list c.subs as sub>
                                            <#if sub.category.id !=cat.id>
                                            <option <#if sub.category.id = cat.parent>selected</#if> value="${(sub.category.id)!}">${(sub.category.name)!}</option>
                                            </#if>
                                        </#list>
                                    </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-8 col-sm-10">
                                    <button type="submit" id="saveCat" class="btn btn-default">更新分类目录</button>
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
    $(document).ready(function(){
        $("#saveCat").click(function(){
            $.ajax({
                type: "POST",
                url: "ajaxEdit.json",
                data: $('#actionForm').serialize(),
                dataType: "json",
                success: function(data){
                    if(data.status===true){
                        window.location.href = 'list.html';
                    }else{
                        alert("error");
                    }
                }
            });
        });
    });
</script>

<#include "../public/footer.ftl"/>