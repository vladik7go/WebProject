<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<html>
<head>

<title><fmt:message key="page.title.edit_question" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>


<div class="login-card">

<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="edit_Questions" ><fmt:message key="test.label.showAllQuestions" /></option>
               	<option value="show_Tests" ><fmt:message key="test.label.showAllTests" /></option>
               		<c:if test="${role == 'root' }">
               		<option value="show_Persons" ><fmt:message key="edit.label.showAllPersons" /></option>
               		</c:if>
				<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
           <input name="testId" type="hidden" value="${testId }" />
           <input type="hidden" name="questionId" value="${ question.id }" />
           
        </form>



<form action="controller" method="post">
					<input name="command" type="hidden" value="edit_Write_Question" />
					<input name="testId" type="hidden" value="${testId }" />
          			 <input type="hidden" name="questionId" value="${ question.id }" />
<table border="3">
				<th> <fmt:message key="edit.label.editing.question" />  </th><th> <fmt:message key="login.label.command"/> </th>
					<tr>
						<td><textarea required="required" rows="4" cols="35" name="questionContent" >${question.content }</textarea></td>
						<!-- td><input type="text" name="questionContent" value="${question.content }" /></td-->
						<td><input type="submit" value="<fmt:message key="edit.button.save" />" />	</td>
				
					<tr/>
</form>
</table>

<br/>
<br/>

					
	<table border="3">

					<th> # </th><th> <fmt:message key="label.vatiantOfAnswer" /> </th><th> <fmt:message key="label.correct" /> </th><th colspan = "2"> <fmt:message key="login.label.command"/> </th>
					<tr></tr>
				<c:forEach var="elem" items="${question.answers}" >
				
				<form name="edit_answer_form" method="POST" action="controller">
				<input name="testId" type="hidden" value="${testId }" />
					<input type="hidden" name="command" value="edit_Write_Answer" />
					<input type="hidden" name="questionId" value="${ question.id }" />
					<tr>
					<td><c:out value="${ elem.id }" /></td>
					<td>
					
					<input type="hidden" name="answerId" value="${ elem.id }" />
					<textarea rows="2" cols="35" name="answerContent" >${ elem.answer } </textarea>
					 <!--  input type="text" name="answerContent" value="${ elem.answer }" /--> 
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
					<input type="hidden" name="command" value="delete_Answer" />
					<input type="hidden" name="answerId" value="${ elem.id }" />
					<input type="hidden" name="questionId" value="${ question.id }" />
					<input name="testId" type="hidden" value="${testId }" />
					<input type="submit" value="<fmt:message key="root.button.delete" />"/>
			</form>
					</td>
					</tr>
					
	</c:forEach>
	
	<form name="add_answer_form" method="POST" action="controller">
	<input type="hidden" name="command" value="add_Answer" />
	<input name="testId" type="hidden" value="${testId }" />
	<input type="hidden" name="questionId" value="${ question.id }" />
	
					<td>
					=>
					</td>
					
					<td>
					<textarea required="required" rows="4" cols="35" name="answerContent" placeholder="<fmt:message key="test.label.addAnswer" />" ></textarea>
					<!-- input type="text" name="answerContent" placeholder="<fmt:message key="test.label.addAnswer" />" /-->
					</td>
					
					<td>
					<select id="choose_value" name="answerValue" >
               						<!-- option selected disabled value="0"  ><fmt:message key="edit.button.select"/></option-->
               						<option <c:if test="${elem.value != null and elem.value eq '0' }">selected</c:if> value="0" ><fmt:message key="edit.label.false"/></option>
               						<option <c:if test="${elem.value != null and elem.value eq '1' }">selected</c:if> value="1" ><fmt:message key="edit.label.true"/></option>
               				</select>
					</td>
					
					<td  colspan = "2">
					<input type="submit" value="<fmt:message key="edit.button.add" />"/>
					</td>
	</form>		
					
</table>
<c:if test="${errorEmptyFieldMessage !=null }"><fmt:message key="registration.label.emptyfielderror"/></c:if>
<c:if test="${successfullyPerformedAction != null and successfullyPerformedAction eq '1' }"><fmt:message key="edit.label.successfullyPerformedAction"/></c:if>
</div>
</body>
</html>