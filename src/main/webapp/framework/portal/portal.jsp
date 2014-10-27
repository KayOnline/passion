<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<script type="text/javascript">
	var panels = undefined;
	var portal = undefined;
	
	$(function() {
		//predefined panels options
 		panels = [ {
			id : 'p1',
			title : '站点',
			height : 200,
			iconCls : 'icon-rainbow',
			collapsible : true,
			href : '${pageContext.request.contextPath}/portal/p1.jsp'
		}, {
			id : 'p2',
			title : 'combobox示例',
			height : 200,
			iconCls : 'icon-rainbow',
			collapsible : true,
			href : '${pageContext.request.contextPath}/portal/p2.jsp'
		}, {
			id : 'p3',
			title : 'combotree示例',
			height : 200,
			iconCls : 'icon-rainbow',
			collapsible : true,
			href : '${pageContext.request.contextPath}/portal/p3.jsp'
		}, {
			id : 'p4',
			title : 'p4',
			height : 200,
			collapsible : true,
			content : '<h1></h1>'
		}, {
			id : 'p5',
			title : 'p5',
			height : 200,
			collapsible : true,
			content : '<h1></h1>'
		} , {
			id : 'p6',
			title : 'p6',
			height : 200,
			collapsible : true,
			content : '<h1></h1>'
		} ];
		
		//init portal
 		portal = $('#layout_portal').portal({
			border : false,
			fit : true,
			//when users drag and drop a panel,cache the sequence in cookie
			onStateChange : function() {
				$.cookie('portal-state', getPortalState(), {
					expires : 7
				});
			}
		});
		
 		var state = $.cookie('portal-state');

 		if (!state) {
 			//group division
			state = 'p1,p2:p3,p4:p5,p6';
		}
 		
 		addPanels(state);
		
	});
	
	
	//get data options object with specified panel
	function getPanelOptions(id) {
		for ( var i = 0; i < panels.length; i++) {
			if (panels[i].id == id) {
				return panels[i];
			}
		}
		return undefined;
	}
	
	function getPortalState() {
		var ret = [];
		for(var i=0; i<3; i++) {
			var pans = portal.portal('getPanels', i);
			var tmp = [];
			for(var j=0; j<pans.length; j++) {
				tmp.push(pans[j].attr('id'));
			}
			ret.push(tmp.join(','));
		}
		console.info(ret.join(':'));
		return ret.join(':');
	}
	
	function addPanels(portalState) {
		var groups = portalState.split(':');
		for(var i=0; i < groups.length; i++) {
			var items = groups[i].split(',');
			for(var j=0; j < items.length; j++) {
				var options = getPanelOptions(items[j]);
				if (options) {
					//var p = $('<div/>').attr('id', options.id).appendTo('body');
					var p = $('<div/>').appendTo('body');
					p.panel(options);
					portal.portal('add', {
						panel : p,
						columnIndex : i 
					});
					portal.portal('resize');
				}
			}
		}
	}
 		
 </script>
	
<div id="layout_portal" style="position:relative">
	<div style="width:33%"></div>
	<div style="width:33%"></div>
	<div style="width:33%"></div>
</div>
    	



