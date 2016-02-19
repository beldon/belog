<#include "../common/_layout.ftl"/>
<#if link?exists>
    <#assign title = "编辑链接"/>
<#else>
    <#assign title = "编辑链接"/>
</#if>

<@html title=title>
<div class="row">
    <div class="col-md-9">
        <!-- Default panel -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
               ${title}
            </div>

            <div class="panel-body">
                <form class="form-horizontal" action="javascript:return false;" id="actionForm">
                    <#if link?exists>
                        <input type="hidden" value="${(link.id)!}" name="id">
                    </#if>
                    <div class="form-group">
                        <label for="title" class="col-sm-1 control-label">名称</label>

                        <div class="col-sm-11">
                            <input type="text" value="${(link.name)!}" name="name" class="form-control"
                                   id="title" placeholder="此处输入链接名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="title" class="col-sm-1 control-label">链接</label>

                        <div class="col-sm-11">
                            <input type="text" value="${(link.url)!}" name="url" class="form-control"
                                   id="title" placeholder="此处输入链接">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="title" class="col-sm-1 control-label">target</label>

                        <div class="col-sm-11">
                            <select class="form-control" name="target">
                                <option value="_blank" <#if (link.target)! = "_blank">selected</#if>>_blank</option>
                                <option value="_parent" <#if (link.target)! = "_parent">selected</#if>>_parent</option>
                                <option value="_self" <#if (link.target)! = "_self">selected</#if>>_self</option>
                                <option value="_top" <#if (link.target)! = "_top">selected</#if>>_top</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="title" class="col-sm-1 control-label">排序</label>

                        <div class="col-sm-11">
                            <input type="number" value="${(link.sort)?default(0)!}" name="sort" class="form-control"
                                   id="title" placeholder="此处输入链接排序">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">链接图片:</label>
                        <div class="col-sm-10">
                        <#--<#if link?exists && link.cover?exists && link.cover != ''>-->
                        <#--<div id="back-img">-->
                        <#--<img id="backImg" style="min-height: 160px;" src="${BASE_PATH}/${(article.cover)!}" alt="背景图片" width="250px" height="160px" class="img-rounded">-->
                        <#--<input type="hidden" name="cover" value="${(article.cover)!}" id="cover">-->
                        <#--</div>-->
                        <#--<input type="button" value="删除图片" id="deleteImg" class="btn btn-default">-->
                        <#--<div id="advancedDropzone" class="droppable-area dz-clickable img-rounded" style="display: none; min-height: 200px;">-->
                        <#--点击上传封面图片-->
                        <#--</div>-->
                        <#--<#else>-->
                        <#--<div id="back-img" style="display: none; ">-->
                        <#--<img id="backImg" style="min-height: 160px;" src="" alt="背景图片" width="250px" height="160px" class="img-rounded">-->
                        <#--<input type="hidden" name="cover" value="" id="cover">-->
                        <#--</div>-->
                        <#--<div id="advancedDropzone" class="droppable-area dz-clickable img-rounded" style="min-height: 200px;">-->
                        <#--点击上传链接图片-->
                        <#--</div>-->
                        <#--</#if>-->

                            <button type="submit" id="js-publish" class="btn btn-default pull-right">
                                <#if link?exists>
                                    更新链接
                                <#else>
                                    发表链接
                                </#if>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>

    <div class="col-md-3">

        <!-- 分类 -->
        <div class="panel panel-default panel-border">
            <div class="panel-heading">
                分类目录
            </div>

            <div class="panel-body">
                <form role="form" class="form-horizontal" id="catForm">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <#list cats as cat>
                                <div class="checkbox">
                                    <label>
                                        <input class="cat" name="catId" type="checkbox"
                                               value="${(cat.category.id)!}">
                                    ${(cat.category.name)!}
                                    </label>
                                </div>
                                <#list cat.subs as sub>
                                    <div class="checkbox">
                                        <label>
                                            <input class="cat" name="catId" type="checkbox"
                                                   value="${(sub.category.id)!}">
                                            --&nbsp;${(sub.category.name)!}
                                        </label>
                                    </div>
                                </#list>
                            </#list>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>

<!-- Imported scripts on this page -->
<script src="${BASE_PATH}/static/admin/js/dropzone/dropzone.min.js"></script>


<script type="text/javascript">

    var cats = [
        <#if link?exists>
            <#list (link.cats)! as cat>
            ${(cat.id)!},
            </#list>
        </#if>];

    $(document).ready(function () {

        $("input[name='catId']").each(function (index) {
            //alert($(this).val());
            var id = $(this).val();
            for (var key in cats) {
                if (cats[key] == id) {
                    $(this).attr("checked", 'checked');
                }
                //alert(key);
                //alert(cats[key]);
            }
        });

        $("#deleteImg").click(function(){
            var that = $(this);
            $.ajax({
                type: "POST",
                url: "deleteCover.json",
                data: {id:'${(article.id)!}'},
                dataType: "json",
                success: function (data) {
                    if (data.status === true) {
                        $("#back-img").hide();
                        that.hide();
                        $("#advancedDropzone").show();
                    } else {
                        alert("error");
                    }
                }
            });

        });

//        $("#js-publish").click(function () {
//            var catForm = $('#catForm').serialize();
//            var actionForm = $('#actionForm').serialize();
//            $.ajax({
//                type: "POST",
//                url: "ajaxEdit.json",
//                data: actionForm + "&" + catForm,
//                dataType: "json",
//                success: function (data) {
//                    if (data.status === true) {
//                        //window.location.reload();
//                        window.location.href = "list.html";
//                    } else {
//                        alert("error");
//                    }
//                }
//            });
//        });

        $('#actionForm').bootstrapValidator({
            message: 'This value is not valid',
            fields: {
                name: {
                    message: '名称不能为空',
                    validators: {
                        notEmpty: {
                            message: '名称不能为空'
                        }
                    }
                },
                url: {
                    message: '请输入合法的网址',
                    validators: {
                        regexp: {
                            regexp: /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/,
                            message: '请输入合法的网址'
                        }
                    }
                }
            }
        }).on('success.form.bv', function(e) {
            var catForm = $('#catForm').serialize();
            var actionForm = $('#actionForm').serialize();
            $.ajax({
                type: "POST",
                url: "ajaxEdit.json",
                data: actionForm + "&" + catForm,
                dataType: "json",
                success: function (data) {
                    if (data.status === true) {
                        //window.location.reload();
                        window.location.href = "list.html";
                    } else {
                        alert("error");
                    }
                }
            });
        });
    });
</script>
<script type="text/javascript">
    jQuery(document).ready(function ($) {
        example_dropzone = $("#advancedDropzone").dropzone({
        <#--url: '${BASE_PATH}/admin/upload/ueditor.json?action=uploadimage',-->
            url: '${BASE_PATH}/admin/upload/uploadImg.json?type=thumbs',
            paramName:'upload',
            acceptedFiles: ".png,.jpg,.gif,.bmp,.jpeg",
            // Events
            addedfile: function (file) {

            },
            uploadprogress: function (file, progress, bytesSent) {
            },

            success: function (file) {
                console.log(file);
                var imagePath = file.xhr.response;
                console.log(imagePath);
//                        var responJson = JSON.parse(file.xhr.response);
                $("#backImg").attr("src","${BASE_PATH}/"+imagePath);
                $("#cover").val(imagePath);
                $("#back-img").show();
                $("#advancedDropzone").hide();
                console.log("url " + imagePath + " uploaded");
            },

            error: function (file) {
                alert("error");
            }
        });

        $("#advancedDropzone").css({
            minHeight: 200
        });

    });
</script>
</@html>