package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String a_uname = request.getParameter("a_uname");
		String a_pass = request.getParameter("a_pass");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		if (a_uname == null || a_uname.equals("")) {
			request.setAttribute("status", "invalidAuname");
			dispatcher = request.getRequestDispatcher("admin_signin.jsp");
			dispatcher.forward(request, response);
		}
		if (a_pass == null || a_pass.equals("")) {
			request.setAttribute("status", "invalidAupass");
			dispatcher = request.getRequestDispatcher("admin_signin.jsp");
			dispatcher.forward(request, response);
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/placementcell?useSSL=false", "root", "password");
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from admin where a_uname = ? and a_pass = ? ");
			preparedStatement.setString(1, a_uname);
			preparedStatement.setString(2, a_pass);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				session.setAttribute("aid", rs.getString("aid"));
				session.setAttribute("a_name", rs.getString("a_name"));
				session.setAttribute("a_uname", rs.getString("a_uname"));
				session.setAttribute("a_pass", rs.getString("a_pass"));
				session.setAttribute("a_email", rs.getString("a_email"));
				request.setAttribute("status", "loggedin");
				dispatcher = request.getRequestDispatcher("admin_signin1.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("admin_signin.jsp");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}