<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="${pageContext.request.contextPath}">

    <title>设备组管理</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<form action="${pageContext.request.contextPath}/deviceGroup/save" method="post">
    <input name="id" value="${deviceGroup.id}" type="hidden"/>
    <div class="user-form">
        <h2>修改设备组</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>设备名称：</label>
                    <div class="textbox">
                        <input id="groupName" class="textbox-text easyui-validatebox textbox-prompt" value="${deviceGroup.groupName}"
                               name="groupName"/>
                    </div>
                </div>
                <div class="formgourp">
                    <label>设备备注：</label>
                    <div class="textbox">
                        <input id='groupDesc' class="textbox-text validatebox-text textbox-prompt" value="${deviceGroup.groupDesc}" name="groupDesc"/>
                    </div>
                </div>
                <div class="formgourp">
                    <label>所属城市：</label>
                    <div class="textbox">
                        <input id="cityCode" name="cityCode" value="${deviceGroup.cityCode}"/>
                    </div>
                </div>
            </div>
        </div>
        <input type="submit" value="保存" style="display: none;"/>
    </div>
</form>

<script type="text/javascript">
    $(document).ready(function () {
        $('#groupName').validatebox({
            required: true
        });
        $('#groupDesc').validatebox({
            required: true
        });
        $('#cityCode').combobox({
            url: '${pageContext.request.contextPath}/city/getCityListForCombo?type=1',
            method: 'GET',
            required: true,
            valueField: 'cityCode',
            textField: 'cityNameCn',
            disabled: true
        });
    });
    /*
     ** 获取ztree选择的ids
     */
    function setZtreeIds() {
    }
</script>
</body>
</html>
