$(function () {
    //加载菜单
    $.post('system/menu/get', {}, function (result) {
        var menuDom = '';
        if (result.status == '0') {
            for (var i = 0; i < result.data.length; i++) {
                var item = result.data[i];
                menuDom += '<li>';
                if (item.subMenus && item.subMenus.length > 0) {
                    menuDom += '<a href="javascript:void(0);">';
                    menuDom += '<i class="fa fa-th-large"></i>';
                    menuDom += ' <span class="nav-label">' + item.title + '</span>';
                    menuDom += '<span class="fa arrow"></span>';
                    menuDom += '</a>';
                    menuDom += creatSubMenus(item.subMenus);
                } else {
                    menuDom += '<a href="' + item.handler + '" data-id="' + item.menuID + '">';
                    menuDom += '<i class="fa fa-diamond"></i>';
                    menuDom += '<span class="nav-label">' + item.title + '</span>';
                    menuDom += '</a>';
                }
                menuDom += '</li>';
            }
            $('#side-menu').append(menuDom);
            $('#side-menu li a').click(function (e) {
//            var tab = '<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="'+$(this).attr('href')+'" frameborder="0" data-id="index_v1.html" seamless=""></iframe>';
//                $('.J_iframe').attr('src', $(this).attr('href'));
                if ($(this).attr('href') != 'javascript:void(0);') {
                    openTab($(this).text(), $(this).attr('data-id'), $(this).attr('href'));
                }
                return false;
            });
        } else {
            alert(result.msg);
        }
        $('#side-menu').metisMenu();
    }, 'json');
});

function creatSubMenus(arr) {
    var menuDom = '<ul class="nav nav-second-level collapse">';
    for (var i = 0; i < arr.length; i++) {
        if (arr[i].subMenus && arr[i].subMenus.length > 0) {
            menuDom += '<li>';
            menuDom += '<a href="javascript:void(0);" data-id="' + arr[i].menuID + '">';
            menuDom += '<i class="fa fa-th-large"></i>';
            menuDom += ' <span class="nav-label">' + arr[i].title + '</span>';
            menuDom += '<span class="fa arrow"></span>';
            menuDom += '</a>';
            menuDom += creatSubMenus(arr[i].subMenus);
            menuDom += '</li>';
        } else {
            menuDom += '<li><a href="' + arr[i].handler + '" data-id="' + arr[i].menuName + '">' + arr[i].title + '</a></li>';
        }
    }
    menuDom += '</ul>';
    return menuDom;
}

function openTab(title, id, url, param, forceReload) {
    var bool = false;
    param = param ? param : {};
    $.each($('.page-tabs .page-tabs-content a'), function (index, item) {
        if ($(item).attr('data-id') == id) {
            $('.page-tabs .page-tabs-content .active').removeClass('active');
            $(item).addClass('active');
            bool = true;
            $.each($('#tabs-content .J_iframe'), function (idx, tabContent) {
                if ($(tabContent).attr('data-id') == id) {
                    $('#tabs-content .J_iframe').hide();
                    $(tabContent).show();
                    if ((url && $(tabContent).attr('src') != url) || forceReload) {
                        $(tabContent)[0].contentWindow.location.reload(false);
                        //$(tabContent).attr('src',url);
                        $(tabContent).load(function () {
                            if ($('#J_frame_' + id)[0].contentWindow.init) {
                                $('#J_frame_' + id)[0].contentWindow.init(param);
                            }
                        });
                        return;
                    }
                }
            });
            return;
        }
    });
    if (!bool) {
        var tab = '<a href="javascript:void(0);" class="active J_menuTab" data-id="' + id + '">' + title;
        tab += '<i class="fa fa-times-circle"></i></a>';
        $('.page-tabs .page-tabs-content .active').removeClass('active');
        $('.page-tabs .page-tabs-content').append(tab);

        $('#tabs-content .J_iframe').hide();
        var tabContent = '<iframe id="J_frame_' + id + '" class="J_iframe"  width="100%" height="100%"';
        tabContent += 'src="' + url + '" frameborder="0" data-id="' + id + '"></iframe>';
        var index = layer.load(0, {shade: false});
        $('#tabs-content').append(tabContent);
        $('#J_frame_' + id).load(function (e) {
            layer.close(index);
            if ($('#J_frame_' + id)[0].contentWindow.init) {
                $('#J_frame_' + id)[0].contentWindow.init(param);
            }
            return true;
        });
        addTabListener();
    }
}

function closeTab(id) {
    if (typeof id == 'string') {
        $.each($('.page-tabs .page-tabs-content a'), function (index, item) {
            if ($(item).attr('data-id') == id) {
                if ($('#tabs-content .J_iframe').length > 1) {
                    if ($(item).prev().length > 0) {
                        openTab(null, $(item).prev().attr('data-id'));
                    } else {
                        openTab(null, $(item).next().attr('data-id'));
                    }
                }
                $.each($('#tabs-content .J_iframe'), function (idx, tabContent) {
                    if ($(tabContent).attr('data-id') == id) {
                        $(tabContent).remove();
                        return;
                    }
                });
                $(item).remove();
                return;
            }
        });
    } else {
        closeTab($(id[0].frameElement).attr('data-id'));
    }
}

function getActivedTab(){
    return $('#J_frame_'+$('.page-tabs .page-tabs-content .active').attr('data-id'));
}

function addTabListener() {
    $('.page-tabs .page-tabs-content a').click(function (e) {
        openTab(null, $(this).attr('data-id'));
    });
    $('.J_menuTab i').click(function () {
        closeTab($(this).parent().attr('data-id'));
    });
}

function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar') || $('body').hasClass('body-small')) {
        // Hide menu in order to smoothly turn on when maximize menu
        $('#side-menu').hide();
        // For smoothly turn on menu
        setTimeout(
            function () {
                $('#side-menu').fadeIn(500);
            }, 100);
    } else if ($('body').hasClass('fixed-sidebar')) {
        $('#side-menu').hide();
        setTimeout(
            function () {
                $('#side-menu').fadeIn(500);
            }, 300);
    } else {
        // Remove all inline style from jquery fadeIn function to reset menu state
        $('#side-menu').removeAttr('style');
    }
}

