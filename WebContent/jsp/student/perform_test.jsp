<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<html>
<head>

<title><fmt:message key="page.title.perform_test" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>


<div class="login-card">

<table border="3">
<td colspan = "5">
<fmt:message key="label.studentName" />: ${person.firstName }  ${person.secondName }
</td>
<tr/>

<td colspan = "5">
<fmt:message key="label.QuantityOfQuestions.Total" />: ${fn:length(test.questions)}</br>
<fmt:message key="label.QuantityOfQuestions.Remain" />: ${fn:length(questionsIdList)}
</td>

<tr/>
<tr/>
</table>


<table border="3">
				<th> <fmt:message key="test.label.answering.question" />  </th>
					<tr>
						<td><textarea readonly="readonly" rows="4" cols="35" name="questionContent" >${question.content }</textarea></td>
						<!-- td><input type="text" name="questionContent" value="${question.content }" /></td-->
			
					<tr/>

</table>

	<table border="3">

					<th> # </th><th> <fmt:message key="label.vatiantOfAnswer" /> </th><th> <fmt:message key="label.correct" /> </th>
					<tr></tr>
				<form name="show_answer_form" method="POST" action="controller">
					<input name="testId" type="hidden" value="${test.id }" />
					<input name="testResult" type="hidden" value="${testResult }" />
					<input type="hidden" name="command" value="perform_Write_Test" />
					<input type="hidden" name="questionId" value="${ questionsIdList[0] }" />
					
					<c:set var="questionsIdList" value="${questionsIdList }" scope="session" />
						<c:forEach var="elem" items="${question.answers}" >
					<tr>
					<td><c:out value="${ elem.id }" /></td>
					<td>
					
					<input type="hidden" name="answerId" value="${ elem.id }" />
					<textarea readonly="readonly" rows="2" cols="35" name="answerContent" >${ elem.answer } </textarea>
					 <!--  input type="text" name="answerContent" value="${ elem.answer }" /--> 
					</td>
					
					<td>  
							<input type="checkbox"  name="answerVariant" value="${ elem.id }">
					</td>
						
					</tr>
					
						</c:forEach>
</table>
<script>
var gr = document.getElementsByName('answerVariant');
window.onclick = function() {
  for(var i=0; i<gr.length; i++)
    if (gr[i].checked) {
    	document.getElementById('delButton').disabled = false;
    } else {
    	document.getElementById('delButton').disabled = true;
    }
}
</script>


	<input type="submit" id="delButton"  value="<fmt:message key="root.button.nextQuestion" />" />
</form>		



</div>



</body>
</html>