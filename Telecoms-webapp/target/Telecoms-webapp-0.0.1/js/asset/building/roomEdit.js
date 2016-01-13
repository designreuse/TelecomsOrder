//房间编辑弹出层index
var roomIndex;
//房间类型
var roomType = {};
//webuploader参数
var uploaderParam = {
    uploadServer: '/asset/room/upload',
    fileNumLimit: 5
}

$(document).ready(function(){
    $("#room_buildingId").val(parent.param.buildingId);
    $("#room_floorId").val(parent.param.floorId);
    $("#roomId").val(parent.param.roomId);
    $("#anchorId").val(parent.param.anchorId);
    $("#posX").val(parent.param.posX);
    $("#posY").val(parent.param.posY);
    $("#room_FloorNumberIn").val(parent.param.floorNumber);
    init();
});

function init()
{
    //初始化校验函数
    roomValidator.init();
    //所数楼宇标识
    $("#room_buildingId").val(parent.param.buildingId);
    //查询房间类型（码表）
    queryRoomType();
}

//查询房间类型
function queryRoomType()
{
    $.ajax({
        type: "POST",
        url: "/system/code/querySysCodes",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data: {codeSortName: "ROOM_TYPE"},
        async: false,
        error: function(json) {
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json) {
            if ('0' == json.status)
            {
                var data = json.data;
                $("#roomTypeIn").empty();
                for (var i=0; i<data.length; i++)
                {
                    roomType[data[i].value] = data[i].content1;
                    //初始化房间类型下拉框
                    $("#roomTypeIn").append('<option value="' + data[i].value + '">' + data[i].name + '</option>');
                    if (!isEmpty(parent.param.roomType))
                    {
                        $("#roomTypeIn option[value=" + parent.param.roomType + "]").attr("selected", "selected");
                    }
                }
                //查询房间类型
                queryRoom(parent.param.roomId);
            }
            else
            {
                layer.alert(json.msg, {icon: 0});
            }
        }
    });
}

//保存房间
function saveRoom()
{
    //if ($('#roomForm').validate().form())
    if (validateRoom())
    {
        $.ajax({
            type: "POST",
            url: "/asset/room/saveRoom",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data: $('#roomForm').serialize(),
            async: false,
            error: function(json) {
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json) {
                if ('0' == json.status)
                {
                    var data = json.data;
                    //给锚点标签赋值
                    parent.$("#" + parent.param.imgId).parent().find("[name='roomId']").val(data.roomId);
                    parent.$("#" + parent.param.imgId).parent().find("[name='anchorId']").val(data.anchorId);
                    parent.$("#" + parent.param.imgId).parent().find("img").attr("src", "../../../images/asset/building/" + roomType[data.roomType]);
                    parent.$("#" + parent.param.imgId).parent().find("p").text(data.roomNo);
                    parent.layer.msg(json.msg, {icon: 1});
                    //关闭房间页面弹出层
                    $("#roomCloseBtn").trigger("click");
                }
                else
                {
                    layer.alert(json.msg, {icon: 0});
                }
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

//关闭房间页面
function closeRoom()
{
    parent.layer.close(parent.roomIndex);
}

//查询房间信息
function queryRoom(roomId)
{
    if (!isEmpty(roomId))
    {
        $.ajax({
            type: "POST",
            url: "/asset/room/queryRoom/" + roomId,
            error: function(json) {
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json){
                if ('0' == json.status)
                {
                    var data = json.data;
                    $("#room_floorId").val(data.floorId);
                    $("#roomId").val(data.roomId);
                    $("#room_FloorNumberIn").val(data.floorNumber);
                    $("#roomNoIn").val(data.roomNo);
                    $("#roomTypeIn option[value=" + data.roomType + "]").attr("selected", "selected");
                    $("#roomAreaIn").val(data.roomArea);
                    $("#remarkIn").val(data.remark);
                    //初始化房间实景图
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
    var layerIndex = layer.confirm('是否要删除该图片？', {
        btn: ['确定','取消'], //按钮
        icon: 3
    }, function(){
        //删除楼宇与楼宇实景图关联关系
        $.ajax({
            type: "POST",
            url: "/system/attachRef/deleteAttachRefsById",
            data: data,
            success: function(json){
                if ('0' == json.status)
                {
                    //删除文件
                    FileBox.deleteFile(data.attachRefId);
                    layer.close(layerIndex);
                    layer.msg(json.msg, {icon: 1});
                }
                else
                {
                    layer.alert(json.msg, {icon: 0});
                }
            }
        });
    }, function(){

    });
}

//表单校验
var roomValidator = function() {
    var handleSubmit = function() {
        $('#roomForm').validate({
            errorElement : 'span',
            errorClass : 'help-block',
            focusInvalid : false,
            rules : {
                roomNo : {
                    required : true
                },
                roomArea : {
                    required : true,
                    number : true,
                    min : 0.1,
                    max : 9999
                },
                remark : {
                    maxlength : 80
                }
            },
            messages : {
                roomNo : {
                    required : "房间号不能为空"
                },
                roomArea : {
                    required : "实用面积不能为空",
                    number : "请输入有效正数"
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

//校验房间表单
function validateRoom()
{
    return $("#roomForm").validate().element($("#roomNoIn")) &&
        $("#roomForm").validate().element($("#roomAreaIn"));
}