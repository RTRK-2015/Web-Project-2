<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Web mail</title>
	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript" src="loadMessage.js"></script>
</head>
<body>
	<h1>Prijavljen korisnik: <%=session.getAttribute("userName")%></h1>
	<h2>Citanje poruke</h2>
	<table border="1" width="60%">
		<tr>
			<td width="20%">Poruka br.</td>
			<td id="idnum" width="80%"></td>
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