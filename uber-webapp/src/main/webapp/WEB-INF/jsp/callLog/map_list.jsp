<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="/template/baseheader.jsp"%>
<base href="<%=basePath%>">  
<title>Hello, World</title>  
<style type="text/css">  
html{height:100%}  
body{height:100%;margin:0px;padding:0px}  
#container{height:100%}  
</style>  
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=hhobTXAvjT8OnEoPpkrMfLzp">
//v2.0版本的引用方式：src="http://api.map.baidu.com/api?v=2.0&ak=您的密钥"
//v1.4版本及以前版本的引用方式：src="http://api.map.baidu.com/api?v=1.4&key=您的密钥&callback=initialize"
</script>
</head>  
 
<!-- 弹窗模板 -->
<div id="wUpdate" class="easyui-window" title="叫车记录" data-options="modal:true,closed:true,iconCls:'icon-save',minimizable:false,maximizable:false,collapsible:false" style="width:600px;height:400px;padding:5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div id="wContainer" data-options="region:'center'" style="padding:10px;height:350px;">
			
		</div>
		<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
			<a id="wCancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" style="width:80px">关闭</a>
		</div>
	</div>
</div>
<!-- /弹窗模板 -->
	 
<body>  
<div id="container"></div> 
<script type="text/javascript"> 
var map = new BMap.Map("container");          // 创建地图实例  
var centerPoint = new BMap.Point(114.065944, 22.548554);  // 创建点坐标  
map.centerAndZoom(centerPoint, 15);                 // 初始化地图，设置中心点坐标和地图级别  
var markList = [];

$(function(){
	refresh();
});

function addMarker(point,uuid){
	var myIcon = new BMap.Icon("<%=path%>/public/easyui/themes/icons/PlayStore.png", new BMap.Size(40, 40));
	
	var marker = new BMap.Marker(point, {icon: myIcon});        // 创建标注    
	map.addOverlay(marker);                     // 将标注添加到地图中
	marker.id = uuid;
	markList.push(marker);
	marker.addEventListener("click", function(){ 
		document.cookie="tempuuid="+marker.id;
		dataLog({
			action: 'calllog/map_callLogList',
			success: function(){
				
			}
			});	   
		});
}

function refresh(){
	for(var dm in markList){
		map.removeOverlay(dm);    
		dm.dispose();
	}
	
	$.ajax({
		url: '<%=path%>/'+"deviceinfo/loadData",
		type: 'GET',
		success: function(data){
			for(var obj in data.rows)
			{
				var tempObj = data.rows[obj];
				var point = new BMap.Point(tempObj.latitude, tempObj.longtitude); 
				addMarker(point,tempObj.uuid);
			}	
		},
		error: function() {
			alert('加载出错！');
		}
	});
	
}

/*
 ** 弹窗叫车记录
 */
 function dataLog(opt){
 	var action = opt.action,
 		params = opt.params==undefined ? '' : opt.params;
 	$.ajax({
 		url: '<%=path%>/'+action,
 		type: 'GET',
 		data: params,
 		success: function(data){
 			if(data.result=="500"){alert('error:'+500)}
 			var dialog = $('#wUpdate');
 			dialog.find('#wContainer').html(data);
 			dialog.window('open');
 			$('#wCancel').on('click',function(){
 				dialog.window('close');
 			});
 		},
 		error: function() {
 			alert('加载出错！')
 		}
 	});	
 }

</script>  
</body>  
</html>