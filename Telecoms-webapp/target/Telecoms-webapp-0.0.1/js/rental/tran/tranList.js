//打开的交接编辑tab页的id
var tranTabId = '';
//当前页码
var pageNo = 1;
//每页条数
var pageSize = 10;
//总页数
var totalPage = 0;
//总记录数
var totalRecord = 0;
//加载层index
var loadIndex;
//请求参数
var param;

$(function() {
    $("#c_consName").bsSuggest({
        allowNoKeyword: true,
        getDataMethod: "url",
        url: '../../../global/cons/queryConsumers?consName=',
        showBtn: false,
        idField: "consId",
        keyField: "consName",
        effectiveFields: ["consName", "consAddr"],
        effectiveFieldsAlias: {"consName":"用户名","consAddr":"租赁地址"},
        showHeader: false,
        autoMinWidth: true,
        listAlign: "right",
        processData: function (json) {    // url 获取数据时，对数据的处理，作为 getData 的回调函数
            //alert(JSON.stringify(json));
            var data = {value: []};
            if (!json || !json.data || json.data.length === 0) {
                return false;
            }
            for (i = 0; i < json.data.length; i++) {
                data.value.push({
                    consId : json.data[i].consId,
                    consName : json.data[i].consName,
                    consAddr : json.data[i].consAddr
                });
            }
            return data;
        }
    }).on("onDataRequestSuccess",
        function(e, result) {
            //alert(JSON.stringify(result));
            //console.log("onDataRequestSuccess: ", result)
        }).on("onSetSelectValue",
        function(e, keyword) {
            $("#c_consName").attr("consId", keyword.id);
        }).on("onUnsetSelectValue",
        function(e) {
            $("#c_consName").attr("consId", "");
        });
    //修改下拉框宽度及位置错乱问题
    $(".input-group-btn .dropdown-menu").css("minWidth", $("#c_consName").css("width"));

    $(window).resize(function() {
        //修改下拉框宽度及位置错乱问题
        $(".input-group-btn .dropdown-menu").css("minWidth", $("#c_consName").css("width"));
    });

    $(window).scroll(function(){
        var scrollTop=$(this).scrollTop();
        var scrollHeight=$(document).height();
        var windowHeight=$(this).height();
        if(scrollTop + windowHeight == scrollHeight)
        {
            if (pageNo < totalPage)
            {
                pageNo++;
                queryTrans(pageNo);
            }
        }
    });
    //初始化查询交接信息
    queryTrans(1);
});

/**
 * 查询交接信息
 * @param consId
 */
