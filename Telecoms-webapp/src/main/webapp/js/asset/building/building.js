//打开的楼宇编辑layer index
var buildingEditIndex = '';
//打开的楼层编辑tab页的id
var floorEditId = '';
//当前页码
var pageNo = 1;
//每页条数
var pageSize = 12;
//总页数
var totalPage = 0;
//总记录数
var totalRecord = 0;
//加载层index
var loadIndex;

//dom加载完毕
$(document).ready(function(){
    $(window).scroll(function(){
        var scrollTop=$(this).scrollTop();
        var scrollHeight=$(document).height();
        var windowHeight=$(this).height();
        if(scrollTop + windowHeight == scrollHeight)
        {
            if (pageNo < totalPage)
            {
                pageNo++;
                queryBuildings(pageNo);
            }
        }
    });
    queryBuildings(pageNo);
});

//查询楼宇列表
function queryBuildings(_pageNo)
{
    loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
    var buildingName = $('#c_buildingName').val();
    var param = {
        buildingName: buildingName,
        pageNo: _pageNo,
        pageSize: pageSize
    };
    $.ajax({
        type: "POST",
        url: "/asset/building/queryBuildings",
        data: param,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        async: false,
        error: function(json) {
            layer.close(loadIndex);
            layer.alert(json.msg, {icon: 2});
        },
        success: function(json) {
            var results = json.data.results;
            if ('0' == json.status)
            {
                totalPage = json.data.totalPage;
                totalRecord = json.data.totalRecord;
                var len = results.length;
                var rowCount = Math.ceil(len/4);
                if (1 == _pageNo)
                {
                    pageNo = _pageNo;
                    $('#building-boxs').empty();
                }
                if (_pageNo == totalPage || 0 == totalPage)
                {
                    $("#recordP").text("共" + totalRecord + "条记录，没有更多数据");
                }
                var maxHeight = ($('body').width()/4-30)/1.45;
                for (var i=0; i<rowCount; i++)
                {
                    var $rowContent = $('<div class="row"><div class="col-sm-12"></div></div>');
                    for (var j=i*4; j<(i+1)*4&&j<len; j++)
                    {
                        var building = results[j];
                        var floorCounts = isEmpty(building.floorCounts) ? '-':building.floorCounts;
                        var liftCounts = isEmpty(building.liftCounts) ? '-':building.liftCounts;
                        var buildingHeight = isEmpty(building.buildingHeight) ? '-':building.buildingHeight;
                        var buildingArea = isEmpty(building.buildingArea) ? '-':building.buildingArea;
                        var attachRefList = building.attachRefList;
                        //设置默认楼宇图片
                        var imgUrl = '../../../images/asset/building/building_default.jpg';
                        if (attachRefList.length > 0)
                        {
                            imgUrl = attachRefList[0].url;
                        }
                        var $content = $('<div class="col-sm-3">' +
                                            '<div class="building-box">' +
                                                '<div style="max-height:' + maxHeight + 'px;overflow: hidden;">' +
                                                    //'<div class="carousel-inner">' +
                                                    //    '<div class="item next left">' +
                                                            '<img alt="image" class="img-responsive" src="' + imgUrl + '">' +
                                                    //    '</div>' +
                                                    //    '<div class="item">' +
                                                    //        '<img alt="image" class="img-responsive" src="../../../images/asset/building/louyu1.jpg">' +
                                                    //    '</div>' +
                                                    //    '<div class="item active left">' +
                                                    //        '<img alt="image" class="img-responsive" src="../../../images/asset/building/louyu2.jpg">' +
                                                    //    '</div>' +
                                                    //'</div>' +
                                                    //'<a data-slide="prev" href="#carousel_building" class="left carousel-control">' +
                                                    //    '<span class="icon-prev"></span>' +
                                                    //'</a>' +
                                                    //'<a data-slide="next" href="#carousel_building" class="right carousel-control">' +
                                                    //    '<span class="icon-next"></span>' +
                                                    //'</a>' +
                                                '</div>' +
                                                '<div class="box-info">' +
                                                    '<label class="box-title">' + building.buildingName + '</label><br>' +
                                                    '<label class="box-desc">共' + floorCounts + '层 电梯数量' + liftCounts + '部</label><br>' +
                                                    '<div class="row box-label">' +
                                                        '<label>&nbsp;建筑高度' + buildingHeight + 'm&nbsp;</label>' +
                                                        '<label>&nbsp;占地面积' + buildingArea + '㎡&nbsp;</label>' +
                                                    '</div>' +
                                                '</div>' +
                                                '<div class="row oper-box">' +
                                                    '<input type="hidden" name="buildingId" value="' + building.buildingId + '"/>' +
                                                    '<input type="hidden" name="buildingName" value="' + building.buildingName + '"/>' +
                                                    '<img src="../../../images/asset/building/building_icon.png" title="编辑楼宇" oper="2" onclick="jumpToBuildingEdit(this)">' +
                                                    '<img src="../../../images/asset/building/floor_icon.png" title="编辑楼层" onclick="jumpToFloorEdit(this)">' +
                                                    '<img src="../../../images/asset/building/del_icon.png" title="删除" onclick="deleteBuilding(this)">' +
                                                '</div>' +
                                            '</div>' +
                                        '</div>').appendTo($rowContent.find('.col-sm-12'));
                    }
                    $rowContent.appendTo($('#building-boxs'));
                }
                //初始化事件
                initEvent();
            }
            else
            {
                layer.alert(json.msg, {icon: 0});
            }
            layer.close(loadIndex);
        }
    });
}

