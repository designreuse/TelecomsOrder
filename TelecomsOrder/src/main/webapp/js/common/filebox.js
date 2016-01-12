var FileBox = {};
//是否展示删除按钮
FileBox.showDelBtn = true;
//公共图片文件夹的路径
FileBox.path = "http://"+window.location.host+"/images/common/";

FileBox.fileMap = {
    "doc": FileBox.path + "word_icon.png",
    "pdf": FileBox.path + "pdf_icon.png",
    "unknow": FileBox.path + "unknow_icon.png"
};

//初始化文件盒
FileBox.init = function (attachRefList, deleteCallback)
{
    var $gallery = $('<div id="blueimp-gallery" class="blueimp-gallery">' +
                        '<div class="slides"></div>' +
                        '<h3 class="title"></h3>' +
                        '<a class="prev">‹</a>' +
                        '<a class="next">›</a>' +
                        '<a class="close">×</a>' +
                        '<a class="play-pause"></a>' +
                        '<ol class="indicator"></ol>' +
                    '</div>').appendTo($("#filebox"));
    for (var i=0; i<attachRefList.length; i++)
    {
        var fileName = attachRefList[i].dataFile.fileName;
        var isImg = false;
        var $li = $('<li></li>');
        var url = FileBox.fileMap["unknow"];
        if (fileName.endWith("doc") || fileName.endWith("docx"))
        {
            url = FileBox.fileMap["doc"];
        }
        else if (fileName.endWith("pdf"))
        {
            url = FileBox.fileMap["pdf"];
        }
        else if (fileName.endWith("jpg") || fileName.endWith("png")
            || fileName.endWith("bmp") || fileName.endWith("jpeg")
            || fileName.endWith("gif"))
        {
            $li.addClass("bg-image");
            url = attachRefList[i].url;
            isImg = true;
        }
        var $a = $('<a href="' + attachRefList[i].url + '" data-gallery=""></a>');
        var $p = $('<p class="imgWrap">' +
                        '<img src="' + url + '">' +
                    '</p>' +
                    '<p class="title">' + fileName + '</p>');
        if (isImg)
        {
            $p.appendTo($a);
            $a.appendTo($li);
        }
        else
        {
            $p.appendTo($li);
        }
        var $btns = $('<div class="file-panel" style="height: 0px;">' +
                    '</div>');
        if (FileBox.showDelBtn)
        {
            var $delBtn = $('<span class="delete">' +
                                '<input name="attachRefId" type="hidden" value="' + attachRefList[i].attachRefId + '"/>' +
                                '<input name="attachId" type="hidden" value="' + attachRefList[i].attachId + '"/>' +
                            '删除</span>').appendTo($btns);
        }
        var $downloadBtn = $('<span class="download">' +
                                 '<input name="url" type="hidden" value="' + attachRefList[i].url + '"/>' +
                             '下载</span>').appendTo($btns);
        $btns.appendTo($li);
        $li.appendTo($("#filebox ul"));
    }
    //初始化悬浮和移出事件
    $("#filebox ul li").on( 'mouseenter', function() {
        $(this).find(".file-panel").stop().animate({height: 30});
    });
    $("#filebox ul li").on( 'mouseleave', function() {
        $(this).find(".file-panel").stop().animate({height: 0});
    });

    $("#filebox ul li .file-panel").on('click', 'span', function() {
        if (FileBox.showDelBtn)
        {
            var index = $(this).index();
            switch ( index ) {
                case 0:
                    var attachRefId = $(this).find("input[name='attachRefId']").val();
                    var attachId = $(this).find("input[name='attachId']").val();
                    var param = {
                        attachRefId: attachRefId,
                        attachId: attachId
                    };
                    deleteCallback(param);
                    return;
                case 1:
                    location.href=$(this).find("input").attr("value");
                    //window.open($(this).find("input").attr("value"));
                    return;
            }
        }
        else
        {
            location.href=$(this).find("input").attr("value");
        }
    });
}

//删除文件
FileBox.deleteFile = function(attachRefId)
{
    $("#filebox ul li").has("input[value='" + attachRefId + "']").remove();
}

//删除所有文件
FileBox.clear = function()
{
    $("#filebox ul").empty();
}

String.prototype.endWith=function(str)
{
    if(str==null||str==""||this.length==0||str.length>this.length)
        return false;
    if(this.substring(this.length-str.length)==str)
        return true;
    else
        return false;
    return true;
}