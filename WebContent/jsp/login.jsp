<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="language" value="${param.language }" scope="session" />
<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />

<html><head><title><fmt:message key="login.lang.select" /></title></head>
<body>
<fmt:message key="login.lang.select" />

<form>
            <select id="language" name="language" onchange="submit()">
               
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
            </select>
        </form>


<form name="loginForm" method="POST" action="controller">
<input type="hidden" name="command" value="login" />
<fmt:message key="login.label.login" />:<br/>
<input type="text" name="login" value=""/>
<br/><fmt:message key="login.label.password" />:<br/>
<input type="password" name="password" value=""/>
<br/>
${errorLoginPassMessage}
<br/>
${wrongAction}
<br/>
${nullPage}
<br/>
<input type="submit" value="Log in"/>
</form><hr/>
</body></html>