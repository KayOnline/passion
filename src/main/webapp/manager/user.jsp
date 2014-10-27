<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<script type="text/javascript">
		
		var manager_user_datagrid = undefined;	//存储动态表格引用
		
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
		
		function assignRole(userId) {
			window.open('${pageContext.request.contextPath}/userAction.action?action=assignRole&userId=' + userId, 'AssignRole', 'width=600,heigth=400');
		}
		
		$(function() {
			// Init Easyui Datagrid
			manager_user_datagrid = $('#manager_user_datagrid').datagrid({
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
	/*
		//添加记录
	   	function addRecord() {
	   		endEdit();
			$.post('${pageContext.request.contextPath}/userAction!addRecord.action',function(data) {
				manager_user_datagrid.datagrid('insertRow',{
					index: 0,	// index start with 0
					row: {
						userId: data
					}
				});
			});
	   	}
		
		function delRecord() {
			var rows = manager_user_datagrid.datagrid('getChecked');
			
			if(rows.length <= 0) {
				$.messager.alert('提示', '请选择您要删除的记录.', 'info');
				return false;
			}
			
			//Easyui批量删除[每次删除都会调整行号，所以必须处理]
			var tmp = [];
			for(var i=0; i<rows.length; i++) {
				var row = rows[i];
				var rowIndex = manager_user_datagrid.datagrid('getRowIndex', row);
				tmp.push(rowIndex-i < 0 ? 0 : rowIndex-i);
			}
			console.info(tmp.join(','));
			
			function deleteRow(index) {
				manager_user_datagrid.datagrid('deleteRow', index);
			}
			
			for(var j=0; j<tmp.length; j++) {
				deleteRow(tmp[j]);
			}
			
		}*/
		
	
// 		function exportExcel() {
//			Note Cannot use Ajax to Export Excel
// 			$('#excelForm').form('submit', {
// 				url : '${pageContext.request.contextPath}/exportExcel.action'
// 			});
// 		}
	
// 		function showChart() {
	
//			Need to dispose the existing chart before init
// 			if (FusionCharts("myChartId")) {
// 				FusionCharts("myChartId").dispose();
// 			}
	
//			Init FushionChart
// 			$("#chartContainer")
// 					.insertFusionCharts(
// 							{
// 								swfUrl : "FusionCharts/Column3D.swf",
// 								dataSource : "${pageContext.request.contextPath}/userAction!getChart.action",
// 								dataFormat : "jsonurl",
// 								width : "500",
// 								height : "300",
// 								id : "myChartId"
// 							});
	
//			Show Dialog
// 			$("#chartContainer").dialog({
// 				title : 'FushionChart图表',
// 				width : 600,
// 				heigth : 450,
// 				resizable : true,
// 				modal : true,
// 				iconCls : "icon-chart-bar"
// 			});
// 		}
		
		//保存记录
// 		function saveRecord() {
			
// 			endEdit()
			
// 			var inserted = manager_user_datagrid.datagrid('getChanges','inserted');	//session.save
// 			var updated = manager_user_datagrid.datagrid('getChanges','updated');	//session.update
// 			var deleted = manager_user_datagrid.datagrid('getChanges','deleted');	//session.delete
	
// 			var effectRow = new Object();
			
// 			if(inserted.length) {
// 				effectRow["inserted"] = JSON.stringify(inserted); 
// 			}
// 			if(updated.length) {
// 				effectRow["updated"] = JSON.stringify(updated);
// 			}
// 			if(deleted.length) {
// 				effectRow["deleted"] = JSON.stringify(deleted);
// 			}
	
// 			$.ajaxSetup({cache:false});
// 			var url = '${pageContext.request.contextPath}/userAction.action?action=saveRecord';
// 			$.post(url, effectRow, function(data) {
// 				alert(data);
// 			}, 'html');
// 		}
		
	
		// Query Condition Part
		function queryRecord() {
			var $form = $('#toolbar form:first');
			manager_user_datagrid.datagrid('load', kay.serializeObject($form));
		}
	
		// Clear Condition Part
		function clearCondition() {
			manager_user_datagrid.datagrid('load', {});
			$('#toolbar form:first').find('input').val('');
		}
	</script>

	<div id="manager_user_datagrid"></div>
	
	<div style="display:none;">
	
		<%-- Used to Show FushionChart--%>
		<div id="chartContainer">FusionCharts XT will load here!</div>
		
		<%-- Used to Export Excel--%>
		<form id="excelForm" method="post"></form> 
	
	</div>
	
	<!-- CONDITION & CRUD -->
	<div id="toolbar">
		<form method="post" style="margin:0; padding:0;">
		<table class="panel-title">
			<tr>
				<td>
					<label for="userName">用户名称：</label>
					<input id="userName" name="userName" type="text" value="" style="width:120px;"/>
					<label for="qureyInfo">创建时间：</label>
					<input name="beginDate" class="easyui-datetimebox" data-options="editable:false"/>至
					<input name="endDate" class="easyui-datetimebox" data-options="editable:false"/>
				</td>
			<tr>
			<tr>
				<td>
					<a id="btnQuery" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="queryRecord();">查询</a>
					<a id="btnClear" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="clearCondition();">清空</a>
					<a id="btnAdd" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addRecord();">添加</a>
					<a id="btnSave" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="saveRecord();">保存</a>
					<a id="btnDelete" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="delRecord();">删除</a>
					<a id="exportExcel" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-excel',plain:true" onclick="exportExcel();">导出Excel</a>
					<a id="showChart" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-chart-bar',plain:true" onclick="showChart();">图表</a>
				</td>
			</tr>
		</table>
		</form>
	</div>

