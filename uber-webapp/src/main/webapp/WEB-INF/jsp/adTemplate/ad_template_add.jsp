<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="${pageContext.request.contextPath}">
    <title>广告组管理</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <style type="text/css">
        .o-display-role-list {
            padding-left: 2px;
        }

        .o-display-role-list li {
            display: inline-block;
            text-align: left;
            padding: 2px 5px;
            margin: 2px;
            border: 1px solid #ddd;;
            border-radius: .4em;
            background-color: #DEE7F8
        }

        .o-display-remove {
            cursor: pointer;
            color: red;
            position: relative;
            left: -3px;
            top: -5px;
        }
    </style>
</head>

<body>
<form action="${pageContext.request.contextPath}/adTemplate/save" method="post">
    <input name="id" value="${adTemplate.id}" type="hidden"/>
    <input id="adFileIds" name="adFileIds" value="${adTemplate.adFileIds}" type="hidden"/>
    <div class="user-form">
        <h2>新增广告组</h2>
        <div class="yui3-g">
            <div class="yui3-u-1-2">
                <div class="formgourp">
                    <label>广告组名：</label>
                    <div class="textbox">
                        <input class="textbox-text validatebox-text textbox-prompt" name="templateName"/>
                    </div>
                </div>
                <div class="formgourp">
                    <label>广告组描述：</label>
                    <div class="textbox">
                        <input class="textbox-text validatebox-text textbox-prompt" name="templateDesc"/>
                    </div>
                </div>
                <div class="formgourp">
                    <label>广告列表：</label>
                    <div class="textbox">
                        <a id="selectFile" href="javascript:void(0)" onclick="openGrid()">选取广告</a>
                    </div>
                </div>
                <div class="formgourp">
                    <div>
                        <div id="showSelect" style="width: 550px;overflow:auto;max-height: 200px;max-width:650px;white-space: normal;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <input type="submit" value="保存" style="display: none;"/>
    </div>
</form>


<div id="fileList">
    <!-- 数据表格区域 -->
    <table id="fileListGrid" style="table-layout:fixed;"></table>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#fileList').dialog({
            close: true,
            title: '广告列表',
            closed: true,
            modal: true,
            width: 550,
            minimizable: true,
            maximizable: true,
            collapsible: true,
            draggable: true,
            resizable: true,
            buttons: [{
                text: '绑定',
                iconCls: 'icon-save',
                handler: function () {
                    appendSelect();
                    $('#fileList').dialog('close');
                }
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#fileList').dialog('close');
                }
            }]
        });

        $('#selectFile').linkbutton({
            iconCls: 'icon-search',
            plain: true
        });
        $('#fileListGrid').datagrid({
            method: 'GET',
            //idField: 'id',
            url: '${pageContext.request.contextPath}/filelist/loadData',
            selectOnCheck: true,
            checkOnSelect: true,
            nowrap: true,
            fitColumns: true,
            rownumbers: true,
            showPageList: false,
            singleSelect: false,
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'company', title: '公司名称', width: 100, halign: 'center', align: 'left'},
                {field: 'fileTitle', title: '文件标题', width: 100, halign: 'center', align: 'left'}
            ]],
            pagination: true,
            pageSize: 10
        })
    });

    function openGrid() {
        $('#fileList').dialog('open');
    }
    function appendSelect() {
        var rows = $('#fileListGrid').datagrid('getSelections');
        if (rows.length == 0) {
            $.messager.alert('提示', '至少需要选中一个广告');
            return;
        }
        var $adFileIds = $('#adFileIds');
        var fileList = $adFileIds.val() != "" ? $adFileIds.val().split(",") : [];
        var ul;
        if ($('#showSelect').find('ul').length == 0) {
            ul = $("<ul></ul>").addClass("o-display-role-list")
        } else {
            ul = $('#showSelect').find('ul');
        }
        for (var i in rows) {
            var flag = true;
            for (var j in fileList) {
                if (rows[i].id == fileList[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                $("<li>" + rows[i].fileTitle + "</li>").attr("fileId", rows[i].id).prepend($("<span>x</span>").addClass("o-display-remove").bind("click", function (e) {
                    $(this).parent().remove();
                    var temp = $adFileIds.val().split(",");
                    for (var i in temp) {
                        if (temp[i] == $(this).parent().attr("fileId")) {
                            temp.splice(i, 1);
                        }
                    }
                    $adFileIds.val(temp.join(","));
                })).appendTo(ul);
                fileList.push(rows[i].id);
            }
        }
        $adFileIds.val(fileList.join(","));
        ul.appendTo($('#showSelect'));
    }

    /*
     ** 获取ztree选择的ids
     */
    function setZtreeIds() {
    }
</script>
</body>
</html>
