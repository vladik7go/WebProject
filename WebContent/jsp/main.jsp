<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Welcome</title></head>
<body>
<h3>Welcome</h3>
<hr/>
${user}, hello!

<hr/>
<form name="chooseParser" method="POST" action="controller">
		<input type="hidden" name="command" value="choose" /> Please, choose
		parser:<br />

		<p>
			<select size="4" multiple name="parser">
				<option disabled>Choose parser</option>
				<option value="dom">DOM</option>
				<option selected value="stax">StAX</option>
				<option value="sax">SAX</option>
			</select>
		</p>

		<input type="submit" value="select" />
	</form>
	<hr/>

<form name="Logout" method="POST" action="controller">
<input type="hidden" name="command" value="Logout" />
<input type="submit" value="Logout"/>
</form>
</body></html>