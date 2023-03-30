package user;

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

public class UserUpdateServlet extends HttpServlet {
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
		String u_name = request.getParameter("u_name");
		String u_uname = request.getParameter("u_uname");
		String u_gender = request.getParameter("u_gender");
		String u_dob = request.getParameter("u_dob");
		String u_pass = request.getParameter("u_pass");
		String u_email = request.getParameter("u_email");
		String u_mobno = request.getParameter("u_mobno");
		String u_address = request.getParameter("u_address");
		String u_status = request.getParameter("u_status");
		String uid = request.getParameter("uid");

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			String sql = "update user set u_name=?, u_uname=?, u_gender=?, u_dob=?, u_pass=?, u_email=?, u_mobno=?, u_address=?, u_status=? where uid = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, u_name);
			preparedStatement.setString(2, u_uname);
			preparedStatement.setString(3, u_gender);
			preparedStatement.setString(4, u_dob);
			preparedStatement.setString(5, u_pass);
			preparedStatement.setString(6, u_email);
			preparedStatement.setString(7, u_mobno);
			preparedStatement.setString(8, u_address);
			preparedStatement.setString(9, u_status);
			preparedStatement.setString(10, uid);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("user_section.jsp");
			} else {
				request.setAttribute("status", "incomplete");
				dispatcher = request.getRequestDispatcher("user_section.jsp");
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
