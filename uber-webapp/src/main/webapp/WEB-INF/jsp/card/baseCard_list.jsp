<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%@include file="/template/baseheader.jsp"%>
	<base href="<%=basePath%>">
    
    <title>库存列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body id="body">
  	<!-- 数据表格区域 -->
    <table id="baseCard" style="table-layout:fixed;">   
    </table> 
    <!-- 表格顶部工具栏 -->
	<div id="toolbar">
		<form class="import-form" namespace="/baseCardAction" action="baseCardfileUpload" method="post" enctype="multipart/form-data">
			<div class="import-box">
				<a href="javascript:void(0)"  id="add" class="easyui-linkbutton" iconCls="icon-add" plain="true">导入</a>
				<input id="import" type="file" name="file">
			</div>  
		    <a href="javascript:void(0)"  id="delete" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		    <a href="javascript:void(0)"  id="refresh" class="easyui-linkbutton" iconCls="icon-reload" plain="true">刷新</a>
	    </form>
	 </div>		
	
	<!-- <form namespace="/baseCardAction" action="baseCardfileUpload" method="post" enctype="multipart/form-data">
    　　
        file: <input type="file" name="file"><br>
        
        <input type="submit" value="submit">
    </form> -->
	
  </body>
  
<script>

	

$(function(){
	var gridDom = $('#baseCard');
	gridDom.datagrid({
		height:$('#body').height()-$('#search').height(),
		width:$('#body').width(),
		idField:'id',
		url:'<%=path%>/basecard/loadData',  
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
			{field:'cardNoLogical',title:'逻辑卡号',width:100,halign:'center', align:'left'},
			{field:'cardNoPhysical',title:'物理卡号',width:100,halign:'center', align:'left'},
			{field:'isLocked',title:'是否锁定',width:100,halign:'center', align:'left',formatter:formatStatus},
			{field:'lockedTime',title:'锁定时间',width:100,halign:'center', align:'left'},
			{field:'lockedByWho',title:'锁定者',width:100,halign:'center', align:'center'},
			{field:'isIssued',title:'是否已发行',width:100,halign:'center', align:'center',formatter:formatStatus},
			{field:'issueTime',title:'发行时间',width:100,halign:'center', align:'center'},
			{field:'issuedByWho',title:'发行人员',width:100,halign:'center', align:'center'},
			{field:'createTime',title:'创建时间',width:100,halign:'center', align:'center'},
			{field:'createByWho',title:'创建人员',width:100,halign:'center', align:'center'},
		]],
		toolbar:'#toolbar',  
        pagination:true,
        pageSize:30,
	});
	
	//上传
	$('#import').on("change",function(e){
		//校验文件
		var $this = $(this),
			file = $this.val(),
			fileExt=file.replace(/.+\./,"");

		if( (fileExt=='xls')||(fileExt=='xlsx') ){
			//提交表单                  
			var $form = $this.closest('form');
			$.messager.progress({
				title: '上传提示',
				text: '正在导入,请稍候...'
			});
			$form.ajaxSubmit({ 
				success:function(data){

			    	if(data.result=="SUCCESS"){
			    		gridDom.datagrid('reload');
			    		 $.messager.show({
			                title:'提示',
			                msg:'导入成功！',
			                timeout:3000,
			                showType:'slide'
			            });
			    	}else if(data.result=="500"){
			    		alert('可能重复上传或服务器导入失败！');
			    	}else{

			    	}
			    	$.messager.progress('close');
			    	$this.val('');
			    }
			});			
		}else{
			alert('请选择excel文件！');
			return;
		}
					
	});

	//删除
	$("#delete").on("click", function(){
		var r = $('#baseCard').datagrid('getSelected');
		
		$.messager.confirm('提醒', '您确定要删除吗？', function(e){
			if (e){
				deleteBaseCard({
					action: 'basecard/delete',
					id: {'baseCard.id' : r.id}
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
		return '是';
	} else {
		return '否';
	}
}

function deleteBaseCard(opts){
	$.ajax({
		url: opts.action,
		data: opts.id, //{key:valule} user.name : 12323
		success: function(data){
		console.log(data)
			if(data.result=="SUCCESS"){
				$('#baseCard').datagrid('reload');
				$('#baseCard').datagrid('clearSelections');
			}else{
				alert(data.result)
			}
		},
		error: function(){		
		}
	});
}

function search(){
	$('#users').datagrid('load',{
		param_cnname: $.trim($('#param_cnname').val()),
	});
}
</script>

</html>
