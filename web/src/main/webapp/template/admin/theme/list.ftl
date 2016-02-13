<#include "../public/header.ftl"/>
<body class="page-body">
<div class="page-container">

<#include "../public/aside.ftl"/>
    <div class="main-content">
    <#include "../public/content-nav.ftl"/>
        <div class="row">
            <div class="col-md-12">
                <!-- Default panel -->
                <div class="panel panel-default panel-border">
                    <div class="panel-heading">
                        模板设置
                    </div>
                    <div class="panel-body">
                        <div class="row">
                        <#list themes as theme>
                            <div class="col-md-4">
                                <!-- Bordered + shadow panel -->
                                <div class="panel panel-default panel-border panel-shadow"><!-- Add class "collapsed" to minimize the panel -->
                                    <div class="panel-heading">
                                        <h3 class="panel-title">${(theme.name)!}</h3>

                                        <div class="panel-options">
                                            <a href="#">
                                                <i class="linecons-cog"></i>
                                            </a>

                                            <a href="#" data-toggle="reload">
                                                <i class="fa-rotate-right"></i>
                                            </a>

                                            <a href="#" data-toggle="remove" class="js-remove">
                                                ×
                                            </a>
                                        </div>
                                    </div>

                                    <div class="panel-body">
                                        <div>${(theme.description)!}</div>
                                         <img src="${(BASE_PATH)!}/static/theme/${(theme.directory)!}/${(theme.logo)!}" alt="" class="img-responsive">
                                    </div>
                                </div>

                            </div>
                        </#list>
                            <div class="col-md-4">

                                <!-- Bordered + shadow panel -->
                                <div class="panel panel-default panel-border panel-shadow"><!-- Add class "collapsed" to minimize the panel -->
                                    <div class="panel-heading">
                                        <h3 class="panel-title">Beldon模板</h3>

                                        <div class="panel-options">
                                            <a href="#">
                                                <i class="linecons-cog"></i>
                                            </a>

                                            <a href="#" data-toggle="panel">
                                                <span class="collapse-icon">–</span>
                                                <span class="expand-icon">+</span>
                                            </a>

                                            <a href="#" data-toggle="reload">
                                                <i class="fa-rotate-right"></i>
                                            </a>

                                            <a href="#" data-toggle="remove">
                                                ×
                                            </a>
                                        </div>
                                    </div>

                                    <div class="panel-body">

                                        <p>She travelling acceptance men unpleasant her especially entreaties law. Law forth but end any arise chief arose. Old her say learn these large. Joy fond many ham high seen this. Few preferred continual sir led incommode neglected. Discovered too old insensible collecting unpleasant but invitation. </p>
                                        <p>Now indulgence dissimilar for his thoroughly has terminated. Agreement offending commanded my an. Change wholly say why eldest period. Are projection put celebrated particular unreserved joy unsatiable its. In then dare good am rose bred or. On am in nearer square wanted. </p>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    <#include "../public/content-footer.ftl"/>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function(){
        $(".js-remove").click(function(){
            alert("dd");
            return false;
        });
    });
</script>

<#include "../public/footer.ftl"/>