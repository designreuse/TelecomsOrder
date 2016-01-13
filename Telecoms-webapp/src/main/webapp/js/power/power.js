/**
 * Created by zhumeijun on 2015/12/17.
 */

//初始化历史审批信息
function initHistoryApproveJqGrid(historyApproveTableObj, historyApprovePagerObj, appNo, tableHeight){
    historyApproveTableObj.jqGrid({
        url: "../../power/appPowerOff/queryApproveInfo",
        postData:{
            appNo:appNo
        },
        datatype : "json",
        height: typeof(tableHeight) == "undefined" ?  '100px' : tableHeight,
        autowidth: true,
        shrinkToFit: true,
        colNames : [  '审批人', '审批结果', '审批意见', '审批时间'],
        colModel : [

            {name : 'approveName',  index : 'approveName',width : 110,  align : "center"},
            {name : 'apprRslt',     index : 'apprRslt',   width : 110,  align : "center"},
            {name : 'apprOpinion',  index : 'apprOpinion',width : 300,  align : "left"},
            {name : 'apprDate',     index : 'apprDate',   width : 160,  align : "center"}

        ],
        rowNum : 10,
        rowList : [ 10, 20, 30 ],
        pager : historyApprovePagerObj,
        viewrecords: true,
        rownumbers: true,
        //multiselect : true,
        caption : "历史审批信息",
        hidegrid: false
    });
    historyApproveTableObj.jqGrid("navGrid", historyApprovePagerObj, {
        add: false,
        edit: false,
        del: false,
        search: false
    },
    {
        height: 200,
        reloadAfterSubmit: true
    });
}

//初始化审批结果
function initApproveStatus(apprRsltObj){
    $.ajax({
        type: 'POST',
        url: '../../../system/code/querySysCodes',
        data: {codeSortName: 'APPROVE_STATUS'},
        dataType: 'json',
        success: function(data){
            //BModal.alert(JSON.stringify(data));
            if('0' == data.status){
                $("#apprRslt").empty();
                var items = data.data;
                for(var i = 0; i < items.length; i++) {
                    if(items[i].value == '01'){
                        var option = $("<option selected>").text(items[i].name).val(items[i].value);
                    }else{
                        var option = $("<option>").text(items[i].name).val(items[i].value);
                    }
                    apprRsltObj.append(option);
                }
            } else{
                BModal.alert(data.msg);
            }
        }
    });
}

