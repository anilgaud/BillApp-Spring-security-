<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Group Details</title>
<script type="text/javascript">
	function deletee() {
		var r = confirm("Are you sure want to delete?");
		if (r == true)
			return true;
		else
			return false;
	}
</script>
</head>
<body>
	<div align="right">
		Logged In user:<b>${username }</b>
		</left>
		<center>
			<a href="/downloadGroupPdf">Export Data</a><br> <br>
			<table border="2">
				<th>Group Name</th>

				<th>Group Members</th>
				<th>Action</th>
				<c:forEach var="gl" items="${grouplist}">
					<tr>
						<td>${gl.groupname }</td>
						<td>${gl.membername }</td>
						<td><a href="/editGroup?grpid=${gl.id }">Edit</a> &nbsp; <a
							href="/deleteGroup?grpid=${gl.id }" onclick="return deletee()">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
			<br> <a href="/back">Back</a>&nbsp;&nbsp;&nbsp; <a
				href="/logout">Logout</a>
		</center>
</body>
</html>