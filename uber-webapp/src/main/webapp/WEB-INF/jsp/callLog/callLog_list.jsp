<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <base href="${ctx}">

    <title>使用卡列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

</head>

<body id="body">
<!-- 查询条件区域 -->
<div id="search" class="easyui-panel" style="padding:5px;margin-bottom:2px;">
    <table cellpadding="1px;" style="font-size:11px;">
        <tr>
            <td>设备ID</td>
            <td><input class="easyui-textbox" type="text" id="param_uuid"/></td>
            <td>帐号</td>
            <td><input class="easyui-textbox" type="text" id="param_userAcc"/></td>
            <td>车卡号</td>
            <td><input class="easyui-textbox" type="text" id="param_cardNoPhisycal"/></td>
        </tr>
        <tr>
            <td>叫车时间</td>
            <td><input id="param_callTime" class="easyui-datebox"/></td>
            <td>司机到达时间</td>
            <td><input id="param_pickupTime" class="easyui-datebox"/></td>
            <td><a href="javascript:search();" class="easyui-linkbutton my-search-button" iconCls="icon-search">查询</a></td>
        </tr>
    </table>

</div>

<!-- 数据表格区域 -->
<table id="callLog" style="table-layout:fixed;">
</table>
<!-- 表格顶部工具栏 -->
<div id="toolbar">
    <a href="javascript:void(0)" id="refresh" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
</div>

</body>

<script>


    $(function () {
        var gridDom = $('#callLog');
        gridDom.datagrid({
            height: $('#body').height() - $('#search').height() - 15,
            width: $('#body').width(),
            idField: 'id',
            url: '${ctx}/calllog/loadData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: true,
            columns: [[
                {field: 'deviceUUID', title: '设备ID', width: 100, halign: 'center', align: 'left'},
                {field: 'address', title: '设备地址', width: 100, halign: 'center', align: 'left'},
                {field: 'userAcc', title: '帐号', width: 100, halign: 'center', align: 'left'},
                {field: 'cardNoPhysical', title: '车卡号', width: 100, halign: 'center', align: 'left'},
                {field: 'accessToken', title: 'accessToken', width: 100, halign: 'center', align: 'left'},
                {field: 'productID', title: 'productID', width: 100, halign: 'center', align: 'left'},
                {field: 'requestID', title: 'requestID', width: 100, halign: 'center', align: 'left'},
                {field: 'state', title: '业务办理结果', width: 100, halign: 'center', align: 'left'},
                {field: 'errorInfo', title: '错误信息', width: 100, halign: 'center', align: 'left'},
                {field: 'callTime', title: '叫车时间', width: 100, halign: 'center', align: 'left', formatter: formatDate},
                {field: 'pickupTime', title: '司机到达时间', width: 100, halign: 'center', align: 'left', formatter: formatDate},
            ]],
            toolbar: '#toolbar',
            pagination: true,
            pageSize: 30
        });

        //刷新
        $("#refresh").on("click", function () {
            gridDom.datagrid('reload');
        });

    });

    function search() {
        $('#callLog').datagrid('load', {
            param_uuid: $.trim($('#param_uuid').val()),
            param_userAcc: $.trim($('#param_userAcc').val()),
            param_cardNoPhisycal: $.trim($('#param_cardNoPhisycal').val()),
            param_callTime: $.trim($('#param_callTime').datebox('getValue')),
            param_pickupTime: $.trim($('#param_pickupTime').datebox('getValue'))
        });
    }

    function formatDate(val, row) {
        if (typeof(val) == "string") {
            return val.substring(0, 10);
        } else {
            return val;
        }
    }

</script>

</html>
