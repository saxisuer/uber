<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@include file="/template/baseheader.jsp" %>
    <title>Uber family后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/public/easyui/default.css">
    <script type="text/javascript" src="${ctx}/public/easyui/outlook.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/public/jquery/filemanager.js"></script>
</head>

<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<div region="north" split="true" border="false"
     style="overflow: hidden; height: 30px;
        background:  #08071c repeat-x center 50%;
        line-height: 18px;color: #fff; font-family: Tahoma,Verdana,微软雅黑,新宋体,serif;">
		<span style="float:right; padding-right:20px;" class="head">
		<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#sysMenus',iconCls:'icon-setting'">系统设置</a>
		</span><span
        style="padding-left:180px; padding-top:2px ; font-size: 16px; background: url(${ctx}/public/uber/logo2.png) no-repeat 2px 3px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
</div>
<div id="sysMenus" style="width:150px;">
    <div id="editpass" data-options="iconCls:'icon-password'">修改密码</div>
    <div id="loginOut" data-options="iconCls:'icon-logout'">安全退出</div>
</div>

<div region="south" split="true"
     style="height: 30px; background: #08071c; ">
    <div class="footer">
        <div style="float:right;">登录用户[${userinfo.cnname}]</div>
    </div>
</div>
<div region="west" split="true" title="菜单栏" style="width:180px;"
     id="west">
    <div class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->

    </div>

</div>
<div id="mainPanle" region="center"
     style="background: #eee; ">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="控制台" style="padding:5px; text-align:center; background-color: #08091A;" id="home">

            <img src="${ctx}/public/uber/banner.png">

        </div>
    </div>
</div>


<!--修改密码窗口-->
<div id="passDialog" class="easyui-dialog" title="修改密码" style="width:300px;height:200px;padding:10px;">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false"
             style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <table cellpadding=3>
                <tr>
                    <td>旧密码：</td>
                    <td><input id="txtOriPass" type="Password" class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>新密码：</td>
                    <td><input id="txtNewPass" type="Password" class="easyui-textbox"/></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input id="txtRePass" type="Password" class="easyui-textbox"/></td>
                </tr>
            </table>
        </div>
        <div region="south" border="false"
             style="text-align: right; height: 30px; line-height: 30px;">
            <a id="changePassBtn" class="easyui-linkbutton" icon="icon-ok"
               href="javascript:void(0)"> 确定</a>
            <a id="closePassDiagBtn" class="easyui-linkbutton"
               icon="icon-cancel" href="javascript:void(0)">取消</a>
        </div>
    </div>
</div>

<div id="mm" class="easyui-menu" style="width:150px;">
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseall">全部关闭</div>
    <div id="mm-tabcloseother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">关闭右侧全部</div>
    <div id="mm-tabcloseleft">关闭左侧全部</div>
    <div class="menu-sep"></div>
    <div id="mm-exit">收起菜单</div>
</div>

<!-- 弹窗模板 -->
<div id="wUpdate" class="easyui-window" title="更新数据"
     style="width:600px;height:400px;padding:5px;">
    <div class="easyui-layout" data-options="fit:true">
        <div id="wContainer" data-options="region:'center'" style="padding:10px;">

        </div>
        <div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0;">
            <a id="wOK" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" style="width:80px">保存</a>
            <a id="wCancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" style="width:80px">取消</a>
        </div>
    </div>
</div>
<!-- /弹窗模板 -->

<div id="loginMask" class="window-mask loading-mask" style="width: 100%; height: 100%; display: block; z-index: 9002;">
    <div class="tips">正在加载中...</div>
