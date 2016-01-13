//加载层index
var loadIndex;

//项目名称数组
var tranItemNames = [];

$(function() {
    //查询交接项目码表
    initTranItems();
});

/**
 * 查询交接项目码表
 */
function initTranItems()
{
    //获取已表格中已有的交接项
    var tranDetails = parent.getTranDetails();
    parent.mergeTranItem(tranDetails);

    for (var i=0; i<parent.tranItemArr.length; i++)
    {
        var checked = "";
        var tranItemCode = parent.tranItemArr[i].tranItemCode;
        var tranItemName = parent.tranItemArr[i].tranItemName;
        for (var j=0; j<tranDetails.length; j++)
        {
            if (tranItemName == tranDetails[j].tranItemName)
            {
                checked = "checked disabled";
            }
        }
        var $tranItem = $('<div class="i-checks col-sm-4" style="padding-bottom: 10px;"><label> <input type="checkbox" lab="' + tranItemName + '" value="' +
        tranItemCode + '" ' + checked + '><i></i> ' +
        tranItemName +'</label></div>').appendTo($("#tranListBox"));
        tranItemNames.push(tranItemName);
    }
    //初始化多选框样式
    initICheck();
}

/**
 * 添加自定义项目
 */
function addTranDetail()
{
    var tranItemName = $("#tranItemNameIn").val();
    //校验该项目是否存在，避免重复添加
    if (-1 != $.inArray(tranItemName, tranItemNames))
    {
        layer.alert("该项目已存在。", {icon: 0});
    }
    else
    {
        var $tranItem = $('<div class="i-checks col-sm-4" style="padding-bottom: 10px;"><label> <input type="checkbox" lab="' + tranItemName + '"  value="" checked=""><i></i> ' +
        tranItemName +'</label></div>').appendTo($("#tranListBox"));
        tranItemNames.push(tranItemName);
        $("#tranItemNameIn").val('');
        //初始化多选框样式
        initICheck();
    }
}

/**
 * 保存选择的交接项目
 */
function saveAddTranDetail()
{
    //选中的交接项
    var tranDetails = getNewCheckedTran();
    //更新父页面表格中的交接项
    parent.updateTranDetails(tranDetails);
    $("#roomCloseBtn").trigger("click");
}

//关闭交接项目新增页面
function closeTranDetail()
{
    parent.layer.close(parent.tranDetailAddIndex);
}

/**
 * 初始化多选框样式
 */
function initICheck()
{
    $('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green'
    });
}

/**
 * 获取选中的交接项
 */
function getNewCheckedTran()
{
    var tranDetails = [];
    var inputs = $(".i-checks .checked").not(".disabled").find("input");
    for(var i=0; i<inputs.length; i++)
    {
        var tranDetail = {
            tranItemCode: $(inputs[i]).val(),
            tranItemName: $(inputs[i]).attr("lab")
        };
        tranDetails.push(tranDetail);
    }
    return tranDetails;
}