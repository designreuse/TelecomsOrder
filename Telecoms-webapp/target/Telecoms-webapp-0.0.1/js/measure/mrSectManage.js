function cellStyle(e, t, o) {
    var n = ["active", "success", "info", "warning", "danger"];
    return o % 2 === 0 && o / 2 < n.length ? {
        classes: n[o / 2]
    } : {}
}
function rowStyle(e, t) {
    var o = ["active", "success", "info", "warning", "danger"];
    return t % 2 === 0 && t / 2 < o.length ? {
        classes: o[t / 2]
    } : {}
}
function scoreSorter(e, t) {
    return e > t ? 1 : t > e ? -1 : 0
}
function nameFormatter(e) {
    return e + '<i class="icon wb-book" aria-hidden="true"></i> '
}
function starsFormatter(e) {
    return '<i class="icon wb-star" aria-hidden="true"></i> ' + e
}
function queryParams() {
    return {
        type: "owner",
        sort: "updated",
        direction: "desc",
        per_page: 100,
        page: 1
    }
}
function buildTable(e, t, o) {
    var n, l, s, a = [],
        c = [];
    for (n = 0; t > n; n++) a.push({
        field: "字段" + n,
        title: "单元" + n
    });
    for (n = 0; o > n; n++) {
        for (s = {},
                 l = 0; t > l; l++) s["字段" + l] = "Row-" + n + "-" + l;
        c.push(s)
    }
    e.bootstrapTable("destroy").bootstrapTable({
        columns: a,
        data: c,
        iconSize: "outline",
        icons: {
            columns: "glyphicon-list"
        }
    })
}
!
    function (e, t, o) {
        "use strict";
        !
            function () {
                o("#exampleTableEvents").bootstrapTable({
                    url: "data.json",
                    search: !0,
                    pagination: !0,
                    showRefresh: !0,
                    showToggle: !0,
                    showColumns: !0,
                    iconSize: "outline",
                    toolbar: "#exampleTableEventsToolbar",
                    icons: {
                        refresh: "glyphicon-repeat",
                        toggle: "glyphicon-list-alt",
                        columns: "glyphicon-list"
                    }
                });
                var e = o("#examplebtTableEventsResult");
                o("#exampleTableEvents").on("all.bs.table",
                    function (e, t, o) {
                        console.log("Event:", t, ", data:", o)
                    }).on("click-row.bs.table",
                    function () {
                        e.text("Event: click-row.bs.table");
                    }).on("dbl-click-row.bs.table",
                    function () {
                        e.text("Event: dbl-click-row.bs.table")
                    }).on("sort.bs.table",
                    function () {
                        e.text("Event: sort.bs.table")
                    }).on("check.bs.table",
                    function () {
                        e.text("Event: check.bs.table")
                    }).on("uncheck.bs.table",
                    function () {
                        e.text("Event: uncheck.bs.table")
                    }).on("check-all.bs.table",
                    function () {
                        e.text("Event: check-all.bs.table")
                    }).on("uncheck-all.bs.table",
                    function () {
                        e.text("Event: uncheck-all.bs.table")
                    }).on("load-success.bs.table",
                    function () {
                        e.text("Event: load-success.bs.table")
                    }).on("load-error.bs.table",
                    function () {
                        e.text("Event: load-error.bs.table")
                    }).on("column-switch.bs.table",
                    function () {
                        e.text("Event: column-switch.bs.table")
                    }).on("page-change.bs.table",
                    function () {
                        e.text("Event: page-change.bs.table")
                    }).on("search.bs.table",
                    function () {
                        e.text("Event: search.bs.table")
                    })
            }()
    }(document, window, jQuery);