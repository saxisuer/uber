<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="${pageContext.request.contextPath}">

    <title>城市管理</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>

<body>
<form action="${pageContext.request.contextPath}/city/save" method="post">
    <input name="id" value="${city.id}" type="hidden"/>
    <div class="user-form">
        <h2>新增城市</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>城市发音：</label>
                    <div class="textbox">
                        <input class="textbox-text validatebox-text textbox-prompt" name="cityName"/>
                    </div>
                </div>
                <div class="formgourp">
                    <label>城市名称：</label>
                    <div class="textbox">
                        <input class="textbox-text validatebox-text textbox-prompt" name="cityNameCn"/>
                    </div>
                </div>
                <div class="formgourp">
                    <label>城市代码：</label>
                    <div class="textbox">
                        <input id="pwd" class="textbox-text validatebox-text textbox-prompt" name="cityCode"/>
                    </div>
                </div>
            </div>
        </div>


        <input type="submit" value="保存" style="display: none;"/>
    </div>
</form>
<script type="text/javascript">
    $(document).ready(function () {
    });

    /*
     ** 获取ztree选择的ids
     */
    function setZtreeIds() {
    }
</script>
</body>
</html>
