package company;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CompServlet extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	private CompDao compDao;

	private void deleteCompany(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		compDao.deleteCompany(cid);
		response.sendRedirect("CompServletList");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/CompServletNew":
				showNewForm(request, response);
				break;

			case "/CompServletDelete":
				deleteCompany(request, response);
				break;

			default:
				listComp(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init() {
		compDao = new CompDao();
	}

	private void listComp(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Comp> listComp = compDao.selectAllComp();
		request.setAttribute("listComp", listComp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_comp_signup.jsp");
		dispatcher.forward(request, response);
	}

}