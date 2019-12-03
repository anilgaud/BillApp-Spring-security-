<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Details</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="js/add.js" ></script>
</head>
<body><div align="right">Logged In user:<b>${userName }</b></div>
	<%-- ${message } --%>
	<br>

	<center>
		<b>welcome ${userName}</b>
		<!-- <a href="/link">click</a> -->
		<br> <br> <a href="/createGroup">CREATE GROUP</a>&nbsp;&nbsp;&nbsp;
		<!-- <a href="/editGroup">EDIT GROUP</a>&nbsp;&nbsp;&nbsp;
 <a href="/deleteGroup">DELETE GROUP</a>&nbsp;&nbsp;&nbsp; -->
		<a href="/myGroup?username=${userName }" onclick="return chkGroup()">MY GROUP</a>&nbsp;&nbsp;&nbsp;
		<a href="/pay">PAY</a></br> <br> <br> <a href="/logout">Logout</a>
		<input type="hidden" id="msg" value="${message }"/>
	</center>
</body>
</html>