<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
		<h2>Registration Details</h2>
		<form:form name="register" method="post" action="/register"
			modelAttribute="user">
			<table>
				<tr>
					<td>User:</td>
					<td><form:input type='text' name='username' path="username" /></td>
					<td><form:errors path="username" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><form:input type='password' name='password'
							path="password" /></td>
							<td><form:errors path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><form:input type='text' name='name' path="name" /></td>
					<td><form:errors path="name" cssClass="error" /></td>
				</tr>
				<tr>
					<td>E-mail:</td>
					<td><form:input type='email' name='email' path="email" /></td>
					<td><form:errors path="email" cssClass="error" /></td>
				</tr>


				<tr>
					<td><input name="submit" type="submit" value="Register" /></td>
				</tr>





			</table>
		</form:form>
	</center>
</body>
</html>