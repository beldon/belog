<#macro footer>
<footer id="footer">
    <div class="outer">
        <div id="footer-info">
            <div class="footer-left">
                &copy; 2016 John Doe
            </div>
            <div class="footer-right">
                <a href="http://hexo.io/" target="_blank">Hexo</a>  Theme <a href="https://github.com/luuman/hexo-theme-spfk" target="_blank">spfk</a> by luuman
            </div>
        </div>

        <div class="visit">

                    <span id="busuanzi_container_site_pv" style='display:none'>
                        <span id="site-visit" >本站到访数:
                            <span id="busuanzi_value_site_uv"></span>
                        </span>
                    </span>


            <span>, </span>


                    <span id="busuanzi_container_page_pv" style='display:none'>
                        <span id="page-visit">本页阅读量:
                            <span id="busuanzi_value_page_pv"></span>
                        </span>
                    </span>

        </div>

    </div>
</footer>

</div>
<script src="${THEME_PATH}/js/jquery-1.9.1.min.js"></script>
<script src="${THEME_PATH}/js/main.js"></script>

<script>
    $(document).ready(function() {
        var backgroundnum = 21;
        var backgroundimg = "url(${THEME_PATH}/img/background/bg-x.jpg)".replace(/x/gi, Math.ceil(Math.random() * backgroundnum));
        $("#mobile-nav").css({"background-image": backgroundimg,"background-size": "cover","background-position": "center"});
        $(".left-col").css({"background-image": backgroundimg,"background-size": "cover","background-position": "center"});
    })
</script>




<div class="scroll" id="scroll">
    <a href="#"><i class="fa fa-arrow-up"></i></a>
    <a href="#comments"><i class="fa fa-comments-o"></i></a>
    <a href="#footer"><i class="fa fa-arrow-down"></i></a>
</div>
<script>
    $(document).ready(function() {
        if ($("#comments").length < 1) {
            $("#scroll > a:nth-child(2)").hide();
        };
    })
</script>

<script async src="${THEME_PATH}/js/busuanzi.pure.mini.js">
</script>

<script language="javascript">
    $(function() {
        $("a[title]").each(function() {
            var a = $(this);
            var title = a.attr('title');
            if (title == undefined || title == "") return;
            a.data('title', title).removeAttr('title').hover(

                    function() {
                        var offset = a.offset();
                        $("<div id=\"anchortitlecontainer\"></div>").appendTo($("body")).html(title).css({
                            top: offset.top - a.outerHeight() - 15,
                            left: offset.left + a.outerWidth()/2 + 1
                        }).fadeIn(function() {
                            var pop = $(this);
                            setTimeout(function() {
                                pop.remove();
                            }, pop.text().length * 800);
                        });
                    }, function() {
                        $("#anchortitlecontainer").remove();
                    });
        });
    });
</script>
    ${javascript!}
</div>
</body>
</html>
</#macro>