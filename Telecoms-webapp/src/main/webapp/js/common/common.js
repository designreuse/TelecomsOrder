/**
 * Created by Zhengtao on 2015/11/27.
 */
var BModal = {};
BModal.msg = function(msg) {
    parent.layer.msg(msg, {icon: 1});
};
BModal.alert = function(msg) {
    parent.layer.alert(msg, {
        skin: 'layui-layer-lan'
    });
};

BModal.confirm = function(msg, callback1,callback2) {
    var index = parent.layer.confirm(msg, {
        skin: 'layui-layer-lan',
        btn: ['确定','取消'], //按钮
        shade: 0.5
    }, function(){
        parent.layer.close(index);
        if(callback1) {
            callback1();
        }
    }, function(){
        if(callback2) {
            callback2();
        }
    });
};

BModal.open = function(title, url, width, height, maxminFlag,end) {
    var index = parent.layer.open({
        //skin: 'layui-layer-lan',
        type: 2,
        title: title,
        shade: 0.5,
        area: [width, height], //宽高
        maxmin: !maxminFlag ? false : maxminFlag,
        content: url,
        end:end
    });
    return index;
};

//获取QueryString的数组
function getQueryString(){
    var result = location.search;
    if(result == null){
        return {};
    }
    result = result.replace("?","");
    var params = result.split("&");
    var paramObject = {};
    for(var i = 0; i < params.length; i++){
        var tmp = params[i].split("=");
        if(tmp.length == 2){
            paramObject[tmp[0]]=tmp[1];
        }
    }
    return paramObject;
}

function replaceEmptyStr(data) {
    return (data == null || data == '' ? '-' : data);
}

function replaceEmptyNum(data) {
    return (data == null || data == '' ? '0' : data);
}

$.fn.serializeJSON = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

if($.jgrid){
    $.jgrid.defaults.styleUI = "Bootstrap";
}
var jqGrid = function () {
    return {
        init: function (DT_Selector, options) {
            if (!jQuery().dataTable) {
                return;
            }

            /*$(DT_Selector).jqGrid({
                url: "../../../measure/mrSect/queryMRSects",
                datatype : "json",
                height: 580,
                autowidth: true,
                shrinkToFit: true,
                colNames : [ '序号', '抄表段名称', '抄表责任人', '抄表段类型', '用户数', '创建人','创建时间', '变更人', '变更时间' ],
                colModel : [
                    {name : 'mrSectId',index : 'mrSectId',width : 55, hidden: true},
                    {name : 'name',  index : 'name',width : 100, align : "left"},
                    {name : 'mrEmpName',index : 'mrEmpName',width : 80, align : "center"},
                    {name : 'sectTypeName',index : 'sectTypeName',  width : 80,  align : "center"},
                    {name : 'consNum',index : 'consNum',width : 60,align : "center"},
                    {name : 'creator',index : 'creator',width : 80,align : "center"},
                    {name : 'createTime',index : 'createTime',width : 120, align : "center"},
                    {name : 'lastChanger',index : 'lastChanger',width : 80, sortable : false, align : "center"},
                    {name : 'lastChangeTime',index : 'lastChangeTime',width : 120,align : "center", sortable : false}
                ],
                rowNum : 10,
                rowList : [ 10, 20, 30 ],
                pager : '#mrSectPager',
                viewrecords: true,
                multiselect : true,
                caption : "抄表段列表",
                hidegrid: false,
                ondblClickRow : editMRSect
            });
            $("#mrSectTable").jqGrid("navGrid", "#mrSectPager", {
                    add: false,
                    edit: false,
                    del: false,
                    search: false
                },
                {
                    height: 200,
                    reloadAfterSubmit: true
                });*/
            $(DT_Selector).dataTable({
                "aoColumns": options.aoColumns,
                "aLengthMenu":!options.aLengthMenu ? [[10, 20],[10, 20]] : options.aLengthMenu,
                "bInfo" : !options.bInfo ? false : options.bInfo,
                "bSort" : !options.bSort ? false : options.bSort,
                "bFilter" : !options.bFilter ? false : options.bFilter,
                "bLengthChange":!options.bLengthChange ? true : options.bLengthChange,
                "bServerSide": !options.bServerSide ? true : options.bServerSide,
                "sAjaxSource": options.sAjaxSource,
                "fnServerParams": !options.fnServerParams ? {} : options.fnServerParams,
                "iDisplayLength": !options.iDisplayLength ? 10 : options.iDisplayLength,
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sSearch":"搜索:",
                    "sProcessing" : "正在获取数据，请稍后...",
                    "sLengthMenu": "_MENU_ 条记录每页",
                    "sInfo":"显示_START_到_END_条 共_TOTAL_条",
                    "oPaginate": {
                        "sPrevious": "上一页",
                        "sNext": "下一页"
                    },
                    "sZeroRecords": "没有查询到结果。",
                    "sInfoEmpty": "没有查询到数据"
                },
                "aoColumnDefs": [{
                    'bSortable': false,
                    'aTargets': [0]
                }
                ]
            });
        }
    };
}();