</div>
</body>
<script>

    var _menus = {};

    var editPassDiagFun = function () {
        $('#passDialog').dialog('open');
    };

    var changePassDiagFun = function () {
        var $oripass = $('#txtOriPass');
        var $newpass = $('#txtNewPass');
        var $rePass = $('#txtRePass');
        $.ajax({
            data: {
                'oldpassword': $oripass.val(),
                'newpassword': $newpass.val()
            },
            method: 'POST',
            url: '${ctx}/user/user_changePassword',
            beforeSend: function () {
                if ($oripass.val() == '') {
                    $.messager.alert('系统提示', '请输入旧密码！', 'warning');
                    return false;
                }
                if ($newpass.val() == '') {
                    $.messager.alert('系统提示', '请输入新密码！', 'warning');
                    return false;
                }
                if ($rePass.val() == '') {
                    $.messager.alert('系统提示', '请重复输入新密码！', 'warning');
                    return false;
                }
                if ($newpass.val() != $rePass.val()) {
                    $.messager.alert('系统提示', '两次密码不一致！', 'warning');
                    return false;
                }
            },
            success: function (msg) {
                if (msg.resCode == '0') {
                    $.messager.alert('系统提示', '密码修改成功，请牢记你的密码', 'info');
                    $oripass.textbox('setValue', '');//赋值
                    $newpass.textbox('setValue', '');//赋值
                    $rePass.textbox('setValue', '');//赋值
                    $('#passDialog').dialog('close');
                }
                else if (msg.resCode == '102004') {
                    $.messager.alert('系统提示', '修改失败，旧密码错误！', 'error');
                }
                else {
                    $.messager.alert('系统提示', '修改失败，错误原因：' + msg.errMsg, 'error');
                }
            }
        });
    };

    var closePassDiagFun = function () {
        var $oripass = $('#txtOriPass');
        var $newpass = $('#txtNewPass');
        var $rePass = $('#txtRePass');
        $oripass.textbox('setValue', '');//赋值
        $newpass.textbox('setValue', '');//赋值
        $rePass.textbox('setValue', '');//赋值
        $('#passDialog').dialog('close');
    };

    var logoutFun = function () {
        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function (r) {
            if (r) {
                location.href = '${ctx}/auth/logout';
            }
        });
    };

    /*
     ** 弹窗更新grid数据
     */
    function dataUpdate(opt) {
        var action = opt.action,
                params = opt.params == undefined ? '' : opt.params;
        var onSub = opt.onSubmit == undefined ? function () {
            console.log("no submit function");
            return true;
        } : opt.onSubmit;
        $.ajax({
            url: '${ctx}/' + action,
            type: 'GET',
            data: params,
            success: function (data) {
                if (data.result == "500") {
                    alert('error:' + 500)
                }
                var dialog = $('#wUpdate');
                dialog.find('#wContainer').html(data);
                dialog.window('open');
                $('#wOK').off('click');
                $('#wOK').on('click', function () {
                    setZtreeIds();
                    dialog.find('form').form('submit', {
                        success: function (data) {
                            data = eval('(' + data + ')');
                            if (data.result == "SUCCESS") {
                                opt.success(data);
                                dialog.window('close');
                            } else if (data.result == "ERROR") {
                                alert(data.str);
                                dialog.window('close');
                            } else {
                                alert('保存失败！');
                                dialog.window('close');
                            }
                        },
                        onSubmit: function () {
                            return onSub(dialog);
                        }
                    });
                });
                $('#wCancel').on('click', function () {
                    $('#wOK').off('click');
                    dialog.window('close');
                });
            },
            error: function () {
                alert('加载出错！')
            }
        });
    }

    $(function () {
        $('#passDialog').dialog('close');
        $('#loadingDialog').dialog('close');

        $('#editpass').click(editPassDiagFun);
        $('#closePassDiagBtn').click(closePassDiagFun);
        $('#changePassBtn').click(changePassDiagFun);
        $('#loginOut').click(logoutFun);
        $('#wUpdate').window({
            title: '更新数据',
            modal: true,
            closed: true,
            iconCls: 'icon-save',
            minimizable: false,
            maximizable: false,
            collapsible: false
        })
    });


    function uploadFileForm(opt) {
        var action = opt.action,
                params = opt.params == undefined ? '' : opt.params;
        $.ajax({
            url: '${ctx}/' + action,
            type: 'GET',
            data: params,
            success: function (data) {
                if (data.result == "500") {
                    alert('error:' + 500)
                }
                var dialog = $('#wUpdate');
                dialog.find('#wContainer').html(data);
                dialog.window('open');
                $('#wOK').one('click', function () {
                    setZtreeIds();
                    dialog.find('form').form('submit', {
                        onSubmit: function () {
                            var input_id = dialog.find('form').find('input:first');
                            var imgval = $('#fileField').filebox('getValue');
                            if (input_id.val() == "") {
                                if ("" == imgval) {
                                    Common.showBySite('温馨提示', '请选择文件！');
                                    return false;
                                } else {
                                    if (-1 == imgval.indexOf('.mp4')) {
                                        Common.showBySite('温馨提示', '请选择正确的文件格式(.mp4)!');
                                        return false;
                                    }
                                }
                            } else {
                                if (imgval != "") {
                                    if (-1 == imgval.indexOf('.mp4')) {
                                        Common.showBySite('温馨提示', '请选择正确的文件格式(.mp4)!');
                                        return false;
                                    }
                                }
                            }
                            var isValid = dialog.find('form').form('validate');
                            if (isValid) {
                                fileManager_Main.resetPercent(imgval);
                            }
                            return isValid;
                        },
                        success: function (data) {
                            data = eval('(' + data + ')');
                            if (data.result == "SUCCESS") {
                                opt.success();
                                dialog.window('close');
                            } else if (data.result == "ERROR") {
                                alert(data.str);
                                dialog.window('close');
                            } else {
                                alert('保存失败！');
                                dialog.window('close');
                            }
                        }
                    });
                });
                $('#wCancel').on('click', function () {
                    dialog.window('close');
                });
            },
            error: function () {
                alert('加载出错！')
            }
        });
    }


</script>
</html>
