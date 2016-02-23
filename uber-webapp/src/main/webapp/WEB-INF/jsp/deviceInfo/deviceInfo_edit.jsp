<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="${pageContext.request.contextPath}">

    <title>设备信息</title>

    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
<form action="${pageContext.request.contextPath}/deviceinfo/save" method="POST">
    <input type="hidden" name="id" value="${deviceInfo.id}"/>
    <div class="user-form">
        <h2>新增设备</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>设备ID：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="deviceUUID"
                                                value="${deviceInfo.deviceUUID}"/></div>
                </div>

                <div class="formgourp">
                    <label>市：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="city"
                                                value="${deviceInfo.city}"/></div>
                </div>

                <div class="formgourp">
                    <label>纬度：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="latitude"
                                                value="${deviceInfo.latitude}"></div>
                </div>

                <div class="formgourp">
                    <label>注册日期：</label>
                    <div class="textbox">
                        <input id="date" class="easyui-datebox" name="installationDate"/>
                        <input id="dateStr" type="hidden" value="${deviceInfo.tempDate}">
                    </div>
                </div>

            </div>
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>省：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="province"
                                                value="${deviceInfo.province}"/></div>
                </div>
                <div class="formgourp">
                    <label>地址：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="address"
                                                value="${deviceInfo.address}"/></div>
                </div>
                <div class="formgourp">
                    <label>经度：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="longtitude"
                                                value="${deviceInfo.longtitude}"/></div>
                </div>
                <div class="formgourp">
                    <label>状态：</label>
                    <div class="textbox">
                        <select class="easyui-combobox" name="state" data-options="required:true">
                            <option value="1" <c:if test="deviceInfo.state==1">selected="selected"</c:if>>启用</option>
                            <option value="0" <c:if test="deviceInfo.state==0">selected="selected"</c:if>>禁用</option>
                        </select>
                    </div>
                </div>
            </div>
        </div>


        <input type="submit" value="保存" style="display: none;"/>
    </div>
</form>


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
