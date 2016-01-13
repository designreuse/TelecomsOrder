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

    <!-- Mainly scripts -->
    <script src="js/common/jquery-2.1.1.js"></script>
    <script src="js/common/bootstrap.min.js"></script>
    <script src="js/common/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="js/common/plugins/pace/pace.min.js"></script>
    <script src="js/common/plugins/jquery-ui/jquery-ui.min.js"></script>
    <script src="js/common/plugins/layer/layer.js"></script>
    <script src="js/common/init.js"></script>
    <script src="js/common/workflow.js"></script>
    <!-- Data Tables -->
    <link href="css/common/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="css/common/plugins/dataTables/dataTables.responsive.css" rel="stylesheet">
    <link href="css/common/plugins/dataTables/dataTables.tableTools.min.css" rel="stylesheet">
</head>
<body>
<div class="wrapper wrapper-content gray-bg animated">
    <!-- 通知公告&常用菜单 开始 -->
    <div class="row">
        <!-- 待办事宜 开始 -->
        <div class="col-sm-8">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="ibox-tools">
                        <a class="close-link" title="发起流程">
                            <i class="fa fa-tasks"></i>
                        </a>
                        <a class="close-link" title="高级搜索">
                            <i class="fa fa-filter"></i>
                        </a>
                        <a class="collapse-link" title="放大窗口">
                            <i class="fa fa-expand"></i>
                        </a>
                    </div>
                    <h2>待办事宜</h2>
                    <div class="clients-list">
                        <ul class="nav nav-tabs">
                            <span class="pull-right small text-muted"><span id="numOfTaskTip">0</span> 项待办任务</span>
                            <li class="active"><a data-toggle="tab" href="#tab-1"><i class="fa fa-user"></i> 待办任务</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-2"><i class="fa fa-briefcase"></i> 已办任务</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="table-responsive">
                                    <table id="todoTaskTable" class="table table-striped table-hover">
                                        <tbody>
                                        </tbody>
                                    </table>
                                </div>
                                <div id="pagination"></div>
                            </div>
                            <div id="tab-2" class="tab-pane">
                                <div class="full-height-scroll">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-hover">
                                            <tbody>
                                            <tr>
                                                <td class="client-avatar"><img alt="image" src="images/common/profile_small.jpg"> </td>
                                                <td><a data-toggle="tab" href="#contact-1" class="client-link">停电计划申请</a></td>
                                                <td> 停电审批</td>
                                                <td> 2015-12-14 11:00:00</td>
                                                <td> 2015-12-15 11:00:00</td>
                                                <td class="client-status"><span class="label label-primary">查看</span></td>
                                            </tr>
                                            <tr>
                                                <td class="client-avatar"><img alt="image" src="images/common/profile_small.jpg"> </td>
                                                <td><a data-toggle="tab" href="#contact-1" class="client-link">停电计划申请</a></td>
                                                <td> 停电审批</td>
                                                <td> 2015-12-14 11:00:00</td>
                                                <td> 2015-12-15 11:00:00</td>
                                                <td class="client-status"><span class="label label-primary">查看</span></td>
                                            </tr>
                                            <tr>
                                                <td class="client-avatar"><img alt="image" src="images/common/profile_small.jpg"> </td>
                                                <td><a data-toggle="tab" href="#contact-1" class="client-link">停电计划申请</a></td>
                                                <td> 停电审批</td>
                                                <td> 2015-12-14 11:00:00</td>
                                                <td> 2015-12-15 11:00:00</td>
                                                <td class="client-status"><span class="label label-primary">查看</span></td>
                                            </tr>
                                            <tr>
                                                <td class="client-avatar"><img alt="image" src="images/common/profile_small.jpg"> </td>
                                                <td><a data-toggle="tab" href="#contact-1" class="client-link">停电计划申请</a></td>
                                                <td> 停电审批</td>
                                                <td> 2015-12-14 11:00:00</td>
                                                <td> 2015-12-15 11:00:00</td>
                                                <td class="client-status"><span class="label label-primary">查看</span></td>
                                            </tr>
                                            <tr>
                                                <td class="client-avatar"><img alt="image" src="images/common/profile_small.jpg"> </td>
                                                <td><a data-toggle="tab" href="#contact-1" class="client-link">停电计划申请</a></td>
                                                <td> 停电审批</td>
                                                <td> 2015-12-14 11:00:00</td>
                                                <td> 2015-12-15 11:00:00</td>
                                                <td class="client-status"><span class="label label-primary">查看</span></td>
                                            </tr>
                                            <tr>
                                                <td class="client-avatar"><img alt="image" src="images/common/profile_small.jpg"> </td>
                                                <td><a data-toggle="tab" href="#contact-1" class="client-link">停电计划申请</a></td>
                                                <td> 停电审批</td>
                                                <td> 2015-12-14 11:00:00</td>
                                                <td> 2015-12-15 11:00:00</td>
                                                <td class="client-status"><span class="label label-primary">查看</span></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 欠费排行榜 开始 -->
        <div class="col-sm-4">
            <div class="ibox">
                <div class="ibox-content">
                    <h2>欠费排行榜</h2>
                    <div id="tab-1" class="tab-pane active">
                        <div class="full-height-scroll">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover">
                                    <tbody>
                                    <tr>
                                        <td class="client-avatar">1</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">2</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">3</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">4</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">5</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">6</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">7</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">8</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">9</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">10</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <!-- 待办事宜 开始 -->
        <div class="col-sm-8">
            <div class="ibox">
                <div class="ibox-content">
                    <h3>通知公告</h3>
                    <div class="clients-list">
                        <div class="full-height-scroll">
                                    <div class="table-responsive">
                                        <table class="table table-striped table-hover">
                                            <tbody>
                                                <tr>
                                                    <td><a data-toggle="tab" href="#contact-1" class="client-link">关于房屋租赁流程变更的通知</a></td>
                                                    <td>
                                                            自2016年1月1日起，园区内所有租户租赁房产必须统一办理电子托收方式缴纳房租费、水电和物业费等，逾期未办理的将不予接受坐收方式缴费，敬请知悉。
                                                    </td>
                                                    <td> 2015-12-14 11:00:00</td>
                                                    <td class="client-status"><span class="label label-primary">查看</span></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 欠费排行榜 开始 -->
        <div class="col-sm-4">
            <div class="ibox">
                <div class="ibox-content">
                    <h2>欠费排行榜</h2>
                    <div id="tab-1" class="tab-pane active">
                        <div class="full-height-scroll">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover">
                                    <tbody>
                                    <tr>
                                        <td class="client-avatar">1</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">2</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">3</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">4</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">5</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">6</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">7</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">8</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">9</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    <tr>
                                        <td class="client-avatar">10</td>
                                        <td><a data-toggle="tab" href="#contact-1" class="client-link">苏宁集团有限公司</a></td>
                                        <td class="client-status"><span class="label label-primary">￥483.00</span></td>
                                    </tr>
                                    </tbody>
                                </table>
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
<script src="js/common/plugins/dataTables/jquery.dataTables.js"></script>
<script src="js/common/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="js/common/plugins/dataTables/dataTables.responsive.js"></script>
<script src="js/common/plugins/dataTables/dataTables.tableTools.min.js"></script>
<script src="js/common/plugins/bootstrap-paginator/bootstrap-paginator.js"></script>
<script src="js/common/common.js"></script>
<script src="js/common/plugins/layer/layer.js"></script>
<script src="js/common/workflowWidget.js"></script>
</html>
