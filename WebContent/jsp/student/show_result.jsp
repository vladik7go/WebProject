<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>show_result.jsp</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />
<div class="login-card">

<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="writeResult" ><fmt:message key="test.label.writeResult" /></option>
               	<option value="showTests" ><fmt:message key="test.label.showAllTests" /></option>
               		<c:if test="${role == 'root' }">
               		<option value="showPersons" ><fmt:message key="edit.label.showAllPersons" /></option>
               		</c:if>
               	<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
             <input name="personId" type="hidden" value="${person.id }" />
             <input name="testId" type="hidden" value="${test.id }" />
             <input name="testMark" type="hidden" value="${testResultFinal }" />
        </form>


<h1><fmt:message key="label.TestCompleted" /></h1>
<table border="3">

<th >
<fmt:message key="edit.label.test.title" />:
</th>
<th>
<fmt:message key="edit.labelt.test.description" />: 
</th>
<th>
<fmt:message key="label.mark" />: 
</th>
<tr>

<td>${test.title }</td>
<td>${test.description}</td>
<td>   ${testResultFinal }</td>
</tr>
</table>

<c:if test="${successfullyPerformedAction != null and successfullyPerformedAction eq '1' }"><fmt:message key="edit.label.successfullyPerformedAction"/></c:if>

</div>
</body>
</html>