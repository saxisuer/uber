<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form action="${pageContext.request.contextPath}/user/save" method="post">
    <input type="hidden" name="id" value="${user.id}"/>
    <div class="user-form">
        <h2>编辑用户</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>账号：</label>
                    <div class="textbox">
                        <input class="textbox-text validatebox-text textbox-prompt" name="name" value="${user.name}"/>
                    </div>
                </div>
                <div class="formgourp">
                    <label>姓名：</label>
                    <div class="textbox">
                        <input class="textbox-text validatebox-text textbox-prompt" name="cnname" value="${user.cnname}"/>
                    </div>
                </div>
                <div class="formgourp hide">
                    <label>密码：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" type="password" name="password"
                                                value="${user.password}"/>
                    </div>
                </div>
                <div class="formgourp">
                    <label>状态：</label>
                    <div class="textbox">
                        <select class="easyui-combobox" name="status" data-options="required:true">
                            <option value="1" <c:if test="${user.status == 1}">selected="selected"</c:if>>启用</option>
                            <option value="0" <c:if test="${user.status == 0}">selected="selected"</c:if>>禁用</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>电话：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="phone"
                                                value="${user.phone}"/>
                    </div>
                </div>
                <div class="formgourp">
                    <label>邮箱：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="email"
                                                value="${user.email}"/>
                    </div>
                </div>


                <div class="formgourp">
                    <label for="cityCodeCombox">所属城市</label>
                    <div class="textbox">
                        <input id="cityCodeCombox" class="easyui-combobox" name="cityCode" data-options="required:true" value="${user.cityCode}"/>
                    </div>
                </div>
            </div>
        </div>


        <input type="submit" value="保存" style="display: none;"/>
        <input id="roleList" type="hidden" value='${user.roleList}'/>
        <input id="roleIds" type="hidden" name="roleIds"/>
    </div>
</form>

<h3 class="user-h3">角色选择</h3>
<div class="zTreeDemoBackground left">
    <ul id="treeUser" class="ztree"></ul>
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

    var zNodes = eval('(' + $('#roleList').val() + ')');
    $(document).ready(function () {
        $.fn.zTree.init($("#treeUser"), setting, zNodes);

        $('#cityCodeCombox').combobox({
            url:'${pageContext.request.contextPath}/city/getCityListForCombo',
            method:'GET',
            valueField:'cityCode',
            textField:'cityNameCn'
        })
    });

    /*
     ** 获取ztree选择的ids
     */
    function setZtreeIds() {
        var treeObj = $.fn.zTree.getZTreeObj("treeUser"),
                nodes = treeObj.getCheckedNodes(true),
                idArr = [];
        $.each(nodes, function (index, el) {
            idArr.push(el.id);
        });
        $('#roleIds').val(idArr.join(","));
    }
    //-->
</SCRIPT>
</body>
</html>
