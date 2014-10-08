<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>

<title>edit_question.jsp</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<div class="login-card">

<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="addAnswer" ><fmt:message key="test.label.addAnswer" /></option>
               	<option value="showTests" ><fmt:message key="test.label.showAllTests" /></option>
				<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
           
        </form>
<br/>
<br/>

<form action="controller" method="post">
					<input name="command" type="hidden" value="editWriteQuestion" />
					<input type="hidden" name="questionId" value="${ question.id }" />
					<fmt:message key="edit.label.editing.question" /> :
 					<input type="text" name="questionContent" value="${question.content }" />  
 					<input type="submit" value="<fmt:message key="edit.button.save" />" />					
</form>


					
				<table border="1">

					<th> ID </th><th> Variant of answer </th><th> True of False </th>
				<c:forEach var="elem" items="${question.answers}" >
				
				<form name="edit_answer_form" method="POST" action="controller">
					<input type="hidden" name="command" value="editWriteAnswer" />
					<input type="hidden" name="questionId" value="${ question.id }" />
					<tr>
					<td><c:out value="${ elem.id }" /></td>
					<td>
					
					<input type="hidden" name="answerId" value="${ elem.id }" />
					 <input type="text" name="answerContent" value="${ elem.answer }" /> 
					</td>
					
					<td> <!-- input type="text" name="value" value="${ elem.value }" /--> 
							<select id="choose_value" name="answerValue" >
               						<option <c:if test="${elem.value != null and elem.value eq '1' }">selected</c:if> value="1" ><fmt:message key="edit.label.true"/></option>
               						<option <c:if test="${elem.value != null and elem.value eq '0' }">selected</c:if> value="0" ><fmt:message key="edit.label.false"/></option>
               				</select>
					</td>
					
					<td>
					<input type="submit" value="<fmt:message key="edit.button.save" />"/>
					</td>
</form>
										
					<td>
			<form name="delete_answer_form" method="POST" action="controller">
					<input type="hidden" name="command" value="deleteAnswer" />
					<input type="hidden" name="answerId" value="${ elem.id }" />
					<input type="hidden" name="questionId" value="${ question.id }" />
					<input type="submit" value="<fmt:message key="root.button.delete" />"/>
			</form>
					</td>
					</tr>
					
	</c:forEach>
					
</table>
<c:if test="${errorEmptyFieldMessage !=null }"><fmt:message key="registration.label.emptyfielderror"/></c:if>
<c:if test="${successfullyPerformedAction != null and successfullyPerformedAction eq '1' }"><fmt:message key="edit.label.successfullyPerformedAction"/></c:if>
</div>
</body>
</html>