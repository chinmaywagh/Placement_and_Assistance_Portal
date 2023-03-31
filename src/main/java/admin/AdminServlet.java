package admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	private AdminDao adminDao;

	private void deleteAdmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int aid = Integer.parseInt(request.getParameter("aid"));
		adminDao.deleteAdmin(aid);
		response.sendRedirect("AdminServletList");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/AdminServletNew":
				showNewForm(request, response);
				break;
			case "/AdminServletDelete":
				deleteAdmin(request, response);
				break;
			default:
				listAdmin(request, response);
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
		adminDao = new AdminDao();
	}

	private void listAdmin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Admin> listAdmin = adminDao.selectAllAdmin();
		request.setAttribute("listAdmin", listAdmin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_crud.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_signup.jsp");
		dispatcher.forward(request, response);
	}

}