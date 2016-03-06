<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <base href="${ctx}">

    <title>设备列表</title>

</head>

<body id="body">
<!-- 查询条件区域 -->
<div id="search" class="easyui-panel" style="padding:5px;margin-bottom:2px;">
    <table cellpadding="1px;" style="font-size:11px;">
        <tr>
            <td>地址</td>
            <td><input class="easyui-textbox" type="text" id="param_address"/></td>
            <td><a href="javascript:search();" class="easyui-linkbutton my-search-button" iconCls="icon-search">查询</a></td>
        </tr>
    </table>

</div>

<!-- 数据表格区域 -->
<table id="deviceInfos" style="table-layout:fixed;">
</table>
<!-- 表格顶部工具栏 -->
<div id="toolbar">
    <a href="javascript:void(0)" id="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
    <a href="javascript:void(0)" id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    <a href="javascript:void(0)" id="refresh" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
</div>
</body>

<script>

    $(function () {
        var gridDom = $('#deviceInfos');
        gridDom.datagrid({
            height: $('#body').height() - $('#search').height(),
            width: $('#body').width(),
            idField: 'id',
            url: '${ctx}/deviceinfo/loadData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: true,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'deviceUUID', title: '设备ID', width: 100, halign: 'center', align: 'left'},
                {field: 'groupName', title: '设备组', width: 100, halign: 'center', align: 'left'},
                {field: 'cityNameCn', title: '市', width: 100, halign: 'center', align: 'left'},
                {field: 'address', title: '地址', width: 100, halign: 'center', align: 'left'},
                {field: 'latitude', title: '纬度', width: 100, halign: 'center', align: 'left'},
                {field: 'longtitude', title: '经度', width: 100, halign: 'center', align: 'left'},
                {field: 'installationDate', title: '注册日期', width: 100, halign: 'center', align: 'left', formatter: formatDate},
                {field: 'state', title: '状态', width: 100, halign: 'center', align: 'left', formatter: formatStatus}
            ]],
            toolbar: '#toolbar',
            pagination: true,
            pageSize: 30
        });

        //新增弹出框
        $("#add").on("click", function () {
            parent.dataUpdate({
                action: 'deviceinfo/add',
                success: function () {
                    return gridDom.datagrid('reload');
                }
            });
        });
        //修改
        $("#edit").on("click", function () {
            var r = gridDom.datagrid('getSelected');
            parent.dataUpdate({
                action: 'deviceinfo/edit',
                params: {'id': r.id},
                success: function () {
                    return gridDom.datagrid('reload');
                }
            });
        });
        //删除
        $("#delete").on("click", function () {
            var r = gridDom.datagrid('getSelected');

            $.messager.confirm('提醒', '您确定要删除吗？', function (e) {
                if (e) {
                    deleteRole({
                        action: '${ctx}/deviceinfo/delete',
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

    function formatDate(val, row) {
        dataFormated();
        var v = new Date(val);
        return v.Format('yyyy-MM-dd');

    }

    function search() {
        $('#deviceInfos').datagrid('load', {
            param_address: $.trim($('#param_address').val())
        });
    }

    function deleteRole(opts) {
        $.ajax({
            url: opts.action,
            data: opts.id,
            success: function (data) {
                if (data.result == "SUCCESS") {
                    $("#deviceInfos").datagrid('reload');
                    $("#deviceInfos").datagrid('clearSelections');
                } else {
                    alert(data.result)
                }
            },
            error: function () {
            }
        });
    }

</script>

</html>
