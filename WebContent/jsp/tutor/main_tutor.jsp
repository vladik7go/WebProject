<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>main tutor page</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>

<div class="login-card">

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="showTests" ><fmt:message key="test.label.showAllTests" /></option>
               	<option value="addTest" ><fmt:message key="test.label.addTest" /></option>
				<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
        </form>
        
    
 <c:if test="${testsList != null }">     
      gggggggggggggggggggggggggggg
 <table border="1">
<th> ID </th><th> Title </th><th> Description </th>
<c:forEach var="elem" items="${testsList}" >

<tr>
<td><c:out value="${ elem.id }" /></td>
<td><c:out value="${ elem.title }" /></td>
<td><c:out value="${ elem.description }" /></td>

<td>

<form name="edit_person_form" method="POST" action="controller">
<input type="hidden" name="command" value="editTest" />
<input type="hidden" name="testId" value="${ elem.id }" />

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
</tr>

</c:forEach>
</table>

</c:if>
 

<c:if test="${successfullyPerformedAction != null and successfullyPerformedAction eq '1' }"><fmt:message key="edit.label.successfullyPerformedAction"/></c:if>


</div>

</body>
</html>