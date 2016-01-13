var typeMap = {
    "01": ["出租方(签字)：","承租方(签字)："],
    "02": ["买方(签字)：","卖方(签字)："]
};

function init(data)
{
    $("#contractNoH4").text("合同编号：" + data.contractNo);
    $("#houseAddrH4").text("房屋地址：" + data.houseAddr);
    $("#partyASp").text(typeMap[data.contractType][0]);
    $("#partyBSp").text(typeMap[data.contractType][1]);
    var tranDetails = parent.$("#tranDetailTable").jqGrid("getRowData");
    var meters = parent.$("#meterTable").jqGrid("getRowData");
    //初始化交接项目
    initTranDetailTable(tranDetails);
    //初始化表计
    initMeterTable(meters);

    //浏览器缩放事件
    $(window).bind('resize', function () {
        var width = $("#tranDetailTable").parent().width();
        $("#tranDetailTable").setGridWidth(width-15);
        $("#meterTable").setGridWidth(width-15);
    });
}

//初始化交接项目明细
function initTranDetailTable(data)
{
    var checkResultsStr = parent.getMapStr(parent.checkResultsMap);
    $("#tranDetailTable").jqGrid({
        data: data,
        datatype: "local",
        autowidth: true,
        height:"none",
        shrinkToFit: true,
        forceFit: true,
        colNames: ['交接项目名称', '验收意见', '备注'],
        colModel: [
            {name: 'tranItemName', index: 'tranItemName', width: 100},
            {name: 'checkResultsName', index: 'checkResults', width: 80, align: "center", editable:true,
                editoptions:{value:checkResultsStr}, formatter: function (v, opt, rec) { return parent.checkResultsMap[v];}},
            {name: 'remark', index: 'remark', align: "left"}
        ],
        viewrecords: true,
        multiselect : false,
        hidegrid: false,
        caption: "交接项目"
    });
}

//初始化表计明细
function initMeterTable(data)
{
    $("#meterTable").jqGrid({
        data: data,
        datatype: "local",
        autowidth: true,
        height:"none",
        shrinkToFit: true,
        forceFit: true,
        colNames: ['资产编号','表计类型', '表示数'],
        colModel: [
            {name: 'ASSET_NO', index: 'assetNo', align: "center", width: 100},
            {name: 'METER_TYPE', index: 'meterType', align: "center", width: 80},
            {name: 'MR_READ', index: 'mrRead', align: "right"}
        ],
        viewrecords: true,
        multiselect : false,
        hidegrid: false,
        caption: "表计装置"
    });
}

//打印
function print()
{
    $("#printArea").jqprint();
}

//关闭页面
function closeLayer()
{
    parent.layer.close(parent.irIndex);
}

