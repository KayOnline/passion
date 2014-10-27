<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<div style="padding:10px;">
	<select id="combotree" class="easyui-combotree" style="width:200px;"  
      		data-options="url:'${pageContext.request.contextPath}/menuAction!tree.action',required:true,multiple:true,checkbox:true,animate:true"></select>
	<a class="easyui-linkbutton" data-options="plain:true" onclick="alert($('#combotree').combotree('getValues'));">显示值</a>
</div>