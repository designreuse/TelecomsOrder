<div class="col-sm-12 input-group animated fadeInLeft">
    <ol class="breadcrumb">
    <#if result['processDefinitionName']!="">
        <li><i class="fa fa-tasks"></i> <strong>${result.processDefinitionName}</strong></li>
    </#if>
    <#if result['nodeName']!="">
        <li><strong>${result.nodeName}</strong></li>
    </#if>
    <#if result['bizKey']!=null>
        <li><strong>流程编号：${result.bizKey}</strong></li>
    </#if>
        <span id="_workflow_tools" class="pull-right">
    <#if !result['isHistory']>
        <#list result['commandList'] as command>
            <a class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="bottom"
               data-original-title="${command['name']}" taskId="${result['taskId']}"
               bizKey="${result['bizKey']}" processInstanceId="${result['processInstanceId']}"
               commandId="${command['id']}" commandType="${command['type']}">
                <!-- 签收 -->
                <#if command['type']=="claim">
                    <i class="fa fa-lock"></i>
                    <!-- 释放任务 -->
                <#elseif command['type']=="releaseTask">
                    <i class="fa fa-unlock"></i>
                    <!-- 提交 启动并提交 传递 -->
                <#elseif command['type']=="submit" || command['type']=="startandsubmit" || command['type']=="general">
                    <i class="fa fa-send"></i>
                    <!-- 退回上一步 -->
                <#elseif command['type']=="rollBackTaskPreviousStep">
                    <i class="fa fa-mail-reply"></i>
                    <!-- 终止 -->
                <#elseif command['type']=="terminationProcess">
                    <i class="fa fa-stop"></i>
                <#elseif command['type']=="processStatus">
                    <i class="fa fa-sitemap"></i>
                </#if>
            </a>
        </#list>
    </#if>
    <#if result['bizKey']!=null>
        <a class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="bottom" data-original-title="重新加载">
            <i class="fa fa-refresh"></i>
        </a>
    </#if>
        <a class="btn btn-white btn-sm" data-toggle="tooltip" data-placement="bottom" data-original-title="发起流程">
            <i class="fa fa-plus"></i>
        </a>
    </span>
    </ol>
</div>
<script>
    $("[data-toggle='tooltip']").tooltip();
    $('#_workflow_tools a').click(function () {
        var commandType = $(this).attr('commandType');
        if (commandType) {
        <#if !result['isHistory']>
            var data = {
                taskComment: $('#approveFrom textarea').val(),
                bizKey: $(this).attr('bizKey'),
                processInstanceId: $(this).attr('processInstanceId'),
                taskId: $(this).attr('taskId'),
                commandId: $(this).attr('commandId'),
                commandType: $(this).attr('commandType'),
                taskId: $(this).attr('taskId')
            };
            if (data.commandType == 'claim' || data.commandType == 'releaseTask') {
                layer.closeAll();
                layer.load(0, {shade: .5});
                $.post('../../workflow/completeTask', data, function (res) {
                    layer.closeAll();
                    if (res.status == '0') {
                        loadWorkflowbar('01');
                    } else {
                        BModal.alert(res.msg);
                    }
                }, 'json');
            } else {
                $('#approveFrom input[name=optType]').val($(this).attr('data-original-title'));
                $('#approveFrom input[name=taskId]').val($(this).attr('taskId'));
                $('#approveFrom input[name=bizKey]').val($(this).attr('bizKey'));
                $('#approveFrom input[name=processInstanceId]').val($(this).attr('processInstanceId'));
                $('#approveFrom input[name=commandType]').val($(this).attr('commandType'));
                $('#approveFrom input[name=commandId]').val($(this).attr('commandId'));
                $('#approveFrom textarea').val("");
                layer.open({
                    type: 1,
                    shade: 0.5,
                    area: ['400px', '310px'], //宽高
                    title: '<i class="fa fa-tasks"></i> 流程传递',
                    content: $('#approveFrom')
                });
            }
        </#if>
        } else {
            //刷新
            if ($(this).find('.fa-refresh').length > 0) {
                var index = layer.load(0, {shade: false});
                $('#taskContentFrame').load(function (e) {
                    layer.close(index);
                });
                $('#taskContentFrame')[0].contentWindow.location.reload(true);
            } else {
                BModal.open('<i class="fa fa-tasks"></i> 发起流程', 'html/workflow/workflowList.html', '600px', '400px');
            }
        }
    });
</script>
