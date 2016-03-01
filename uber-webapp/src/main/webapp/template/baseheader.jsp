<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<c:set var="publicpath" value="${path}/public"></c:set>
<c:set var="basePath" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${path}/"></c:set>
<%
    String path = request.getContextPath();
    String publicpath = request.getContextPath() + "/public";
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<link rel="icon" type="image/png" href="${ctx}/public/uber/badges.png"/>
<link rel="stylesheet" type="text/css" href="${ctx}/public/easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/easyui/themes/zTreeStyle/zTreeStyle.css">
<link rel="stylesheet" type="text/css" href="${ctx}/public/easyui/themes/style.css">
<script type="text/javascript" src="${ctx}/public/jquery/jquery.min.js"></script>
<%--<script type="text/javascript" src="${ctx}/public/jquery/filemanager.js"></script>--%>
<script type="text/javascript" src="${ctx}/public/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/public/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/public/jquery/jquery.ztree.min.js"></script>
<script type="text/javascript" src="${ctx}/public/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/public/uber/common.js"></script>

<script type="text/javascript">
    var sy = sy || {};
    var ctx = '${pageContext.request.contextPath}'
</script>