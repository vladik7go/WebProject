<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>show_results.jsp</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />
<div class="login-card">

<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="showTests" ><fmt:message key="test.label.showAllTests" /></option>
               		<c:if test="${role == 'root' or role == 'tutor' }">
               		<option value="showPersons" ><fmt:message key="edit.label.showAllPersons" /></option>
               		</c:if>
               	<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
        </form>

${role }   
<table border="3">

<td colspan = "5">
<fmt:message key="label.studentName" />: ${resultsList[0][0]} ${resultsList[0][1]}
</td>
<tr/>

<td colspan = "5">
<fmt:message key="label.nmbrOfPerformedTests" />: ${fn:length(resultsList)}
</td>

<tr/>
<tr/>

<th> <fmt:message key="edit.label.test.title" /> </th><th> <fmt:message key="edit.labelt.test.description" /> </th><th> <fmt:message key="label.mark" /> </th>
<c:forEach var="elem" items="${resultsList}" varStatus="status" >
<tr>

<td><c:out value="${ elem[2]}" /></td>
<td><c:out value="${ elem[3]}" /></td>
<td><c:out value="${ elem[4]}" /></td>

<tr/>
</c:forEach>

</table>

</div>
</body>
</html>