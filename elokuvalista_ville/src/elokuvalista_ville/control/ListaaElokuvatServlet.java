package elokuvalista_ville.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import elokuvalista_ville.model.Elokuva;
import elokuvalista_ville.model.dao.ElokuvaDAO;

@WebServlet("/listaa-elokuvat")
public class ListaaElokuvatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String poista = request.getParameter("poista");
		ElokuvaDAO elokuvadao = new ElokuvaDAO();
		ArrayList<Elokuva> elokuvat = elokuvadao.findAll();
		// listaa katsotut elokuvat
		if (poista == null) {
			request.setAttribute("elokuvat", elokuvat);

			String jsp = "/view/elokuvalista.jsp";
			RequestDispatcher dispather = getServletContext()
					.getRequestDispatcher(jsp);
			dispather.forward(request, response);
			// poistaa elokuvan kannasta kun painetaan linkkiä
		} else if (poista != null) {
			int id = new Integer(poista);
			try {
				elokuvadao.poistaElokuva(id);
			} catch (SQLException e) {

				e.printStackTrace();
			}
			ElokuvaDAO elokuvadao1 = new ElokuvaDAO();
			ArrayList<Elokuva> elokuvat1 = elokuvadao1.findAll();
			request.setAttribute("elokuvat", elokuvat1);
			String jsp = "/view/elokuvalista.jsp";
			RequestDispatcher dispather = getServletContext()
					.getRequestDispatcher(jsp);
			dispather.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