//初始化楼宇宫格事件
function initEvent()
{
    //鼠标悬浮缩放效果
    //$('#building-boxs .building-box').each(function() {
    //    animationHover(this, 'pulse');
    //});
    $('#building-boxs .building-box').mouseover(function(){
        $(this).find('.oper-box').show();
    });
    $('#building-boxs .building-box').mouseout(function(){
        $(this).find('.oper-box').hide();
    });
}

//跳转至楼宇编辑页面
function jumpToBuildingEdit(target)
{
    var oper = Number($(target).attr("oper"));
    switch (oper)
    {
        case 1:
            buildingEditIndex = layer.open({
                type: 2,
                title: '楼宇编辑',
                shadeClose: false,
                shade: [0.5],
                maxmin: true, //开启最大化最小化按钮
                area: ['80%', '80%'],
                content: '../../../html/asset/building/buildingEdit.html'
            });
            break;
        case 2:
            var buildingId = $(target).parent().find("[name='buildingId']").val();
            buildingEditIndex = layer.open({
                type: 2,
                title: '楼宇编辑',
                shadeClose: false,
                shade: [0.5],
                maxmin: true, //开启最大化最小化按钮
                area: ['80%', '80%'],
                content: '../../../html/asset/building/buildingEdit.html?buildingId='+buildingId
            });
            break;
        default:
            break;
    }
}

//跳转至楼层编辑页面
function jumpToFloorEdit(target)
{
    if ('' == floorEditId)
    {
        floorEditId = uuid();
    }
    else
    {
        top.closeTab(floorEditId);
    }
    var buildingId = $(target).parent().find("[name='buildingId']").val();
    var buildingName = $(target).parent().find("[name='buildingName']").val();
    top.openTab("楼层编辑", floorEditId, "html/asset/building/floorEdit.html", {buildingId: buildingId, buildingName: buildingName}, false);
}

//删除楼宇
function deleteBuilding(target)
{
    var index = layer.confirm('是否要删除该楼宇？', {
        btn: ['确定','取消'], //按钮
        icon: 3
    }, function(){
        loadIndex = layer.load(0, {shade: [0.5, '#FFFFFF']});
        var buildingId = $(target).parent().find("input").val();
        $.ajax({
            type: "POST",
            url: "/asset/building/deleteBuilding/" + buildingId,
            error: function(json) {
                layer.close(loadIndex);
                layer.alert(json.msg, {icon: 2});
            },
            success: function(json){
                if ('0' == json.status)
                {
                    layer.close(index);
                    layer.msg(json.msg, {icon: 1});
                    //$(target).parent().parent().parent().remove();
                    //$("#recordP").text("共" + (totalRecord-1) + "条记录，没有更多数据");
                    queryBuildings(1);
                }
                else
                {
                    layer.alert(json.msg, {icon: 0});
                }
                layer.close(loadIndex);
            }
        });
    }, function(){

    });
}