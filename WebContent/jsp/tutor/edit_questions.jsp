<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<html>
<head>

<title><fmt:message key="page.title.edit_questions" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>


<div class="login-card">


<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="show_Tests" ><fmt:message key="test.label.showAllTests" /></option>
               		<c:if test="${role == 'root' }">
               		<option value="show_Persons" ><fmt:message key="edit.label.showAllPersons" /></option>
               		</c:if>
				<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
           <input name="testId" type="hidden" value="${test.id }" />
           
        </form>


<table border="3">
<td colspan = "4">
<fmt:message key="edit.label.test.title" />: ${test.title }
</td>
<tr/>

<td colspan = "4">
<fmt:message key="label.QuantityOfQuestions.Total" />: ${fn:length(test.questions)}
</td>

<tr/>
<tr/>


<th> # </th><th> <fmt:message key="label.Questions" /> </th><th colspan = "2"> <fmt:message key="login.label.command" /> </th>

<c:forEach var="elem" items="${test.questions}" >

<tr>
<td><c:out value="${ elem.id }" /></td>
<td><c:out value="${ elem.content }" /></td>

<td>

<form name="edit_question_form" method="POST" action="controller">
<input type="hidden" name="command" value="edit_Question" />
<input type="hidden" name="questionId" value="${ elem.id }" />
<input type="hidden" name="testId" value="${ test.id }" />

<input type="submit" value="<fmt:message key="root.button.edit" />"/>
</form>

</td>
<td>

<form name="delete_person_form" method="POST" action="controller">
<input type="hidden" name="command" value="delete_Question" />
<input type="hidden" name="questionId" value="${ elem.id }" />
<input type="hidden" name="testId" value="${ test.id }" />
<input type="submit" value="<fmt:message key="root.button.delete" />"/>
</form>

</td>
</tr>

</c:forEach>

<form name="add_question_form" method="POST" action="controller">
	<input type="hidden" name="command" value="add_Question" />
	<input name="testId" type="hidden" value="${test.id }" />
	
	
					<td>
					=>
					</td>
					
					<td>
					<textarea required="required" rows="4" cols="35" name="questionContent" placeholder="<fmt:message key="test.label.addQuestion" />" ></textarea>
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