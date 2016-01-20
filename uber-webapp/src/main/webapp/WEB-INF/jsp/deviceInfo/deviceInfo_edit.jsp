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
    
    <title>设备信息</title>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
	<s:form namespace="/deviceInfo" action="/deviceinfo/save" method="post" theme="simple">
	<s:token />
	<s:hidden name="deviceInfo.id" value="%{deviceInfo.id}" />
	<div class="user-form">
		<h2>新增设备</h2>
		<div class="yui3-g">
			<div class="yui3-u-1-2">
				<div class="formgourp">
					<label>设备ID：</label>
					<div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="deviceInfo.deviceUUID" value="<s:property value="deviceInfo.deviceUUID"/>"></div>
				</div>
				
				<div class="formgourp">
					<label>市：</label>
					<div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="deviceInfo.city" value="<s:property value="deviceInfo.city"/>"></div>
				</div>
				
				<div class="formgourp">
					<label>纬度：</label>
					<div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="deviceInfo.latitude" value="<s:property value="deviceInfo.latitude"/>"></div>
				</div>
				
				<div class="formgourp"> 
					<label>注册日期：</label>
					<div  class="textbox">
					<input id="date" class="easyui-datebox" name="deviceInfo.installationDate" value="<s:property value="deviceInfo.installationDate"/>">
					<input id="dateStr" type="hidden" value="<s:property value="deviceInfo.tempDate"/>">
					</div>
				</div>
				
			</div>
			<div class="yui3-u-1-2">
				<div class="formgourp">
					<label>省：</label>
					<div  class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="deviceInfo.province" value="<s:property value="deviceInfo.province"/>"></div>
				</div>
				<div class="formgourp">
					<label>地址：</label>
					<div  class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="deviceInfo.address" value="<s:property value="deviceInfo.address"/>"></div>
				</div>
				<div class="formgourp">
					<label>经度：</label>
					<div  class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="deviceInfo.longtitude" value="<s:property value="deviceInfo.longtitude"/>"></div>
				</div>
				<div class="formgourp">
					<label>状态：</label>
					<!-- <div  class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="deviceInfo.state" value="<s:property value="deviceInfo.state"/>"></div> -->
					<div class="textbox">
						<select class="easyui-combobox" name="deviceInfo.state" data-options="required:true">
						    <option value="1" <s:if test="deviceInfo.state==1">selected="selected"</s:if>>启用</option>
							<option value="0" <s:if test="deviceInfo.state==0">selected="selected"</s:if>>禁用</option>
						</select>
					</div>
				</div>
			</div>
		</div>

	    
	    <s:submit value="保存" style="display: none;"/>
	    	            <s:actionmessage />
						<s:actionerror />
	</div>
	</s:form>
	

	<SCRIPT type="text/javascript">
		$('#date').datebox();
		var dateStr = $('#dateStr').val();
		$('#date').datebox('setValue', dateStr);
		/*
		** 获取ztree选择的ids
		*/
		function setZtreeIds() {
			//为空
		}
		//-->
	</SCRIPT>
  </body>
</html>
