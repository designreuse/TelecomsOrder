//参数
var params;
//加载层index
var loadIndex;
//交接单打印页面index
var irIndex;
//新增交接项目详细页面的layer index
var tranDetailAddIndex = '';
//验收意见码表数据
var checkResultsMap = {};
//表计类型码表数据
var meterTypeMap = {};
//交接项目码表信息
var tranItemArr = [];
//用户、租赁及表计信息
var consRentData = {};
//webuploader参数
var uploaderParam = {
    uploadServer: '/rental/tran/upload',
    fileNumLimit: 5
};

//opentab回调
function init(_param)
{
    //重置表单
    //reset();
    //初始化校验函数
    tranValidator.init();
    params = _param;

    $("#tranContentBox").height($('body').height()-130);
    $("#tranContentBox").css("overflow-y", "auto");

    //日期控件初始化
    $('#tranDateIn').datepicker({
        language: 'zh-CN',
        todayBtn: "linked",
        keyboardNavigation: false,
        forceParse: false,
        calendarWeeks: true,
        endDate: new Date(),
        autoclose: true,
        format : 'yyyy-mm-dd'
    });

    //浏览器缩放事件
    $(window).bind('resize', function () {
        var width = $('#tab-1').width();
        $("#tranDetailTable").setGridWidth(width);
        $("#meterTable").setGridWidth(width);
    });

    $("#img-group").show();
    $("#contractIdIn").val(params.contractId);
    $("#tranTypeIn").val(params.tranType);

    //查询验收意见CHECK_RESULTS码表信息
    queryCheckResults();
    //查询表计类型METER_TYPE码表信息
    queryMeterType();
    //查询交接项目TRAN_ITEM_CODE码表信息
    queryTranItems();
}

//根据根据contractId查询用户及租赁信息
function queryConsRent(contractId)
{
    $.ajax({
        type: "POST",
        url: "/rental/tran/queryConsRent/" + contractId,
        error: function(json) {
            layer.close(loadIndex);
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json){
            if ('0' == json.status)
            {
                var data = json.data;
                params["contractNo"] = data.CONTRACT_NO;
                params["contractType"] = data.CONTRACT_TYPE;
                $("#consNameSp").text(data.CONS_NAME);
                $("#houseAddrSp").text(data.HOUSE_ADDR);
                $("#bizTransactorSp").text(data.BIZ_TRANSACTOR);
                $("#contactTelSp").text(data.CONTACT_TEL);
                $("#contactAddSp").text(data.CONTACT_ADD);

                consRentData["partyAPerson"] = data.PARTY_A_PERSON;
                consRentData["partyBPerson"] = data.PARTY_B_PERSON;

                var urls = data.urls;
                var content = '';
                if (isEmpty(urls) || 0 == urls.length)
                {
                    //使用默认图片
                    content = '<img alt="image" class="img-responsive" src="../../../images/rental/tran/louyu1.jpg">';
                }
                else
                {
                    content = '<div class="carousel slide" id="carousel1">' +
                            '<div class="carousel-inner">';
                    for (var i=0; i<urls.length; i++)
                    {
                        content += '<div class="item">' +
                                        '<img alt="image" class="img-responsive" src="' + urls[i] + '">' +
                                    '</div>';
                    }
                    content += '</div>' +
                            '<a data-slide="prev" href="carousel.html#carousel1" class="left carousel-control">' +
                                '<span class="icon-prev"></span>' +
                            '</a>' +
                            '<a data-slide="next" href="carousel.html#carousel1" class="right carousel-control">' +
                                '<span class="icon-next"></span>' +
                            '</a>' +
                        '</div>';

                }
                $("#roomImgBox").html(content);
                $("#roomImgBox .carousel-inner").find("div").eq(0).addClass("active");

                var meterList = data.meterList;
                if (0 == meterList.length)
                {
                    layer.alert("该用户未安装计量表计。", {icon: 0});
                    //清除表格数据
                    //$("#meterTable").clearGridData(true);
                    //初始化计量表计
                    initMeterTable([]);
                }
                else
                {
                    //初始化计量表计
                    initMeterTable(meterList);
                }
                //根据contractId, tranType查询合同交接信息
                queryTran(params);
            }
            else
            {
                layer.close(loadIndex);
                layer.alert(json.msg, {icon: 0});
            }
        }
    });
}

