package company;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InternCompServlet extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	private InternDao internDao;

	private void deleteIntern(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int intern_id = Integer.parseInt(request.getParameter("intern_id"));
		internDao.deleteIntern(intern_id);
		response.sendRedirect("comp_intern_crud.jsp");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {

			case "/InternCompServletDelete":
				deleteIntern(request, response);
				break;
			case "/InternCompServletEdit":
				showEditForm(request, response);
				break;
			case "/InternCompServletEdit1":
				showEditForm1(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (NumberFormatException e) {
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init() {
		internDao = new InternDao();
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int intern_id = Integer.parseInt(request.getParameter("intern_id"));
		Intern existingIntern = internDao.selectIntern(intern_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("comp_intern_profile.jsp");
		request.setAttribute("intern", existingIntern);
		dispatcher.forward(request, response);
	}

	private void showEditForm1(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int intern_id = Integer.parseInt(request.getParameter("intern_id"));
		Intern existingIntern = internDao.selectIntern(intern_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("comp_intern_update.jsp");
		request.setAttribute("intern", existingIntern);
		dispatcher.forward(request, response);
	}

}
