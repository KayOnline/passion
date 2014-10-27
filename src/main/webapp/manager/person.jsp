<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../framework/include/pageset.jsp"%>
<%@ taglib uri="/easyui-tags" prefix="easyui"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<script type="text/javascript" src="../manager/personScript.js"></script>
</head>
<body style="margin:0;padding:0;">
	<easyui:layout>
		<easyui:layoutArea region="west" title="组织人员设置" style="width:200px;">
			<easyui:tree id="tree" url="retrieveTree" onClick="clickNode" />
		</easyui:layoutArea>
		<easyui:layoutArea region="center" title="详细信息" style="background:#eee;">
			<easyui:DataGrid id="datagrid" idField="partyId" title="组织机构详细列表" url="retrieveDataGrid" toolbar="tb">
				<easyui:DataGridColumn title="唯一标识" field="partyId" hidden="true"/>
				<easyui:DataGridColumn title="人员编号" field="personCode" editorType="text"/>
				<easyui:DataGridColumn title="人员名称" field="personName" editorType="text"/>
				<easyui:DataGridColumn title="人员名称拼音" field="personNameSpell" editorType="text"/>
				<easyui:DataGridColumn title="人员状态" field="personStatus" editorType="combobox" editorOptionsDropCode="APP.PERSON_STATUS" />
				<easyui:DataGridColumn title="性别" field="gender" editorType="combobox" editorOptionsDropCode="APP.GENDER" />
				<easyui:DataGridColumn title="国籍" field="nationality"  editorType="combobox" editorOptionsDropCode="APP.NATIONALITY" />
				<easyui:DataGridColumn title="政治面貌" field="politicalStatus"  editorType="combobox" editorOptionsDropCode="APP.POLITICAL_STATUS" />
				<easyui:DataGridColumn title="出生日期" field="birthday" editorType="datebox" />
				<easyui:DataGridColumn title="备注信息" field="description" editorType="text" />
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
