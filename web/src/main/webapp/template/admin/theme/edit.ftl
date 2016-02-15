<#include "../public/header.ftl"/>
<link rel="stylesheet" href="${BASE_PATH}/static/admin/js/jqueryFileTree/jqueryFileTree.css">
<style type="text/css" media="screen">
    #editor {
        margin: 0;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
        height:500px;
    }
</style>
<body class="page-body">
<div class="page-container">

<#include "../public/aside.ftl"/>
    <div class="main-content">
    <#include "../public/content-nav.ftl"/>
        <div class="row">
            <div class="col-md-12">
                <div class="alert alert-success alert-dismissible hide" role="alert" id="alert-success">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>success!</strong> 保存成功！
                </div>
                <!-- Default panel -->
                <div class="panel panel-default panel-border">
                    <div class="panel-heading">
                        模板设置
                    </div>

                    <div class="panel-body">
                        <div class="col-md-9">
                            <pre id="editor"></pre>
                            <button type="submit" id="js-save" class="btn btn-default pull-right">保  存</button>
                        </div>
                        <div class="col-md-3" id="fileTree"></div>
                    </div>
                </div>

            </div>
        </div>
    <#include "../public/content-footer.ftl"/>
    </div>
</div>

<script src="${BASE_PATH}/static/admin/js/jqueryFileTree/jqueryFileTree.js"></script>
<script src="${BASE_PATH}/static/admin/js/emmet.js"></script>
<script src="${BASE_PATH}/static/admin/js/ace/ace.js"></script>
<script src="${BASE_PATH}/static/admin/js/ace/ext-emmet.js"></script>
<script type="text/javascript">
    var currentFile = "/beldon/index.ftl";
    setValue('fileContent.html?path=' + currentFile);
    $(document).ready(function(){
        $("#js-save").click(function(){
            var content = $("#editor").html();
            console.log(editor.getValue());
//            alert(editor.getValue());
//            alert(currentFile);
            $.ajax({
                type: "POST",
                url: "saveFile.json",
                data: {fileName:currentFile,content:editor.getValue()},
                dataType: "json",
                success: function(data){
                    if(data.status===true){
                        $("#alert-success").removeClass("hide");
                        $("#alert-success").show();
                        setInterval(function(){
                            $("#alert-success").hide();
                        },1000)
                    }else{
                        alert("error");
                    }
                }
            });
        });
        $('#fileTree').fileTree({ root: '/beldon/' ,script:'fileTree.html'}, function(file) {
//            alert(file);
            currentFile = file;
            setValue('fileContent.html?path=' + file);
        });
    });

    var editor = ace.edit("editor");
    editor.session.setMode("ace/mode/ftl");
    editor.setOption("enableEmmet", true);

    require = ace.require;
    function setValue(url) {
        require("ace/lib/net").get(url, function(t){
            var el = document.getElementById("editor");
            el.env.editor.setValue(t, 1);

        })
    }
</script>

<#include "../public/footer.ftl"/>