<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <base href="${ctx}">

    <title>申请列表</title>
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
            <td>逻辑卡号</td>
            <td><input class="easyui-textbox" type="text" id="param_cardNoLogical"/></td>
            <td>
            <td>联系人</td>
            <td><input class="easyui-textbox" type="text" id="param_contactMan"/></td>
            <td>
            <td>电话</td>
            <td><input class="easyui-textbox" type="text" id="param_mobile"/></td>
            <td>
                <a href="javascript:search();" class="easyui-linkbutton my-search-button" iconCls="icon-search">查询</a></td>
        </tr>
    </table>

</div>

<!-- 数据表格区域 -->
<table id="applicant" style="table-layout:fixed;">
</table>
<!-- 表格顶部工具栏 -->
<div id="toolbar">
    <a href="javascript:void(0)" id="refresh" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
</div>

</body>

<script>


    $(function () {
        var gridDom = $('#applicant');
        gridDom.datagrid({
            height: $('#body').height() - $('#search').height() - 15,
            width: $('#body').width(),
            idField: 'id',
            url: '${ctx}/applicant/loadHistoryData',
            singleSelect: false,
            method: 'GET',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'userAcc', title: '账号', width: 100, halign: 'center', align: 'left'},
                {field: 'cardNoPhysical', title: '物理卡号', width: 100, halign: 'center', align: 'left'},
                {field: 'cardNoLogical', title: '逻辑卡号', width: 100, halign: 'center', align: 'left'},
                {field: 'contactMan', title: '联系人', width: 100, halign: 'center', align: 'left'},
                {field: 'mobile', title: '手机号码', width: 100, halign: 'center', align: 'center'},
                {field: 'rprovince', title: '省份', width: 100, halign: 'center', align: 'center'},
                {field: 'rcity', title: '城市', width: 100, halign: 'center', align: 'center'},
                {field: 'rcounty', title: '县/区', width: 100, halign: 'center', align: 'center'},
                {field: 'addrDetail', title: '详细地址', width: 100, halign: 'center', align: 'center'},
                {field: 'requestDate', title: '申请日期', width: 100, halign: 'center', align: 'center', formatter: formatDate}
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

    function formatStatus(val, row) {
        if (val == 1) {
            return '启用';
        } else {
            return '禁用';
        }
    }

    function search() {
        $('#applicant').datagrid('load', {
            mobile: $.trim($('#param_mobile').val()),
            contactMan: $.trim($('#param_contactMan').val()),
            cardNoLogical: $.trim($('#param_cardNoLogical').val())
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
