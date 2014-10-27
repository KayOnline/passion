<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="framework/include/pageset.jsp" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Home Page</title>
  </head>
  
<%--   	 <h1>Home Page</h1>
     <p>Your login code is :<security:authentication property="principal.userCode" /></p>
     <p>Your login name is :<security:authentication property="principal.userName" /></p>
     <p>Your email is :<security:authentication property="principal.email" /></p>
     <p><security:authorize access="hasRole('ROLE_USER')">secret</security:authorize></p>
     <a href="j_spring_security_logout" >login out</a>
     <hr> --%>
	<body class="easyui-layout">
	  	<div data-options="region:'north',split:false,href:'framework/layout/north.jsp'" style="height:80px;overflow:hidden;"></div>
		<div data-options="region:'west',title:'功能导航',split:true,href:'framework/layout/west.jsp'" style="width:200px;overflow:hidden;"></div>
		<div data-options="region:'center',title:'欢迎使用系统',split:true,href:'framework/layout/center.jsp'" style="overflow:hidden;" ></div>
		<div data-options="region:'east',title:'当期日期',split:true,href:'framework/layout/east.jsp'" style="width:180px;overflow:hidden;"></div>
		<div data-options="region:'south',split:false,href:'framework/layout/south.jsp'" style="height:20px;"></div>
	</body>
</html>
