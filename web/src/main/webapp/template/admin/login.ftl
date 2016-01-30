<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="Belog个人博客" />
    <meta name="author" content="" />

    <title>Belog后台登陆</title>

    <#--<link rel="stylesheet" href="http://fonts.useso.com/css?family=Arimo:400,700,400italic">-->
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/fonts/linecons/css/linecons.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/fonts/fontawesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/bootstrap.min.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/xenon-core.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/xenon-forms.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/xenon-components.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/xenon-skins.css">
    <link rel="stylesheet" href="${BASE_PATH}/static/admin/css/custom.css">

    <script src="${BASE_PATH}/static/admin/js/jquery-1.11.1.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body class="page-body login-page" style="padding-top: 0px">

<div class="login-container">

    <div class="row">

        <div class="col-sm-6 col-md-offset-3">

            <!-- Errors container -->
            <div class="errors-container">

            </div>

            <!-- Add class "fade-in-effect" for login form effect -->
            <form method="post" role="form" id="login" class="login-form fade-in-effect">

                <div class="login-header">
                    <a href="#login" class="logo">
                        <img src="${BASE_PATH}/static/admin/images/logo@2x.png" alt="" width="80" />
                        <span>登陆</span>
                    </a>
                    <p>亲爱的用户, 登陆并进入后台系统!</p>
                </div>


                <div class="form-group">
                    <#--<label class="control-label" for="username">Username</label>-->
                    <input type="text" class="form-control input-dark" name="username" id="username" placeholder="用户名" autocomplete="off" />
                </div>

                <div class="form-group">
                    <#--<label class="control-label" for="passwd">Password</label>-->
                    <input type="password" class="form-control input-dark" name="passwd" id="passwd" placeholder="密码" autocomplete="off" />
                </div>

                <div class="form-group">
                    <button type="submit" class="btn btn-dark  btn-block text-left">
                        <i class="fa-lock"></i>
                        登陆
                    </button>
                </div>

                <div class="login-footer">
                    <a href="#">忘记密码?</a>
                </div>

            </form>

            <!-- External login -->
            <!--
            <div class="external-login">
                <a href="#" class="facebook">
                    <i class="fa-facebook"></i>
                    Facebook Login
                </a>

                <a href="#" class="twitter">
                    <i class="fa-twitter"></i>
                    Login with Twitter
                </a>

                <a href="#" class="gplus">
                    <i class="fa-google-plus"></i>
                    Login with Google Plus
                </a>
            </div>
            -->
        </div>

    </div>

</div>

<script type="text/javascript">
    jQuery(document).ready(function($)
    {
        // Reveal Login form
        setTimeout(function(){ $(".fade-in-effect").addClass('in'); }, 1);


        // Validation and Ajax action
        $("form#login").validate({
            rules: {
                username: {
                    required: true
                },

                passwd: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: '请输入您的用户名.'
                },

                passwd: {
                    required: '请输入您的密码.'
                }
            },
            // Form Processing via AJAX
            submitHandler: function(form)
            {
                show_loading_bar(70); // Fill progress bar to 70% (just a given value)

                var opts = {
                    "closeButton": true,
                    "debug": false,
                    "positionClass": "toast-top-full-width",
                    "onclick": null,
                    "showDuration": "300",
                    "hideDuration": "1000",
                    "timeOut": "5000",
                    "extendedTimeOut": "1000",
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                };

                $.ajax({
                    url: "login.json",
                    method: 'POST',
                    dataType: 'json',
                    data: {
                        do_login: true,
                        username: $(form).find('#username').val(),
                        password: $(form).find('#passwd').val(),
                    },
                    success: function(resp)
                    {
                        show_loading_bar({
                            delay: .5,
                            pct: 100,
                            finish: function(){

                                // Redirect after successful login page (when progress bar reaches 100%)
                                if(resp.status)
                                {
                                    window.location.href = 'index.html';
                                }
                                else
                                {
                                    toastr.error("You have entered wrong password, please try again. User and password is <strong>demo/demo</strong> :)", "Invalid Login!", opts);
                                    $passwd.select();
                                }
                            }
                        });

                    }
                });

            }
        });

        // Set Form focus
        $("form#login .form-group:has(.form-control):first .form-control").focus();
    });
</script>

<!-- Bottom Scripts -->
<script src="${BASE_PATH}/static/admin/js/bootstrap.min.js"></script>
<script src="${BASE_PATH}/static/admin/js/TweenMax.min.js"></script>
<script src="${BASE_PATH}/static/admin/js/resizeable.js"></script>
<script src="${BASE_PATH}/static/admin/js/joinable.js"></script>
<script src="${BASE_PATH}/static/admin/js/xenon-api.js"></script>
<script src="${BASE_PATH}/static/admin/js/xenon-toggles.js"></script>
<script src="${BASE_PATH}/static/admin/js/jquery-validate/jquery.validate.min.js"></script>
<script src="${BASE_PATH}/static/admin/js/toastr/toastr.min.js"></script>


<!-- JavaScripts initializations and stuff -->
<script src="${BASE_PATH}/static/admin/js/xenon-custom.js"></script>

</body>
</html>