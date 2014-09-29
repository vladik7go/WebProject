<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>main root page</title>
</head>
<body>

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="login.lang.select" /></option>
               	<option value="showPersons" >Показать всех пользователей</option>
               	
                <option value="en" ${language == 'en' ? 'selected' : ''}>Редактировать тест</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Удалить тест</option>
            </select>
        </form>
        
      
 <table border="1">
<th> ID </th><th> Role_type </th><th> First name </th><th> Second name </th><th> login </th><th> Password </th>
<c:forEach var="elem" items="${personsList}" >

<tr>

<td><c:out value="${ elem.id }" /></td>
<td><c:out value="${ elem.roleType }" /></td>
<td><c:out value="${ elem.firstName }" /></td>
<td><c:out value="${ elem.secondName }" /></td>
<td><c:out value="${ elem.login }" /></td>
<td><c:out value="${ elem.password }" /></td>
<td>

<form name="edit_person_form" method="POST" action="controller">
<input type="hidden" name="command" value="editPerson" />
<input type="hidden" name="personId" value="${ elem.id }" />

<input type="submit" value="<fmt:message key="root.button.edit" />"/>
</form>

</td>
</tr>

</c:forEach>
</table>

main root page
</body>
</html>