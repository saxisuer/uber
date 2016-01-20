$(function(){
    //Grid Resize
    $(window).on('resize',function(){
        var gridDom = $('.datagrid-view').children('table'), searchH = 0;
        if($('#search').parent().length){
            searchH = $('#search').parent().outerHeight();
            $('#search').panel('resize');
        }

        if(gridDom.length){
            $('.datagrid').hide();
            gridDom.datagrid('resize',{height:$('#body').height() - searchH, width:$('body').width()});
            $('.datagrid').show();
            gridDom.datagrid('resize'); 
        } 
    })
})

//日期格式化
function dataFormated(){
    Date.prototype.Format = function (fmt) { 
        var weekday = ['日','一','二','三','四','五','六'];
        var o = {
        	"y+": this.getFullYear(), //年份 
            "M+": this.getMonth() + 1, //月份 
            "d+": this.getDate(), //日 
            "h+": this.getHours(), //小时 
            "m+": this.getMinutes(), //分 
            "s+": this.getSeconds(), //秒 
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
            "S": this.getMilliseconds(), //毫秒 
            "D": weekday[this.getDay()] //星期
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
    return new Date().Format('MM月dd日(星期D)')
}