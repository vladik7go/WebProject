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
</table>

<table border="3">
				<th> <fmt:message key="test.label.answering.question" />  </th><th> Command </th>
					<tr>
						<td><textarea rows="4" cols="35" name="questionContent" >${question.content }</textarea></td>
						<!-- td><input type="text" name="questionContent" value="${question.content }" /></td-->
			
					<tr/>

</table>

	<table border="3">

					<th> ID </th><th> Variant of answer </th><th> Correct </th>
					<tr></tr>
				<form name="show_answer_form" method="POST" action="controller">
				<input name="testId" type="hidden" value="${test.id }" />
					<input type="hidden" name="command" value="tempCommand" />
					<input type="hidden" name="questionId" value="${ question.id }" />
			<c:forEach var="elem" items="${question.answers}" >
					<tr>
					<td><c:out value="${ elem.id }" /></td>
					<td>
					
					<input type="hidden" name="answerId" value="${ elem.id }" />
					<textarea readonly="readonly" rows="2" cols="35" name="answerContent" >${ elem.answer } </textarea>
					 <!--  input type="text" name="answerContent" value="${ elem.answer }" /--> 
					</td>
					
					<td>  
							<input type="checkbox" name="answerVariant" value="${ elem.id }">
					</td>
						
					</tr>
					
	</c:forEach>
</table>
	<input type="submit" value="<fmt:message key="edit.button.save" />" />
</form>		
<c:if test="${errorEmptyFieldMessage !=null }"><fmt:message key="registration.label.emptyfielderror"/></br></c:if>
объект тест: ${test }</br>
объект персон: ${person }</br>
роль: ${role }</br>
персон Ид: ${personId }</br>
объект вопрос: ${question } </br>
perform_test.jsp

</div>
</body>
</html>