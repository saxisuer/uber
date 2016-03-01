<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="${pageContext.request.contextPath}">
</head>

<body>
<form action="${pageContext.request.contextPath}/filelist/updateData" method="POST" enctype="multipart/form-data">
    <input type="text" name="id" value="${fileList.id}"/>
    <input type="text" name="fileUUID" value="${fileList.fileUUID}"/>
    <input type="text" name="uniqueFileName" value="${fileList.uniqueFileName}"/>
    <input type="text" name="md5Check" value="${fileList.md5Check}"/>
    <input type="text" name="fileVersion" value="${fileList.fileVersion}"/>

    <div id="p" class="easyui-progressbar" data-options="value:60" style="width:400px;"></div>
    <div class="user-form">
        <h2>新增广告</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>公司名称：</label>
                    <div class="textbox"><input class="easyui-validatebox textbox-text" data-options="required:true,validType:['length[0,20]']"
                                                name="company" value="${fileList.company}"/></div>
                </div>

                <div class="formgourp">
                    <label>开播时间：</label>
                    <input id="date" class="easyui-datebox" name="startTime" value="${fileList.startTime}"
                           data-options="editable:false,required:true"/>
                </div>

            </div>

            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>文件标题：</label>
                    <div class="textbox"><input class="easyui-validatebox textbox-text" name="fileTitle"
                                                value="${fileList.fileTitle}"/></div>
                </div>

                <div class="formgourp">
                    <label>停播时间：</label>
                    <input id="date2" class="easyui-datebox" name="endTime" value="${fileList.endTime}" data-options="editable:false,required:true"/>
                </div>
            </div>
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>广告级别:</label>
                        <select class="easyui-combobox" name="fileLevel" data-options="required:true">
                            <option value="3" <c:if test="fileList.fileLevel==3">selected="selected"</c:if>>CITY</option>
                            <option value="2" <c:if test="fileList.fileLevel==2">selected="selected"</c:if>>GROUP</option>
                            <option value="1" <c:if test="fileList.fileLevel==1">selected="selected"</c:if>>CUSTOMER</option>
                        </select>
                </div>
            </div>
        </div>

        <input type="submit" value="保存" style="display: none;"/>
    </div>

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
            <div class="yui3-u-1">
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    var ctx = "${pageContext.request.contextPath}";
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

</script>
</body>
</html>
