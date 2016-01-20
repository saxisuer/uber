<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="/template/baseheader.jsp"%>
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
    <table id="fileLists" style="table-layout:fixed;">   
    </table> 
    <!-- 表格顶部工具栏 -->
	<div id="toolbar">
	    <a href="javascript:void(0)"  id="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
	    <a href="javascript:void(0)"  id="edit" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
	    <a href="javascript:void(0)"  id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
	    <a href="javascript:void(0)"  id="refresh" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
	 </div>
  </body>
  
<script>

$(function(){
	var gridDom = $('#fileLists');
	gridDom.datagrid({
		height:$('#body').height()-$('#search').height(),
		width:$('#body').width(),
		idField:'id',
		url:'<%=path%>/filelist/loadData',  
		singleSelect:false, 
		selectOnCheck: true,
		checkOnSelect: true,
		nowrap:true,
		fitColumns:true,
		rownumbers:true,
		showPageList:false,
		singleSelect:true,
		columns:[[
			{field:'ck',checkbox:true },
			{field:'company',title:'公司名称',width:100,halign:'center', align:'left'},
			{field:'fileTitle',title:'文件标题',width:100,halign:'center', align:'left'},
			{field:'uploadByWho',title:'文件上传者',width:100,halign:'center', align:'left'},
			{field:'uploadTime',title:'上传时间',width:100,halign:'center', align:'left', formatter:formatDate},
			{field:'startTime',title:'开播时间',width:100,halign:'center', align:'left', formatter:formatDate},
			{field:'endTime',title:'停播时间',width:100,halign:'center', align:'left', formatter:formatDate},
		]],
		toolbar:'#toolbar',  
        pagination:true,
        pageSize:30,
	});

	$(window).on('resize',function(){
		$('.datagrid').hide();
		gridDom.datagrid('resize',{height:$('#body').height()-$('#search').height()});
		$('.datagrid').show();
		gridDom.datagrid('reload');
	})
	
	//新增弹出框
	$("#add").on("click", function() {		
		parent.dataUpdate({
			action: 'filelist/add',
			success: function(){
				return gridDom.datagrid('reload');
			}
		});		
	});
	//修改
	$("#edit").on("click", function(){
		var r = gridDom.datagrid('getSelected');
		parent.dataUpdate({
			action: 'filelist/edit',
			params: {'fileList.id': r.id},
			success: function(){
				return gridDom.datagrid('reload');
			}
		});			
	});
	//删除
	$("#delete").on("click", function(){
		var r = gridDom.datagrid('getSelected');
		
		$.messager.confirm('提醒', '您确定要删除吗？', function(e){
			if (e){
				deleteRole({
					action: 'filelist/delete',
					id: {'fileList.id' : r.id}
				});	
			}
		});
	});
	//刷新
	$("#refresh").on("click",function(){
		gridDom.datagrid('reload');
	});
});

function formatStatus(val,row){
	if (val == 1){
		return '启用';
	} else {
		return '禁用';
	}
}

function formatDate(val,row){
	if(typeof(val)=="string"){
		return val.substring(0,19).replace("T", " ");
	}else{
		return val;
	}
	
}

function search(){
	gridDom.datagrid('load',{
		param_cnname: $.trim($('#param_cnname').val()),
	});
}

function deleteRole(opts){
	$.ajax({
		url: opts.action,
		data: opts.id, 
		success: function(data){
		console.log(data)
			if(data.result=="SUCCESS"){
				$("#fileLists").datagrid('reload');
				$("#fileLists").datagrid('clearSelections');
			}else{
				alert(data.result)
			}
		},
		error: function(){		
		}
	});
}

/*
** 弹窗更新grid数据
*/
/*function dataUpdate(opt){
	var action = opt.action,
	id = opt.id==undefined ? '' : opt.id;
	console.log( '<%=path%>/fileList/'+action)
	$.ajax({
	url: 'fileList/add',
	type: 'GET',
	data: {'fileList.id': id},
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
