var _currentPage;
var _layerIdx;
function loadTodoTask(page) {
    _currentPage = page;
    $.post('workflow/getTodoTask', {page: page, rows: 10}, function (res) {
        $('#numOfTaskTip').text(res.data.totalRecord);
        if (res.data.totalPage > 1) {
            $('#pagination').bootstrapPaginator({
                currentPage: res.data.pageNo,//当前页
                totalPages: res.data.totalPage,//总页数
                numberofPages: res.data.pageSize,//显示的页数
                alignment: 'right',
                showFirst: false,
                showLast: false,
                itemTexts: function (type, page, current) { //修改显示文字
                    switch (type) {
                        case 'first':
                            return '首页';
                        case 'prev':
                            return '上一页';
                        case 'next':
                            return '下一页';
                        case 'last':
                            return '末页';
                        case 'page':
                            return page;
                    }
                }, onPageClicked: function (event, originalEvent, type, page) { //异步分页
                    loadTodoTask(page);
                },
            });
        }
        var trs = '';
        $.each(res.data.results, function (index, item) {
            var data = {
                _a: item.taskInstanceId,
                _b: item.processInstanceId,
                _c: item.bizKey,
                _d: item.processDefinitionKey,
                _e: item.formUri
            };
            trs +=
                '<tr>' +
                '<td>' + item.bizKey + '</td>' +
                //'<td class="client-avatar"><img alt="image" src="images/common/profile_small.jpg" title="' + item.userName + '"></td>' +
                '<td>' + item.processDefinitionName + '</td>' +
                //'<td>' + item.nodeName + '</td>' +
                '<td>' + item.description + '</td>' +
                '<td>' + item.PI_START_TIME + '</td>';
            if (item.claimTime) {
                trs += '<td class="client-status">';
                trs += '<button onclick="unclaimTask(\'' + data._a + '\',\'' + data._b + '\',\'' + data._c + '\',\'' + data._d + '\')" class="btn btn-warning btn-circle" title="退签" type="button"><i class="fa fa-unlock"></i></button>';
                trs += '<button onclick="viewTask('+page+','+index+')" class="btn btn-success btn-circle" title="查看" type="button"><i class="fa fa-link"></i></button>';
                trs += '</td>';
            } else {
                trs += '<td class="client-status"><button onclick="claimTask(\'' + data._a + '\',\'' + data._b + '\',\'' + data._c + '\',\'' + data._d + '\')" class="btn btn-primary btn-circle" title="签收" type="button"><i class="fa fa-lock"></i></button></td>';
            }
            trs += '</tr>';

        });
        $('#todoTaskTable tbody').empty();
        $('#todoTaskTable tbody').append(trs);
    }, 'json');
}
function viewTask(page,index) {
    top.openTab('待办事宜', 'TodoTask', 'html/workflow/taskCenter.html?page='+page+'&index='+index, {},true);
}
function claimTask(taskId, processInstanceId, bizKey, processDefinitionKey) {
    _layerIdx = layer.load(0, {shade: false});
    $.post('workflow/completeTask', {
        taskId: taskId,
        processInstanceId: processInstanceId,
        bizKey: bizKey,
        processDefinitionKey: processDefinitionKey,
        commandType: 'claim'
    }, function (res) {
        if (res.status == '0') {
            loadTodoTask(_currentPage);
        } else {
            BModal.alert('待办签收失败。');
        }
        layer.close(_layerIdx);
    }, 'json');
}
function unclaimTask(taskId, processInstanceId, bizKey, processDefinitionKey) {
    _layerIdx = layer.load(0, {shade: false});
    $.post('workflow/completeTask', {
        taskId: taskId,
        processInstanceId: processInstanceId,
        bizKey: bizKey,
        processDefinitionKey: processDefinitionKey,
        commandType: 'releaseTask'
    }, function (res) {
        if (res.status == '0') {
            loadTodoTask(_currentPage);
        } else {
            BModal.alert('待办退签失败。');
        }
        layer.close(_layerIdx);
    }, 'json');
}
$(function () {
    loadTodoTask(1);
    $('.close-link:eq(0)').click(function () {
        loadTodoTask(_currentPage);
    });
    $('.close-link:eq(1)').click(function () {
        BModal.open('发起流程','html/workflow/workflowList.html','600px', '400px',false,function(){
            loadTodoTask(1);
        });
    });
    $('.close-link:eq(2)').click(function () {
        top.openTab('待办事宜','TodoTask','html/workflow/taskCenter.html');
    });
});