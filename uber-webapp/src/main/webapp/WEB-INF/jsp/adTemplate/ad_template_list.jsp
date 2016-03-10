<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <base href="${ctx}">

    <title>广告组列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="uber,城市,station">
    <meta http-equiv="description" content="广告组列表">
</head>
<body id="body">
<!-- 查询条件区域 -->
<div id="search" class="easyui-panel" style="padding:5px;margin-bottom:2px;">
    <table cellpadding="1px;" style="font-size:11px;">
        <tr>
            <td>模板名称</td>
            <td><label for="param_templateName"></label><input class="easyui-validatebox" type="text" id="param_templateName"/></td>
            <td>
                <a href="javascript:void(0);" class="easyui-linkbutton my-search-button" iconCls="icon-search" onclick='search1()'>查询</a>
            </td>
        </tr>
    </table>
</div>
<!-- 数据表格区域 -->
<table id="users" style="table-layout:fixed;">
</table>
<div id="cityDialog" style="max-height: 280px;">
    <table id="city"></table>
</div>
<div id="groupDialog" style="max-height: 280px;">
    <table id="group"></table>
</div>

<div id="deviceDialog" style="max-height: 280px;">
    <table id="device"></table>
</div>
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
            url: '${ctx}/adTemplate/loadData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: true,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'templateName', title: '模板名称', width: 100, halign: 'center', align: 'left'},
                {field: 'templateDesc', title: '模板备注', width: 100, halign: 'center', align: 'left'},
                {field: 'creator', title: '创建人', width: 100, halign: 'center', align: 'left'},
                {field: 'fileCount', title: '广告个数', width: 100, halign: 'center', align: 'left'},
                {field: 'id', title: '操作', formatter: gridFormatter}
            ]],
            toolbar: '#toolbar',
            pagination: true,
            pageSize: 30
        });

        $('#cityDialog').dialog({
            close: true,
            title: '城市列表',
            closed: true,
            modal: true,
            width: 550,
            minimizable: true,
            maximizable: true,
            collapsible: true,
            draggable: true,
            resizable: true,
            buttons: [{
                text: '绑定',
                iconCls: 'icon-save',
                handler: function () {
                    bindDevice('city')
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#cityDialog').dialog('close');
                }
            }]
        });
        $('#city').datagrid({
            autoLoad: false,
            method: 'POST',
            url: '${ctx}/adTemplate/loadCityData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            width: 550,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: false,
            onBeforeLoad: function () {
                var opts = $(this).datagrid('options');
                return opts.autoLoad;
            },
            onLoadSuccess: function (data) {
                if (data.total > 0) {
                    $('#cityDialog').dialog('open');
                } else {
                    $.messager.alert("提示", '尚无适合此模板的城市')
                }
            },
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'cityName', title: '城市发音', width: 50, halign: 'center', align: 'left'},
                {field: 'cityNameCn', title: '城市名称', width: 50, halign: 'center', align: 'left'}
            ]]
        });
        $('#group').datagrid({
            autoLoad: false,
            method: 'POST',
            url: '${ctx}/adTemplate/loadGroupData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            width: 540,
            fitColumns: true,
            rownumbers: true,
            singleSelect: false,
            onBeforeLoad: function () {
                var opts = $(this).datagrid('options');
                return opts.autoLoad;
            },
            onLoadSuccess: function (data) {
                if (data.total > 0) {
                    $('#groupDialog').dialog('open');
                } else {
                    $.messager.alert("提示", '尚无适合此模板的设备组')
                }
            },
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'groupName', title: '设备组名称', width: 50, halign: 'center', align: 'left'},
                {field: 'groupDesc', title: '设备组备注', width: 50, halign: 'center', align: 'left'},
                {field: 'cityNameCn', title: '所属城市', width: 50, halign: 'center', align: 'left'}
            ]]
        });
        $('#device').datagrid({
            autoLoad: false,
            method: 'POST',
            url: '${ctx}/adTemplate/loadDeviceData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            width: 550,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: false,
            onLoadSuccess: function (data) {
                if (data.total > 0) {
                    $('#deviceDialog').dialog('open');
                } else {
                    $.messager.alert("提示", '尚无适合此模板的设备')
                }
            },
            onBeforeLoad: function () {
                var opts = $(this).datagrid('options');
                return opts.autoLoad;
            },
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'deviceUUID', title: '设备ID', width: 50, halign: 'center', align: 'left'},
                {field: 'groupName', title: '设备组', width: 50, halign: 'center', align: 'left'},
                {field: 'cityNameCn', title: '市', width: 50, halign: 'center', align: 'left'}
            ]]
        });
        $('#groupDialog').dialog({
            close: true,
            title: '设备组列表',
            closed: true,
            modal: true,
            width: 550,
            minimizable: true,
            maximizable: true,
            collapsible: true,
            draggable: true,
            resizable: true,
            buttons: [{
                text: '绑定',
                iconCls: 'icon-save',
                handler: function () {
                    bindDevice('group')
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#groupDialog').dialog('close');
                }
            }]
        });
        $('#deviceDialog').dialog({
            close: true,
            title: '设备列表',
            closed: true,
            modal: true,
            width: 550,
            minimizable: true,
            maximizable: true,
            collapsible: true,
            draggable: true,
            resizable: true,
            buttons: [{
                text: '绑定',
                iconCls: 'icon-save',
                handler: function () {
                    bindDevice('device')
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#deviceDialog').dialog('close');
                }
            }]
        });

        //新增弹出框
        $("#add").on("click", function () {
            parent.dataUpdate({
                action: 'adTemplate/add',
                success: function () {
                    $.messager.show({
                        title: '提示',
                        msg: '新增成功'
                    });
                    return gridDom.datagrid('reload');
                },
                onSubmit: function (dialog) {
                    var ids = dialog.find('form').find('input[name="adFileIds"]').val();
                    if (ids == '') {
                        alert('提示,必须选择至少一个广告');
                        return false;
                    }
                }
            });
        });
        //修改
        $("#edit").on("click", function () {
            var r = gridDom.datagrid('getSelected');
            parent.dataUpdate({
                action: 'adTemplate/edit/' + r.id,
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
                        action: '${ctx}/adTemplate/delete/' + r.id,
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

    function gridFormatter(value, rowData, rowIndex) {
        var rowId = rowData.id;
        var url = "";
        url += "<button title='城市绑定' onclick='bindCity(" + rowId + ")'>城市绑定 </button>&nbsp;";
        url += "<button title='设备组绑定' onclick='bindGroup(" + rowId + ")'>设备组绑定 </button>&nbsp;";
        url += "<button title='设备绑定' onclick='bindDeviceInfo(" + rowId + ")'>设备绑定 </button>";
        return url;
    }


    function bindCity(rowId) {
        $('#city').datagrid('options').autoLoad = true;
        $('#city').datagrid('load', {
            tempId: rowId
        });
    }

    function bindGroup(rowId) {
        $('#group').datagrid('options').autoLoad = true;
        $('#group').datagrid('load', {
            tempId: rowId
        });
    }

    function bindDeviceInfo(rowId) {
        $('#device').datagrid('options').autoLoad = true;
        $('#device').datagrid('load', {
            tempId: rowId
        });
    }

    function bindDevice(bindType) {
        var adTempId = $('#users').datagrid('getSelected').id;
        var selections = $('#city').datagrid('getSelections');
        var cityIds = [];
        $.each(selections, function (i, v) {
            cityIds.push(v.cityCode);
        });
        $.ajax({
            url: '${ctx}/adTemplate/bindDevice',
            method: 'POST',
            data: {
                'adTempId': adTempId,
                'bindId': cityIds.join(","),
                'bindType': bindType
            },
            success: function (data) {
                if (data.result == 'SUCCESS') {
                    $.messager.show({
                        title: '提示',
                        msg: '绑定成功'
                    });
                    $('#cityDialog').dialog('close');
                    $('#groupDialog').dialog('close');
                    $('#deviceDialog').dialog('close');
                } else {
                    $.messager.alert('提示', '绑定失败');
                }
            },
            error: function () {
                $.messager.alert('提示', '系统故障');
            }
        })
    }
    function formatStatus(val, row) {
        if (val == 1) {
            return '启用';
        } else {
            return '禁用';
        }
    }

    function search1() {
        $('#users').datagrid('load', {
            templateName: $.trim($('#param_templateName').val())
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
        $('#param_templateName').val("");
        $('#users').datagrid('load', {});
    }
</script>
</body>
</html>
