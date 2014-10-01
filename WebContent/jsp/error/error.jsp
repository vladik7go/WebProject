<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head><title>Error Page</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen">
</head>
<body>
<div class="login-card">
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.errorData.throwable}

<form name="toLoginPage" method="POST" action="controller">
						<input type="hidden" name="command" value="" />
						<input type="submit" value="Return to Login page"/>
</form>
</div>
</body></html>