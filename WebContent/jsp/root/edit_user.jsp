<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<html>
<head>

<title><fmt:message key="page.title.edit_user" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<div class="login-card">


<table >
<tr>
<td >
				<form action="controller" method="post">
					<input name="command" type="hidden" value="edit_Write_Person" />
					<input name="id" type="hidden" value="${person.id }" />
					<fieldset>
						
						<p>
							<fmt:message key="login.label.role" />:			<!-- input type="text" name="role" value="${person.roleType } " /--> 
								<select id="choose_role" name="role" >
               						<option <c:if test="${person.roleType != null and person.roleType eq '1' }">selected</c:if> value="1" ><fmt:message key="edit.label.root"/></option>
               						<option <c:if test="${person.roleType != null and person.roleType eq '2' }">selected</c:if> value="2" ><fmt:message key="edit.label.tutor"/></option>
               						<option <c:if test="${person.roleType != null and person.roleType eq '3' }">selected</c:if> value="3" ><fmt:message key="edit.label.student"/></option>
								</select><br>
							
							<fmt:message key="login.label.firstname" />:<input type="text" name="firstName" value="${person.firstName }" pattern="[a-zA-Zа-яА-Я0-9@.]{1,20}$" required="required" /><br>
							<fmt:message key="login.label.secondname" />:<input type="text" name="secondName" value="${person.secondName }" pattern="[a-zA-Zа-яА-Я0-9@.]{1,45}$" required="required" /><br>
							<fmt:message key="login.label.login" />:<input type="text" name="login" value="${person.login }" pattern="[a-zA-Zа-яА-Я0-9@.]{1,45}$" required="required" /><br>
							<fmt:message key="login.label.password" />: <input type="text" name="password" value="${person.password }" pattern="[a-zA-Zа-яА-Я0-9@.]{1,45}$" required="required" /><br> 
							<input type="submit" value="<fmt:message key="login.button.submit" />" />
						</p>
						
						
						
					</fieldset>
				</form>
			</td>
			
		</tr>
	</table>
<c:if test="${errorNonNumberMessage !=null }"><fmt:message key="edit.label.nonnumbererror"/></c:if>
<c:if test="${errorEmptyFieldMessage !=null }"><fmt:message key="registration.label.emptyfielderror"/></c:if>

</div>
</body>
</html>