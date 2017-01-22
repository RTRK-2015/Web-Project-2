<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Web mail</title>
</head>
<body>
	<h1>Prijavljen korisnik: <%=session.getAttribute("userName")%></h1>
	<h2>Sve primljene poruke</h2>
	<table border="1" width="60%">
		<tr bgcolor="lightgrey">
			<th width="10%">Redni broj</th>
			<th width="30%">Od</th>
			<th width="60%">Tema</th>
		</tr>
		<tr>
			<td><a href="citanje_poruke.html">1</a></td>
			<td><a href="citanje_poruke.html">mika</a></td>
			<td><a href="citanje_poruke.html">Pozdrav</a></td>
		</tr>
		<tr>
			<td><a href="citanje_poruke.html">2</a></td>
			<td><a href="citanje_poruke.html">pera</a></td>
			<td><a href="citanje_poruke.html">Ocene</a></td>
		</tr>
		<tr>
			<td><a href="citanje_poruke.html">3</a></td>
			<td><a href="citanje_poruke.html">djura</a></td>
			<td><a href="citanje_poruke.html">Kako si</a></td>
		</tr>
	</table>
	<p>
		<a href="pisanje_poruke.html">Napisi novu poruku</a>
	</p>
	<p>
		<a href="index.html">Odjava</a>
	</p>
</body>
</html>
