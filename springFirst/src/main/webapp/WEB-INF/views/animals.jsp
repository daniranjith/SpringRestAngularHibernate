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
			<td>Animal id</td>
			<td>Animal Name</td>
			<td>Country Of Origin</td>
			<td>Edit</td>
		</tr>
		<c:forEach items="${animalList}" var="animal">
			<tr>
				<td><c:out value="${animal.animalId}" /></td>
				<td><c:out value="${animal.name}" /></td>
				<td><c:out value="${animal.countryOfOrigin}" /></td>
				<td>
					<a href="/update/${animal.animalId}">
					<input type="submit" value="Edit" /></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p>
		<font color="red">${response}</font>
	</p>
	<br/><br/> <a href="/home"> Home </a>
</body>
</html>