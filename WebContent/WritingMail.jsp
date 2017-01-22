<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="sendMessage.js"></script>
</head>
<body>
	<h1>
		Prijavljen korisnik:
		<%=session.getAttribute("userName")%></h1>
	<h2>Pisanje poruke</h2>
	<table border="0" width="60%">
		<tr>
			<td>Ime primaoca:</td>
			<td><input type="text" id="mailto"></td>
		</tr>
		<tr>
			<td>Tema:</td>
			<td><input id="mailsubject" type="text"></td>
		</tr>
		<tr>
			<td>Tekst poruke:</td>
			<td><textarea id="mailbody" rows="7"></textarea></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><button id="send">Posalji</button></td>
		</tr>
	</table>
	<p>
		<a href="Main">Spisak svih poruka</a>
	</p>
</body>
</html>