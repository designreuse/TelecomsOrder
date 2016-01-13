<#if appNo ??>
<div class="row">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>历史环节</h5>
            </div>
            <div class="ibox-content">
                <div class="full-height-scroll">
                    <div class="table-responsive" class="col-sm-5">
                        <div class="jqGrid_wrapper">
                            <table id="historyApproveTable"></table>
                            <div id="historyApprovePager"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        $('#historyApproveTable').jqGrid({
            url: '../../workflow/getWorkflowApprove',
            postData: {
                appNo: '${appNo}'
            },
            datatype: 'json',
            autowidth: true,
            shrinkToFit: true,
            colNames: ['处理人','流程环节', '操作类型', '处理意见', '接收时间','完成时间'],
            colModel: [
                {name: 'assigneeUser', index: 'assigneeUser', width:'10%', align: 'center'},
                {name: 'nodeName', index: 'nodeName', width:'15%',align: 'center'},
                {name: 'commandType', index: 'commandType', width:'10%',align: 'center'},
                {name: 'taskComment', index: 'taskComment',width:'35%', align: 'left'},
                {name: 'claimTime', index: 'claimTime', width:'20%',align: 'center'},
                {name: 'endTime', index: 'endTime', width:'20%',align: 'center'}
            ],
            rowNum: 10,
            rowList: [10, 20, 30],
            pager: '#historyApprovePager',
            rownumbers: true,
            hidegrid: false
        });
        $('#historyApproveTable').jqGrid('navGrid', '#historyApprovePager', {
                    add: false,
                    edit: false,
                    del: false,
                    search: false
                },
                {
                    reloadAfterSubmit: true
                });
    </script>
</div>
</#if>