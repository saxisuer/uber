<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <base href="${ctx}">

    <title>城市列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="uber,城市,station">
    <meta http-equiv="description" content="城市列表">
</head>
<body id="body">
<!-- 查询条件区域 -->
<div id="search" class="easyui-panel" style="padding:5px;margin-bottom:2px;">
    <table cellpadding="1px;" style="font-size:11px;">
        <tr>
            <td>城市名称</td>
            <td><label for="param_cityNameCn"></label><input class="easyui-validatebox" type="text" id="param_cityNameCn"/></td>
            <td>
                <a href="javascript:void(0);" class="easyui-linkbutton my-search-button" iconCls="icon-search" onclick='search1()'>查询</a>
            </td>
        </tr>
    </table>
</div>
<!-- 数据表格区域 -->
<table id="users" style="table-layout:fixed;">
</table>
<!-- 表格顶部工具栏 -->
<div id="toolbar">
    <a href="javascript:void(0)" id="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
    <a href="javascript:void(0)" id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    <a href="javascript:void(0)" id="refresh" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
</div>
<script>
    $(function () {
        var gridDom = $('#users');
        gridDom.datagrid({
            height: $('#body').height() - $('#search').height() - 15,
            width: $('#body').width(),
            idField: 'id',
            method: 'POST',
            url: '${ctx}/city/loadData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: true,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'cityName', title: '城市发音', width: 100, halign: 'center', align: 'left'},
                {field: 'cityNameCn', title: '城市名称', width: 100, halign: 'center', align: 'left'},
                {field: 'cityCode', title: '城市代码', width: 100, halign: 'center', align: 'left'}
            ]],
            toolbar: '#toolbar',
            pagination: true,
            pageSize: 30
        });


        //新增弹出框
        $("#add").on("click", function () {
            parent.dataUpdate({
                action: 'city/add',
                success: function () {
                    $.messager.show({
                        title: '提示',
                        msg: '新增成功'
                    });
                    return gridDom.datagrid('reload');
                }
            });
        });
        //修改
        $("#edit").on("click", function () {
            var r = gridDom.datagrid('getSelected');
            parent.dataUpdate({
                action: 'city/edit/' + r.id,
                success: function (data) {
                    $.messager.show({
                        title: '提示',
                        msg: '更新成功'
                    });
                    return gridDom.datagrid('reload');
                }
            });
        });
        //删除
        $("#delete").on("click", function () {
            var r = gridDom.datagrid('getSelected');
            $.messager.confirm('提醒', '您确定要删除吗？', function (e) {
                if (e) {
                    deleteUser({
                        action: '${ctx}/city/delete/' + r.id,
                        id: {'id': r.id}
                    });
                }
            });
        });

        //刷新
        $("#refresh").on("click", function () {
            gridDom.datagrid('reload');
        });
    });

    function formatStatus(val, row) {
        if (val == 1) {
            return '启用';
        } else {
            return '禁用';
        }
    }

    function search1() {
        $('#users').datagrid('load', {
            cityNameCn: $.trim($('#param_cityNameCn').val())
        });
    }

    function deleteUser(opts) {
        $.ajax({
            url: opts.action,
            //data: opts.id, //{key:valule} user.name : 12323
            success: function (data) {
                if (data.result == "SUCCESS") {
                    $('#users').datagrid('reload');
                    $('#users').datagrid('clearSelections');
                } else {
                    alert(data.result)
                }
            },
            error: function () {
            }
        });
    }

    function reset() {
        $('#param_cityNameCn').val("");
        $('#users').datagrid('load', {});
    }
</script>
</body>
</html>
