<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>

<title>Test edit page</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<div class="login-card">

<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="editQuestions" ><fmt:message key="test.label.showAllQuestions" /></option>
               	<option value="showTests" ><fmt:message key="test.label.showAllTests" /></option>
				<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
            <input name="testId" type="hidden" value="${test.id }" />
            ${test.id }
        </form>

<table >
<tr>
<td >
				<form action="controller" method="post">
					<input name="command" type="hidden" value="editWriteTest" />
					<input name="id" type="hidden" value="${test.id }" />
					<fieldset>
						
						<p>
							
							
							<fmt:message key="edit.label.test.title" />:<input type="text" name="title" value="${test.title }" /><br>
							<fmt:message key="edit.labelt.test.description" />:<input type="text" name="description" value="${test.description }" /><br>
							 
							<input type="submit" value="<fmt:message key="edit.button.save" />" />
						</p>
						
						
						
					</fieldset>
				</form>
			</td>
			
		</tr>
	</table>
<c:if test="${errorEmptyFieldMessage !=null }"><fmt:message key="registration.label.emptyfielderror"/></c:if>

</div>
</body>
</html>