<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>main_student.jsp</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />
<div class="login-card">

<c:if test="${testsList == null }">
<c:redirect url="controller">
<c:param name="command" value="showTests"/>
<c:param name="successfullyPerformedAction" value="${successfullyPerformedAction}"/>
</c:redirect>
</c:if>




<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="showTests" ><fmt:message key="test.label.showAllTests" /></option>
               	<option value="showResult" ><fmt:message key="test.label.showResult" /></option>
               		<c:if test="${role == 'root' }">
               		<option value="showPersons" ><fmt:message key="edit.label.showAllPersons" /></option>
               		</c:if>
               	<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
             <input name="personId" type="hidden" value="${person.id }" />
        </form>

${role }   
<table border="3">

<td colspan = "4">
Student name: ${person.firstName }  ${person.secondName }
</td>
<tr/>

<td colspan = "4">
Number of tests: ${fn:length(testsList)}
</td>

<tr/>
<tr/>


<th> ID </th><th> Title </th><th> Description </th><th colspan="2"> Command </th>
<c:forEach var="elem" items="${testsList}" >

<tr>
<td><c:out value="${ elem.id }" /></td>
<td><c:out value="${ elem.title }" /></td>
<td><c:out value="${ elem.description }" /></td>

<td>
<form name="perform_test_form" method="POST" action="controller">
<input type="hidden" name="command" value="performTest" />
<input type="hidden" name="testId" value="${ elem.id }" />
<input type="submit" value="<fmt:message key="root.button.perform" />"/>
</form>
</td>

</tr>

</c:forEach>

</table>

main student page



</div>
</body>
</html>