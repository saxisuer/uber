<%@ page language="java" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
<s:form namespace="role" action="/role/save" method="post" theme="simple">
    <s:token/>
    <s:hidden name="role.id" value="%{role.id}"/>
    <div class="user-form">
        <h2>新增角色</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>名称：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name=" role.name"
                                                value="<s:property value="role.name"/>"></div>
                </div>
            </div>
        </div>


        <s:submit value="保存" style="display: none;"/>
        <s:actionmessage/>
        <s:actionerror/>
        <input id="permissionList" type="hidden" value="<s:property value="role.permissionList"/>">
        <input id="permissionIds" type="hidden" name="role.permissionIds">
    </div>
</s:form>

<h3 class="user-h3">资源选择</h3>
<div class="zTreeDemoBackground left">
    <ul id="treePermission" class="ztree"></ul>
</div>
<SCRIPT type="text/javascript">
    <!--
    var setting = {
        check: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };

    var zNodes = eval('(' + $('#permissionList').val() + ')');
    $(document).ready(function () {
        $.fn.zTree.init($("#treePermission"), setting, zNodes);
    });

    /*
     ** 获取ztree选择的ids
     */
    function setZtreeIds() {
        var treeObj = $.fn.zTree.getZTreeObj("treePermission"),
                nodes = treeObj.getCheckedNodes(true),
                idArr = [];
        $.each(nodes, function (index, el) {
            idArr.push(el.id);
        });
        $('#permissionIds').val(idArr.join(","));
    }
    //-->
</SCRIPT>
</body>
</html>
