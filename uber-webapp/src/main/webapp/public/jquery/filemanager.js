//init var
var url = "";
var progressBar = "";

$(function () {
    /**
     * 指定位置显示
     */
    $.extend($.messager, {
        showBySite: function (options, param) {
            var site = $.extend({
                left: "",
                top: "",
                right: 0,
                bottom: -document.body.scrollTop - document.documentElement.scrollTop
            }, param || {});
            var win = $("body > div .messager-body");
            if (win.length <= 0)
                $.messager.show(options);
            win = $("body > div .messager-body");
            win.window("window").css({
                left: site.left,
                top: site.top,
                right: site.right,
                zIndex: $.fn.window.defaults.zIndex++,
                bottom: site.bottom
            });
        }
    });
});

/**
 * 常用的公共JS类
 *
 */
Common = new function () {
    var _this = this;


    this.showBySite = function (param1Tit, param2Msg) {
        $.messager.showBySite({
            title: param1Tit,
            msg: param2Msg,
            showType: 'slide',
            timeout: 1000
        }, {
            top: ($(document.body).height() / 2 - 30),
            left: ($(document.body).width() / 2 - 100),
            bottom: ""
        });
    };
};

/*******************************************************************************
 * @Function: fileManager_Main /FILE管理 Anthor：ChenJ
 */
fileManager_Main = new function () {
    var _this = this;
    this.getHei = function () {
        return document.body.scrollHeight;
    };

    /**
     * 1 添加upload
     */
    this.add = function () {
        $('#fileField').val("");
        $("#prosbar").hide();
        $('#dlg_fileinfo').dialog('open').dialog('setTitle', '上传文件');
    };

    /**
     * 1.1 upload 检测镜像文件格式
     */
    this.fileInit = function (thisval) {
        if (-1 == thisval.indexOf('.mp4')) {
            Common.showBySite('温馨提示', '请选择正确的文件格式(.mp4)!');
            return;
        }
        // var fileInput = $("#fileField")[0];
        var fileInput = $("input[type='file']");
        byteSize = fileInput.files[0].size;// Byte
        if (byteSize > (50 * 1024 * 1024)) {
            Common.showBySite('温馨提示', '上传文件最大值为50兆(M)!');
            return;
        }
    };

    /*
     * 1.2 进度条显示
     */
    this.progressBarPro = function () {
        var everylisten = function () {
            $.ajax({
                url: ctx + '/filelist/upload_listenPresent',
                method: 'GET',
                success: function (result) {
                    if ("" != result) {
                        if (null != result) {
                            var pernum = result.substring(0, result.length - 1);
                            if (pernum >= 0) {
                                $("#prosbar").show();
                            }
                            if ("100%" == result) {
                                $('#prosbar').progressbar({
                                    value: 100
                                });
                                Common.showBySite('温馨提示', '操作成功！');
                                var imgval = $('#fileField').filebox('getValue');
                                $('#fileName').val(imgval.replace('C:\\fakepath\\', ''));
                                $('#dlg_fileinfo').dialog('close');
                            } else {
                                $('#prosbar').progressbar({
                                    value: pernum
                                });
                                t = setTimeout(everylisten, 500);
                            }
                        } else {
                            t = setTimeout(everylisten, 500);
                        }
                    } else {
                        Common.showBySite('温馨提示', '文件上传失败！');
                    }
                }
            });
        };
        everylisten();
    };

    // //终止文件上传操作
    this.showResult = function () {
        console.info('showResult');
        // 1:终止循环调用文件进度方法
        clearTimeout(t);
        // 2 终止文件上传方法
        $.ajax({
            url: './ajaxupload/upload_stopUpload',
            method: 'GET',
            timeout: 60000,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (result) {
            }
        });
    };

    /**
     * 5 镜像文件upload
     */
    this.file_upload = function () {
        // var imgval = $('#fileField').val();
        var imgval = $('#fileField').filebox('getValue');
        if ("" == imgval) {
            Common.showBySite('温馨提示', '请选择文件！');
            return;
        } else {
            if (-1 == imgval.indexOf('.mp4')) {
                Common.showBySite('温馨提示', '请选择正确的文件格式(.mp4)!');
                return;
            }
            var fileInput = $("#fileField").filebox('getValue');
        }

        var flag = $('#fm_fileinfo_info').form('validate');
        if (flag) {
            $("#fm_fileinfo_info").form('submit', {
                url: './ajaxupload/upload_add',
                timeout: 3000000,
                success: function (data) {
                    console.info(data);
                    var json = eval("(" + data + ")");
                    if ("true" != json.msg) {
                        Common.showBySite('温馨提示', '操作失败！');
                    }
                }
            });
            $("#prosbar").show();
            _this.progressBarPro();
        } else {
            return flag;
        }
    };

    this.resetPercent = function (imgval) {
        if ("" != imgval) {
            $.ajax({
                url: ctx + '/filelist/resetPercent',
                method: 'GET',
                success: function (result) {
                    $('#prosbar').progressbar({
                        value: 0
                    });
                    $("#prosbar").hide();
                    _this.progressBarPro();
                }
            });
        }
    }
};
