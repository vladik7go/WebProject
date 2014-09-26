<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />
<html><head><title>Registration form</title></head>





<body>

<br/>
${errorEmptyFieldMessage}
<br/>


<table>
<tr>
<td class = "alignTop">
				<form action="controller" method="post">
					<input name="command" type="hidden" value="addperson" />
					<fieldset>
						
						<p>
							<fmt:message key="login.label.firstname" />:<input type="text" name="firstName" /><br>
							<fmt:message key="login.label.secondname" />:<input type="text" name="secondName" /><br>
							
							<fmt:message key="login.label.login" />:<input type="text" name="login" /><br>
							<fmt:message key="login.label.password" />: <input type="password" name="password" /><br> 
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

</body>
</html>