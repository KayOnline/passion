<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../framework/include/pageset.jsp"%>
<%@ taglib uri="/easyui-tags" prefix="easyui"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<script type="text/javascript" src="../manager/organizationScript.js"></script>
</head>
<body style="margin:0;padding:0;">
	<easyui:layout>
		<easyui:layoutArea region="west" title="组织机构设置" style="width:200px;">
			<easyui:tree id="tree" url="retrieveTree" onClick="clickNode" />
		</easyui:layoutArea>
		<easyui:layoutArea region="center" title="详细信息" style="background:#eee;">
			<easyui:DataGrid id="datagrid" idField="partyId" title="组织机构详细列表" url="retrieveDataGrid" toolbar="tb">
				<easyui:DataGridColumn title="唯一标识" field="partyId" hidden="false"/>
				<easyui:DataGridColumn title="组织机构编号" field="groupCode" editorType="text"/>
				<easyui:DataGridColumn title="组织机构名称" field="groupName" editorType="text"/>
				<easyui:DataGridColumn title="组织机构级别" field="groupLevel" />
				<easyui:DataGridColumn title="组织机构类型" field="groupType" editorType="combobox" editorOptionsDropCode="APP.GROUP_LEVEL" />
				<easyui:DataGridColumn title="备注" field="description" editorType="text"/>
			</easyui:DataGrid>
			<div id="tb" align="right">
				<input type="button" id="addBtn" value="添 加" onclick="addRecord()"/>
				<input type="button" id="saveBtn" value="保 存" onclick="saveRecord()"/>
				<input type="button" id="delBtn" value="删 除" onclick="delRecord()"/>
			</div>
		</easyui:layoutArea>
	</easyui:layout>
	<input type="hidden" id="partyId" name="partyId" />
</body>
</html>
