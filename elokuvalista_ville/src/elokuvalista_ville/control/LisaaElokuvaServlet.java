package elokuvalista_ville.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elokuvalista_ville.model.Elokuva;
import elokuvalista_ville.model.UusiElokuva;
import elokuvalista_ville.model.dao.ElokuvaDAO;
import elokuvalista_ville.model.dao.UusiElokuvaDAO;

@WebServlet("/lisaa-elokuva")
public class LisaaElokuvaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String jsp = "/view/elokuvalomake.jsp";
		RequestDispatcher dispather = getServletContext().getRequestDispatcher(
				jsp);
		dispather.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// lis‰‰ elokuva tietokantaan valitun radion mukaan
		try {
			String tietokantaan = request.getParameter("radio");
			// Lis‰t‰‰n katsotunelokuvan tiedot tietokantaan
			if (tietokantaan.equals("katsottu")) {

				String nimi = request.getParameter("nimi");
				String genre = request.getParameter("genre");
				int id = 0;

				Elokuva elokuva = new Elokuva(id, nimi, genre);

				ElokuvaDAO elokuvadao = new ElokuvaDAO();

				elokuvadao.addElokuva(elokuva);
				String jsp = "/view/elokuvalomake.jsp";
				RequestDispatcher dispather = getServletContext()
						.getRequestDispatcher(jsp);
				dispather.forward(request, response);
			}
			// Lis‰t‰‰n uuden elokuvan tiedot tietokantaan
			else if (tietokantaan.equals("uusi")) {
				String nimi = request.getParameter("nimi");
				String genre = request.getParameter("genre");
				int id = 0;

				UusiElokuva uusielokuva = new UusiElokuva(id, nimi, genre);

				UusiElokuvaDAO uusielokuvadao = new UusiElokuvaDAO();

				uusielokuvadao.addUusiElokuva(uusielokuva);
				String jsp = "/view/elokuvalomake.jsp";
				RequestDispatcher dispather = getServletContext()
						.getRequestDispatcher(jsp);
				dispather.forward(request, response);
			}

		}

		catch (SQLException e) {

			System.out
					.println("Sovelluksessa tapahtui virhe " + e.getMessage());
		}

	}

}
