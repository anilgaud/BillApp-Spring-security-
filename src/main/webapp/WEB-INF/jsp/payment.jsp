<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body><div align="right">Logged In user:<b>${username }</b></left>
	<center>
		Payment Group<br>
		<br>
		<table border="2">
			<th>Group Name</th>
			<th>Payment</th>
			<c:forEach var="grp" items="${ group}">
				<tr>
					<td>${grp.groupname }</td>
					<td><a href="/singlePay?grpid=${grp.id }">Pay</a></td>
				</tr>


			</c:forEach>
		</table>
		<a href="/back">Back</a>&nbsp;&nbsp;&nbsp;
		<a href="/logout">Logout</a>
	</center>
</body>
</html>