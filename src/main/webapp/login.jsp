<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ include file="framework/include/pageset.jsp" %>
<head>
<title>Login Page</title>
</head>
<body>
	<div>${SPRING_SECURITY_LAST_EXCEPTION.message}</div>
	<form method="post" action="${pageContext.request.contextPath}/j_spring_security_check">
		<fieldset>
			<table>
				<tr>
					<th><label for="username">Username</label></th>
					<td><input id="username" name="j_username" type="text" /></td>
				</tr>
				<tr>
					<th><label for="password">Password</label></th>
					<td>
						<input id="password" name="j_password" type="password" />
					</td>
				</tr>
				<tr>
					<th><label for="jcaptcha">Captcha</label></th>
					<td>
						<input type="text" id="jcaptcha" name="jcaptcha" value="" />
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<img id="jcaptchaImg" src="jcaptcha.jpg" />
					</td>
				</tr>
				<tr>
					<th></th>
					<td>
						<input id="remember_me" name="_spring_security_remember_me" type="checkbox" />
						<label for="remember_me">Remember me</label>
					</td>
				</tr>
				<tr>
					<th></th>
					<td><input name="commit" type="submit" value="Sign In" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
