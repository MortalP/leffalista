<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="elokuvalista_ville.model.Elokuva"%>
<jsp:useBean id="elokuvat" type="java.util.ArrayList<Elokuva>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista elokuvista</title>
</head>
<body>
	<h1>Katsotut elokuvat</h1>
	<a href="index.jsp">Etusivulle</a>
	<br>

	<table>

		<%
			Elokuva elokuva = null; 
			for(int i = 0; i < elokuvat.size(); i++) { 
				elokuva = elokuvat.get(i);
		%>
		<tr>
			<td><%=elokuva.getNimi()%></td>
			<td><%=elokuva.getGenre()%></td>
			<td><a href="listaa-elokuvat?poista=<%=elokuva.getId()%>">Poista</a></td>
		</tr>
		<%
			}
		%>


	</table>
</body>
</html>