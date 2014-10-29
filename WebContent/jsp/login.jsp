<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />


<html>
<head>
<title><fmt:message key="page.title.login" /></title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<div class="login-card">
<c:set var="language" value="${param.language }" scope="session" />


<!--  fmt:message key="login.lang.select" /-->

<form action="controller">
            <select id="language" name="language" onchange="submit()">
               	<option selected disabled><fmt:message key="login.lang.select" /></option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
            </select>
        </form>
<form name="loginForm" method="POST" action="controller">
<input type="hidden" name="command" value="login" />

<input type="hidden" name="language" value="${language }" />

<fmt:message key="login.label.login" />:<br/>
<input type="text" name="login" value="" required="required" pattern="[a-zA-Zа-яА-Я0-9@.]{1,45}$"/>
<br/><fmt:message key="login.label.password" />:<br/>
<input type="password" name="password" value="" required="required" pattern="[a-zA-Zа-яА-Я0-9@.]{1,45}$"/>
<br/>

<c:if test="${errorLoginPassMessage != null }"><fmt:message key="message.loginerror"/></c:if>
<br/>
<!-- ${errorLoginPassMessage} -->
<br/>

<c:if test="${wrongAction != null }"><fmt:message key="message.wrongaction"/></c:if>

<br/>

<c:if test="${nullPage != null }"><fmt:message key="message.nullpage"/></c:if>
<br/>
<input type="submit" value="<fmt:message key="login.button.submit" />"/>
</form>

<form name="registration" method="POST" action="controller">
<input type="hidden" name="command" value="registration" />
<input type="submit" value="<fmt:message key="login.button.newuser" />"/>
</form>
<hr/>
</div>
</body></html>