function queryTrans(_pageNo)
{
    loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
    //01:全部 02:未交付 03:已交付 04:已收回
    var tranState = $("#tranStateIn").val();
    if (1 == _pageNo)
    {
        param = {
            "tranState": tranState,
            "consId": $("#c_consName").attr("consId"),
            pageNo: _pageNo,
            pageSize: pageSize
        };
        pageNo = _pageNo;
        $("#contractTable tbody").empty();
    }
    else
    {
        param['pageNo'] = _pageNo;
    }
    //根据consId查询交接信息
    $.ajax({
        type: "POST",
        url: "/rental/tran/queryTranList",
        data: param,
        error: function(json) {
            layer.close(loadIndex);
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json){
            if ('0' == json.status)
            {
                var data = json.data;
                totalPage = data.totalPage;
                totalRecord = data.totalRecord;
                var results = data.results;
                if (_pageNo == totalPage || 0 == totalPage)
                {
                    $("#recordP").text("共" + totalRecord + "条记录，没有更多数据");
                }
                for (var i=0;i <results.length; i++)
                {
                    //合同id
                    var contractId = results[i].CONTRACT_ID;
                    //01:租房 02:购置
                    var contractType = results[i].CONTRACT_TYPE;
                    //交付日期
                    var deliverDate = results[i].DELIVER_DATE;
                    //回收日期
                    var callbackDate = results[i].CALLBACK_DATE;
                    var $statusTd;
                    var $dateTd;
                    var $actionsTd;
                    //待交付
                    if (isEmpty(deliverDate))
                    {
                        $statusTd = $('<td class="contract-status">' +
                                        '<span class="label label-warning">待交付' +
                                        '</span>' +
                                    '</td>');
                        $dateTd = $('<td class="contract-date">' +
                                        '<small>签订日期 <span style="color:#18a689;">' + results[i].SIGN_DATE + '</span></small>' +
                                    '</td>');
                        $actionsTd = $('<td class="project-actions">' +
                                            '<a href="javascript:void(0);" contractId="' + contractId + '" onclick="popupDeliver(this)" class="btn btn-white btn-sm"><i class="fa fa-hand-o-right"></i> 交付 </a>' +
                                        '</td>');
                    }
                    //已交付，待收回
                    else if (isEmpty(callbackDate))
                    {
                        $statusTd = $('<td class="contract-status">' +
                                            '<span class="label label-info">已交付' +
                                            '</span>' +
                                        '</td>');
                        $dateTd = $('<td class="contract-date">' +
                                        '<small>签订日期 <span style="color:#18a689;">' + results[i].SIGN_DATE + '</span></small><br>' +
                                        '<small>交付日期 <span style="color:#18a689;">' + results[i].DELIVER_DATE + '</span></small><br>' +
                                    '</td>');
                        //TODO 出售是否要收回，如果出现退房的情况，应该有收回
                        $actionsTd = $('<td class="project-actions">' +
                                            '<a href="javascript:void(0);" contractId="' + contractId + '" onclick="popupDeliver(this)" class="btn btn-white btn-sm"><i class="fa fa-hand-o-right"></i> 交付 </a>' +
                                            '<a href="javascript:void(0);" contractId="' + contractId + '" onclick="popupCallback(this)" class="btn btn-white btn-sm"><i class="fa fa-hand-o-left"></i> 收回 </a>' +
                                        '</td>');
                    }
                    //已收回
                    else
                    {
                        $statusTd = $('<td class="contract-status">' +
                                            '<span class="label label-primary">已收回' +
                                            '</span>' +
                                        '</td>');
                        $dateTd = $('<td class="contract-date">' +
                                        '<small>签订日期 <span style="color:#18a689;">' + results[i].SIGN_DATE + '</span></small><br>' +
                                        '<small>交付日期 <span style="color:#18a689;">' + results[i].DELIVER_DATE + '</span></small><br>' +
                                        '<small>收回日期 <span style="color:#18a689;">' + results[i].CALLBACK_DATE + '</span></small>' +
                                    '</td>');
                        $actionsTd = $('<td class="project-actions">' +
                                            '<a href="javascript:void(0);" contractId="' + contractId + '" onclick="popupDeliver(this)" class="btn btn-white btn-sm"><i class="fa fa-hand-o-right"></i> 交付 </a>' +
                                            '<a href="javascript:void(0);" contractId="' + contractId + '" onclick="popupCallback(this)" class="btn btn-white btn-sm"><i class="fa fa-hand-o-left"></i> 收回 </a>' +
                                        '</td>');
                    }
                    var $tr = $('<tr></tr>');
                    $statusTd.appendTo($tr);
                    var $titleTd = $('<td class="contract-title">' +
                                        '<small>' + results[i].CONTRACT_NAME + '</small><br>' +
                                        '<i class="fa fa-map-marker"></i><small> ' + results[i].HOUSE_ADDR + '</small>' +
                                    '</td>').appendTo($tr);
                    $dateTd.appendTo($tr);
                    $actionsTd.appendTo($tr);
                    $tr.appendTo($("#contractTable tbody"));
                }
            }
            else
            {
                layer.alert(json.msg, {icon: 0});
            }
            layer.close(loadIndex);
        }
    });
}

/**
 * 交付
 */
function popupDeliver(target)
{
    var contractId = $(target).attr("contractId");
    if ('' == tranTabId)
    {
        tranTabId = uuid();
    }
    else
    {
        top.closeTab(tranTabId);
    }
    top.openTab("房屋交付", tranTabId, "html/rental/tran/tran.html", {"tranTabId": tranTabId, "contractId": contractId, "tranType": "01"}, false);
}

/**
 * 收回
 */
function popupCallback(target)
{
    var contractId = $(target).attr("contractId");
    if ('' == tranTabId)
    {
        tranTabId = uuid();
    }
    else
    {
        top.closeTab(tranTabId);
    }
    top.openTab("房屋收回", tranTabId, "html/rental/tran/tran.html", {"tranTabId": tranTabId, "contractId": contractId, "tranType": "02"}, false);
}