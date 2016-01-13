//生成uuid
function uuid()
{
    var s = [];
    var hexDigits = "0123456789abcdef";
    for ( var i = 0; i < 36; i++)
    {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";

    var uuid = s.join("");
    return uuid;
}

//判断字符串是否为空、undefined、''
function isEmpty(obj)
{
    if (undefined == obj || null == obj
        || 'null' == obj || '' == obj)
    {
        return true;
    }
    return false;
}

//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

//遮罩层数组
var maskArr = [];
//浏览器缩放
$(window).resize(function() {
    for (var i=0; i<maskArr.length; i++)
    {
        initMask(maskArr[i]);
    }
});
//是否遮罩
function hasMask(id)
{
    var index = $.inArray(id, maskArr);
    return -1 == index ? false : true;
}
//给目标元素添加遮罩
function addMask(id)
{
    if (hasMask(id))
    {
        return false;
    }
    maskArr.push(id);
    var content = '<div id=mask_' + id + ' class="maskLayer"></div>';
    $(document.body).append(content);
    //$('#' + id).append(content);
    initMask(id);
}
//初始化遮罩层
function initMask(id)
{
    $('#mask_' + id).css({
        top: $('#' + id).offset().top,
        left: $('#' + id).offset().left,
        width: $('#' + id).outerWidth(true),
        height: $('#' + id).outerHeight(true)
    });
}
//取消遮罩
function removeMask(id)
{
    for (var i=0; i<maskArr.length; i++)
    {
        if (id == maskArr[i])
        {
            maskArr.splice(i, 1);
        }
    }
    $('#mask_' + id).remove();
}

/**
 * 获取当前日期
 * @returns {string}
 */
function getCurrentDate()
{
    var d = new Date();
    var vYear = d.getFullYear();
    var vMon = d.getMonth() + 1;
    var vDay = d.getDate();
    var h = d.getHours();
    var m = d.getMinutes();
    var se = d.getSeconds();
    var s=vYear+"-"+(vMon<10 ? "0" + vMon : vMon)+"-"+(vDay<10 ? "0"+ vDay : vDay);
    //+(h<10 ? "0"+ h : h)+(m<10 ? "0" + m : m)+(se<10 ? "0" +se : se)
    return s;
}

/**
 * 获取jqGrid select编辑需要的value值
 * @param map
 * @returns {string}
 */
function getMapStr(map)
{
    var str = '';
    for (key in map)
    {
        str += ';' + key + ':' + map[key]; //转换为jqGrid select编辑需要的value值
    }
    str = str.substring(1); //去掉第一个;
    return str;
}