<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<title>perform_test.jsp</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />
<div class="login-card">


<table border="3">

<td colspan = "5">
Student name: ${person.firstName }  ${person.secondName }
</td>
<tr/>

<td colspan = "5">
Number of questions: ${fn:length(test.questions)}
</td>

<tr/>
<tr/>

<th> current question number </th><th> Questions </th><th colspan = "2"> Command </th>

<c:forEach var="elem" items="${test.questions}" varStatus="status">

<tr>
<td><c:out value="${ status.count }" /></td>
<td><c:out value="${ elem.content }" /></td>




</tr>

</c:forEach>

</table>











объект тест: ${test }
объект персон: ${person }
роль: ${role }
персон Ид: ${personId }

perform_test.jsp



</div>
</body>
</html>