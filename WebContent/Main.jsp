<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*"
	import="aleksandar.vuk.pavlovic.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Web mail</title>
</head>
<body>
	<h1>
		Prijavljen korisnik:
		<%=session.getAttribute("userName")%></h1>
	<h2>Sve primljene poruke</h2>
	<table border="1" width="60%">
		<tr bgcolor="lightgrey">
			<th width="10%">Redni broj</th>
			<th width="30%">Od</th>
			<th width="60%">Tema</th>
		</tr>
		<%
			Collection<MailSnippet> snippets = (Collection<MailSnippet>) session.getAttribute("mails");
		%>
		<%
			for (MailSnippet snippet : snippets)
			{
		%>
		<tr>
			<td><%=snippet.id%></td>
			<td><%=snippet.from%></td>
			<td><a href="ReadingMail?id=<%=snippet.id%>"><%=snippet.subject%></a></td>
		</tr>
		<%
			}
		%>
	</table>

	<form method="GET" action="WritingMail">
		<input type="submit" value="Napisi novu poruku">
	</form>
	<a href="Logoff">Odjava</a>
</body>
</html>
