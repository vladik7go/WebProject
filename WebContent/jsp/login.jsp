<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">




<html><head><title><fmt:message key="login.lang.select" /></title></head>
<body>

<c:set var="language" value="${param.language }" scope="session" />
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<!--  fmt:message key="login.lang.select" /-->
aaaaaaaaaa ${language }
<form>
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
<input type="text" name="login" value=""/>
<br/><fmt:message key="login.label.password" />:<br/>
<input type="password" name="password" value=""/>
<br/>

<c:if test="${errorLoginPassMessage != null }"><fmt:message key="login.message.loginerror"/></c:if>
<br/>
<!-- ${errorLoginPassMessage} -->
<br/>
${wrongAction}
<br/>
${nullPage}
<br/>
<input type="submit" value="<fmt:message key="login.button.submit" />"/>
</form>

<form name="registration" method="POST" action="controller">
<input type="hidden" name="command" value="registration" />
<input type="submit" value="<fmt:message key="login.button.newuser" />"/>
</form>
<hr/>
</body></html>