package elokuvalista_ville.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import elokuvalista_ville.model.UusiElokuva;

public class UusiElokuvaDAO extends DataAccessObject {

	// lis‰‰ elokuvan uusiin elokuviin
	public void addUusiElokuva(UusiElokuva uusielokuva) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtInsert = null;

		try {

			connection = getConnection();

			String sqlInsert = "INSERT INTO uudetelokuvat(id, nimi, genre) VALUES (?, ?, ?)";
			stmtInsert = connection.prepareStatement(sqlInsert);
			stmtInsert.setInt(1, uusielokuva.getId());
			stmtInsert.setString(2, uusielokuva.getNimi());
			stmtInsert.setString(3, uusielokuva.getGenre());
			stmtInsert.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtInsert, connection);
		}
	}

	// hakee kannasta kaikki uudet elokuvat
	public ArrayList<UusiElokuva> findAll() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<UusiElokuva> uusielokuvat = new ArrayList<UusiElokuva>();
		UusiElokuva uusielokuva = null;
		try {

			conn = getConnection();

			String sqlSelect = "SELECT id, nimi, genre FROM uudetelokuvat;";

			stmt = conn.prepareStatement(sqlSelect);

			rs = stmt.executeQuery();

			while (rs.next()) {
				uusielokuva = readUusiElokuva(rs);

				uusielokuvat.add(uusielokuva);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(rs, stmt, conn);
		}

		return uusielokuvat;
	}

	private UusiElokuva readUusiElokuva(ResultSet rs) {
		try {

			int id = rs.getInt("id");
			String nimi = rs.getString("nimi");
			String genre = rs.getString("genre");

			return new UusiElokuva(id, nimi, genre);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// poistaa elokuvan uusienelokuvien taulusta ja lis‰‰ katsottuihin
	// elokuviin.
	public void siirraElokuva(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement stmtMove = null;
		PreparedStatement stmtDel = null;
		try {

			connection = getConnection();

			String sqlMove = "INSERT INTO katsotutelokuvat(nimi, genre) SELECT nimi, genre FROM uudetelokuvat WHERE id = ?";

			stmtMove = connection.prepareStatement(sqlMove);

			stmtMove.setInt(1, id);

			stmtMove.executeUpdate();

			String sqlDel = "DELETE FROM uudetelokuvat WHERE id = ?";
			stmtDel = connection.prepareStatement(sqlDel);

			stmtDel.setInt(1, id);

			stmtDel.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			close(stmtMove, connection);
			close(stmtDel, connection);
		}
	}

}
