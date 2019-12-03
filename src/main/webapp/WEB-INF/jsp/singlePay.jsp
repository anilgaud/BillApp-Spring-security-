<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="right">
		Logged In user:<b>${username }</b>
		</left>
		<center>
			<br> <br>
			<form:form action="/doPaymentSingleGroup" method="POST"
				modelAttribute="payment">
				<table border="2">
					<th>GroupName</th>
					<th>Member Name</th>
					<th>Amount</th>
					<c:forEach var="pay" items="${paymentlist }">

						<tr>
							<td><form:input type="text" value="${pay.groupname }"
									path="groupname" disabled="true" name="grp" /></td>

							<td><form:input type="text" value="${pay.membername }"
									path="membername" disabled="true" /></td>
							<c:choose>
								<c:when test="${username eq pay.membername }">
									<td><input type="text" value="${pay.amount }"
										path="amount" name="amount" /></td>
								</c:when>
								<c:otherwise>
									<td><form:input type="text" value="${pay.amount }"
											path="amount" disabled="true" /></td>
								</c:otherwise>
							</c:choose>

						</tr>


					</c:forEach>
					<input type="hidden" name="groupname" value="${groupname}" />
					<input type="hidden" name="membername" value="${username }" />
					<tr>
						<td colspan="3" style="text-align: center;"><input
							type="submit" value="Pay" /></td>
					</tr>

				</table>
			</form:form>
			<a href="/back">Back</a> &nbsp;&nbsp;&nbsp; <a href="/logout">Logout</a>
		</center>
</body>
</html>