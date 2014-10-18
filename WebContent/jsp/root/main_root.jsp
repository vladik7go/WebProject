<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<html>
<head>

<title><fmt:message key="page.title.main_root" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>

<div class="login-card">



<c:if test="${personsList == null }">
<c:redirect url="controller">
<c:param name="command" value="showPersons"/>
<c:param name="successfullyPerformedAction" value="${successfullyPerformedAction}"/>
<c:param name="errorEmptyResultTableMessage" value="${errorEmptyResultTableMessage}"/>
</c:redirect>
</c:if>

<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="showPersons" ><fmt:message key="edit.label.showAllPersons" /></option>
               	<option value="showTests" ><fmt:message key="test.label.showAllTests" /></option>
				<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
        </form>
        
      
 <c:if test="${personsList != null }">     
      
 <table  border="3">
<th> # </th><th> <fmt:message key="login.label.role"/> </th><th><fmt:message key="login.label.firstname"/> </th><th> <fmt:message key="login.label.secondname"/> </th><th> <fmt:message key="login.label.login"/> </th><th> <fmt:message key="login.label.password"/></th><th colspan="3"> <fmt:message key="login.label.command"/> </th>
<c:forEach var="elem" items="${personsList}" >

<tr>
<td><c:out value="${ elem.id }" /></td>
<td> 
<c:if test="${elem.roleType != null and elem.roleType eq '1' }"><fmt:message key="edit.label.root"/></c:if>
<c:if test="${elem.roleType != null and elem.roleType eq '2' }"><fmt:message key="edit.label.tutor"/></c:if>
<c:if test="${elem.roleType != null and elem.roleType eq '3' }"><fmt:message key="edit.label.student"/></c:if>
</td>
<!-- td><c:out value="${ elem.roleType }" /></td-->
<td><c:out value="${ elem.firstName }" /></td>
<td><c:out value="${ elem.secondName }" /></td>
<td><c:out value="${ elem.login }" /></td>
<!-- td ><c:out value="${ elem.password }" /></td-->

<c:if test="${role == 'root' }">
<td>
<form name="edit_person_form" method="POST" action="controller">
<input type="hidden" name="command" value="editPerson" />
<input type="hidden" name="personId" value="${ elem.id }" />
<input type="submit" value="<fmt:message key="root.button.edit" />"/>
</form>
</td>

<td>
<form name="delete_person_form" method="POST" action="controller">
<input type="hidden" name="command" value="deletePerson" />
<input type="hidden" name="personId" value="${ elem.id }" />
<input type="submit" value="<fmt:message key="root.button.delete" />"/>
</form>
</td>
</c:if>


<td>

<form name="show_results_form" method="POST" action="controller">
<input type="hidden" name="command" value="showResults" />
<input type="hidden" name="personId" value="${ elem.id }" />
<input type="submit" value="<fmt:message key="root.button.show.results" />"/>
</form>

</td>

</tr>

</c:forEach>
</table>

</c:if>

<c:if test="${successfullyPerformedAction != null and successfullyPerformedAction eq '1' }"><fmt:message key="edit.label.successfullyPerformedAction"/></c:if>
<c:if test="${errorEmptyResultTableMessage != null and errorEmptyResultTableMessage eq '1' }"><fmt:message key="message.errorEmptyResultTableMessage"/></c:if>


</div>

</body>
</html>