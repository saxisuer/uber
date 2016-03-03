<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <base href="<%=basePath%>">

    <title>产品配置</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

</head>

<body id="body" class="easyui-layout">
<div region="west" split="true" title="地区" style="width:180px;" id="west">
    <div fit="true" border="false">
        <!--  导航内容 -->
        <ul id="areaId" class="sub-menu">
        </ul>
    </div>
</div>

<div id="mainPanle" class="baseproducts-list" region="center" style="background: #eee;">
    <table id="products" class="baseproducts-table" style="table-layout:fixed;">
    </table>

    <div id="toolbar">
        <a href="javascript:void(0)" id="save" class="easyui-linkbutton" iconCls="icon-save" plain="true">保存</a>
        <a href="javascript:void(0)" id="refresh" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
    </div>
</div>

</body>

<script>

    $(function () {
        $.ajax({
            url: "<%=path%>/baseproducts/getcity",
            success: function (data) {
                console.log(data);

                var i = 0;
                //根据id查找对象，
                $.each(data, function (key, val) {
                    var li = document.createElement('li');
                    li.innerHTML = val;
                    li.id = key;
                    var areaId = document.getElementById('areaId');
                    li.onclick = function () {
                        getProductsByCity(li.id);
                    };
                    areaId.appendChild(li);
                    if (i == 0) {
                        getProductsByCity(li.id);
                        $(li).addClass('active');
                    }
                    i++;
                });
                $('#areaId li').on('click', function () {
                    $(this).addClass('active').siblings().removeClass('active');
                })
            },
            error: function () {
            }
        });
    });

    function getProductsByCity(city) {
        var gridDom = $('#products');
        gridDom.datagrid({
            height: $('#mainPanle').height(),
            width: $('#mainPanle').width(),
            idField: 'id',
            url: '<%=path%>/baseproducts/loadData?param_city=' + city,
            singleSelect: false,
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'productName', title: '帐号', width: 100, halign: 'center', align: 'left'},
            ]],
            toolbar: '#toolbar',
            pagination: true,
            pageSize: 30,
            onLoadSuccess: function (data) {
                var $this = $(this);
                $("#products").datagrid('clearSelections');
                $.each(data.rows, function (k, v) {
                    if (v.aid != null) {
                        $this.datagrid('selectRow', k);
                    }
                })
            }
        });
    }

    //刷新
    $("#refresh").on("click", function () {
        var gridDom = $('#products');
        gridDom.datagrid('reload');
    });

    //刷新
    $("#save").on("click", function () {
        var gridDom = $('#products');
        var rows = gridDom.datagrid('getSelections');
        if (rows.length == 0) {
            $.messager.alert('系统提示', '至少选择一个产品！', 'warning');
            return;
        } else if (rows.length > 2) {
            $.messager.alert('系统提示', '不能选择多于两个设备！', 'warning');
            return;
        } else {
            idArr = [];
            $.each(rows, function (index, el) {
                idArr.push(el.id);
            });
            var idStr = idArr.join(",");
            console.log(rows);

            $.ajax({
                url: "<%=path%>/baseproducts/save",
                data: {'ids': idStr},
                success: function (data) {
                    console.log(data);
                    $('#products').datagrid('reload');
                }
            });
        }

    });

    //resize of div
    (function ($, h, c) {
        var a = $([]),
                e = $.resize = $.extend($.resize, {}),
                i,
                k = "setTimeout",
                j = "resize",
                d = j + "-special-event",
                b = "delay",
                f = "throttleWindow";
        e[b] = 250;
        e[f] = true;
        $.event.special[j] = {
            setup: function () {
                if (!e[f] && this[k]) {
                    return false;
                }
                var l = $(this);
                a = a.add(l);
                $.data(this, d, {
                    w: l.width(),
                    h: l.height()
                });
                if (a.length === 1) {
                    g();
                }
            },
            teardown: function () {
                if (!e[f] && this[k]) {
                    return false;
                }
                var l = $(this);
                a = a.not(l);
                l.removeData(d);
                if (!a.length) {
                    clearTimeout(i);
                }
            },
            add: function (l) {
                if (!e[f] && this[k]) {
                    return false;
                }
                var n;

                function m(s, o, p) {
                    var q = $(this),
                            r = $.data(this, d);
                    r.w = o !== c ? o : q.width();
                    r.h = p !== c ? p : q.height();
                    n.apply(this, arguments);
                }

                if ($.isFunction(l)) {
                    n = l;
                    return m;
                } else {
                    n = l.handler;
                    l.handler = m;
                }
            }
        };
        function g() {
            i = h[k](function () {
                        a.each(function () {
                            var n = $(this),
                                    m = n.width(),
                                    l = n.height(),
                                    o = $.data(this, d);
                            if (m !== o.w || l !== o.h) {
                                n.trigger(j, [o.w = m, o.h = l]);
                            }
                        });
                        g();
                    },
                    e[b]);
        }
    })(jQuery, this);
</script>

</html>
