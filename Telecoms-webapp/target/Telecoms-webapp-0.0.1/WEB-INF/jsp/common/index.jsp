<%--
  Created by IntelliJ IDEA.
  User: Bingdor
  Date: 2015/11/25
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>智能园区云服务平台</title>
    <link href="css/common/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
    <!-- Toastr style -->
    <link href="css/common/plugins/toastr/toastr.min.css" rel="stylesheet">
    <!-- Gritter -->
    <link href="js/common/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <link href="css/common/animate.css" rel="stylesheet">
    <link href="css/common/style.css" rel="stylesheet">
    <link href="js/common/plugins/layer/skin/layer.css" rel="stylesheet">
    <script src="js/common/jquery-2.1.1.js"></script>
    <script src="js/common/bootstrap.min.js"></script>
    <script src="js/common/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/common/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="js/common/index.js"></script>
    <script src="js/common/inspinia.js"></script>
    <script src="js/common/plugins/pace/pace.min.js"></script>
    <script src="js/common/plugins/layer/layer.js"></script>
</head>
<body>
<div id="wrapper">
    <%@include file="left.jsp" %>
    <%@include file="main.jsp" %>
    <%@include file="header.jsp" %>
</div>
</body>
</html>
