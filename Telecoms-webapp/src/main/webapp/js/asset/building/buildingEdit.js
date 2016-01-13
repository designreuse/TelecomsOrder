//webuploader参数
var uploaderParam = {
    uploadServer: '/asset/building/upload',
    fileNumLimit: 5
}
//加载层index
var loadIndex;
$(document).ready(function(){
    var buildingId = getUrlParam("buildingId");
    init(buildingId);
});

function init(buildingId)
{
    //初始化校验函数
    buildingValidator.init();

    //初始化楼宇
    initBuilding(buildingId);
}

//初始化楼宇信息
function initBuilding(buildingId)
{
    if (!isEmpty(buildingId))
    {
        loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
        //根据buildingId查询楼宇信息
        $.ajax({
            type: "POST",
            url: "/asset/building/queryBuilding/" + buildingId,
            error: function(json) {
                layer.close(loadIndex);
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json){
                if ('0' == json.status)
                {
                    var data = json.data;
                    $("#buildingIdIn").val(data.buildingId);
                    $("#buildingNameIn").val(data.buildingName);
                    $("#buildingHeightIn").val(data.buildingHeight);
                    $("#buildingAreaIn").val(data.buildingArea);
                    $("#areaIn").val(data.area);
                    $("#liftCountsIn").val(data.liftCounts);
                    //if (!isEmpty(data.floorCounts))
                    //{
                    //    $("#floorCountsIn").val(data.floorCounts);
                    //}
                    $("#remarkIn").val(data.remark);
                    //初始化楼宇实景图
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
        $("#img-group").hide();
    }
}

//删除楼宇实景图
function deleteAttachRef(data)
{
    var layerIndex = layer.confirm('确定要删除该图片？', {
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

//保存楼宇
function saveBuilding(oper)
{
    //if ($('#buildingForm').validate().form())
    if (validateBuilding())
    {
        loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
        $.ajax({
            type: "POST",
            url: "/asset/building/saveBuilding",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data: $('#buildingForm').serialize(),
            async: false,
            error: function(json) {
                layer.close(loadIndex);
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json) {
                if ("0" == json.status)
                {
                    parent.layer.msg(json.msg, {icon: 1});
                    //保存并编辑楼层
                    if (2 == oper)
                    {
                        //跳转至楼层编辑页面
                        if ('' == parent.floorEditId)
                        {
                            parent.floorEditId = uuid();
                        }
                        else
                        {
                            top.closeTab(parent.floorEditId);
                        }
                        top.openTab("楼层编辑", parent.floorEditId, "html/asset/building/floorEdit.html",
                            {buildingId: json.data.buildingId, buildingName: json.data.buildingName}, false);
                    }
                    //刷新楼宇列表
                    parent.queryBuildings(1);
                    //关闭当前layer
                    closeLayer();
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

//实景图上传成功
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

//关闭当前弹出层
function closeLayer()
{
    parent.layer.close(parent.buildingEditIndex);
}

//重置表单
function reset()
{
    $("#buildingIdIn").val('');
    $("#attachIdsIn").val('');
    $("#buildingNameIn").val('');
    $("#buildingHeightIn").val('');
    $("#buildingAreaIn").val('');
    $("#areaIn").val('');
    $("#liftCountsIn").val('');
    //$("#floorCountsIn").val('');
    $("#remarkIn").val('');
}

//表单校验
var buildingValidator = function() {
    var handleSubmit = function() {
        $('#buildingForm').validate({
            errorElement : 'span',
            errorClass : 'help-block',
            focusInvalid : false,
            rules : {
                buildingName : {
                    minlength : 1,
                    maxlength : 80,
                    required : true
                },
                buildingHeight : {
                    number : true,
                    max : 999,
                    min : 1
                },
                buildingArea : {
                    number : true,
                    max : 9999,
                    min : 1
                },
                liftCounts : {
                    min : 0,
                    max : 999,
                    digits : true
                },
                remark : {
                    maxlength : 80
                }
            },
            messages : {
                buildingName : {
                    required : "楼宇名称不能为空",
                    maxlength : "楼宇名称不能超过80个字符"
                },
                buildingHeight : {
                    number : "请输入有效正数"
                },
                buildingArea : {
                    number : "请输入有效正数"
                },
                liftCounts : {
                    number : "请输入非负整数"
                },
                remark : {
                    maxlength : "备注不能超过80个字符"
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
function validateBuilding()
{
    return $("#buildingForm").validate().element($("#buildingNameIn")) &&
        $("#buildingForm").validate().element($("#buildingHeightIn")) &&
        $("#buildingForm").validate().element($("#buildingAreaIn")) &&
        $("#buildingForm").validate().element($("#liftCountsIn"));
}