<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to my Spring POC !!</title>
</head>
<body>
	<table border="1" align="center" width="500px">
		<tr>
			<td>Request</td>
			<td>Date</td>
		</tr>
		<c:forEach items="${auditList}" var="audit">
			<tr>
				<td><c:out value="${audit.request}" /></td>
				<td><c:out value="${audit.date}" /></td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/> <a href="/home"> Home </a>
</body>
</html>