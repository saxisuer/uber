<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <base href="${basePath}">

    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="">
    <meta http-equiv="description" content="Uber Family Application">

    <link rel="stylesheet" type="text/css" href="${path}/public/uber/login.css">
</head>

<body>
<div class="login-box">
    <img src="${ctx}/public/uber/logo.png" alt="">
    <form id="login_form" action="${ctx}/auth/login" method="post">
        <div class="input-group">
            <label for="username">账户</label>
            <input id="username" type="text" placeholder="账户" name="name">
        </div>
        <div class="input-group">
            <label for="password">密码</label>
            <input id="password" type="password" placeholder="密码" name="password">
        </div>
        <button type="submit" class="login-btn">登录</button>
    </form>
</div>

</body>
</html>
