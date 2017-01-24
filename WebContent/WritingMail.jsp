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
	<script type="text/javascript" src="js/sendMail.js"></script>
</head>
<body>
	<h1>
		Prijavljen korisnik:
		<%=session.getAttribute("userName")%></h1>
	<h2>Pisanje poruke</h2>
	<table class="show unbordered">
		<tr>
			<td>Ime primaoca:</td>
			<td><input type="text" id="mailto" placeholder="To..."></td>
		</tr>
		<tr>
			<td>Tema:</td>
			<td><input id="mailsubject" type="text" placeholder="Subject..."></td>
		</tr>
		<tr>
			<td>Tekst poruke:</td>
			<td><textarea id="mailbody" rows="7" placeholder="Text..."></textarea></td>
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