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
<!-- 数据表格区域 -->
<table id="fileLists" style="table-layout:fixed;">
</table>
<!-- 表格顶部工具栏 -->
<div id="toolbar">
    <a href="javascript:void(0)" id="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a href="javascript:void(0)" id="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
    <a href="javascript:void(0)" id="set" class="easyui-linkbutton" iconCls="icon-edit" plain="true">设置</a>
    <a href="javascript:void(0)" id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    <a href="javascript:void(0)" id="refresh" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
</div>
<div id="deviceInfoDiv">
    <table id="deviceInfoTable">
    </table>
</div>
<script type="text/javascript">
    $(function () {
        var gridDom = $('#fileLists');
        gridDom.datagrid({
            height: $('#body').height() - $('#search').height(),
            width: $('#body').width(),
            method: 'GET',
            idField: 'id',
            url: '${ctx}/filelist/loadData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: true,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'company', title: '公司名称', width: 100, halign: 'center', align: 'left'},
                {field: 'fileTitle', title: '文件标题', width: 100, halign: 'center', align: 'left'},
                {field: 'fileLevel', title: '广告级别', width: 100, halign: 'center', align: 'left', hidden: true},
                {field: 'uploadByWho', title: '文件上传者', width: 100, halign: 'center', align: 'left'},
                {field: 'uploadTime', title: '上传时间', width: 100, halign: 'center', align: 'left', formatter: formatDate},
                {field: 'startTime', title: '开播时间', width: 100, halign: 'center', align: 'left', formatter: formatDate},
                {field: 'endTime', title: '停播时间', width: 100, halign: 'center', align: 'left', formatter: formatDate},
            ]],
            toolbar: '#toolbar',
            pagination: true,
            pageSize: 30
        });

        $(window).on('resize', function () {
            $('.datagrid').hide();
            gridDom.datagrid('resize', {height: $('#body').height() - $('#search').height()});
            $('.datagrid').show();
            gridDom.datagrid('reload');
        });

        //新增弹出框
        $("#add").on("click", function () {
            parent.uploadFileForm({
                action: 'filelist/add',
                success: function () {
                    return gridDom.datagrid('reload');
                }
            });
        });
        //修改
        $("#edit").on("click", function () {
            var r = gridDom.datagrid('getSelected');
            parent.uploadFileForm({
                action: 'filelist/edit',
                params: {'id': r.id},
                success: function () {
                    return gridDom.datagrid('reload');
                }
            });
        });
        $('#deviceInfoTable').datagrid({
            //idField: 'id',
            url: '${ctx}/deviceinfo/loadData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            height: 300,
            fitColumns: false,
            rownumbers: true,
            showPageList: false,
            singleSelect: false,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'deviceUUID', title: '设备ID', halign: 'center', align: 'left'},
                {field: 'province', title: '省', halign: 'center', align: 'left'},
                {field: 'city', title: '市', halign: 'center', align: 'left'},
                {field: 'address', title: '地址', halign: 'center', align: 'left'},
                {field: 'installationDate', title: '注册日期', halign: 'center', align: 'left', formatter: formatDate},
                {field: 'state', title: '状态', halign: 'center', align: 'left', formatter: formatStatus}
            ]],
            pagination: true,
            pageSize: 30
        });
        $("#set").on('click', function () {
            $("#deviceInfoDiv").dialog('open');
        });
        //删除
        $("#delete").on("click", function () {
            var r = gridDom.datagrid('getSelected');

            $.messager.confirm('提醒', '您确定要删除吗？', function (e) {
                if (e) {
                    deleteRole({
                        action: 'filelist/delete/'+ r.id,
                        id: {'fileList.id': r.id}
                    });
                }
            });
        });
        //刷新
        $("#refresh").on("click", function () {
            gridDom.datagrid('reload');
        });
        $("#deviceInfoDiv").dialog({
            title: '设备列表',
            closed: true,
            model: true,
            width: 550,
            minimizable: true,
            maximizable: true,
            collapsible: true,
            draggable: true,
            resizable: true,
            top: 50,
            buttons: [{
                text: '绑定',
                iconCls: 'icon-save',
                handler: function () {
                    var deviceSelection = $('#deviceInfoTable').datagrid('getSelections');
                    var deviceId = [];
                    $.each(deviceSelection, function (i, v) {
                        deviceId.push(v.id);
                    });
                    var r = gridDom.datagrid('getSelected');
                    console.log(r);
                    $.ajax({
                        method: 'GET',
                        url: '${ctx}/filelist/bindDeviceInfo',
                        data: {
                            'deviceIds': deviceId.join(','),
                            'fileListId': r.id,
                            'fileListLevel': r.fileLevel
                        },
                        success: function (data) {
                            console.log(data);
                            if (data.result == 'success') {
                                $.messager.show({
                                    title: '提示',
                                    msg: '绑定广告成功',
                                    showType: 'slide'
                                })
                            }
                            $('#deviceInfoTable').datagrid('clearSelections');
                            $("#deviceInfoDiv").dialog('close');
                        }
                    })
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#deviceInfoTable').datagrid('clearSelections');
                    $("#deviceInfoDiv").dialog('close');
                }
            }],
            onBeforeOpen: function () {
                var r = gridDom.datagrid('getSelected');
                if (null == r) {
                    $.messager.show({
                        title: '提示',
                        msg: '尚未选择广告文件',
                        showType: 'slide'
                    });
                    return false;
                }
            }
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
        return v.Format('yyyy-MM-dd hh:mm-ss');
    }

    function search() {
        gridDom.datagrid('load', {
            param_cnname: $.trim($('#param_cnname').val())
        });
    }

    function deleteRole(opts) {
        $.ajax({
            url: opts.action,
            success: function (data) {
                if (data.result == "SUCCESS") {
                    $("#fileLists").datagrid('reload');
                    $("#fileLists").datagrid('clearSelections');
                } else {
                    alert(data.result)
                }
            },
            error: function () {
            }
        });
    }
</script>
</body>
</html>
