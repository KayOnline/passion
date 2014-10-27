<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="framework/include/pageset.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<div id="datagrid"></div>
<script type="text/javascript">
	function formatComboByGridEditor(value, row, rowIndex) {
		var retLabel = value;
		if (this.editor && this.editor.options) {
			var data = this.editor.options.data;
			$(data).each(function(index, item) {
				if (item.value == value) {
					retLabel = item.label;
				}
			});
		}
		return retLabel;
	}
	$(function() {
		datagrid = $('#datagrid').datagrid({
			url : 'retrieveDataGrid',
			title : '组织机构详细列表',
			iconCls : 'icon-rainbow',
			fit : true,
			striped : true,
			fitColumns : false,
			singleSelect : true,
			selectOnCheck : false,
			checkOnSelect : false,
			collapsible : true,
			rownumbers : true,
			ctrlSelect : true,
			pagination : true,
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			toolbar : '#tb',
			onClickCell : beginEdit,
			idField : 'partyId',
			columns : [ [ {
				field : 'checked',
				checkbox : true
			}, {
				field : 'partyId',
				title : '唯一标识',
				width : '100',
				align : 'center',
				halign : 'center',
				checkbox : false,
				hidden : false
			}, {
				field : 'groupCode',
				title : '组织机构编号',
				width : '100',
				align : 'center',
				halign : 'center',
				checkbox : false,
				hidden : false,
				editor : {
					type : 'text'
				}
			}, {
				field : 'groupName',
				title : '组织机构名称',
				width : '100',
				align : 'center',
				halign : 'center',
				checkbox : false,
				hidden : false,
				editor : {
					type : 'text'
				}
			}, {
				field : 'groupLevel',
				title : '组织机构级别',
				width : '100',
				align : 'center',
				halign : 'center',
				checkbox : false,
				hidden : false
			}, {
				field : 'groupType',
				title : '组织机构类型',
				width : '100',
				align : 'center',
				halign : 'center',
				checkbox : false,
				hidden : false,
				formatter : formatComboByGridEditor,
				editor : {
					type : 'combobox',
					options : {
						panelHeight : 'auto',
						valueField : 'value',
						textField : 'label',
						data : [ {
							"value" : "001",
							"label" : "公司"
						}, {
							"value" : "002",
							"label" : "部门"
						}, {
							"value" : "003",
							"label" : "科室"
						} ],
					}
				}
			}, {
				field : 'description',
				title : '备注',
				width : '100',
				align : 'center',
				halign : 'center',
				checkbox : false,
				hidden : false,
				editor : {
					type : 'text'
				}
			} ] ]
		});
	});
</script>
<script type="text/javascript">
	$(function() {
		var rows = $('#datagrid').datagrid('getRows');
		$.each(rows, function(index, item) {
			$('#datagrid').datagrid('endEdit', parseInt(index, 10));
		});
	});
</script>
</body>
</html>