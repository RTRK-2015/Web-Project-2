<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Web mail</title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="js/loadMail.js"></script>
</head>
<body>
	<h1>Prijavljen korisnik: <%=session.getAttribute("userName")%></h1>
	<h2>Citanje poruke</h2>
	<table class="show bordered">
		<tr>
			<td width="20%">Poruka br.</td>
			<td width="80%" id="mailid"></td>
		</tr>
		<tr>
			<td>Od:</td>
			<td id="mailfrom"></td>
		</tr>
		<tr>
			<td>Tema:</td>
			<td id="mailsubject"></td>
		</tr>
		<tr>
			<td>Tekst poruke:</td>
			<td id="mailbody"></td>
		</tr>
	</table>
	<p>
		<a href="Main">Spisak svih poruka</a>
	</p>
</body>
</html>