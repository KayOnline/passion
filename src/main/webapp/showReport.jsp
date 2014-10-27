<%@page import="net.sf.jasperreports.engine.xml.JasperDesignFactory"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ include file="framework/include/pageset.jsp" %>
<%@ page import="javax.servlet.*"%>
<%@ page import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.util.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="net.sf.jasperreports.j2ee.servlets.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<script type="text/javascript" src="showReportScritp.js"></script>
</head>

<body>

<ul>
<li>${param.kay.toUpperCase()}</li>
<li>${aaa.getTime() }</li>
<li>${requestScope.aaa}</li>
<li>${sessionScope.bbb}</li>
<li>${paramValues.kay[0]}</li>
<li>${param.vita}</li>
<li>${pageContext.request.localAddr}</li>
</ul>

</body>
</html>
