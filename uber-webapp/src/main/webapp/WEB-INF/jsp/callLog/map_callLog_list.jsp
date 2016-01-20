<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>	

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>使用卡列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body id="body">
  
 	<!-- 数据表格区域 -->
    <table id="callLog" style="table-layout:fixed;">   
    </table>
  </body>
  
<script>

$(function(){
	var gridDom = $('#callLog');
	gridDom.datagrid({
		height:290,
		width:1200,
		idField:'id',
		url:'<%=path%>/calllog/loadDataByMap',  
		singleSelect:false,  
		selectOnCheck: true,
		checkOnSelect: true,
		nowrap:true,
		fitColumns:true,
		rownumbers:true,
		showPageList:false,
		singleSelect:true,
		columns:[[
			{field:'uuid',title:'设备标识码',width:220,halign:'center', align:'left'},
			{field:'userAcc',title:'帐号',width:100,halign:'center', align:'left'},
			{field:'cardNoPhisycal',title:'物理卡号',width:100,halign:'center', align:'left'},
			{field:'address',title:'地址',width:100,halign:'center', align:'left'},
			{field:'accessToken',title:'accessToken',width:100,halign:'center', align:'left'},
			{field:'productID',title:'productID',width:100,halign:'center', align:'left'},
			{field:'requestID',title:'requestID',width:100,halign:'center', align:'left'},
			{field:'state',title:'业务办理结果',width:100,halign:'center', align:'left'},
			{field:'errorInfo',title:'错误信息',width:100,halign:'center', align:'left'},
			{field:'callTime',title:'叫车时间',width:100,halign:'center', align:'left', formatter:formatDate},
			{field:'pickupTime',title:'司机到达时间',width:100,halign:'center', align:'left', formatter:formatDate},
		]],
		toolbar:'#toolbar',  
        pagination:true,
        pageSize:30,
	});
	
	$(document).ready(function(){ 
		gridDom.datagrid('resize');  
	}); 
	
	gridDom.datagrid({onLoadSuccess : function(data){
		gridDom.datagrid('resize');  
	}});
	
});

function formatDate(val,row){
	if(typeof(val)=="string"){
		return val.substring(0,10);
	}else{
		return val;
	}
}

</script>

</html>
