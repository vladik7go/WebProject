<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<html>
<head><title><fmt:message key="page.title.registration" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<div class="login-card">


<br/>

<c:if test="${errorEmptyFieldMessage !=null }"><fmt:message key="registration.label.emptyfielderror"/></c:if>
<c:if test="${errorDuplicateEntryMessage !=null }"><fmt:message key="registration.label.duplicateEntryError"/></c:if>

<br/>


<table >
<tr>
<td >
				<form action="controller" method="post">
					<input name="command" type="hidden" value="add_person" />
					<input name="role" type="hidden" value="3" />
					<fieldset>
						
						<p>
							<fmt:message key="login.label.firstname" />:<input type="text" name="firstName" pattern="[a-zA-Zа-яА-Я0-9@.]{1,20}$" required="required" /><br>
							<fmt:message key="login.label.secondname" />:<input type="text" name="secondName" pattern="[a-zA-Zа-яА-Я0-9@.]{1,45}$" required="required" /><br>
							
							<fmt:message key="login.label.login" />:<input type="text" name="login" pattern="[a-zA-Zа-яА-Я0-9@.]{1,45}$" required="required" /><br>
							<fmt:message key="login.label.password" />: <input type="password" name="password" pattern="[a-zA-Zа-яА-Я0-9@.]{1,45}$" required="required" /><br> 
							<input type="submit" value="<fmt:message key="login.button.submit" />" />
						</p>
						
						
						
					</fieldset>
				</form>
			</td>
			
		</tr>
	</table>
<form name="toLoginPage" method="POST" action="controller">
						<input type="hidden" name="command" value="" />
						<input type="submit" value="<fmt:message key="login.button.tologin" />"/>
						</form>
</div>
</body>
</html>