/*获取审批Panel拼接数据
approvePanel:置放审批panel的容器
tab1Title:审批信息名称
approveTabId:历史审批信息tabId
apprRsltId:审批结果ID
apprOpinionId:审批意见ID
historyApproveDivId:包含审批jqgrid的divID
historyApproveTableId:历史审批信息tableId
historyApprovePagerId:历史审批信息pagerId*/
function getApprovePanel(approvePanelId, tab1Title, approveTabId, apprRsltId, apprOpinionId, historyApproveDivId,
                         historyApproveTableId, historyApprovePagerId){
    //拼接历史审批信息
    var approveStr = "";
    approveStr = approveStr + "<ul class=\"nav nav-tabs\">";
        approveStr = approveStr + "<li class=\"active\"><a data-toggle=\"tab\" href=\"#tab-1\"><i class=\"fa fa-user\"></i>"+tab1Title+"</a></li>";
        approveStr = approveStr + "<li><a data-toggle=\"tab\" id=\""+approveTabId+"\" href=\"#tab-2\"><i class=\"fa fa-briefcase\"></i> 历史审批信息</a></li>";
    approveStr = approveStr + "</ul>";
    approveStr = approveStr + "<div class=\"tab-content\">";
        approveStr = approveStr + "<div id=\"tab-1\" class=\"tab-pane active\">";
            approveStr = approveStr + "<br/>";
            approveStr = approveStr + "<form method=\"get\" id=\"approveForm\" class=\"form-horizontal\" style=\"padding: 5px;width:105%;\">";
                approveStr = approveStr + "<div class=\"form-group\">";
                    approveStr = approveStr + "<label class=\"col-sm-2 control-label\">审批结果<span style=\"color:red;\">*</span></label>";
                    approveStr = approveStr + "<div class=\"col-sm-9\">";
                        approveStr = approveStr + "<select id=\""+apprRsltId+"\" class=\"form-control m-b\" style=\"margin-bottom:0px;\" name=\"apprRslt\">";
                        approveStr = approveStr + "</select>";
                    approveStr = approveStr + "</div>";
                approveStr = approveStr + "</div>";
                approveStr = approveStr + "<div class=\"hr-line-dashed\"></div>";
                    approveStr = approveStr + "<div class=\"form-group\">";
                        approveStr = approveStr + "<label class=\"col-sm-2 control-label\">审批意见<span style=\"color:red;\">*</span></label>";
                        approveStr = approveStr + "<div class=\"col-sm-9\">";
                            approveStr = approveStr + "<textarea id=\""+apprOpinionId+"\" name=\"apprOpinion\" class=\"form-control\" maxlength=\"128\" required=\"\"";
                            approveStr = approveStr + "aria-required=\"true\" style=\"height:75px;\">同意</textarea>";
                        approveStr = approveStr + "</div>";
                approveStr = approveStr + "</div>";
            approveStr = approveStr + "</form>";
        approveStr = approveStr + "</div>";
        approveStr = approveStr + "<div id=\"tab-2\" class=\"tab-pane\">";
            approveStr = approveStr + "<br/>";
            approveStr = approveStr + "<div class=\"full-height-scroll\">";
                approveStr = approveStr + "<div class=\"table-responsive\" class=\"col-sm-5\" style=\"padding: 5px; margin-botton:0px;\">";
                    approveStr = approveStr + "<div class=\"jqGrid_wrapper\" id=\""+historyApproveDivId+"\">";
                        approveStr = approveStr + "<table id=\""+historyApproveTableId+"\"></table>";
                        approveStr = approveStr + "<div id=\""+historyApprovePagerId+"\"></div>";
                    approveStr = approveStr + "</div>";
                approveStr = approveStr + "</div>";
            approveStr = approveStr + "</div>";
        approveStr = approveStr + "</div>";
    approveStr = approveStr + "</div>";

    //把拼接的历史审批信息放至审批panel容器中
    document.getElementById(approvePanelId).innerHTML=approveStr;
    //初始化审批结果
    initApproveStatus($("#"+apprRsltId));
    //初始化历史审批信息
    initHistoryApproveJqGrid($("#"+historyApproveTableId), "#"+historyApprovePagerId, appNo, "100px");
    //自动调整表格宽度
    $(window).bind("resize",
        function() {
            var width = $("#"+historyApproveDivId).width();
            $("#"+historyApproveTableId).setGridWidth(width);
        })
    $("#"+approveTabId).bind("click",
        function() {
            var width = $(".ibox-title").width();
            $("#"+historyApproveTableId).setGridWidth(width*0.98);
        })

    //验证初始化
    validateInit();

    var e = "<i class='fa fa-times-circle'></i> ";
    $("#approveForm").validate({
        rules: {
            apprOpinion: {
                required:true
            }
        },
        messages: {
            apprOpinion: {
                required:e+"请输入审批意见"
            }
        }
    });
}

//验证初始化
function validateInit(){
    $.validator.setDefaults({
        highlight: function(e) {
            $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
        },
        success: function(e) {
            e.closest(".form-group").removeClass("has-error").addClass("has-success")
        },
        errorElement: "span",
        errorPlacement: function(e, r) {
            e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
        },
        errorClass: "help-block m-b-none",
        validClass: "help-block m-b-none"
    },{
        debug:true
    })
}

//流程提交前验证审批意见
function validateApprove() {
    var boolean1 = $("#approveForm").validate().element(document.getElementsByName("apprOpinion"));
    return boolean1;
}
