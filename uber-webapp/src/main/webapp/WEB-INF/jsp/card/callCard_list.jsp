<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="/template/baseheader.jsp"%>
	<base href="<%=basePath%>">
    
    <title>使用卡列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body id="body">
  	<!-- 数据表格区域 -->
    <table id="callCard" style="table-layout:fixed;">   
    </table> 
    <!-- 表格顶部工具栏 -->
	<div id="toolbar">
	    <a href="javascript:void(0)"  id="loss" class="easyui-linkbutton" iconCls="icon-setCardLoss" plain="true">挂失换卡</a>
	    <a href="javascript:void(0)"  id="damaged" class="easyui-linkbutton" iconCls="icon-setDamaged" plain="true">损坏换卡</a>
	    <a href="javascript:void(0)"  id="returnCard" class="easyui-linkbutton" iconCls="icon-setReturnCard" plain="true">退货</a>
	    <a href="javascript:void(0)"  id="exceptionLock" class="easyui-linkbutton" iconCls="icon-setExceptionLock" plain="true">异常卡锁定</a>
	    <a href="javascript:void(0)"  id="exceptionUnLock" class="easyui-linkbutton" iconCls="icon-setExceptionUnLock" plain="true">解锁</a>
	    <a href="javascript:void(0)"  id="refresh" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
	 </div>
	
  </body>
  
<script>

	

$(function(){
	var gridDom = $('#callCard');	
	gridDom.datagrid({
		height:$('#body').height()-$('#search').height(),
		width:$('#body').width(),
		idField:'id',
		url:'<%=path%>/callcard/loadData',  
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
			{field:'userAcc',title:'账号',width:100,halign:'center', align:'left'},
			{field:'cardNoLogical',title:'逻辑卡号',width:100,halign:'center', align:'left'},
			{field:'cardNoPhysical',title:'物理卡号',width:100,halign:'center', align:'center'},
			{field:'cardStatusDesc',title:'卡状态描述',width:200,halign:'center', align:'center'},
			{field:'createTime',title:'创建时间',width:100,halign:'center', align:'center'},
			{field:'isDisabled',title:'是否禁用',width:100,halign:'center', align:'center',formatter:formatStatus},
			{field:'disabledTime',title:'禁用时间',width:100,halign:'center', align:'center'},
			{field:'isDeleted',title:'是否销卡',width:100,halign:'center', align:'center',formatter:formatStatus},
			{field:'deletedTime',title:'销卡时间',width:100,halign:'center', align:'center'}
		]],
		toolbar:'#toolbar',  
        pagination:true,
        pageSize:30,
	});

	//挂失换卡
	$("#loss").on("click", function(){
		var r = $('#callCard').datagrid('getSelected');
		editCardStatus({
			action: 'callcard/setCardLoss',
			id: {'callCard.cardNoLogical' : r.cardNoLogical}
		});	
	});
	
	//损坏换卡
	$("#damaged").on("click", function(){
		var r = $('#callCard').datagrid('getSelected');
		editCardStatus({
			action: 'callcard/setDamaged',
			id: {'callCard.cardNoLogical' : r.cardNoLogical}
		});	
	});
	
	//退货
	$("#returnCard").on("click", function(){
		var r = $('#callCard').datagrid('getSelected');
		editCardStatus({
			action: 'callcard/setReturnCard',
			id: {'callCard.cardNoLogical' : r.cardNoLogical}
		});	
	});
	
	//异常卡锁定
	$("#exceptionLock").on("click", function(){
		var r = $('#callCard').datagrid('getSelected');
		editCardStatus({
			action: 'callcard/setExceptionLock',
			id: {'callCard.cardNoLogical' : r.cardNoLogical}
		});	
	});
	
	//解锁
	$("#exceptionUnLock").on("click", function(){
		var r = $('#callCard').datagrid('getSelected');
		editCardStatus({
			action: 'callcard/setExceptionUnLock',
			id: {'callCard.cardNoLogical' : r.cardNoLogical}
		});	
	});
	
	//刷新
	$("#refresh").on("click",function(){
		gridDom.datagrid('reload');
	});
	
});

function formatStatus(val,row){
	if (val == 1){
		return '是';
	} else {
		return '否';
	}
}

function search(){
	$('#users').datagrid('load',{
		param_cnname: $.trim($('#param_cnname').val()),
	});
}

function editCardStatus(opts){
	$.ajax({
		url: opts.action,
		data: opts.id, //{key:valule} user.name : 12323
		success: function(data){
		console.log(data)
			if(data.result.state =='0'){
				//reload
				alert('操作成功')
				$('#callCard').datagrid('reload');
			}else{
				alert(data.result.info)
			}
		},
		error: function(){		
		}
	});
}
</script>

</html>
