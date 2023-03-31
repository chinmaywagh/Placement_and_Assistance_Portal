package user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	private UserDao userDao;

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int uid = Integer.parseInt(request.getParameter("uid"));
		UserDao.deleteUser(uid);
		response.sendRedirect("UserServletList");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try {
			switch (action) {
			case "/UserServletNew":
				showNewForm(request, response);
				break;

			case "/UserServletDelete":
				deleteUser(request, response);
				break;

			default:
				listUser(request, response);
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
		userDao = new UserDao();
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<User> listUser = userDao.selectAllUser();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_user_crud.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin_user_signup.jsp");
		dispatcher.forward(request, response);
	}

}