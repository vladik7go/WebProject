<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="page.title.main_tutor" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>

<div class="login-card">
${resultMap } 


 <c:if test="${testsList == null }">
<c:redirect url="controller">
<c:param name="command" value="show_Tests"/>
<c:param name="successfullyPerformedAction" value="${successfullyPerformedAction}"/>
</c:redirect>
</c:if>

<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="command" onchange="submit()">
               	<option selected disabled><fmt:message key="edit.label.shooseAction" /></option>
               	<option value="show_Tests" ><fmt:message key="test.label.showAllTests" /></option>
               		<c:if test="${role == 'root' or role == 'tutor' }">
               		<option value="show_Persons" ><fmt:message key="edit.label.showAllPersons" /></option>
               		</c:if>
               	<!-- option value=""> <fmt:message key="login.button.tologin" /></option-->
                <option value="Logout"><fmt:message key="login.label.logout" /></option>
            </select>
        </form>
        
 ${role }   
 <c:if test="${testsList != null }">     
      
 <table border="3">
<th> # </th><th> <fmt:message key="edit.label.test.title" /> </th><th> <fmt:message key="edit.labelt.test.description" /> </th><th colspan="2"> <fmt:message key="login.label.command"/> </th>
<c:forEach var="elem" items="${testsList}" >

<tr>
<td><c:out value="${ elem.id }" /></td>
<td><c:out value="${ elem.title }" /></td>
<td><c:out value="${ elem.description }" /></td>

<c:if test="${role == 'tutor' or role == 'root' }">
<td>

<form name="edit_test_form" method="POST" action="controller">
<input type="hidden" name="command" value="edit_Test" />
<input type="hidden" name="testId" value="${ elem.id }" />

<input type="submit" value="<fmt:message key="root.button.edit" />"/>
</form>

</td>
<td>

<form name="delete_test_form" method="POST" action="controller">
<input type="hidden" name="command" value="delete_Test" />
<input type="hidden" name="testId" value="${ elem.id }" />

<input type="submit" value="<fmt:message key="root.button.delete" />"/>
</form>

</td>

</c:if>

<c:if test="${role == 'student' }">
<td>

<form name="perform_test_form" method="POST" action="controller">
<input type="hidden" name="command" value="perform_Test" />
<input type="hidden" name="testId" value="${ elem.id }" />

<input type="submit" value="<fmt:message key="root.button.perform" />"/>
</form>

</td>
</c:if>

</tr>

</c:forEach>
<c:if test="${role == 'tutor' or role == 'root' }"> 
<form name="add_test_form" method="POST" action="controller">
	<input type="hidden" name="command" value="add_Test" />
	<input name="testId" type="hidden" value="${test.id }" />
	
	
					<td>
					=>
					</td>
					
					<td>
					<textarea  required="required" rows="4" cols="25" name="testTitle" placeholder="<fmt:message key="test.label.addTestTitle" />" ></textarea>
					</td>
					
					<td>
					<textarea  required="required" rows="4" cols="25" name="testDescription" placeholder="<fmt:message key="test.label.addTestDescription" />" ></textarea>
					</td>
										
					<td  colspan = "2">
					<input type="submit" value="<fmt:message key="edit.button.add" />"/>
					</td>
	</form>	
</c:if>
</table>

</c:if>
 
<c:if test="${errorEmptyFieldMessage !=null }"><fmt:message key="registration.label.emptyfielderror"/></c:if>
<c:if test="${successfullyPerformedAction != null and successfullyPerformedAction eq '1' }"><fmt:message key="edit.label.successfullyPerformedAction"/></c:if>


</div>

</body>
</html>