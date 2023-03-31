package admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?useSSL=false";
	public static String jdbcUsername = "root";
	public static String jdbcPassword = "password";
	Connection conn = null;
	String message = null;
	RequestDispatcher dispatcher = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String a_name = request.getParameter("a_name");
		String a_uname = request.getParameter("a_uname");
		String a_pass = request.getParameter("a_pass");
		String a_email = request.getParameter("a_email");
		String aid = request.getParameter("aid");

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			String sql = "update admin set a_name=?, a_uname=?, a_pass=?, a_email=? where aid = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, a_name);
			preparedStatement.setString(2, a_uname);
			preparedStatement.setString(3, a_pass);
			preparedStatement.setString(4, a_email);
			preparedStatement.setString(5, aid);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("admin_section.jsp");
			} else {
				request.setAttribute("status", "incomplete");
				dispatcher = request.getRequestDispatcher("admin_section.jsp");
			}
			dispatcher.forward(request, response);
		} catch (SQLException ex) {
			message = "ERROR: " + ex.getMessage();
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			request.setAttribute("Message", message);

		}
	}
}
