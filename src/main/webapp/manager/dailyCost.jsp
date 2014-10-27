<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
		
		var dailyCost_datagrid = undefined;	//存储动态表格引用
		
		var editIndex = undefined;	//存储正在编辑的行号
		
		function endEdit() {   
			if (editIndex == undefined) { return true; }
			manager_user_datagrid.datagrid('endEdit', editIndex);
		    editIndex = undefined;
		}

		function beginEdit(index, field) {
			manager_user_datagrid.datagrid('selectRow', index).datagrid('editCell', {index:index,field:field});
			editIndex = index; 
		}
		
		
		$(function() {
			// Init Easyui Datagrid
			dailyCost_datagrid = $('#dailyCost_datagrid').datagrid({
				url:'${pageContext.request.contextPath}/userController/query',
				title:'用户管理',
				iconCls:'icon-rainbow',
				fit:true,
				idField:'userId',
				striped: true,
				fitColumns: false,
				singleSelect: true,
				selectOnCheck: false,
				checkOnSelect: false,
				collapsible: true,
				rownumbers: true,
				pagination: true,
				pageNumber: 1,
		        pageSize: 10,
		        pageList: [10,20,30,40,50],
		        sortName: 'userCode',
		        sortOrder: 'asc',
		        onDblClickCell: beginEdit,
		        onClickRow: endEdit,
		        onBeforeLoad: setDropListFieldOptions,
		        toolbar:'#toolbar',
				frozenColumns:[[   
					{field:'ck',checkbox:true}   
				]],
				columns:[[
					{ field:'userId', title:'用户编号', width:100, halign:'center', align:'center', hidden:true },
					{ field:'userCode', title:'用户代码', width:100, halign:'center', align:'center', sortable:true,
					  editor: { type: 'text' } },
					{ field:'userName', title:'用户名称', width:100, halign:'center', align:'center',
					  editor: { type: 'text' } },
					
					
					{ field:'mobilephone', title:'手机号码', width:100, halign:'center', align:'center',
					  editor: { type: 'text' } },
				    { field:'email', title:'电子邮箱', width:100, halign:'center', align:'center',
					  editor: { type: 'text' } },
				]]
			});
			
		});

	</script>

<div id="dailyCost_datagrid"></div>