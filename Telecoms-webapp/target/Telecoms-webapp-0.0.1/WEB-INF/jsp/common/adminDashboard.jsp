<%--
  Created by IntelliJ IDEA.
  User: Bingdor
  Date: 2015/12/1
  Time: 15:12
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
    <title></title>
    <link href="css/common/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Toastr style -->
    <link href="css/common/plugins/toastr/toastr.min.css" rel="stylesheet">

    <link href="css/common/animate.css" rel="stylesheet">
    <link href="css/common/style.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/common/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="css/common/plugins/dataTables/dataTables.responsive.css" rel="stylesheet">
    <link href="css/common/plugins/dataTables/dataTables.tableTools.min.css" rel="stylesheet">
    <%--<link href="css/common/plugins/footable/footable.core.css" rel="stylesheet">--%>
    <!-- Mainly scripts -->
</head>
<body>
<div class="wrapper wrapper-content gray-bg animated">
    <!-- Dashboard 开始 -->
    <div class="row">
        <div class="col-sm-3">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-success pull-right">本年度</span>
                    <h5>收费总额</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">--</h1>

                    <div class="stat-percent font-bold text-success">--</div>
                    <small>同比</small>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-info pull-right">本年度</span>
                    <h5>欠费总额</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">--</h1>

                    <div class="stat-percent font-bold text-info">--</div>
                    <small>同比</small>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-primary pull-right">截止当前</span>
                    <h5>房产面积</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">--</h1>

                    <div class="stat-percent font-bold text-navy">--</div>
                    <small>租用率</small>
                </div>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-danger pull-right">截止当前</span>
                    <h5>客户数量</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">80,600</h1>

                    <div class="stat-percent font-bold text-danger">38% <i class="fa fa-level-down"></i></div>
                    <small>同比</small>
                </div>
            </div>
        </div>
    </div>
    <!-- 图表统计 开始 -->
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>营收统计</h5>
                    <div class="pull-right">
                        <div class="btn-group">
                            <button type="button" class="btn btn-xs btn-white active">实收</button>
                            <button type="button" class="btn btn-xs btn-white">预收</button>
                            <button type="button" class="btn btn-xs btn-white">押金</button>
                        </div>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-9">
                            <div>
                                <canvas id="flot-dashboard-chart" height="80"></canvas>
                            </div>
                        </div>
                        <div class="col-sm-3">
                            <ul class="stat-list active">
                                <li>
                                    <h2 class="no-margins">2,346</h2>
                                    <small>出售实收</small>
                                    <div class="stat-percent">48% <i class="fa fa-level-up text-navy"></i></div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </li>
                                <li>
                                    <h2 class="no-margins ">4,422</h2>
                                    <small>租赁实收</small>
                                    <div class="stat-percent">60% <i class="fa fa-level-down text-navy"></i></div>
                                    <div class="progress progress-mini">
                                        <div style="width: 60%;" class="progress-bar"></div>
                                    </div>
                                </li>
                                <li>
                                    <h2 class="no-margins ">9,180</h2>
                                    <small>其他实收</small>
                                    <div class="stat-percent">22% <i class="fa fa-bolt text-navy"></i></div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar"></div>
                                    </div>
                                </li>
                            </ul>
                            <ul class="stat-list hide">
                                <li>
                                    <h2 class="no-margins">2,346</h2>
                                    <small>出售预收</small>
                                    <div class="stat-percent">48% <i class="fa fa-level-up text-navy"></i></div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </li>
                                <li>
                                    <h2 class="no-margins ">4,422</h2>
                                    <small>租赁预收</small>
                                    <div class="stat-percent">60% <i class="fa fa-level-down text-navy"></i></div>
                                    <div class="progress progress-mini">
                                        <div style="width: 60%;" class="progress-bar"></div>
                                    </div>
                                </li>
                                <li>
                                    <h2 class="no-margins ">9,180</h2>
                                    <small>其他预收</small>
                                    <div class="stat-percent">22% <i class="fa fa-bolt text-navy"></i></div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar"></div>
                                    </div>
                                </li>
                            </ul>
                            <ul class="stat-list hide">
                                <li>
                                    <h2 class="no-margins">2,346</h2>
                                    <small>房屋租赁押金</small>
                                    <div class="stat-percent">48% <i class="fa fa-level-up text-navy"></i></div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </li>
                                <li>
                                    <h2 class="no-margins ">4,422</h2>
                                    <small>车位租赁押金</small>
                                    <div class="stat-percent">60% <i class="fa fa-level-down text-navy"></i></div>
                                    <div class="progress progress-mini">
                                        <div style="width: 60%;" class="progress-bar"></div>
                                    </div>
                                </li>
                                <li>
                                    <h2 class="no-margins ">9,180</h2>
                                    <small>公共资源租赁押金</small>
                                    <div class="stat-percent">22% <i class="fa fa-bolt text-navy"></i></div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar"></div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <!-- 待办事宜&欠费排行榜 开始 -->
    <div class="row">
        <div class="col-sm-8">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>工作待办</h5>

                    <div class="ibox-tools">
                        <a class="close-link" title="发起流程">
                            <i class="fa fa-tasks"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <div class="input-daterange input-group">
                                    <input class="form-control" type="text" name="daterange"
                                           value="01/01/2015 - 01/31/2015"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="form-group">
                                <select name="status" id="status" class="form-control">
                                    <option value="1" selected>待办任务</option>
                                    <option value="0">已办任务</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="input-group">
                                <input type="text" placeholder="流程名称/发起人 " class="input form-control">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn btn-primary"><i class="fa fa-search"></i> 搜索
                                    </button>
                                </span>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-12">
                            <table id="todoTaskTable" class="table table-striped table-hover">
                                <thead>
                                <tr>
                                    <th>流程编号</th>
                                    <th>流程名称</th>
                                    <th>任务描述</th>
                                    <th>接收时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                            <div id="pagination"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="ibox ">
                <div class="ibox-content">
                    <div class="tab-content">
                        <div id="contact-1" class="tab-pane active">
                            <div class="row m-b-lg">
                                <div class="col-sm-4 text-center">
                                    <h2>Nicki Smith</h2>

                                    <div class="m-b-sm">
                                        <img alt="image" class="img-circle" src="img/a2.jpg"
                                             style="width: 62px">
                                    </div>
                                </div>
                                <div class="col-sm-8">
                                    <strong>
                                        About me
                                    </strong>

                                    <p>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                        tempor incididunt ut labore et dolore magna aliqua.
                                    </p>
                                    <button type="button" class="btn btn-primary btn-sm btn-block"><i
                                            class="fa fa-envelope"></i> Send Message
                                    </button>
                                </div>
                            </div>
                            <div class="client-detail">
                                <div class="full-height-scroll">

                                    <strong>Last activity</strong>

                                    <ul class="list-group clear-list">
                                        <li class="list-group-item fist-item">
                                            <span class="pull-right"> 09:00 pm </span>
                                            Please contact me
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 10:16 am </span>
                                            Sign a contract
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 08:22 pm </span>
                                            Open new shop
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 11:06 pm </span>
                                            Call back to Sylvia
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 12:00 am </span>
                                            Write a letter to Sandra
                                        </li>
                                    </ul>
                                    <strong>Notes</strong>

                                    <p>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                        tempor incididunt ut labore et dolore magna aliqua.
                                    </p>
                                    <hr/>
                                    <strong>Timeline activity</strong>

                                    <div id="vertical-timeline" class="vertical-container dark-timeline">
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-coffee"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Conference on the sales results for the previous year.
                                                </p>
                                                <span class="vertical-date small text-muted"> 2:10 pm - 12.06.2014 </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="contact-2" class="tab-pane">
                            <div class="row m-b-lg">
                                <div class="col-sm-4 text-center">
                                    <h2>Edan Randall</h2>

                                    <div class="m-b-sm">
                                        <img alt="image" class="img-circle" src="img/a3.jpg"
                                             style="width: 62px">
                                    </div>
                                </div>
                                <div class="col-sm-8">
                                    <strong>
                                        About me
                                    </strong>

                                    <p>
                                        Many desktop publishing packages and web page editors now use Lorem Ipsum as
                                        their default tempor incididunt model text.
                                    </p>
                                    <button type="button" class="btn btn-primary btn-sm btn-block"><i
                                            class="fa fa-envelope"></i> Send Message
                                    </button>
                                </div>
                            </div>
                            <div class="client-detail">
                                <div class="full-height-scroll">

                                    <strong>Last activity</strong>

                                    <ul class="list-group clear-list">
                                        <li class="list-group-item fist-item">
                                            <span class="pull-right"> 09:00 pm </span>
                                            Lorem Ipsum available
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 10:16 am </span>
                                            Latin words, combined
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 08:22 pm </span>
                                            Open new shop
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 11:06 pm </span>
                                            The generated Lorem Ipsum
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 12:00 am </span>
                                            Content here, content here
                                        </li>
                                    </ul>
                                    <strong>Notes</strong>

                                    <p>
                                        There are many variations of passages of Lorem Ipsum available, but the majority
                                        have suffered alteration in some form, by injected humour, or randomised words.
                                    </p>
                                    <hr/>
                                    <strong>Timeline activity</strong>

                                    <div id="vertical-timeline" class="vertical-container dark-timeline">
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-bolt"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>There are many variations of passages of Lorem Ipsum available.
                                                </p>
                                                <span class="vertical-date small text-muted"> 06:10 pm - 11.03.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon navy-bg">
                                                <i class="fa fa-warning"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>The generated Lorem Ipsum is therefore.
                                                </p>
                                                <span class="vertical-date small text-muted"> 02:50 pm - 03.10.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-coffee"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Conference on the sales results for the previous year.
                                                </p>
                                                <span class="vertical-date small text-muted"> 2:10 pm - 12.06.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="contact-3" class="tab-pane">
                            <div class="row m-b-lg">
                                <div class="col-sm-4 text-center">
                                    <h2>Jasper Carson</h2>

                                    <div class="m-b-sm">
                                        <img alt="image" class="img-circle" src="img/a4.jpg"
                                             style="width: 62px">
                                    </div>
                                </div>
                                <div class="col-sm-8">
                                    <strong>
                                        About me
                                    </strong>

                                    <p>
                                        Latin professor at Hampden-Sydney College in Virginia, looked embarrassing
                                        hidden in the middle.
                                    </p>
                                    <button type="button" class="btn btn-primary btn-sm btn-block"><i
                                            class="fa fa-envelope"></i> Send Message
                                    </button>
                                </div>
                            </div>
                            <div class="client-detail">
                                <div class="full-height-scroll">

                                    <strong>Last activity</strong>

                                    <ul class="list-group clear-list">
                                        <li class="list-group-item fist-item">
                                            <span class="pull-right"> 09:00 pm </span>
                                            Aldus PageMaker including
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 10:16 am </span>
                                            Finibus Bonorum et Malorum
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 08:22 pm </span>
                                            Write a letter to Sandra
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 11:06 pm </span>
                                            Standard chunk of Lorem
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 12:00 am </span>
                                            Open new shop
                                        </li>
                                    </ul>
                                    <strong>Notes</strong>

                                    <p>
                                        Lorem Ipsum passage, and going through the cites of the word in classical
                                        literature, discovered the undoubtable source.
                                    </p>
                                    <hr/>
                                    <strong>Timeline activity</strong>

                                    <div id="vertical-timeline" class="vertical-container dark-timeline">
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-coffee"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Conference on the sales results for the previous year.
                                                </p>
                                                <span class="vertical-date small text-muted"> 2:10 pm - 12.06.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-bolt"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>There are many variations of passages of Lorem Ipsum available.
                                                </p>
                                                <span class="vertical-date small text-muted"> 06:10 pm - 11.03.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon navy-bg">
                                                <i class="fa fa-warning"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>The generated Lorem Ipsum is therefore.
                                                </p>
                                                <span class="vertical-date small text-muted"> 02:50 pm - 03.10.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-coffee"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Conference on the sales results for the previous year.
                                                </p>
                                                <span class="vertical-date small text-muted"> 2:10 pm - 12.06.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="contact-4" class="tab-pane">
                            <div class="row m-b-lg">
                                <div class="col-sm-4 text-center">
                                    <h2>Reuben Pacheco</h2>

                                    <div class="m-b-sm">
                                        <img alt="image" class="img-circle" src="img/a5.jpg"
                                             style="width: 62px">
                                    </div>
                                </div>
                                <div class="col-sm-8">
                                    <strong>
                                        About me
                                    </strong>

                                    <p>
                                        Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero,written in
                                        45 BC. This book is a treatise on.
                                    </p>
                                    <button type="button" class="btn btn-primary btn-sm btn-block"><i
                                            class="fa fa-envelope"></i> Send Message
                                    </button>
                                </div>
                            </div>
                            <div class="client-detail">
                                <div class="full-height-scroll">

                                    <strong>Last activity</strong>

                                    <ul class="list-group clear-list">
                                        <li class="list-group-item fist-item">
                                            <span class="pull-right"> 09:00 pm </span>
                                            The point of using
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 10:16 am </span>
                                            Lorem Ipsum is that it has
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 08:22 pm </span>
                                            Text, and a search for 'lorem ipsum'
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 11:06 pm </span>
                                            Passages of Lorem Ipsum
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> 12:00 am </span>
                                            If you are going
                                        </li>
                                    </ul>
                                    <strong>Notes</strong>

                                    <p>
                                        Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore
                                        always free from repetition, injected humour, or non-characteristic words etc.
                                    </p>
                                    <hr/>
                                    <strong>Timeline activity</strong>

                                    <div id="vertical-timeline" class="vertical-container dark-timeline">
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-coffee"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Conference on the sales results for the previous year.
                                                </p>
                                                <span class="vertical-date small text-muted"> 2:10 pm - 12.06.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-bolt"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>There are many variations of passages of Lorem Ipsum available.
                                                </p>
                                                <span class="vertical-date small text-muted"> 06:10 pm - 11.03.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon navy-bg">
                                                <i class="fa fa-warning"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>The generated Lorem Ipsum is therefore.
                                                </p>
                                                <span class="vertical-date small text-muted"> 02:50 pm - 03.10.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-coffee"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Conference on the sales results for the previous year.
                                                </p>
                                                <span class="vertical-date small text-muted"> 2:10 pm - 12.06.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="company-1" class="tab-pane">
                            <div class="m-b-lg">
                                <h2>Tellus Institute</h2>

                                <p>
                                    Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero,written in 45
                                    BC. This book is a treatise on.
                                </p>

                                <div>
                                    <small>Active project completion with: 48%</small>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="client-detail">
                                <div class="full-height-scroll">

                                    <strong>Last activity</strong>

                                    <ul class="list-group clear-list">
                                        <li class="list-group-item fist-item">
                                            <span class="pull-right"> <span
                                                    class="label label-primary">NEW</span> </span>
                                            The point of using
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> <span
                                                    class="label label-warning">WAITING</span></span>
                                            Lorem Ipsum is that it has
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> <span
                                                    class="label label-danger">BLOCKED</span> </span>
                                            If you are going
                                        </li>
                                    </ul>
                                    <strong>Notes</strong>

                                    <p>
                                        Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore
                                        always free from repetition, injected humour, or non-characteristic words etc.
                                    </p>
                                    <hr/>
                                    <strong>Timeline activity</strong>

                                    <div id="vertical-timeline" class="vertical-container dark-timeline">
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-coffee"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Conference on the sales results for the previous year.
                                                </p>
                                                <span class="vertical-date small text-muted"> 2:10 pm - 12.06.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-bolt"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>There are many variations of passages of Lorem Ipsum available.
                                                </p>
                                                <span class="vertical-date small text-muted"> 06:10 pm - 11.03.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon navy-bg">
                                                <i class="fa fa-warning"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>The generated Lorem Ipsum is therefore.
                                                </p>
                                                <span class="vertical-date small text-muted"> 02:50 pm - 03.10.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-coffee"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Conference on the sales results for the previous year.
                                                </p>
                                                <span class="vertical-date small text-muted"> 2:10 pm - 12.06.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="company-2" class="tab-pane">
                            <div class="m-b-lg">
                                <h2>Penatibus Consulting</h2>

                                <p>
                                    There are many variations of passages of Lorem Ipsum available, but the majority
                                    have suffered alteration in some.
                                </p>

                                <div>
                                    <small>Active project completion with: 22%</small>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="client-detail">
                                <div class="full-height-scroll">

                                    <strong>Last activity</strong>

                                    <ul class="list-group clear-list">
                                        <li class="list-group-item fist-item">
                                            <span class="pull-right"> <span class="label label-warning">WAITING</span> </span>
                                            Aldus PageMaker
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"><span
                                                    class="label label-primary">NEW</span> </span>
                                            Lorem Ipsum, you need to be sure
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"> <span
                                                    class="label label-danger">BLOCKED</span> </span>
                                            The generated Lorem Ipsum
                                        </li>
                                    </ul>
                                    <strong>Notes</strong>

                                    <p>
                                        Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore
                                        always free from repetition, injected humour, or non-characteristic words etc.
                                    </p>
                                    <hr/>
                                    <strong>Timeline activity</strong>

                                    <div id="vertical-timeline" class="vertical-container dark-timeline">
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-coffee"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Conference on the sales results for the previous year.
                                                </p>
                                                <span class="vertical-date small text-muted"> 2:10 pm - 12.06.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-bolt"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>There are many variations of passages of Lorem Ipsum available.
                                                </p>
                                                <span class="vertical-date small text-muted"> 06:10 pm - 11.03.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon navy-bg">
                                                <i class="fa fa-warning"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>The generated Lorem Ipsum is therefore.
                                                </p>
                                                <span class="vertical-date small text-muted"> 02:50 pm - 03.10.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-coffee"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Conference on the sales results for the previous year.
                                                </p>
                                                <span class="vertical-date small text-muted"> 2:10 pm - 12.06.2014 </span>
                                            </div>
                                        </div>
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div id="company-3" class="tab-pane">
                            <div class="m-b-lg">
                                <h2>Ultrices Incorporated</h2>

                                <p>
                                    Many desktop publishing packages and web page editors now use Lorem Ipsum as their
                                    default model text.
                                </p>

                                <div>
                                    <small>Active project completion with: 72%</small>
                                    <div class="progress progress-mini">
                                        <div style="width: 72%;" class="progress-bar"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="client-detail">
                                <div class="full-height-scroll">

                                    <strong>Last activity</strong>

                                    <ul class="list-group clear-list">
                                        <li class="list-group-item fist-item">
                                            <span class="pull-right"> <span
                                                    class="label label-danger">BLOCKED</span> </span>
                                            Hidden in the middle of text
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right"><span
                                                    class="label label-primary">NEW</span> </span>
                                            Non-characteristic words etc.
                                        </li>
                                        <li class="list-group-item">
                                            <span class="pull-right">  <span class="label label-warning">WAITING</span> </span>
                                            Bonorum et Malorum
                                        </li>
                                    </ul>
                                    <strong>Notes</strong>

                                    <p>
                                        There are many variations of passages of Lorem Ipsum available, but the majority
                                        have suffered alteration in some form, by injected humour.
                                    </p>
                                    <hr/>
                                    <strong>Timeline activity</strong>

                                    <div id="vertical-timeline" class="vertical-container dark-timeline">
                                        <div class="vertical-timeline-block">
                                            <div class="vertical-timeline-icon gray-bg">
                                                <i class="fa fa-briefcase"></i>
                                            </div>
                                            <div class="vertical-timeline-content">
                                                <p>Many desktop publishing packages and web page editors now use Lorem.
                                                </p>
                                                <span class="vertical-date small text-muted"> 4:20 pm - 10.05.2014 </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="js/common/jquery-2.1.1.js"></script>
