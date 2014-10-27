<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<style type="text/css">
	@font-face {
		font-family: "Flex Display";
		src: url(${pageContext.request.contextPath}/resources/font/FlexDisplay-Thin.otf);
	}	
	@font-face {
		font-family: "Podkova";
		src: url(${pageContext.request.contextPath}/resources/font/Podkova-Regular.ttf);
	}	
	h1 {
		color: #92B901;
		font-size: 42;
		font-weight: normal;
		font-family: 'Podkova';
		margin: 4px;
		padding: 0;
	}	
	
</style>
<script type="text/javascript">
	$(function() {
		$('#mb').menubutton({ 
		    iconCls: 'icon-edit',   
		    menu: '#theme'  
		});  
	});
</script>

<h1 id="north_title">Passion Project</h1>
<div style="position:absolute;right:0;bottom:0;">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true">
		欢迎您,<security:authentication property="principal.userName" />
	</a>
	<a href="javascript:void(0)" id="mb">设置</a>  
	<input type="button" value="注销" onclick="location.href='j_spring_security_logout'" />
	<a href="/j_spring_security_logout" class="easyui-linkbutton" data-options="plain:true, iconCls:'icon-edit'">注销</a>
</div>

<div id="theme" style="width:150px">  
	<div onclick="alert('个人信息')">个人信息</div>
	<div class="menu-sep"></div>
	<div onclick="alert('修改密码');">修改密码</div>
	<div class="menu-sep"></div>
	<div>
		<span>更换主题</span>
		<div style="width: 120px;">
			<div onclick="kay.changeTheme('default');">默认(default)</div>
			<div onclick="kay.changeTheme('gray');">gray</div>
			<div onclick="kay.changeTheme('metro');">metro</div>
			<div onclick="kay.changeTheme('cupertino');">cupertino</div>
			<div onclick="kay.changeTheme('dark-hive');">dark-hive</div>
			<div onclick="kay.changeTheme('pepper-grinder');">pepper-grinder</div>
			<div onclick="kay.changeTheme('sunny');">sunny</div>
		</div>
	</div>
</div> 
