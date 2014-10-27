<%--  

##JSP
<%@ page language="java" contentType="text/html;charset=UTF-8"%>

##JSTL
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  


--%>


<%-- jQuery --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.js"></script>


<%-- jQuery Cookie Plugin --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.cookie.js"></script>

<%-- jQuery UI --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.theme.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/resources/jquery-ui/jquery-ui.structure.min.css" />

<%-- jqGrid --%>
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jqGrid/css/ui.jqgrid.css" type="text/css"></link>--%>
<%--<script src="${pageContext.request.contextPath}/resources/jqGrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>--%>
<%--<script src="${pageContext.request.contextPath}/resources/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>--%>

<%-- EasyUI --%>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-easyui/themes/${cookie.easyuiThemeName.value==null ? 'default' : cookie.easyuiThemeName.value}/easyui.css">  
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-easyui/themes/icon.css">  
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-easyui/locale/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-easyui/portal/jquery.portal.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-easyui/portal/portal.css">  
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-easyui-extension.js"></script>


<%-- DragTable --%>
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/dragtable.js"></script> -->
   
<%-- FusionCharts --%>
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/FusionCharts/FusionCharts.js"></script> -->
<!-- <script type="text/javascript" src="${pageContext.request.contextPath}/FusionCharts/FusionCharts.jqueryplugin.js"></script> -->

<%-- CKEditor --%>
<!-- <script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script> -->
<!-- <script src="${pageContext.request.contextPath}/ckeditor/adapters/jquery.js"></script> -->

<%-- json2.js --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/json2.js"></script> 

<%-- Custom JS Lib --%>
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/icon.css"> -->
