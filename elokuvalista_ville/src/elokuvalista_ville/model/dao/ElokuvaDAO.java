package elokuvalista_ville.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import elokuvalista_ville.model.Elokuva;

public class ElokuvaDAO extends DataAccessObject {

	public void addElokuva(Elokuva elokuva) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {
			// lis‰‰ elokuvan katsottuihin
			connection = getConnection();

			String sqlInsert = "INSERT INTO katsotutelokuvat(id, nimi, genre) VALUES (?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setInt(1, elokuva.getId());
			stmtInsert.setString(2, elokuva.getNimi());
			stmtInsert.setString(3, elokuva.getGenre());
			stmtInsert.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}

	// hakee kannasta kaikki katsotut elokuvat
	public ArrayList<Elokuva> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Elokuva> elokuvat = new ArrayList<Elokuva>();
		Elokuva elokuva = null;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT id, nimi, genre FROM katsotutelokuvat;";

			stmt = conn.prepareStatement(sqlSelect);

			rs = stmt.executeQuery();

			while (rs.next()) {
				elokuva = readElokuva(rs);

				elokuvat.add(elokuva);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}

		return elokuvat;
	}

	private Elokuva readElokuva(ResultSet rs) {
		try {

			int id = rs.getInt("id");
			String nimi = rs.getString("nimi");
			String genre = rs.getString("genre");

			return new Elokuva(id, nimi, genre);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// poistaa elokuvan kannasta
	public void poistaElokuva(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtDelete = null;
		try {

			connection = getConnection();

			String sqlDelete = "DELETE FROM katsotutelokuvat WHERE id=?";
			stmtDelete = connection.prepareStatement(sqlDelete);
			stmtDelete.setInt(1, id);

			stmtDelete.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtDelete, connection);
		}
	}

}
