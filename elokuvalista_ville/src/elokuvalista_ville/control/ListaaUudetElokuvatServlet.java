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

import elokuvalista_ville.model.UusiElokuva;

import elokuvalista_ville.model.dao.UusiElokuvaDAO;

@WebServlet("/listaauudet-elokuvat")
public class ListaaUudetElokuvatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String siirra = request.getParameter("siirra");
		UusiElokuvaDAO uusielokuvadao = new UusiElokuvaDAO();
		ArrayList<UusiElokuva> uusielokuvat = uusielokuvadao.findAll();
		// listaa kaikki uudet elokuvat
		if (siirra == null) {
			request.setAttribute("uusielokuvat", uusielokuvat);

			String jsp = "/view/uusienelokuvienlista.jsp";
			RequestDispatcher dispather = getServletContext()
					.getRequestDispatcher(jsp);
			dispather.forward(request, response);

			// poistaa elokuvan uusienelokuvien taulusta ja lis‰‰ sen
			// katsottuihin linkin painamisen j‰lkeen
		} else if (siirra != null) {
			int id = new Integer(siirra);
			try {
				uusielokuvadao.siirraElokuva(id);
			} catch (SQLException e) {

				e.printStackTrace();
			}
			UusiElokuvaDAO uusielokuvadao1 = new UusiElokuvaDAO();
			ArrayList<UusiElokuva> uusielokuvat1 = uusielokuvadao1.findAll();
			request.setAttribute("uusielokuvat", uusielokuvat1);
			String jsp = "/view/uusienelokuvienlista.jsp";
			RequestDispatcher dispather = getServletContext()
					.getRequestDispatcher(jsp);
			dispather.forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
