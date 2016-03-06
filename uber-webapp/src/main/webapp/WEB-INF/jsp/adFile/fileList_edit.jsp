<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" pageEncoding="utf-8" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery/filemanager.js"></script>
</head>

<body>
<form action="${pageContext.request.contextPath}/filelist/updateData" method="POST" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${fileList.id}"/>
    <input type="hidden" name="fileUUID" value="${fileList.fileUUID}"/>
    <input type="hidden" name="uniqueFileName" value="${fileList.uniqueFileName}"/>
    <input type="hidden" name="md5Check" value="${fileList.md5Check}"/>
    <input type="hidden" name="fileVersion" value="${fileList.fileVersion}"/>
    <input type="hidden" name="filePostfix" value="${fileList.filePostfix}"/>

    <div id="p" class="easyui-progressbar" data-options="value:60" style="width:400px;"></div>
    <div class="user-form">
        <h2>编辑广告</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>公司名称：</label>
                    <div class="textbox">
                        <input class="easyui-validatebox textbox-text" data-options="required:true,validType:['length[0,20]']"
                               name="company"
                               value="${fileList.company}"/>
                    </div>
                </div>

                <div class="formgourp">
                    <label>开播时间：</label>
                    <input id="date" class="easyui-datetimebox" name="startTime"
                           value='<fmt:formatDate value="${fileList.startTime}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>'
                           data-options="editable:false,required:true"/>
                </div>

                <div class="formgourp">
                    <label>文件名称：</label>
                    <input id="fileName" class="easyui-validatebox textbox-text" name="fileName" value="${fileList.fileName}"/>
                </div>
            </div>

            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>文件标题：</label>
                    <div class="textbox">
                        <input class="easyui-validatebox textbox-text" name="fileTitle" value="${fileList.fileTitle}"/>
                    </div>
                </div>

                <div class="formgourp">
                    <label>停播时间：</label>
                    <input id="date2" class="easyui-datetimebox" name="endTime"
                           data-options="editable:false,required:true"
                           value='<fmt:formatDate value="${fileList.endTime}"  pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>'/>
                </div>
                <div class="formgourp">
                    <label>广告级别：</label>
                    <%--<select class="easyui-combobox" name="fileLevel">--%>
                        <%--<option value="3" <c:if test="${fileList.fileLevel==3}">selected="selected"</c:if>>CITY</option>--%>
                        <%--<option value="2" <c:if test="${fileList.fileLevel==2}">selected="selected"</c:if>>GROUP</option>--%>
                        <%--<option value="1" <c:if test="${fileList.fileLevel==1}">selected="selected"</c:if>>CUSTOMER</option>--%>
                    <%--</select>--%>
                    <span><c:if test="${fileList.fileLevel==3}">CITY</c:if></span>
                    <span><c:if test="${fileList.fileLevel==2}">GROUP</c:if></span>
                    <span><c:if test="${fileList.fileLevel==1}">CUSTOMER</c:if></span>
                </div>
            </div>
            <div class="yui3-u-1-2">

            </div>
        </div>
        <input type="button" value="保存" style="display: none;"/>
        <div class="user-form">
            <div class="yui3-g">
                <div class="yui3-u-1">
                    <div class="formgourp">
                        <label>
                            <span style="color: RED; ">*</span>文件上传：
                        </label>
                        <input id="fileField" name="file" type="text" style="width:200px"/>
                        <br>
                        <br>
                        <div id="prosbar" class="easyui-progressbar" style="width: 520px;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<SCRIPT type="text/javascript">
    dataFormated();
    $('#date').datetimebox();
    $('#date2').datetimebox();
    $('#downBtn').linkbutton();
    $('#upBtn').linkbutton();
    $('#fileField').filebox({
        buttonText: '选择文件',
        buttonAlign: 'right'
    });

    /*
     ** 获取ztree选择的ids
     */
    function setZtreeIds() {
        //为空
    }

    $('#import').on("change", function (e) {
        var src = e.target || window.event.srcElement;
        var filename = src.value;
        $('#fileName').val(filename.substring(filename.lastIndexOf('\\') + 1));
    });
    //-->
</SCRIPT>
</body>
</html>
