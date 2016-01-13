//背景色class数组
var bgcolorArr = ['navy-bg', 'blue-bg', 'lazur-bg', 'yellow-bg'];
//边框色class数组
var bordercolorArr = ['#87D3C4', '#88BCDD', '#8BDDDE', '#F6D0A6'];
//允许拖拽的房间类型图标id
var drapImgIdArr = [];
//楼层复制弹出层index
var copyFloorIndex;
//房间编辑弹出层index
var roomIndex;
//房间类型
var roomType = {};
//加载层index
var loadIndex;
//参数信息,给房间弹出页面使用
var param = {};

function init(param)
{
    //初始化校验函数
    floorValidator.init();

    //重置表单
    //reset();
    //遮盖楼层编辑页面
    if (!hasMask("floorContent"))
    {
        addMask("floorContent");
    }
    //遮盖锚点编辑页面
    if (!hasMask("anchorContent"))
    {
        addMask("anchorContent");
    }
    //所数楼宇标识、名称赋值
    $("#belongBuildingIdIn").val(param.buildingId);
    $("#belongBuildingNameIn").val(param.buildingName);
    //根据楼宇标识查询楼层列表
    initFloor(null, null);
    //查询房间类型（码表）
    queryRoomType();
}

//初始化楼层
function initFloor(defaultFloorId, defaultFloorNumber)
{
    var buildingId = $("#belongBuildingIdIn").val();
    if (!isEmpty(buildingId))
    {
        loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
        //根据楼宇标识查询楼层列表
        $.ajax({
            type: "POST",
            url: "/asset/floor/queryFloors/" + buildingId,
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            async: false,
            error: function(json) {
                layer.close(loadIndex);
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json) {
                if ('0' == json.status)
                {
                    var results = json.data;
                    //楼宇下有楼层
                    if (0 != results.length)
                    {
                        $("#copyFloorBtn").show();
                        //楼层列表
                        var content = "";
                        //楼层下拉框
                        var optionContent = "";
                        for (var i=0; i<results.length; i++)
                        {
                            content += '<div class="vertical-timeline-block" bordercolor="' + bordercolorArr[i%bgcolorArr.length] + '">' +
                                            '<div class="vertical-timeline-icon ' + bgcolorArr[i%bgcolorArr.length] + '">' +
                                                '<input type="hidden" value="' + results[i].floorId + '"/>' +
                                                '<i>' + results[i].floorNumber + '</i>' +
                                            '</div>' +
                                        '</div>';
                            optionContent += '<option value="' + results[i].floorId + '">' + results[i].floorNumber + '</option>';
                        }
                        $("#vertical-timeline").html(content);
                        $("#copyFloorNumberSelect").html(optionContent);
                        //初始化楼层相关事件
                        initFloorEvent();
                        //默认选中保存的楼层
                        if (!isEmpty(defaultFloorId))
                        {
                            setSelectedFloor(defaultFloorId, defaultFloorNumber);
                        }
                        else
                        {
                            if (results.length > 0)
                            {
                                setSelectedFloor(results[0].floorId, results[0].floorNumber);
                            }
                        }
                    }
                    else
                    {
                        $("#copyFloorBtn").hide();
                        //如果未遮挡则遮住
                        //遮盖楼层编辑页面
                        if (!hasMask("floorContent"))
                        {
                            addMask("floorContent");
                        }
                        //遮盖锚点编辑页面
                        if (!hasMask("anchorContent"))
                        {
                            addMask("anchorContent");
                        }
                        //清空楼层列表
                        $("#vertical-timeline").empty();
                        //清空楼层复制页面楼层下拉框
                        $("#copyFloorNumberSelect").empty();
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
        layer.alert("楼宇标识为空", {icon: 0});
    }
}

//初始化楼层相关事件
function initFloorEvent()
{
    $(".vertical-timeline-block").each(function(){
        animationHover(this, 'pulse');
    });
    $(".vertical-timeline-block").click(function(){
        var currentFloorId = $("#floorIdIn").val();
        var floorId = $(this).find("input").val();
        if (currentFloorId == floorId)
        {
            return false;
        }
        else
        {
            var floorNumber = $(this).find("i").text();
            //设置默认选中楼层
            setSelectedFloor(floorId, floorNumber);
        }
    });
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
                $("#dragDiv").empty();
                drapImgIdArr.length = 0;
                for (var i=0; i<data.length; i++)
                {
                    var imgUUID = uuid();
                    drapImgIdArr.push(imgUUID);
                    roomType[data[i].value] = data[i].content1;
                    //初始化拖拽图标
                    $("#dragDiv").append('<img src="../../../images/asset/building/' + data[i].content1 +
                    '" title="' + data[i].name + '" for="' + data[i].value + '" id="' + imgUUID +
                    '" style="width:30px;height:30px;margin:0px 3px;" draggable="true" ondragstart="drag(event)"/>');
                }
                $("#dragDiv").append('<label style="color:#BBBBBB;margin: 0 5px;vertical-align: bottom;">温馨提示:1、上传楼层平面图 2、拖动图标至平面图</label>');
            }
            else
            {
                layer.alert(json.msg, {icon: 0});
            }
        }
    });
}

//添加楼层
function addFloor()
{
    //var optionContent = '<option value="' + results[i].floorId + '">' + results[i].floorNumber + '</option>';
    var length = $("#vertical-timeline").find(".vertical-timeline-block").length;
    var floorNumber = 1;
    if (0 != length)
    {
        var preFloorNumber = $("#vertical-timeline .vertical-timeline-block i:first").text();
        if (!isNaN(preFloorNumber))
        {
            floorNumber = Number(preFloorNumber) + 1;
        }
    }
    var buildingId = $("#belongBuildingIdIn").val();
    var floorId = '';
    $.ajax({
        type: "POST",
        url: "/asset/floor/saveFloor",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        data: {buildingId: buildingId, floorNumber: floorNumber},
        async: false,
        error: function(json) {
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json) {
            if ('0' == json.status)
            {
                floorId = json.data.floorId;
                //重新渲染楼层列表
                initFloor(json.data.floorId, json.data.floorNumber);
            }
            else
            {
                layer.alert(json.msg, {icon: 0});
            }
        }
    });
}

//根据楼层id查询楼层信息
function queryFloor(floorId)
{
    loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
    $.ajax({
        type: "POST",
        url: "/asset/floor/queryFloor/" + floorId,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        async: false,
        error: function(json) {
            layer.close(loadIndex);
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json) {
            if ('0' == json.status)
            {
                var data = json.data;
                var attachRefList = data.attachRefList;
                var attachId = '';
                if (attachRefList.length > 0)
                {
                    attachId = attachRefList[0].attachId;
                }
                $("#floorIdIn").val(data.floorId);
                $("#floorNumberIn").val(data.floorNumber);
                $("#floorHeightIn").val(data.floorHeight);
                $("#floorAreaIn").val(data.floorArea);
                $("#remarkIn").val(data.remark);
                //下载楼层平面图
                if (!isEmpty(attachId))
                {
                    downloadAttach(attachId);
                }
                else
                {
                    $("#floorImg").attr("src", "");
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

//下载文件
function downloadAttach(attachId)
{
    $.ajax({
        type: "POST",
        url: "/asset/floor/getDownloadUrl/" + attachId,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        async: false,
        error: function(json) {
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json) {
            if ('0' == json.status)
            {
                //楼层平面图赋值
                $("#floorImg").attr("src", json.data);
            }
            else
            {
                layer.alert(json.msg, {icon: 0});
            }
        }
    });
}

//根据楼层id查询锚点信息
function queryAnchors(floorId)
{
    loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
    $.ajax({
        type: "POST",
        url: "/asset/anchor/queryAnchors/" + floorId,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        async: false,
        error: function(json) {
            layer.close(loadIndex);
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json) {
            if ('0' == json.status)
            {
                var data = json.data;
                $("#imgDiv").find("div").remove();
                var content = $("#imgDiv").html();
                for (var i=0; i<data.length; i++)
                {
                    var position = "left:" + data[i].posX + "px;top:" + data[i].posY + "px;";
                    var _uuid = uuid();
                    content += "<div style='position:absolute;" + position + "cursor:pointer;'>" +
                                    "<input name='roomId' type='hidden' value='" + data[i].roomId + "'/>" +
                                    "<input name='anchorId' type='hidden' value='" + data[i].anchorId + "'/>" +
                                    "<img id=" + _uuid + " operate='update' draggable='true' ondragstart='drag(event)' " +
                                        " style='width:30px;height:30px;' src='../../../images/asset/building/" + roomType[data[i].roomType] + "'/>" +
                                    "<p class='roomNo'>" + data[i].roomNo + "</p>" +
                                "</div>";
                }
                $("#imgDiv").html(content);
                //初始化锚点相关事件
                initAnchorEvent();
            }
            else
            {
                layer.alert(json.msg, {icon: 0});
            }
            layer.close(loadIndex);
        }
    });
}

//设置默认选中楼层
function setSelectedFloor(floorId, floorNumber)
{
    //根据楼层id查询楼层信息
    queryFloor(floorId);
    //根据楼层id查询锚点信息
    queryAnchors(floorId);

    $(".vertical-timeline-icon").each(function(){
        $(this).css("border", "5px solid #f6f6f6");
    });
    var bordercolor = $(".vertical-timeline-block").has("input[value=" + floorId + "]").attr("bordercolor");
    $(".vertical-timeline-icon").has("input[value=" + floorId + "]").css("border", "5px solid " + bordercolor);

    //给楼层编辑页面赋值
    $("#floorIdIn").val(floorId);
    $("#floorNumberIn").val(floorNumber);
    //给楼层平面图表单赋值
    $("#belongFloorId").val(floorId);
    //给复制楼层页面下拉框赋值
    $("#copyFloorNumberSelect option[value=" + floorId + "]").attr("selected", "selected");
    //给锚点编辑页面标题赋值
    $("#roomEditTitle").text(floorNumber);
    //移除遮罩
    removeMask("floorContent");
    removeMask("anchorContent");
}

function allowDrop(ev)
{
    ev.preventDefault();
}
function drag(ev)
{
    ev.dataTransfer.setData("Text", ev.target.id);
    ev.dataTransfer.setData("offsetX", ev.offsetX);
    ev.dataTransfer.setData("offsetY", ev.offsetY);
}
function drop(ev)
{
    ev.preventDefault();
    var data = ev.dataTransfer.getData("Text");
    //偏移量
    var offsetX = ev.offsetX - ev.dataTransfer.getData("offsetX");
    var offsetY = ev.offsetY - ev.dataTransfer.getData("offsetY");
    var operate = $("#" + data).attr("operate");
    //修改
    if ("update" == operate)
    {
        //修改锚点位置信息
        var anchorId = $("#" + data).parent().find("[name='anchorId']").val();
        //如果锚点id不为空，修改锚点位置信息
        if (!isEmpty(anchorId))
        {
            $.ajax({
                type: "POST",
                url: "/asset/anchor/saveAnchor/",
                contentType: "application/x-www-form-urlencoded;charset=utf-8",
                data: {"anchorId":anchorId, "posX":offsetX, "posY":offsetY},
                async: false,
                error: function(json) {
                    layer.alert(json.msg, {icon: 2});
                },
                success: function(json) {
                    $("#" + data).parent().css({
                        "left" : offsetX + "px",
                        "top" : offsetY + "px"
                    });
                }
            });
        }
    }
    //新增房间
    else
    {
        //解决外来图片可拖拽放入的问题
        if (isEmpty(data) || -1 == $.inArray(data, drapImgIdArr))
        {
            return false;
        }
        var _uuid = uuid();
        var position = "left:" + offsetX + "px;top:" + offsetY + "px;";
        var elem = "<div style='position:absolute;" + position + "cursor:pointer;'>" +
                        "<input name='roomId' type='hidden' />" +
                        "<input name='anchorId' type='hidden' />" +
                        "<img id=" + _uuid + " operate='update' draggable='true' ondragstart='drag(event)'" +
                            " style='width:30px;height:30px;' src='" + $("#" + data).attr("src") + "' for='" + $("#" + data).attr("for") + "'/>" +
                        "<p class='roomNo'></p>" +
                    "</div>";
        var content = $("#imgDiv").html();
        content += elem;
        $("#imgDiv").html(content);
        //初始化锚点相关事件
        initAnchorEvent();
        //触发锚点点击事件，自动弹出房间编辑框
        $("#"+_uuid).parent().trigger("click");
    }
}

//初始化锚点相关事件
function initAnchorEvent()
{
    //取消绑定事件
    $("#imgDiv div").unbind();
    //绑定左击事件
    $("#imgDiv div").bind("click", function(e) {
        var roomId = $(this).find("[name='roomId']").val();
        var anchorId = $(this).find("[name='anchorId']").val();
        var imgId = $(this).find("img").attr("id");
        var buildingId = $("#belongBuildingIdIn").val();
        var roomType = $(this).find("img").attr("for");
        var floorId = $("#floorIdIn").val();
        var floorNumber = $("#floorNumberIn").val();
        //锚点赋值
        param = {
            buildingId: buildingId,
            floorId: floorId,
            roomId: roomId,
            imgId: imgId,
            roomType: roomType,
            anchorId: anchorId,
            posX: e.currentTarget.offsetLeft,
            posY: e.currentTarget.offsetTop,
            floorNumber: floorNumber
        }
        //打开房间编辑页面
        roomIndex = layer.open({
            type: 2,
            title: '房间编辑',
            shadeClose: false,
            shade: [0.5],
            maxmin: true, //开启最大化最小化按钮
            area: ['80%', '80%'],
            content: '../../../html/asset/building/roomEdit.html'
        });
    });
    //绑定右击事件
    $("#imgDiv div").bind("contextmenu", function(e) {
        $("#calMenu").css({
            "left" : e.pageX + "px",
            "top" : e.pageY + "px"
        });
        $("#calMenu").attr("for", e.target.id);
        $("#calMenu").show();
        return false;
    });
}

//上传楼层平面图
function floorFileChange(file)
{
    if (file.files && file.files.length > 0)
    {
        var formData = new FormData($( "#floorImgForm" )[0]);
        $.ajax({
            url: '/asset/floor/upload' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (json) {
                if ('0' == json.status)
                {
                    //楼层平面图赋值
                    $("#floorImg").attr("src", json.data);
                    //清空文件上传框
                    clearFileInput(document.getElementById('floorImgIn'));
                }
                else
                {
                    layer.alert(json.msg, {icon: 0});
                }
            },
            error: function (returndata) {
                layer.alert(json.msg, {icon: 2});
            }
        });
    }
}

//保存楼层
function saveFloor()
{
    //if ($('#floorForm').validate().form())
    if (validateFloor())
    {
        loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
        $.ajax({
            type: "POST",
            url: "/asset/floor/saveFloor",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data: $('#floorForm').serialize(),
            async: false,
            error: function(json) {
                layer.close(loadIndex);
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json) {
                if ('0' == json.status)
                {
                    layer.msg(json.msg, {icon: 1});
                    //收起楼层编辑页面
                    $("#collapse_floor").trigger("click");
                    //重新渲染楼层列表
                    initFloor(json.data.floorId, json.data.floorNumber);
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

//打开楼层复制页面
function openCopyFloor()
{
    copyFloorIndex = layer.open({
        type: 1,
        page : {dom : '#floorCopylayer'},
        title: "楼层复制",
        shade: [0.5],
        //skin: 'layui-layer-rim', //加上边框
        area: ['600px'], //宽高
        content: $('#floorCopyDiv')
    });
}

//关闭楼层复制页面
function closeCopyLayer()
{
    layer.close(copyFloorIndex);
}

//复制楼层
function copyFloor()
{
    //if ($('#floorCopyForm').validate().form())
    if (validateCopyFloor())
    {
        loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
        $.ajax({
            type: "POST",
            url: "/asset/floor/copyFloor",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data: $('#floorCopyForm').serialize(),
            async: false,
            error: function(json) {
                layer.close(loadIndex);
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json) {
                if ('0' == json.status)
                {
                    var selectedFloorId = $("#copyFloorNumberSelect").val();
                    var selectedFloorNumber = $("#copyFloorNumberSelect").text();
                    //重新渲染楼层列表
                    initFloor(selectedFloorId, selectedFloorNumber);
                    //清空楼层复制表单
                    resetFloorCopyForm();
                    //关闭复制页面弹出层
                    closeCopyLayer();
                    layer.msg(json.msg, {icon: 1});
                }
                else
                {
                    layer.alert(json.msg, {icon: 0});
                }
                layer.close(loadIndex);
            }
        });
    }
}

//删除楼层
function deleteFloor()
{
    var floorId = $("#floorIdIn").val();
    var defaultFloor = $("#vertical-timeline").find("input[value='" + floorId + "']").parent().parent().next();
    var defaultFloorId = defaultFloor.find("input").val();
    var defaultFloorName = defaultFloor.find("i").text();
    if (isEmpty(defaultFloorId))
    {
        defaultFloor = $("#vertical-timeline").find("input[value='" + floorId + "']").parent().parent().prev();
        defaultFloorId = defaultFloor.find("input").val();
        defaultFloorName = defaultFloor.find("i").text();
    }
    var layerIndex = layer.confirm('是否要删除该楼层？', {
        btn: ['确定','取消'], //按钮
        icon: 3
    }, function(){
        $.ajax({
            type: "POST",
            url: "/asset/floor/deleteFloor/" + floorId,
            error: function(json) {
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json){
                if ('0' == json.status)
                {
                    //重新渲染楼层列表
                    if (isEmpty(defaultFloorId))
                    {
                        initFloor(null, null);
                        //清空楼层编辑表单，楼层能删除说明没有房间，故不用清空锚点
                        resetFloorForm();
                    }
                    else
                    {
                        initFloor(defaultFloorId, defaultFloorName);
                    }
                    //关闭弹出框
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

//删除房间及锚点信息
function deleteRoom()
{
    $("#calMenu").hide();
    //删除房间及锚点信息
    var imgId = $("#calMenu").attr("for");
    var roomId = $("#"+imgId).parent().find("[name='roomId']").val();
    var buildingId = $("#belongBuildingIdIn").val();
    var floorId = $("#floorIdIn").val();
    if (!isEmpty(roomId))
    {
        $.ajax({
            type: "POST",
            url: "/asset/room/deleteRoom",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            data: {roomId: roomId, buildingId: buildingId, floorId: floorId},
            async: false,
            error: function(json) {
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json) {
                if ('0' == json.status)
                {
                    //移除页面上的锚点
                    $("#" + $("#calMenu").attr("for")).parent().remove();
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
        //移除页面上的锚点
        $("#" + $("#calMenu").attr("for")).parent().remove();
    }
}

//重置页面
function reset()
{
    $("#belongBuildingIdIn").val('');
    $("#belongBuildingNameIn").val('');
    //清空楼层编辑表单
    resetFloorForm();
    //清空楼层复制表单
    resetFloorCopyForm();
    //清空楼层列表
    $("#vertical-timeline").empty();
    //清空锚点
    $("#imgDiv").find("div").remove();
}

//清空楼层编辑表单
function resetFloorForm()
{
    $("#floorIdIn").val('');
    $("#floorNumberIn").val('');
    $("#floorHeightIn").val('');
    $("#floorAreaIn").val('');
    $("#remarkIn").val('');
}

//清空楼层复制表单
function resetFloorCopyForm()
{
    $("#startFloorNumberIn").val('');
    $("#endFloorNumberIn").val('');
}

//鼠标点击删除按钮菜单外任意地方，隐藏删除按钮菜单
document.onclick = function(event)
{
    var e = event || window.event;
    var elem = e.srcElement || e.target;

    while (elem) {
        if (elem.id == "calMenu") {
            return;
        }
        elem = elem.parentNode;
    }
    //隐藏div的方法
    $("#calMenu").hide();
};

//表单校验
var floorValidator = function() {
    var handleSubmit = function() {
        $('#floorForm').validate({
            errorElement : 'span',
            errorClass : 'help-block',
            focusInvalid : false,
            rules : {
                floorNumber : {
                    max : 250,
                    required : true,
                    nonzeroInteger : true
                },
                floorHeight : {
                    number : true,
                    min : 0.1,
                    max : 99
                },
                remark : {
                    maxlength : 80
                }
            },
            messages : {
                floorNumber : {
                    required : "楼层号不能为空"
                },
                floorHeight : {
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

        $('#floorCopyForm').validate({
            errorElement : 'span',
            errorClass : 'help-block',
            focusInvalid : false,
            rules : {
                startFloorNumber : {
                    max : 250,
                    required : true,
                    nonzeroInteger : true
                },
                endFloorNumber : {
                    max : 250,
                    required : true,
                    nonzeroInteger : true
                }
            },
            messages : {
                startFloorNumber : {
                    required : "开始楼层号不能为空"
                },
                endFloorNumber : {
                    required : "结束楼层号不能为空"
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

//校验楼层信息表单
function validateFloor()
{
    return $("#floorForm").validate().element($("#floorNumberIn")) &&
        $("#floorForm").validate().element($("#floorHeightIn"));
}

//校验复制楼层表单
function validateCopyFloor()
{
    return $("#floorCopyForm").validate().element($("#startFloorNumberIn")) &&
        $("#floorCopyForm").validate().element($("#endFloorNumberIn"));
}

//清空文件上传输入框
function clearFileInput(file){
    if (file.outerHTML) {  // for IE, Opera, Safari, Chrome
        file.outerHTML = file.outerHTML;
    } else { // FF(包括3.5)
        file.value = "";
    }
}
