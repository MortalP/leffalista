<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="viesti" class="java.lang.String" scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elokuvan lisäys</title>
</head>
<body>
	<h1>Lisää elokuva</h1>
	<form method="post">
		<table>

			<tr>
				<td>Nimi:</td>
				<td><input type="text" value="" name="nimi" /></td>
			</tr>
			<tr>
				<td>Genre:</td>
				<td><select name="genre">
						<option value="dokumentti">Dokumentti</option>
						<option value="draama">Draama</option>
						<option value="komedia">Komedia</option>
						<option value="lyhyt">Lyhyt</option>
						<option value="musikaali">Musikaali</option>
						<option value="seikkailu">Seikkailu</option>
						<option value="toiminta">Toiminta</option>
						<option value="jännitys">Jännitys</option>
						<option value="tieteis">Tieteis</option>
				</select></td>
			</tr>
			<tr>
				<td>Katsottu:</td>
				<td><input type="radio" name="radio" value="katsottu" /></td>
			</tr>
			<tr>
				<td>Uusi:</td>
				<td><input type="radio" name="radio" value="uusi" checked /></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit-button"
					class="submit-button" value="Tallenna" /></td>
			</tr>
		</table>

	</form>

	<br>
	<a href="index.jsp">Etusivulle</a>
	<br>
	<a href="listaa-elokuvat">Lista katsotuista elokuvista</a>
	<br>
	<a href="listaauudet-elokuvat">Lista uusista elokuvista</a>
	<br>


</body>
</html>