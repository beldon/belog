<#macro footer>
    <@links_tag type='list' pageSize='20'>
        <#assign pm=pm>
    </@links_tag>

<footer>
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-md-12">
                <h4>友情链接</h4>
                <ul class="list-inline">
                    <li><a target="_blank" href="http://beldon.me">Beldon</a></li>
                <#list pm.results as link>
                    <li><a target="${(link.target)!}" href="${(link.url)!}">${(link.name)!}</a></li>
                </#list>
                </ul>
            </div><!-- /.col-sm-2 -->

        </div><!-- /.row -->
    </div><!-- /.container -->
</footer>

<div class="footer">
    <div class="container">
        <div class="row">
            <div class="col-sm-5">
                Copyright &copy; 2016 <a href="#fakelink">Beldon</a>
            </div><!-- /.col-sm-5 -->
            <div class="col-sm-7 text-right">
                <ul class="list-inline">
                    <li><a target="_blank" href="http://beldon.me">Beldon</a></li>
                </ul>
            </div><!-- /.col-sm-7 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</div><!-- /.footer -->
<!-- END FOOTER -->



<!-- BEGIN BACK TO TOP BUTTON -->
<div id="back-top">
    <i class="fa fa-chevron-up"></i>
</div>
<!-- END BACK TO TOP -->

<!--
===========================================================
END PAGE
===========================================================
-->



<!--
===========================================================
Placed at the end of the document so the pages load faster
===========================================================
-->
<!-- MAIN JAVASRCIPT (REQUIRED ALL PAGE)-->
<script src="${THEME_PATH}/js/jquery.js"></script>
<script src="${THEME_PATH}/js/bootstrap.min.js"></script>
<script src="${THEME_PATH}/plugins/retina/retina.min.js"></script>
<script src="${THEME_PATH}/plugins/backstretch/jquery.backstretch.min.js"></script>
<script src="${THEME_PATH}/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
<script src="${THEME_PATH}/plugins/owl-carousel/owl.carousel.min.js"></script>
<script src="${THEME_PATH}/plugins/mixitup/jquery.mixitup.js"></script>
<script>
    $("#full-img-slide").backstretch([
        "${THEME_PATH}/img/image-1.jpg"
    ], {
        fade: 750,
        duration: 6000
    });
</script>
<script>
    $(document).ready(function(){
        $(function(){
            var shrinkHeader = 250;
            $(window).scroll(function() {
                var scroll = getCurrentScroll();
                if ( scroll >= shrinkHeader ) {
                    $('.top-navbar').addClass('shrink-nav');
                }
                else {
                    $('.top-navbar').removeClass('shrink-nav');
                }
            });
            function getCurrentScroll() {
                return window.pageYOffset || document.documentElement.scrollTop;
            }
        });
    })
</script>
<script src="${THEME_PATH}/js/apps.js"></script>
</body>
</html>
</#macro>