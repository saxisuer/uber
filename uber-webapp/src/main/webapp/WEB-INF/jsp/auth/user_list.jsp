<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <base href="${ctx}">

    <title>用户列表</title>
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
            <td>姓名</td>
            <td><label for="param_cnname"></label><input class="easyui-textbox" type="text" id="param_cnname"/></td>
            <td><a href="javascript:void(0);" class="easyui-linkbutton my-search-button" iconCls="icon-search" onclick="search()">查询</a></td>
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
    <a href="javascript:void(0)" id="resetPassword" class="easyui-linkbutton" iconCls="icon-reset" plain="true">密码重置</a>
    <a href="javascript:void(0)" id="refresh" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
</div>
</body>
<script>
    $(function () {
        var gridDom = $('#users');
        gridDom.datagrid({
            height: $('#body').height() - $('#search').height() - 15,
            width: $('#body').width(),
            idField: 'id',
            method: 'GET',
            url: '${ctx}/user/loadData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: true,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'name', title: '账号', width: 100, halign: 'center', align: 'left'},
                {field: 'cnname', title: '姓名', width: 100, halign: 'center', align: 'left'},
                {field: 'phone', title: '联系电话', width: 100, halign: 'center', align: 'left'},
                {field: 'email', title: '邮箱', width: 100, halign: 'center', align: 'left'},
                {field: 'status', title: '状态', width: 100, halign: 'center', align: 'center', formatter: formatStatus}
            ]],
            toolbar: '#toolbar',
            pagination: true,
            pageSize: 30
        });


        //新增弹出框
        $("#add").on("click", function () {
            parent.dataUpdate({
                action: 'user/add',
                success: function () {
                    return gridDom.datagrid('reload');
                }
            });
        });
        //修改
        $("#edit").on("click", function () {
            var r = gridDom.datagrid('getSelected');
            parent.dataUpdate({
                action: 'user/edit',
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
                    deleteUser({
                        action: '${ctx}/user/delete/' + r.id,
                        id: {'id': r.id}
                    });
                }
            });
        });

        //密码重置
        $("#resetPassword").on("click", function () {
            var r = gridDom.datagrid('getSelected');

            $.ajax({
                url: '${ctx}/user/resetPassword',
                data: {'id': r.id},
                success: function (data) {
                    if (data.result == "SUCCESS") {
                        $('#users').datagrid('reload');
                        $('#users').datagrid('clearSelections');
                        alert("密码重置成功,初始化密码为12345")
                    } else {
                        alert(data.result)
                    }
                },
                error: function () {
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

    function search() {
        console.log('aaaaa');
        $('#users').datagrid('load', {
            param_cnname: $.trim($('#param_cnname').val())
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

</script>

</html>
