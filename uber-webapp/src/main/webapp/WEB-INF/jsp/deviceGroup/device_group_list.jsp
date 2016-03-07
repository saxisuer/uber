<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <base href="${ctx}">

    <title>设备组管理</title>
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
            <td>设备组名</td>
            <td><label for="param_groupName"></label><input class="easyui-validatebox" type="text" id="param_groupName"/></td>
            <td>
                <a href="javascript:void(0);" class="easyui-linkbutton my-search-button" iconCls="icon-search" onclick='search1()'>查询</a>
            </td>
            <td>
                <a href="javascript:void(0);" class="easyui-linkbutton my-search-button" iconCls="icon-clear" onclick='reset()'>重置</a>
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
            method: 'GET',
            url: '${ctx}/deviceGroup/loadData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: true,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'groupName', title: '设备组名称', width: 100, halign: 'center', align: 'left'},
                {field: 'groupDesc', title: '设备组备注', width: 100, halign: 'center', align: 'left'},
                {field: 'cityNameCn', title: '所属城市', width: 100, halign: 'center', align: 'left'}
            ]],
            toolbar: '#toolbar',
            pagination: true,
            pageSize: 30
        });


        //新增弹出框
        $("#add").on("click", function () {
            parent.dataUpdate({
                action: 'deviceGroup/add',
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
                action: 'deviceGroup/edit/' + r.id,
                params: {'id': r.id},
                success: function () {
                    $.messager.show({
                        title: '提示',
                        msg: '修改成功'
                    });
                    return gridDom.datagrid('reload');
                },
                onSubmit: function (dialog) {
                }
            });
        });
        //删除
        $("#delete").on("click", function () {
            var r = gridDom.datagrid('getSelected');
            $.messager.confirm('提醒', '您确定要删除吗？', function (e) {
                if (e) {
                    deleteUser({
                        action: '${ctx}/deviceGroup/delete/' + r.id,
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

    function search1() {
        $('#users').datagrid('load', {
            groupName: $.trim($('#param_groupName').val())
        });
    }

    function deleteUser(opts) {
        $.ajax({
            url: opts.action,
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
        $('#param_groupName').val("");
        $('#users').datagrid('load', {});
    }
</script>
</body>
</html>
