<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%-- <%@ include file="../framework/include/pageset.jsp"%> --%>
<script type="text/javascript" charset="utf-8">
	
	var tg = undefined;
	
	var dropLists = [];	//存储下拉数据
	
	var nodeIndex = undefined;	//存储正在编辑的行号
	
	function endEditing(){      
		
		if (nodeIndex != undefined){
			tg.treegrid('endEdit', nodeIndex);
		    nodeIndex = undefined;
		} 
		
	    return true; 
	}
	
	
	$(function() {
		tg = $('#tg').treegrid({ 
		    url:'${pageContext.request.contextPath}/menuController/query',   
		    idField:'id',   
		    treeField:'text',
		    animate: true,
		    fit: true,
		    rownumbers: true,
		    onLoadSuccess: function(row, data) {
		    	$(this).treegrid('expandAll');
		    },
		    columns:[[   
				{title:'菜单编号', field:'id', width:100, halign:'center', align: 'center', hidden: false},
		        {title:'菜单名称', field:'text', width:150, halign:'center',editor: {type: 'text'}},
				{title:'菜单连接', field:'url', width:200, halign:'center',editor: {type: 'text'}}
		    ]],
		    toolbar: [{
		    	iconCls: 'icon-save',
		    	text: '保存',
		    	handler: saveRecord
		    },'-',{
		    	iconCls: 'icon-add',
		    	text: '添加',
		    	handler: addRecord
		    },'-',{
		    	iconCls: 'icon-reload',
		    	text: '刷新',
		    	handler: function(){
		    		endEditing();
		    		tg.treegrid('reload');
		    	}
		    },'-',{
		    	iconCls: 'icon-remove',
		    	text: '删除',
		    	handler: delRecord
		    }],
	        onDblClickCell: function(field, row) {
	        	if (endEditing()){
	        		$(this).treegrid('beginEdit', row.id);
	        		nodeIndex = row.id;
				}
	        },
	        onClickCell: function(field, row) {
	        	endEditing();
	        }
		});
		
		function delRecord() {
	    		endEditing();
	    		if(!confirm('您确定要删除该节点及其子节点？')) {
	    			return false;
	    		}
   				var node = tg.treegrid('getSelected');
   				$.post('${pageContext.request.contextPath}/menuController/delete', {id : node.id}, function(data) {
   					if($.trim(data) == "success") {
   						tg.treegrid('remove', node.id);
   						$.messager.alert('信息', '删除节点成功！', 'info');
   					} else {
   						$.messager.alert('信息', '删除节点失败！', 'info');
   					}
   				},'html');
		}
		
		function saveRecord() {
    		endEditing()
    		
    		var inserted = tg.treegrid('getChanges','inserted');	//session.save
    		var updated = tg.treegrid('getChanges','updated');		//session.update
    		var deleted = tg.treegrid('getChanges','deleted');		//session.delete

    		var effectRow = new Object();
    		
    		if(inserted.length) {
    			effectRow["inserted"] = JSON.stringify(inserted); 
    		}
    		if(updated.length) {
    			effectRow["updated"] = JSON.stringify(updated);
    		}
    		if(deleted.length) {
    			effectRow["deleted"] = JSON.stringify(deleted);
    		}
    		
    		$.ajaxSetup({cache:false});
    		$.post('${pageContext.request.contextPath}/menuController/save', effectRow, function(data) {
    			var msg = undefined;
    			if (data == 'success') {
    				msg = "保存成功！";
    			} else {
    				msg = "保存失败！"; 
    			}
    			$.messager.alert('提示', msg, 'info');
    		}, 'html');
		}
		
		function addRecord() {
    		endEditing();
    		var node = tg.treegrid('getSelected');
    		if(node == null) {
    			$.messager.alert('Warning','请选择新增节点的位置！');  
    			return false;
    		}
    		$.post('${pageContext.request.contextPath}/menuController/addNode',function(data) {
    			console.info(this);
    			tg.treegrid('append',{
    				parent: node.id,
    				data: [{
    					id: data
    				}]
    			});
    		});
		}
		
	});	
	</script>
	<div id="tg"></div>
