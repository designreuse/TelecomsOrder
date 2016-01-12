/**
 * 工作流jQuery插件
 * @type {{}}
 * @Auth Fangyuan Chen
 * @Date 2015-12-14
 */
jQuery.workflow = {
    completeTask: function (params) {
        layer.closeAll();
        if(params && params.commandType=='claim'){
            layer.load(0, {shade:.5});
            $.post('../../workflow/completeTask', params, function (res) {
                layer.closeAll();
                if (res.status == '0') {
                    BModal.msg(res.msg);
                    loadTasks(1);
                }else{
                    BModal.alert(res.msg);
                }
            }, 'json');
        }else{
            $('#approveFrom textarea').val("");
            layer.open({
                type: 1,
                shade: 0.5,
                area: ['400px', '310px'], //宽高
                title: '<i class="fa fa-tasks"></i> 流程传递',
                content: $('#approveFrom')
            });
        }
    },
    showWorkflowGraph: function (processInstanceId, processDefinitionKey) {
        BModal.open('<i class="fa fa-tasks"></i> 流程图', '../../html/workflow/workflowGraph.html?processInstanceId=' + processInstanceId + '&processDefinitionKey=' + processDefinitionKey, '800px', '600px');
    },
    _listeners: {},
    // 添加
    addEventListener: function (type, fn) {
        if (typeof this._listeners[type] === "undefined") {
            this._listeners[type] = [];
        }
        if (typeof fn === "function") {
            this._listeners[type].push(fn);
        }
        return this;
    },
    // 触发
    triggerEvent: function (type, data) {
        var arrayEvent = this._listeners[type];
        if (arrayEvent instanceof Array) {
            for (var i = 0, length = arrayEvent.length; i < length; i += 1) {
                if (typeof arrayEvent[i] === "function") {
                    arrayEvent[i]({type: type, data: data});
                }
            }
        }
        return this;
    },
    // 删除
    removeEventListener: function (type, fn) {
        var arrayEvent = this._listeners[type];
        if (typeof type === "string" && arrayEvent instanceof Array) {
            if (typeof fn === "function") {
                // 清除当前type类型事件下对应fn方法
                for (var i = 0, length = arrayEvent.length; i < length; i += 1) {
                    if (arrayEvent[i] === fn) {
                        this._listeners[type].splice(i, 1);
                        break;
                    }
                }
            } else {
                // 如果仅仅参数type, 或参数fn邪魔外道，则所有type类型事件清除
                delete this._listeners[type];
            }
        }
        return this;
    }
};