//根据contractId, tranType查询合同交接信息
function queryTran(param)
{
    $.ajax({
        type: "POST",
        url: "/rental/tran/queryTran",
        data: param,
        error: function(json) {
            layer.close(loadIndex);
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json){
            if ('0' == json.status)
            {
                var data = json.data;
                //未交接或未收回
                if (isEmpty(data))
                {
                    $("#mask_uploader").show();
                    $("#img-group").hide();
                    $("#partyAPersonIn").val(consRentData.partyAPerson);
                    $("#partyBPersonIn").val(consRentData.partyBPerson);
                    $("#tranDateIn").val(getCurrentDate());

                    //根据码表查询结果，初始化交接项目明细
                    initTranDetailTable(tranItemArr);
                }
                //已交接或已收回
                else
                {
                    $("#tranIdIn").val(data.tranId);
                    //给上传控件赋值
                    uploader.options.formData.tranId = data.tranId;
                    $("#partyAPersonIn").val(data.partyaPerson);
                    $("#partyBPersonIn").val(data.partybPerson);
                    $("#tranDateIn").val(data.tranDate.substr(0, 10));

                    //初始化交接项目明细
                    var tranDetails = data.tranDetailList;
                    initTranDetailTable(tranDetails);

                    //将码表中没有的已交接项合并到tranItemArr中
                    mergeTranItem(tranDetails);

                    //初始化交接单扫描件
                    var attachRefList = data.attachRefList;
                    if (attachRefList.length > 0)
                    {
                        //初始化文件盒子
                        FileBox.init(attachRefList, deleteAttachRef);
                    }
                    else
                    {
                        $("#img-group").hide();
                    }
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

//初始化交接项目明细
function initTranDetailTable(data)
{
    var lastsel;
    var checkResultsStr = getMapStr(checkResultsMap);
    $("#tranDetailTable").jqGrid({
        data: data,
        datatype: "local",
        height: 160,
        autowidth: true,
        shrinkToFit: true,
        forceFit: true,
        rowNum: 10,
        colNames: ['操作', '交接明细记录标识', '交接记录标识', '交接项目编号', '交接项目名称', '验收意见', '备注'],
        colModel: [
            {name: 'act',index : 'act', sortable : false, align: "center", width: 80},
            {name: 'tranDetailId',index : 'tranDetailId', hidden: true},
            {name: 'tranId',index : 'tranId', hidden: true},
            {name: 'tranItemCode', index: 'tranItemCode', hidden: true},
            {name: 'tranItemName', index: 'tranItemName', width: 120},
            //{name: 'checkResults', index: 'checkResults', width: 100, align: "center", edittype:'select', editable:true,
            //    editoptions:{value:checkResultsStr}, formatter: function (v, opt, rec) { return checkResultsMap[v]; },
            //    unformat: function (v) { for (k in checkResultsMap) if (checkResultsMap[k] == v) return k; return '01'; }},
            {name: 'checkResultsName', index: 'checkResults', width: 80, align: "center", edittype:'select',
                editable:true, editoptions:{value:checkResultsStr},
                unformat: function (v) { for (k in checkResultsMap) if (checkResultsMap[k] == v) return k; return '01'; }},
            {name: 'remark', index: 'remark', align: "left", edittype:'textarea', editable:true}
        ],
        gridComplete : function() {
            var ids = $("#tranDetailTable").jqGrid('getDataIDs');
            for ( var i = 0; i < ids.length; i++) {
                var cl = ids[i];
                var del = '<img src="../../../images/rental/tran/trash_icon.png" title="删除" onclick="delDetailRow(' + cl + ')"/>';
                var save = '<img src="../../../images/rental/tran/save_icon.png" style="margin-left:5px;" title="保存" onclick="saveDetailRow(' + cl + ')"/>';
                $("#tranDetailTable").jqGrid('setRowData', ids[i],
                    {
                        act : del + save
                    }
                );
            }
        },
        onCellSelect : function(rowid,iCol,cellcontent,e) {
            if (0 != iCol)
            {
                if (rowid)
                {
                    if (!isEmpty(lastsel))
                    {
                        $('#tranDetailTable').jqGrid('saveRow', lastsel);
                    }
                    $('#tranDetailTable').jqGrid('editRow', rowid, true);
                    lastsel = rowid;
                }
            }
        },
        //cellEdit: true,
        //cellsubmit: 'clientArray',
        viewrecords: true,
        multiselect : false,
        hidegrid: false
    });
}

/**
 * 保存交接项目明细行
 * @param cl
 */
function saveDetailRow(cl)
{
    $('#tranDetailTable').jqGrid('saveRow', cl);
}

/**
 * 删除交接项目明细行
 * @param cl
 */
function delDetailRow(cl)
{
    var layerIndex = layer.confirm('确定要删除该项目？', {
        btn: ['是','否'], //按钮
        icon: 3
    }, function(){
        jQuery("#tranDetailTable").delRowData(cl);
        layer.close(layerIndex);
    }, function(){

    });
    return false;
}

//初始化表计明细
function initMeterTable(data)
{
    var lastsel;
    var meterTypeStr = getMapStr(meterTypeMap);
    $("#meterTable").jqGrid({
        data: data,
        datatype: "local",
        height: 160,
        autowidth: true,
        shrinkToFit: true,
        forceFit: true,
        rowNum: 10,
        colNames: ['操作', '计量装置运行标识', '资产编号','表计类型', '表示数', '安装人员', '安装日期'],
        colModel: [
            {name: 'act',index : 'act', sortable : false, align: "center", width: 80},
            {name: 'CM_ID',index : 'cmId', hidden: true},
            {name: 'ASSET_NO', index: 'assetNo', align: "center", width: 100},
            {name: 'METER_TYPE', index: 'meterType', align: "center", width: 80, edittype:'select',
                editoptions:{value:meterTypeStr}, formatter: function (v, opt, rec) { return meterTypeMap[v]; }},
            {name: 'MR_READ', index: 'mrRead', editable:true, editrules:{required:true, number:true}, align: "right", width: 80},
            {name: 'USER_NAME', index: 'userName'},
            {name: 'INST_DATE',index : 'instDate', align: "center", width: 120,
                formatter: function (v, opt, rec) { return v.substr(0, 10); }}
        ],
        gridComplete : function() {
            var ids = $("#meterTable").jqGrid('getDataIDs');
            for ( var i = 0; i < ids.length; i++) {
                var cl = ids[i];
                var save = '<img src="../../../images/rental/tran/save_icon.png" title="保存" onclick="saveMeterRow(' + cl + ')"/>';
                $("#meterTable").jqGrid('setRowData', ids[i],
                    {
                        act : save
                    }
                );
            }
        },
        onCellSelect:function(rowid,iCol,cellcontent,e) {
            if (0 != iCol)
            {
                if (rowid)
                {
                    if (!isEmpty(lastsel))
                    {
                        $('#meterTable').jqGrid('saveRow', lastsel);
                    }
                    $('#meterTable').jqGrid('editRow', rowid, true);
                    lastsel = rowid;
                }
            }
        },
        //cellEdit: true,
        //cellsubmit: 'clientArray',
        viewrecords: true,
        multiselect : false,
        hidegrid: false
    });
    var width = $('#tab-1').width();
    $("#meterTable").setGridWidth(width);
}

/**
 * 保存计量表计行
 * @param cl
 */
function saveMeterRow(cl)
{
    $('#meterTable').jqGrid('saveRow', cl);
}

/**
 * 生成交接单
 */
function makeIR()
{
    var detailEditRowId = $('#tranDetailTable').jqGrid('getGridParam','selrow');
    var meterEditRowId = $('#meterTable').jqGrid('getGridParam','selrow');
    if (!isEmpty(detailEditRowId))
    {
        $('#tranDetailTable').jqGrid('saveRow', detailEditRowId);
    }
    if (!isEmpty(meterEditRowId))
    {
        var meterSaveFlag = $('#meterTable').jqGrid('saveRow', meterEditRowId);
        //TODO 有个bug，选中行 但非编辑状态下，保存不了
        //可以通过判断是否包含input来修改，
        if (!meterSaveFlag)
        {
            return false;
        }
    }
    if (validateTran())
    {
        //获取交接项目明细信息
        var tranDetails = getTranDetails();
        //获取表计信息
        var meters = getMeters();
        var data = {
            tranId: $("#tranIdIn").val(),
            tranType: $("#tranTypeIn").val(),
            contractId: $("#contractIdIn").val(),
            partyAPerson: $("#partyAPersonIn").val(),
            partyBPerson: $("#partyBPersonIn").val(),
            tranDate: $("#tranDateIn").val(),
            tranDetails: JSON.stringify(tranDetails),
            meters: JSON.stringify(meters)
        };
        loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
        $.ajax({
            type: "POST",
            url: "/rental/tran/saveTran",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data: data,
            async: false,
            error: function(json) {
                layer.close(loadIndex);
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json) {
                if ("0" == json.status)
                {
                    $("#tranIdIn").val(json.data);
                    //弹出交接单打印页面
                    irIndex = layer.open({
                        type: 2,
                        title: '房屋交接单打印',
                        shadeClose: false,
                        shade: [0.5],
                        maxmin: false, //开启最大化最小化按钮
                        area: ['80%', '80%'],
                        content: '../../../html/rental/tran/ir.html',
                        success: function (layero, index){
                            var iframeWin = window[layero.find('iframe')[0]['name']];
                            var initData = {
                                tranType: $("#tranTypeIn").val(),
                                contractId: $("#contractIdIn").val(),
                                houseAddr: $("#houseAddrSp").text(),
                                contractNo: params["contractNo"],
                                contractType: params["contractType"]
                            };
                            //初始化打印页面
                            iframeWin.init(initData);
                        }
                    });
                }
                else
                {
                    layer.alert(json.msg, {icon: 0});
                }
                layer.close(loadIndex);
            }
        });
    }
    else
    {
        document.getElementsByClassName("has-error")[0].scrollIntoView();
    }
}

/**
 * 弹出新增交接项页面
 */
function addTranDetail()
{
    tranDetailAddIndex = layer.open({
        type: 2,
        title: '交接项选择',
        shadeClose: false,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['800px', '430px'],
        content: '../../../html/rental/tran/tranAdd.html'
    });
}

//删除交接单扫描件
function deleteAttachRef(data)
{
    var layerIndex = layer.confirm('确定要删除该扫描件？', {
        btn: ['是','否'], //按钮
        icon: 3
    }, function(){
        loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
        //删除楼宇与楼宇实景图关联关系
        $.ajax({
            type: "POST",
            url: "/system/attachRef/deleteAttachRefsById",
            data: data,
            error: function(json) {
                layer.close(loadIndex);
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json){
                if ('0' == json.status)
                {
                    //删除文件
                    FileBox.deleteFile(data.attachRefId);
                    layer.msg(json.msg, {icon: 1});
                }
                else
                {
                    layer.alert(json.msg, {icon: 0});
                }
                layer.close(loadIndex);
                layer.close(layerIndex);
            }
        });
    }, function(){

    });
}

/**
 * 查询交接项目码表
 */
function queryCheckResults()
{
    $.ajax({
        type: "POST",
        url: "/system/code/querySysCodes",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data: {codeSortName: "CHECK_RESULTS"},
        async: false,
        error: function(json) {
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json) {
            if ('0' == json.status)
            {
                var data = json.data;
                for (var i=0; i<data.length; i++)
                {
                    checkResultsMap[data[i].value] = data[i].name;
                }
            }
            else
            {
                layer.alert(json.msg, {icon: 0});
            }
        }
    });
}

/**
 * 查询表计类型码表
 */
function queryMeterType()
{
    $.ajax({
        type: "POST",
        url: "/system/code/querySysCodes",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data: {codeSortName: "METER_TYPE"},
        async: false,
        error: function(json) {
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json) {
            if ('0' == json.status)
            {
                var data = json.data;
                for (var i=0; i<data.length; i++)
                {
                    meterTypeMap[data[i].value] = data[i].name;
                }
            }
            else
            {
                layer.alert(json.msg, {icon: 0});
            }
        }
    });
}

/**
 * 查询交接项目码表
 */
function queryTranItems()
{
    loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
    $.ajax({
        type: "POST",
        url: "/system/code/querySysCodes",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data: {codeSortName: "TRAN_ITEM_CODE"},
        async: false,
        error: function(json) {
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json) {
            if ('0' == json.status)
            {
                var data = json.data;
                for (var i=0; i<data.length; i++)
                {
                    var tranItemCode = data[i].value;
                    var tranItemName = data[i].name;
                    tranItemArr.push({tranItemCode: tranItemCode,
                        tranItemName: tranItemName,
                        //checkResults: "01",
                        checkResultsName: "合格",
                        remark: ""
                    });
                }
                //根据contractId查询用户、租赁及表计信息
                queryConsRent(params.contractId);
            }
            else
            {
                layer.close(loadIndex);
                layer.alert(json.msg, {icon: 0});
            }
        }
    });
}

/**
 * 获取表格中交接项目明细
 */
function getTranDetails()
{
    var tranDetails = [];
    var detailRowData = $("#tranDetailTable").jqGrid("getRowData");
    jQuery(detailRowData).each(function(){
        var tranDetail = {
            tranDetailId: this.tranDetailId,
            tranItemCode: this.tranItemCode,
            tranItemName: this.tranItemName,
            checkResults: this.checkResultsName,
            remark: this.remark
        };
        tranDetails.push(tranDetail);
    });
    return tranDetails;
}

/**
 * 获取表格中表计明细
 */
function getMeters()
{
    var meters = [];
    var meterRowData = $("#meterTable").jqGrid("getRowData");
    jQuery(meterRowData).each(function(){
        var meter = {
            cmId: this.CM_ID,
            mrRead: this.MR_READ
        };
        meters.push(meter);
    });
    return meters;
}

/**
 * 更新表格中的交接项
 * @param tranDetails
 */
function updateTranDetails(tranDetails)
{
    var reccount = $("#tranDetailTable").getGridParam("reccount");
    reccount = isEmpty(reccount) ? 0 : reccount;
    for (var i=0; i<tranDetails.length; i++)
    {
        var rowData = {
            tranDetailId: "",
            tranId: "",
            tranItemCode: tranDetails[i].tranItemCode,
            tranItemName: tranDetails[i].tranItemName,
            //checkResults: "01",
            checkResultsName: checkResultsMap["01"],
            remark: ""
        };
        jQuery("#tranDetailTable").jqGrid('addRowData', reccount + i + 1, rowData);
    }
}

/**
 * 将已选择的数据合并到码表中
 * @param tranDetails
 */
function mergeTranItem(tranDetails)
{
    //将码表中没有的已交接项合并到tranItemArr中
    for (var i=0; i<tranDetails.length; i++)
    {
        var tranDetailName = tranDetails[i].tranItemName;
        var notHas = true;
        for (var j=0; j<tranItemArr.length; j++)
        {
            var tranItemName = tranItemArr[j].tranItemName;
            if (tranDetailName == tranItemName)
            {
                notHas = false;
                continue;
            }
        }
        if (notHas)
        {
            tranItemArr.push(tranDetails[i]);
        }
    }
}

//房屋交接单上传成功
function uploadImgSuccess(json)
{
    if ("0" == json.status)
    {
        var attachId = json.data;
        var attachIds = $("#attachIdsIn").val();
        if (isEmpty($("#attachIdsIn").val()))
        {
            $("#attachIdsIn").val(attachId);
        }
        else
        {
            $("#attachIdsIn").val(attachIds + "," + attachId);
        }
    }
}

/**
 * 重置
 */
function reset()
{
    tranItemArr.length = 0;
    checkResultsMap = {};
    consRentData = {};
    FileBox.clear();
    $("#tranIdIn").val('');
    $("#contractIdIn").val('');
    $("#tranTypeIn").val('');
    $("#attachIdsIn").val('');
    $("#partyAPersonIn").val('');
    $("#partyBPersonIn").val('');
    $("#tranDateIn").val('');
}

//表单校验
var tranValidator = function() {
    var handleSubmit = function() {
        $('#tranForm').validate({
            errorElement : 'span',
            errorClass : 'help-block',
            focusInvalid : false,
            rules : {
                partyAPerson : {
                    required : true,
                    maxlength : 10
                },
                partyBPerson : {
                    required : true,
                    maxlength : 10
                },
                tranDate : {
                    required : true
                }
            },
            messages : {
                partyAPerson : {
                    required : "出租方交验人不能为空",
                    maxlength : "不能超过10个字符"
                },
                partyBPerson : {
                    required : "承租方交验人不能为空",
                    maxlength : "不能超过10个字符"
                },
                tranDate : {
                    required : "日期不能为空"
                }
            },
            highlight : function(element) {
                $(element).closest('.form-group').addClass('has-error');
            },
            success : function(label) {
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },
            errorPlacement : function(error, element) {
                element.parent('div').append(error);
            }
        });
    }
    return {
        init : function() {
            handleSubmit();
        }
    };

}();

//校验表单
function validateTran()
{
    return $("#tranForm").validate().element($("#partyAPersonIn")) &&
        $("#tranForm").validate().element($("#partyBPersonIn")) &&
        $("#tranForm").validate().element($("#tranDateIn"));
}

function getRealValue(pValue) {
    if ($.browser.msie) {
        var oReg2 = /(\s+\w+=)('|")?(.*?)('|")?(?=\s+\w+=|\s*>|\s*\/>)/gi;
        pValue = pValue.replace(oReg2, "$1\"$3\"");
        var oReg = /^<(input|INPUT).*id=\"(.*?)\"/m;
        var val = pValue.match(oReg);
        return !val ? pValue : $("#" + val[2]).val();
    }
    else {
        var regExp = /^<input.*id=\"(\w*)\".*>$/;
        var val = pValue.match(regExp);
        return !val ? pValue : $("#" + val[1]).val();
    }
}
