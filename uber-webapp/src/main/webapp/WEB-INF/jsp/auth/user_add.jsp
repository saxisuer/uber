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
<s:form namespace="/user" action="/user/save" method="post" theme="simple">
    <s:token/>
    <s:hidden name="user.id" value="%{user.id}"/>
    <div class="user-form">
        <h2>新增用户</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>账号：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="user.name"
                                                value="<s:property value="user.name"/>"></div>
                </div>
                <div class="formgourp">
                    <label>姓名：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="user.cnname"></div>
                </div>
                <div class="formgourp">
                    <label>密码：</label>
                    <div class="textbox"><input id="pwd" class="textbox-text validatebox-text textbox-prompt" type="password" name="user.password">
                    </div>
                </div>
                <div class="formgourp">
                    <label>状态：</label>
                    <div class="textbox">
                        <select class="easyui-combobox" name="user.status" data-options="required:true">
                            <option value="1" <s:if test="user.status==1">selected="selected"</s:if>>启用</option>
                            <option value="0" <s:if test="user.status==0">selected="selected"</s:if>>禁用</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>电话：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="user.phone"
                                                value="<s:property value="user.phone"/>"></div>
                </div>
                <div class="formgourp">
                    <label>邮箱：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="user.email"
                                                value="<s:property value="user.email"/>"></div>
                </div>
                <div class="formgourp">
                    <label>密码确认：</label>
                    <div class="textbox"><input id="confirmPwd" class="textbox-text validatebox-text textbox-prompt" type="password"></div>
                </div>
            </div>
        </div>


        <s:submit value="保存" style="display: none;"/>
        <s:actionmessage/>
        <s:actionerror/>
        <input id="roleList" type="hidden" value="<s:property value="user.roleList"/>">
        <input id="roleIds" type="hidden" name="user.roleIds">
    </div>
</s:form>

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

        $('#confirmPwd').blur(function () {
            if ($(this).val() != $('#pwd').val()) {
                alert('用户名和密码不一致！');
            }
        });
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
