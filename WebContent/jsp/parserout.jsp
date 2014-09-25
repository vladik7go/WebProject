<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Login</title>

</head>
<body>

	
<h2>Used parser: ${usedParser}
<br/>
Number of entities: ${fn:length(list)}
</h2>

<h2>Managers: </h2>
<table border="1">
<th> Number </th><th> ID </th><th> First name </th><th> Second name </th><th> City </th><th> Bonus </th><th> Password </th>
<c:forEach var="elem" items="${list}" varStatus="status">
<c:if test="${elem['class'].simpleName == 'Manager' }">
<tr>
<td><c:out value="${ status.count }" /></td>
<td><c:out value="${ elem.id }" /></td>
<td><c:out value="${ elem.firstName }" /></td>
<td><c:out value="${ elem.secondName }" /></td>
<td><c:out value="${ elem.city }" /></td>
<td><c:out value="${ elem.bonus }" /></td>
<td><c:out value="${ elem.password }" /></td>
</tr>
</c:if>
</c:forEach>
</table>

<h2>Operators: </h2>
<table border="1">
<th> Number </th><th> ID </th><th> First name </th><th> Second name </th><th> City </th><th> # of tools </th>
<c:forEach var="elem" items="${list}" varStatus="status">
<c:if test="${elem['class'].simpleName == 'Operator' }">
<tr>
<td><c:out value="${ status.count }" /></td>
<td><c:out value="${ elem.id }" /></td>
<td><c:out value="${ elem.firstName }" /></td>
<td><c:out value="${ elem.secondName }" /></td>
<td><c:out value="${ elem.city }" /></td>
<td><c:out value="${ elem.numberOfTools }" /></td>
</tr>
</c:if>
</c:forEach>
</table>


<hr/>
<form name="Logout" method="POST" action="controller">
<input type="hidden" name="command" value="Logout" />
<input type="submit" value="Logout"/>
</form>

<hr/>

</body></html>