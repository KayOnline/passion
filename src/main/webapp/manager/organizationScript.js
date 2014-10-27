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

function saveRecord() {
	var partyId = $("#partyId").val();
	
	var inserted = datagrid.datagrid('getChanges','inserted');	// session.save
	var updated = datagrid.datagrid('getChanges','updated');		// session.update
	var deleted = datagrid.datagrid('getChanges','deleted');		// session.delete
	var t = {};
	t.inserted = JSON.stringify(inserted);
	t.updated = JSON.stringify(updated);
	t.deleted = JSON.stringify(deleted);
	t.partyId = partyId;
	
	$.ajax({
		type: 'POST',
		url: 'save',
		data: t,
		dataType: 'json',
		success: function(response) {
			$.messager.alert('提示信息', response.message);
			datagrid.datagrid('reload');
			var node = tree.tree('find', partyId);
			tree.tree('reload', node.target);
		}
	});

}

function clickNode(node) {
	datagrid.datagrid('load', {partyId : node.id});
	$('#partyId').val(node.id);
}

$(function() {
	$("input[type='button']").button();
});

