<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<SCRIPT type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</SCRIPT>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>
<body>
	<center>

		<h1>Login</h1>
		${Message }
		<form name='login' action="/checkLogin" method='POST'>
			<table>
				<div class="error">${error }</div>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username' /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td><input name="submit" type="submit" value="Login" /></td>
				</tr>
			</table>
			</form>
				<a href="/registerPage">Click to register</a>
	</center>
</body>
</html>