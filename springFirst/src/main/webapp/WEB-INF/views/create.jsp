<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome to my Spring POC</title>
</head>
<body>
	<form action="/create" method="POST">
		<table border="1" align="center" width="500px">
			<tr>
				<td class="textAlign">Animal Name :</td>
				<td><input name="name" type="text" /></td>
			</tr>
			<tr>
				<td class="textAlign">Country :</td>
				<td><input name="countryOfOrigin" type="text" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" /></td>
			</tr>
		</table>
		<p>
			<font color="red">${response}</font>
		</p>
		<br/><br/> <a href="/home"> Home </a>
	</form>
</body>
</html>