<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%-- <%@include file="logout.jsp" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="js/add.js"></script>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>
<body>
	<div align="right">
		Logged In user:<b>${username }</b>
		</left>
		<center>
			<h2>Create Group</h2>
			<form:form action="/saveGroup" method="POST" modelAttribute="group">
				<table>
					<tr>
						<td>Enter Group Name</td>
						<td><form:input type="text" path="groupname" /></td>
						<td><form:errors path="groupname" cssClass="error" /></td>
					</tr>
	
					<tr id="m1">
						<td>Members Name</td>

						<td><form:input type="text" path="membername" /></td>
						<td><form:errors path="membername" cssClass="error" /></td>
						<td>e.g. abc,xyz</td>
						<input type="button" value="+" onclick="fs()" />
					</tr>
				<tr id="nm"></tr>
					<form:input type="hidden" path="id" value="${grpid }" />
					<tr>
						<td colspan="2"><input type="submit" value="Create" /></td>
					</tr>


				</table>
			</form:form>
			<br> <a href="/back">Back</a>&nbsp;&nbsp;&nbsp; <a
				href="/logout">Logout</a>
		</center>
</body>
</html>