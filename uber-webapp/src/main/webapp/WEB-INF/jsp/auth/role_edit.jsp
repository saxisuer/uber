<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="${pageContext.request.contextPath}">

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
<form action="${pageContext.request.contextPath}/role/save" method="post">
    <input type="hidden" name="id" value="${role.id}"/>
    <div class="user-form">
        <h2>编辑角色</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>名称：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="name"
                                                value="${role.name}"></div>
                </div>
            </div>
        </div>
        <input type="submit" value="保存" style="display: none;"/>
        <input id="permissionList" type="hidden" value='${role.permissionList}'>
        <input id="permissionIds" type="hidden" name="permissionIds">
    </div>
</form>

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
                enable: true,
                pIdKey: "pid",
                rootPId: 0
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
