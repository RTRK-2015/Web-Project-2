<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*"
	import="aleksandar.vuk.pavlovic.model.*"
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Web mail</title>
	<link type="text/css" rel="stylesheet" href="css/main.css">
</head>
<body>
	<h1>Prijavljen korisnik: <%=session.getAttribute("userName")%></h1>
	<h2>Sve primljene poruke</h2>
	<table class="show bordered" id="main-table">
		<tr>
			<th width="10%">Redni broj</th>
			<th width="30%">Od</th>
			<th width="60%">Tema</th>
		</tr>
		<%
			@SuppressWarnings("unchecked")
			Collection<MailSnippet> snippets = (Collection<MailSnippet>) session.getAttribute("mails");
		%>
		<% for (MailSnippet snippet : snippets)
		{%>
		<tr>
			<td><%=snippet.id%></td>
			<td><%=snippet.from%></td>
			<td><a href="ReadingMail?id=<%=snippet.id%>"><%=snippet.subject%></a></td>
		</tr>
		<%}%>
	</table>

	<form method="GET" action="WritingMail">
		<input type="submit" value="Napisi novu poruku">
	</form>
	<p>
		<a href="Logoff">Odjava</a>
	</p>
</body>
</html>
