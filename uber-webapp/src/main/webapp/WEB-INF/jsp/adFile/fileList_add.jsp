<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="s" uri="/struts-tags"%>	

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
	<s:form namespace="fileListAction" action="/filelist/save" method="post" theme="simple">
	<s:token />
	<s:hidden name="fileList.id" value="%{fileList.id}" />
	<s:hidden name="fileList.fileUUID" value="%{fileList.fileUUID}" />
	<s:hidden name="fileList.uniqueFileName" value="%{fileList.uniqueFileName}" />
	<s:hidden name="fileList.md5Check" value="%{fileList.md5Check}" />
	<s:hidden name="fileList.fileVersion" value="%{fileList.fileVersion}" />
	
	<div class="user-form">
		<h2>新增广告</h2>
		<div class="yui3-g">
			<div class="yui3-u-1-2">
				<div class="formgourp">
					<label>公司名称：</label>
					<div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="fileList.company" value="<s:property value="fileList.company"/>"></div>
				</div>
				
				<div class="formgourp">
					<label>开播时间：</label>
					<input id="date" class="easyui-datebox" name="fileList.startTime" value="<s:property value="fileList.startTime"/>">
				</div>
				
				<div class="formgourp">
					<input id="import" type="file" name="file">  
				</div>
			</div>
			
			<div class="yui3-u-1-2">
				<div class="formgourp">
					<label>文件标题：</label>
					<div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="fileList.fileTitle" value="<s:property value="fileList.fileTitle"/>"></div>
				</div>
				
				<div class="formgourp">
					<label>停播时间：</label>
					<input id="date2" class="easyui-datebox" name="fileList.endTime" value="<s:property value="fileList.endTime"/>">
				</div>
				
				<div class="formgourp">
					<label>文件名称：</label>
					<input id="fileName" class="easyui-datebox" name="fileList.fileName" value="<s:property value="fileList.fileName"/>">
				</div>
			</div>
		</div>
		<!-- 
		<div  class="formgourp">
			<label>备注：</label>
			<textarea name="fileList.note" value="<s:property value="fileList.note"/>"  style="width:100%; height:60px;" />				
		</div>
	     -->
	    <s:submit value="保存" style="display: none;"/>
	    	            <s:actionmessage />
						<s:actionerror />
						
						
	</div>
	</s:form>
	<!-- 
	<iframe id='target_upload' name='target_upload' src=''
			style='display: none'></iframe>
   <form action="/filelist/fileUpload" id="uploadForm" enctype="multipart/form-data" method="post" target="target_upload">
   <input type="file" name="upload"> <input type="button" id="subButton" value="上传">
   </form>
   <div id="progress">
   <div id="title"><span id="text">上传进度</span><div id="close">X</div></div>
   <div id="progressBar">
   	<div id="uploaded"></div>
   	</div>
   	<div id="info"></div>
   </div>
    -->	  
	
	<SCRIPT type="text/javascript">
		dataFormated();
		$('#date').datetimebox();
		$('#date2').datetimebox();
		
		$('#import').on("change",function(e){
			var src=e.target || window.event.srcElement;
			var filename=src.value;
			$('#fileName').val(filename.substring( filename.lastIndexOf('\\')+1 ));
		});
		
		//上传
	//	$('#uploadBtn').on('click', function() {  
    //    $.ajaxFileUpload({  
    //        url:'filelist/fileUpload',  
    //        secureuri:false,  
    //        fileElementId:'fileId',//file标签的id  
    //        dataType: 'text',//返回数据的类型  
    //        success: function (data, status) {  
    //        	alert("111");  
    //        },  
    //        error: function (data, status, e) {  
    //           alert(e);  
    //        }  
    //    });  
    //	}); 
		
		//-->
	</SCRIPT>
  </body>
</html>
