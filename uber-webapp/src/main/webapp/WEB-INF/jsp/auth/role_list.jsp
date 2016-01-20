<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <base href="<%=basePath%>">

    <title>用户列表</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

</head>

<body id="body">
<!-- 数据表格区域 -->
<table id="roles" style="table-layout:fixed;">
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
        var gridDom = $('#roles');
        gridDom.datagrid({
            height: $('#body').height() - $('#search').height(),
            width: $('#body').width(),
            idField: 'id',
            url: '<%=path%>/role/loadData',
            singleSelect: false,
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: true,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'name', title: '名称', width: 100, halign: 'center', align: 'left'}
            ]],
            toolbar: '#toolbar',
            pagination: true,
            pageSize: 30,
        });

        //新增弹出框
        $("#add").on("click", function () {
            parent.dataUpdate({
                action: 'role/add',
                success: function () {
                    return gridDom.datagrid('reload');
                }
            });
        });
        //修改
        $("#edit").on("click", function () {
            var r = gridDom.datagrid('getSelected');
            parent.dataUpdate({
                action: 'role/edit',
                params: {'role.id': r.id},
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
                        action: 'role/delete',
                        id: {'role.id': r.id}
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

    function search() {
        gridDom.datagrid('load', {
            param_cnname: $.trim($('#param_cnname').val()),
        });
    }

    function deleteRole(opts) {
        $.ajax({
            url: opts.action,
            data: opts.id,
            success: function (data) {
                console.log(data)
                if (data.result == "SUCCESS") {
                    $("#roles").datagrid('reload');
                    $("#roles").datagrid('clearSelections');
                } else {
                    alert(data.result)
                }
            },
            error: function () {
            }
        });
    }

    /*
     ** 弹窗更新grid数据
     */
    /*function dataUpdate(opt){
     var action = opt.action,
     id = opt.id==undefined ? '' : opt.id;
     console.log( '<%=path%>/role/'+action)
     $.ajax({
     url: 'role/add',
     type: 'GET',
     data: {'role.id': id},
     success: function(data){
     if(data.result=="500"){alert('error:'+500)}
     var dialog = $('#wUpdate');
     dialog.find('#wContainer').html(data);
     dialog.window('open');
     $('#wOK').one('click',function(){
     var form = $("form:first");
     form.find('input[type="submit"]').trigger('click');
     setPermissionIds();
     dialog.find('form').ajaxSubmit({
     success: function(data){
     if(data.result=="SUCCESS"){
     opt.success();
     dialog.window('close');
     }else{
     alert('保存失败！');
     dialog.window('close');
     }
     }
     });
     });
     $('#wCancel').on('click',function(){
     dialog.window('close');
     });
     },
     error: function() {
     alert('加载出错！')
     }
     });
     }*/

</script>

</html>