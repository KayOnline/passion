<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<script type="text/javascript">
	var centerTab = undefined;
	$(function() {
		// Init tab
 		centerTab = $('#layout_center_tabs').tabs({   
 		    border: false,  
 		    fit: true   
 		});  
	});
</script>
	

<div id="layout_center_tabs">  
    <div title="首页" data-options="href:'${pageContext.request.contextPath}/framework/portal/portal.jsp'"  style="overflow:hidden"></div>   
</div> 

