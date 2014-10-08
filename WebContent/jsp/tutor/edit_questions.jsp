<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>

<title>edit_questions.jsp</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<div class="login-card">

Page: edit questions.
<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="addQuestion" ><fmt:message key="test.label.addQuestion" /></option>
               	<option value="showTests" ><fmt:message key="test.label.showAllTests" /></option>
				<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
           
        </form>

Number of questions: ${fn:length(test.questions)}
Test title: ${test.title }
<table border="1">
<th> ID </th><th> Questions </th>

<c:forEach var="elem" items="${test.questions}" >

<tr>
<td><c:out value="${ elem.id }" /></td>
<td><c:out value="${ elem.content }" /></td>

<td>

<form name="edit_question_form" method="POST" action="controller">
<input type="hidden" name="command" value="editQuestion" />
<input type="hidden" name="questionId" value="${ elem.id }" />
<input type="hidden" name="testId" value="${ test.id }" />

<input type="submit" value="<fmt:message key="root.button.edit" />"/>
</form>

</td>
<td>

<form name="delete_person_form" method="POST" action="controller">
<input type="hidden" name="command" value="deleteQuestion" />
<input type="hidden" name="questionId" value="${ elem.id }" />

<input type="submit" value="<fmt:message key="root.button.delete" />"/>
</form>

</td>
</tr>

</c:forEach>
</table>

</div>
</body>
</html>