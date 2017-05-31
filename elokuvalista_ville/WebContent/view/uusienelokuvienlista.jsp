<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="elokuvalista_ville.model.UusiElokuva"%>
<jsp:useBean id="uusielokuvat" type="java.util.ArrayList<UusiElokuva>"
	scope="request" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uudet elokuvat</title>
</head>
<body>

	<h1>Uudet elokuvat</h1>
	<a href="index.jsp">Etusivulle</a>
	<table>


		<%
			UusiElokuva uusielokuva = null; 
			for(int i = 0; i < uusielokuvat.size(); i++) { 
				uusielokuva = uusielokuvat.get(i);
		%>
		<tr>
			<td><%=uusielokuva.getNimi()%></td>
			<td><%=uusielokuva.getGenre()%></td>
			<td><a
				href="listaauudet-elokuvat?siirra=<%=uusielokuva.getId()%>">Siirrä
					katsottuihin</a></td>
		</tr>
		<%
			}
		%>


	</table>

</body>
</html>