<script src="js/common/bootstrap.min.js"></script>
<script src="js/common/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Data Tables -->
<script src="js/common/plugins/bootstrap-paginator/bootstrap-paginator.js"></script>
<script src="js/common/plugins/layer/layer.js"></script>

<script src="js/common/plugins/flot/jquery.flot.js"></script>
<script src="js/common/plugins/flot/jquery.flot.tooltip.min.js"></script>
<script src="js/common/plugins/flot/jquery.flot.spline.js"></script>
<script src="js/common/plugins/flot/jquery.flot.resize.js"></script>
<script src="js/common/plugins/flot/jquery.flot.pie.js"></script>
<script src="js/common/plugins/flot/jquery.flot.symbol.js"></script>
<script src="js/common/plugins/flot/jquery.flot.time.js"></script>
<script src="js/common/workflow.js"></script>
<script src="../../../js/common/common.js"></script>
<script src="js/common/workflowWidget.js"></script>

<!-- ChartJS-->
<script src="js/common/plugins/chartJs/Chart.min.js"></script>
<script>

    $(document).ready(function () {
        initOverviewBoard();
        initLineChart();
        initRightOverview();
        $('.pull-right button').click(function(){
            $('.pull-right button.active').removeClass('active');
            $(this).addClass('active');
            $('.stat-list').removeClass('active');
            $('.stat-list').addClass('hide');
            $('.stat-list:eq('+$(this).index()+')').removeClass('hide');
        });
    });
    function initOverviewBoard(){
        $.post('overview', {}, function (res) {
            if (res.status == '0') {
                $('.ibox-content:eq(0) h1').text(res.data.RCV_AMT);
                $('.ibox-content:eq(0) div:eq(0)').html(res.data.RCV_AMT_RATE + ' <i class="fa fa-level-up"></i>');

                $('.ibox-content:eq(1) h1').text(res.data.OWE_AMT);
                $('.ibox-content:eq(1) div:eq(0)').html(res.data.OWE_AMT_RATE + ' <i class="fa fa-level-up"></i>');

                $('.ibox-content:eq(2) h1').text(res.data.ROOM_AREA);
                $('.ibox-content:eq(2) div:eq(0)').html(res.data.ROOM_AREA_RATE + ' <i class="fa fa-level-up"></i>');

                $('.ibox-content:eq(3) h1').text(res.data.CONS_NUM);
                $('.ibox-content:eq(3) div:eq(0)').html(res.data.CONS_NUM_RATE + ' <i class="fa fa-level-up"></i>');
            }
        }, 'json');
    }
    function initRightOverview(){
        $.post('yearCharge',{},function(res){
            if(res.status == '0'){
                $('.stat-list:eq(0) li:eq(0) h2').text(res.data.SALES_RCVED_AMT);
                $('.stat-list:eq(0) li:eq(1) h2').text(res.data.RENTAL_RCVED_AMT);
                $('.stat-list:eq(0) li:eq(2) h2').text(res.data.OTHER_RCVED_AMT);

                $('.stat-list:eq(1) li:eq(0) h2').text(res.data.SALES_PRE_AMT);
                $('.stat-list:eq(1) li:eq(1) h2').text(res.data.RENTAL_PRE_AMT);
                $('.stat-list:eq(1) li:eq(2) h2').text(res.data.OTHER_PRE_AMT);

                $('.stat-list:eq(2) li:eq(0) h2').text(res.data.ROOM_DEPOSIT_AMT);
                $('.stat-list:eq(2) li:eq(1) h2').text(res.data.PARKING_DEPOSIT_AMT);
                $('.stat-list:eq(2) li:eq(2) h2').text(res.data.PUBLIC_DEPOSIT_AMT);
            }else{
                BModal.alert(res.msg)
            }
        },'json');
    }
    function initLineChart(){
        $.post('monthData',{},function(res){
            if(res.status=='0'){
                var data = {
                    labels: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    datasets: [{
                        label: '应收金额',
                        fillColor: 'rgba(220,220,220,0.5)',
                        strokeColor: 'rgba(220,220,220,1)',
                        pointColor: 'rgba(220,220,220,1)',
                        pointStrokeColor: '#fff',
                        pointHighlightFill: '#fff',
                        pointHighlightStroke: 'rgba(220,220,220,1)',
                        data: res.data.RCV_AMT
                    }, {
                        label: '实收金额',
                        fillColor: 'rgba(26,179,148,0.5)',
                        strokeColor: 'rgba(26,179,148,0.7)',
                        pointColor: 'rgba(26,179,148,1)',
                        pointStrokeColor: '#fff',
                        pointHighlightFill: '#fff',
                        pointHighlightStroke: 'rgba(26,179,148,1)',
                        data: res.data.RCVED_AMT
                    }]
                };
                var defaults = {
                    scaleShowGridLines: true,
                    scaleGridLineColor: 'rgba(0,0,0,.05)',
                    scaleGridLineWidth: 1,
                    scaleShowLabels : true,
                    bezierCurve: true,
                    bezierCurveTension: 0.4,
                    pointDot: true,
                    pointDotRadius: 4,
                    pointDotStrokeWidth: 1,
                    pointHitDetectionRadius: 20,
                    datasetStroke: true,
                    datasetStrokeWidth: 2,
                    datasetFill: true,
                    responsive: true
                };
                var context = $('#flot-dashboard-chart').get(0).getContext('2d');
                var lineChart = new Chart(context).Line(data, defaults);
//                console.log(lineChart.generateLegend());
            }else{
                BModal.alert(res.msg);
            }
        },'json');
    }
</script>
</html>
