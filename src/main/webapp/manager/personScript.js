

function clickNode(node) {
	datagrid.datagrid('load', {partyId : node.id});
	$('#partyId').val(node.id);
}

function saveRecord() {
	var data = datagrid.datagrid('collectData');
	data.partyId = $("#partyId").val();
	
	$.post("save", data, function(response) {
		$.messager.alert('提示信息', response.message);
		datagrid.datagrid('reload');
//		var node = tree.tree('find', partyId);
//		tree.tree('reload', node.target);
	});
}

function addRecord() {
	var partyId = $("#partyId").val();
	if (partyId == undefined || partyId == '') {
		$.messager.alert('提示信息','请选择树节点！');
		return false;
	}
	$.post('add', function(response) {
		datagrid.datagrid('insertRow', {
			index : 0, // index start with 0
			row : response
		});
	});
}

function delRecord() {
	var checkedRows = datagrid.datagrid('getChecked');
	for(var i=0; i<checkedRows.length; rowIndex++) {
		var rowIndex = datagrid.datagrid('getRowIndex', checkedRows[i]);
		datagrid.datagrid('deleteRow', rowIndex);
	}
}