<#include "../public/header.ftl"/>
<body class="page-body">
<div class="page-container">

<#include "../public/aside.ftl"/>

    <div class="main-content">
    <#include "../public/content-nav.ftl"/>
        <div class="row">
            <div class="col-md-9">
                <!-- Default panel -->
                <div class="panel panel-default panel-border">
                    <div class="panel-heading">
                    <#if article?exists>
                        编辑文章
                    <#else>
                        添加文章
                    </#if>
                    </div>

                    <div class="panel-body">
                        <form class="form-horizontal" action="javascript:return false;" id="actionForm">
                        <#if article?exists>
                            <input type="hidden" value="${(article.id)!}" name="id">
                        </#if>
                            <div class="form-group">
                                <label for="title" class="col-sm-1 control-label">标题</label>

                                <div class="col-sm-11">
                                    <input type="text" value="${(article.title)!}" name="title" class="form-control"
                                           id="title" placeholder="此处输入标题">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="content" class="col-sm-1 control-label">内容</label>
                                <div class="col-sm-11">
                                <textarea name="content" class="form-control ckeditor" rows="10">${(article.content)!}</textarea>
                                    <#--<textarea id="container" name="content" class="" rows="6">${(article.content)!}</textarea>-->
                                <#--<script id="container" name="content" class="form-control ckeditor" type="text/plain">这里写你的初始化内容</script>-->
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="title" class="col-sm-2 control-label">封面图片:</label>
                                <div class="col-sm-10">
                                    <#if article?exists && article.cover?exists && article.cover != ''>
                                        <div id="back-img">
                                                <img id="backImg" style="min-height: 160px;" src="${BASE_PATH}/${(article.cover)!}" alt="背景图片" width="160px" height="160px" class="img-rounded">
                                                <input type="hidden" name="cover" value="${(article.cover)!}" id="cover">
                                        </div>
                                        <input type="button" value="删除图片" id="deleteImg" class="btn btn-default">
                                        <div id="advancedDropzone" class="droppable-area dz-clickable img-rounded" style="display: none; min-height: 200px;">
                                            点击上传封面图片
                                        </div>
                                    <#else>
                                        <div id="back-img" style="display: none; ">
                                                <img id="backImg" style="min-height: 160px;" src="" alt="背景图片" width="160px" height="160px" class="img-rounded">
                                                <input type="hidden" name="cover" value="" id="cover">
                                        </div>
                                        <div id="advancedDropzone" class="droppable-area dz-clickable img-rounded" style="min-height: 200px;">
                                            点击上传封面图片
                                        </div>
                                    </#if>

                                    <button type="submit" id="js-publish" class="btn btn-default pull-right">
                                    <#if article?exists>
                                        更新文章
                                    <#else>
                                        发表文章
                                    </#if>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>

            <div class="col-md-3">

                <!-- Bordered panel -->
                <div class="panel panel-default panel-border">
                    <div class="panel-heading">
                        分类目录
                    </div>

                    <div class="panel-body">
                        <form role="form" class="form-horizontal" id="catForm">
                            <div class="form-group">
                                <div class="col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox">
                                            默认分类
                                        </label>
                                    </div>
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
    <#include "../public/content-footer.ftl"/>
    </div>

</div>

<input type="hidden" id="basePath" value="${BASE_PATH}/">

<!-- Imported scripts on this page -->
<script src="${BASE_PATH}/static/admin/js/ckeditor/ckeditor.js"></script>
<script src="${BASE_PATH}/static/admin/js/ckeditor/adapters/jquery.js"></script>

<script src="${BASE_PATH}/static/admin/js/dropzone/dropzone.min.js"></script>

<script type="text/javascript">

    var cats = [
    <#if article?exists>
        <#list (article.cats)! as cat>
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

        $("#js-publish").click(function () {
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


<#include "../public/footer.ftl"/>