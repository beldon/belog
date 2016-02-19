<#macro footer>
<!-- Bottom Scripts -->
<script src="${BASE_PATH}/static/admin/js/bootstrap.min.js"></script>
<script src="${BASE_PATH}/static/admin/js/bootstrap-validator/js/bootstrapValidator.min.js"></script>
<script src="${BASE_PATH}/static/admin/js/TweenMax.min.js"></script>
<script src="${BASE_PATH}/static/admin/js/resizeable.js"></script>
<script src="${BASE_PATH}/static/admin/js/joinable.js"></script>
<script src="${BASE_PATH}/static/admin/js/xenon-api.js"></script>
<script src="${BASE_PATH}/static/admin/js/xenon-toggles.js"></script>



<!-- JavaScripts initializations and stuff -->
<script src="${BASE_PATH}/static/admin/js/xenon-custom.js"></script>

<script src="${BASE_PATH}/static/admin/js/tagsinput/bootstrap-tagsinput.min.js"></script>
<script src="${BASE_PATH}/static/admin/js/artDialog/dialog-plus-min.js"></script>

<script>
    $(function(){
        $("#logout").click(function(){
            $.ajax({
                url: "${BASE_PATH}/admin/logout.json",
                method: 'POST',
                dataType: 'json',
                success: function(resp)
                {
                    window.location.reload();
                }
            });
        });
    });
</script>

</body>
</html>
</#macro>