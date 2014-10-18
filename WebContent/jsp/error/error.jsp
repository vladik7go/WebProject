<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${language }" />
<fmt:setBundle basename="resources.messages_bundle" />


<html>
<head><title>Error Page</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<div class="login-card">
<fmt:message key="page.error.content.request_from" /> ${pageContext.errorData.requestURI} <fmt:message key="page.error.content.is_failed" />
<br/>
<fmt:message key="page.error.content.servlet_name_or_type" />: ${pageContext.errorData.servletName}
<br/>
<fmt:message key="page.error.content.status_code" />: ${pageContext.errorData.statusCode}
<br/>
<fmt:message key="page.error.content.exception" />: ${pageContext.errorData.throwable}

<form name="toLoginPage" method="POST" action="controller">
						<input type="hidden" name="command" value="" />
						<input type="submit" value="<fmt:message key="login.button.tologin" />"/>
</form>
</div>
</body></html>