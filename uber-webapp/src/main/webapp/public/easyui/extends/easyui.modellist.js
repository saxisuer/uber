/**
 * jQuery EasyUI ModelList 1.0
 * 
 * 用于定义对象列表，含查询、工具栏、列表和分页4个主要组件 参数定义
 * 
 * @param searchbox
 *            是否显示查询框,取值true/false
 * @param searchItems
 *            查询项,数组类型[{label:'',id:'', type:'', options:[{'name':'value'}]}],type的取值包括ftString, ftNumber,
 *            ftDate, ftDatetime, ftCombobox
 * @param name
 *            代码生成的datagrid的id
 * @param idField
 *            datatgrid的自有options
 * @param url
 *            datatgrid的自有options
 * @param columns
 *            datatgrid的自有options
 * @param toolbar
 *            是否显示工具栏,取值true/false
 * @param toolbarItems
 *            toolbar的按钮项,数组类型[{label:'',id:'', icon:''}]
 */

(function($) {
	$.fn.modellist = function(arg) {
		var htmlSearchBox = '';
		var htmlDatagrid = '';
		var htmlToolbar = '';
		/** 查询条件区域 */
		if (arg.searchbox) {
			htmlSearchBox = '<div id="searchPnl" class="easyui-panel" style="padding:5px;margin-bottom:2px;" >'
					+ '<table cellpadding="1px;" style="font-size:11px;">';
			for (var i = 0; i < arg.searchItems.length; i++)
			{
				searchItem =  arg.searchItems[i];
				htmlSearchBox = htmlSearchBox+'<tr><td>'+searchItem.label+'</td>';
				if (searchItem.type == 'ftNumber'){
					htmlSearchBox = htmlSearchBox+'<td><input class="easyui-numberbox" type="text" id="'+searchItem.id+'" ></input></td>';
				}
				else if (searchItem.type == 'ftDate'){
					htmlSearchBox = htmlSearchBox+'<td><input class="easyui-datebox" style="width:153px" id="'+searchItem.id+'from'+'">至<input class="easyui-datebox" style="width:153px" id="'+searchItem.id+'to'+'"></td>';
				}
				else if (searchItem.type == 'ftDatetime'){
					htmlSearchBox = htmlSearchBox+'<td><input class="easyui-datetimebox" style="width:153px" id="'+searchItem.id+'from'+'">至<input class="easyui-datetimebox" style="width:153px" id="'+searchItem.id+'to'+'"></td>';
				}			
				else if (searchItem.type == 'ftCombobox'){
					htmlSearchBox = htmlSearchBox+'<td><select class="easyui-combobox" id="state" style="width:153px;">'
					//???待完善
					htmlSearchBox = htmlSearchBox+'</select></td>';
				}					
				else{
					htmlSearchBox = htmlSearchBox+'<td><input class="easyui-textbox" type="text" id="'+searchItem.id+'" ></input></td>';
				}
				if (i==arg.searchItems.length-1){
					htmlSearchBox = htmlSearchBox+'<td><a  href="javascript:void(0)" id="search" class="easyui-linkbutton my-search-button" iconCls="icon-search" >查询</a></td></tr>';
				}
				else{
					htmlSearchBox = htmlSearchBox+'<td></td></tr>';
				}
			}
			htmlSearchBox = htmlSearchBox+'</table>'
								+'</div>';
		}
		var obj1 = this.append(htmlSearchBox);
		$.parser.parse(obj1);//渲染动态控件
		/** 数据表格区域 */
		htmlDatagrid = '<table id="'+arg.name+'" style="table-layout:fixed;"></table> ';
		this.append(htmlDatagrid);
		
		/** 表格顶部工具栏 */
		if (arg.toolbar) {
			htmlToolbar = '<div id="toolbar">';
			for (var j = 0; j < arg.toolbarItems.length; j++){
				toolbarItem = arg.toolbarItems[j];
				htmlToolbar = htmlToolbar + '<a href="javascript:void(0)"  id="'+toolbarItem.id+'" class="easyui-linkbutton" iconCls="'+toolbarItem.icon+'" plain="true">'+toolbarItem.label+'</a>';
			}
			htmlToolbar = htmlToolbar + '</div>';
		}
		
		/** 生成html标记 */
		var obj2 = this.append(htmlToolbar);
		$.parser.parse(obj2);//渲染动态控件
		
		/** 初始化datagrid */
		var obj = $('#'+arg.name).datagrid({
			height:this.height()-$('#searchPnl').height()-15,
			width:this.width(),
			idField:arg.idField,
			url:arg.url,  
			loadFilter:function(data){  
                if(!data.rows||!data.total){  
                    if(data.errMsg){  
                        $.messager.alert('错误提示','操作失败!错误原因：<hr/>'+data.errMsg,'error');  
                    }else{  
                        $.messager.alert('错误提示','操作失败!','error');  
                    }  
                    return {total:0,rows:[]};  
                }  
            },  
			singleSelect:false, 
			selectOnCheck: true,
			checkOnSelect: true,
			nowrap:true,
			fitColumns:true,
			rownumbers:true,
			showPageList:false,
			columns:arg.columns,
			toolbar:'#toolbar',  
	        pagination:true,
	        pageSize:30,
		});
		return obj;
	};
})(jQuery);