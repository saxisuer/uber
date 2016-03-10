<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="${pageContext.request.contextPath}">

    <title>设备信息</title>

</head>

<body>
<form action="${pageContext.request.contextPath}/deviceinfo/save" method="post">
    <input type="hidden" name="id"/>
    <div class="user-form">
        <h2>新增设备</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>设备ID：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="deviceUUID"
                    /></div>
                </div>
                <div class="formgourp">
                    <label>所属设备组：</label>
                    <div class="textbox">
                        <input id="deviceGroupId" name="deviceGroupId"/></div>
                </div>


                <div class="formgourp">
                    <label>纬度：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="latitude"
                    /></div>
                </div>

                <div class="formgourp">
                    <label>安装日期：</label>
                    <div class="textbox">
                        <input id="date" class="easyui-datebox" name="installationDate"/>
                    </div>
                </div>

            </div>
            <div class="yui3-u-1-2">
                <%--<div class="formgourp">--%>
                <%--<label>省：</label>--%>
                <%--<div class="textbox">--%>
                <%--<input class="textbox-text validatebox-text textbox-prompt" name="province"/></div>--%>
                <%--</div>--%>
                <div class="formgourp">
                    <label>市：</label>
                    <div class="textbox">
                        <input id="cityCodeCombox" name="cityCode" data-options="required:true"/>
                    </div>
                </div>
                <div class="formgourp">
                    <label>地址：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="address"
                    /></div>
                </div>
                <div class="formgourp">
                    <label>经度：</label>
                    <div class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="longtitude"
                    /></div>
                </div>
                <div class="formgourp">
                    <label>状态：</label>
                    <!-- <div  class="textbox"><input class="textbox-text validatebox-text textbox-prompt" name="deviceInfo.state" value="<s:property value="deviceInfo.state"/>"></div> -->
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

<script type="text/javascript">
    $(function () {
        $('#cityCodeCombox').combobox({
            url: '${pageContext.request.contextPath}/city/getCityListForCombo?type=1',
            method: 'GET',
            valueField: 'cityCode',
            editable: false,
            textField: 'cityNameCn',
            onSelect: function (record) {
                $('#deviceGroupId').combobox('clear');
                $('#deviceGroupId').combobox('reload', '${pageContext.request.contextPath}/deviceGroup/getCombobo?cityCode=' +
                        $('#cityCodeCombox').combobox('getValue'));
            }
        });

        $('#deviceGroupId').combobox({
            url: '${pageContext.request.contextPath}/deviceGroup/getCombobo',
            method: 'GET',
            required: true,
            valueField: 'id',
            editable: false,
            textField: 'groupName',
            onSelect: function (record) {
                $('#cityCodeCombox').combobox('setValue', record.cityCode);
            }
        })
    });


    dataFormated();
    $('#date').datebox();
    var date = new Date().Format('yyyy-MM-dd');
    $('#date').datebox('setValue', date);
    /*
     ** 获取ztree选择的ids
     */
    function setZtreeIds() {
        //为空
    }
    //-->
</script>
</body>
</html>
