package company;

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

public class CompUpdateServlet extends HttpServlet {
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
		String c_name = request.getParameter("c_name");
		String c_uname = request.getParameter("c_uname");
		String c_pass = request.getParameter("c_pass");
		String c_email = request.getParameter("c_email");
		String c_mobno = request.getParameter("c_mobno");
		String c_address = request.getParameter("c_address");
		String cid = request.getParameter("cid");

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			String sql = "update company set c_name=?, c_uname=?, c_pass=?, c_email=?, c_mobno=?, c_address=? where cid = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, c_name);
			preparedStatement.setString(2, c_uname);
			preparedStatement.setString(3, c_pass);
			preparedStatement.setString(4, c_email);
			preparedStatement.setString(5, c_mobno);
			preparedStatement.setString(6, c_address);
			preparedStatement.setString(7, cid);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("comp_section.jsp");
			} else {
				request.setAttribute("status", "incomplete");
				dispatcher = request.getRequestDispatcher("comp_section.jsp");
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
