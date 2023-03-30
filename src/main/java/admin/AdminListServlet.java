package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminListServlet() {
		super();
	}

	public static String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?useSSL=false";
	public static String jdbcUsername = "root";
	public static String jdbcPassword = "password";
	Connection conn = null;
	ResultSet rs = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		String aid = request.getParameter("aid");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/placementcell?useSSL=false", "root", "password");
			PreparedStatement preparedStatement = connection.prepareStatement("select * from admin where aid=?");
			preparedStatement.setString(1, aid);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				session.setAttribute("aid", rs.getString("aid"));
				session.setAttribute("a_name", rs.getString("a_name"));
				session.setAttribute("a_uname", rs.getString("a_uname"));
				session.setAttribute("a_pass", rs.getString("a_pass"));
				session.setAttribute("a_email", rs.getString("a_email"));
				dispatcher = request.getRequestDispatcher("admin_profile.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("admin_section.jsp");
			}
			dispatcher.forward(request, response);

		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

		}

	}
}