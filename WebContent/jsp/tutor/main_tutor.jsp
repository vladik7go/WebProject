<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>main tutor page</title>
</head>
<body>

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<form name="choose_action_form" method="POST" action="controller">
            <select id="choose_action" name="tutor_action" onchange="submit()">
               	<option selected disabled><fmt:message key="login.lang.select" /></option>
               	<option value="showTests" >Показать все тесты</option>
               	
                <option value="en" ${language == 'en' ? 'selected' : ''}>Редактировать тест</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Удалить тест</option>
            </select>
        </form>


main tutor page
</body>
</html>