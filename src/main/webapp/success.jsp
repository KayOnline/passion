<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="framework/include/pageset.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'index.jsp' starting page</title>
<script type="text/javascript" src="resources/js/print2flash.js"></script>
<script type="text/javascript">
	$(function() {
		var content = Print2Flash();
		$("#print2flash").html(content);
	});
</script>
</head>
<body>
	<div id="print2flash" style="width:80%;height:100%;"></div>
</body>
</html>
