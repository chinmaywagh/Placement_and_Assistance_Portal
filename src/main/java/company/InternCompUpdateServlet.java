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

public class InternCompUpdateServlet extends HttpServlet {
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
		String intern_id = request.getParameter("intern_id");
		String intern_role = request.getParameter("intern_role");
		String intern_description = request.getParameter("intern_description");
		String intern_type = request.getParameter("intern_type");
		String intern_duration = request.getParameter("intern_duration");
		String intern_stipend = request.getParameter("intern_stipend");
		String intern_location = request.getParameter("intern_location");

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			String sql = "update internship set intern_role=?, intern_description=?, intern_type=?, intern_duration=?, intern_stipend=?, intern_location where intern_id = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, intern_id);
			preparedStatement.setString(2, intern_role);
			preparedStatement.setString(3, intern_description);
			preparedStatement.setString(4, intern_type);
			preparedStatement.setString(5, intern_duration);
			preparedStatement.setString(6, intern_stipend);
			preparedStatement.setString(7, intern_location);
			System.out.println(preparedStatement);
			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("comp_intern_crud.jsp");
			} else {
				request.setAttribute("status", "incomplete");
				dispatcher = request.getRequestDispatcher("comp_intern_crud.jsp");
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
