<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>page edit_user.jsp</title>
</head>
<body>
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />


page edit_user
${person.id }
${person.firstName }
<table >
<tr>
<td >
				<form action="controller" method="post">
					<input name="command" type="hidden" value="editWritePerson" />
					<input name="id" type="hidden" value="${person.id }" />
					<fieldset>
						
						<p>
							<fmt:message key="login.label.role" />:			<!-- input type="text" name="role" value="${person.roleType } " /--> 
								<select id="choose_role" name="role" >
               						<option <c:if test="${person.roleType != null and person.roleType eq '1' }">selected</c:if> value="1" ><fmt:message key="edit.label.root"/></option>
               						<option <c:if test="${person.roleType != null and person.roleType eq '2' }">selected</c:if> value="2" ><fmt:message key="edit.label.tutor"/></option>
               						<option <c:if test="${person.roleType != null and person.roleType eq '3' }">selected</c:if> value="3" ><fmt:message key="edit.label.student"/></option>
								</select><br>
							
							<fmt:message key="login.label.firstname" />:<input type="text" name="firstName" value="${person.firstName }" /><br>
							<fmt:message key="login.label.secondname" />:<input type="text" name="secondName" value="${person.secondName }" /><br>
							<fmt:message key="login.label.login" />:<input type="text" name="login" value="${person.login }" /><br>
							<fmt:message key="login.label.password" />: <input type="text" name="password" value="${person.password }" /><br> 
							<input type="submit" value="<fmt:message key="login.button.submit" />" />
						</p>
						
						
						
					</fieldset>
				</form>
			</td>
			
		</tr>
	</table>
<c:if test="${errorNonNumberMessage !=null }"><fmt:message key="edit.label.nonnumbererror"/></c:if>



</body>
</html>