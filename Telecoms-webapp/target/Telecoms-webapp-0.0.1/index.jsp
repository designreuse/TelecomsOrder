<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>智能园区云服务平台</title>
    <link href="css/common/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="css/common/animate.css" rel="stylesheet">
    <link href="css/common/style.css" rel="stylesheet">
</head>
<body class="gray-bg">

<div class="loginColumns animated fadeInDown">
    <div class="row">

        <div class="col-md-6">
            <h2 class="font-bold">Welcome to Cloudx</h2>
            <p>
                Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.
            </p>
            <p>
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the
                industry's standard dummy text ever since the 1500s.
            </p>
            <p>
                When an unknown printer took a galley of type and scrambled it to make a type specimen book.
            </p>
            <p>
                <small>It has survived not only five centuries, but also the leap into electronic typesetting, remaining
                    essentially unchanged.
                </small>
            </p>
        </div>
        <div class="col-md-6">
            <div class="ibox-content">
                <form class="m-t" role="form" method="post" action="j_spring_security_check">
                    <div class="form-group">
                        <input type="text" name="flute_username" class="form-control" placeholder="用户名" required="">
                    </div>
                    <div class="form-group">
                        <input type="password" name="flute_password" class="form-control" placeholder="密码" required="">
                    </div>
                    <button type="submit" class="btn btn-primary block full-width m-b">登录</button>

                    <a href="#">
                        <small>忘记密码?</small>
                    </a>

                    <p class="text-muted text-center">
                        <small>Do not have an account?</small>
                    </p>
                    <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
                </form>
            </div>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="col-md-6">
            江苏苏源高科技有限公司
        </div>
        <div class="col-md-6 text-right">
            <small>© 2015</small>
        </div>
    </div>
</div>
<script>
    if(top.location != location){
        top.location.href= location.href;
    }
</script>
</body>
</html>
