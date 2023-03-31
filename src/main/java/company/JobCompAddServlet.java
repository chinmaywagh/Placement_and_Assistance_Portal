package company;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(maxFileSize = 16177215)
public class JobCompAddServlet extends HttpServlet {

	private static final long serialVersionUID = 102831973239L;

	public static String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?useSSL=false";
	public static String jdbcUsername = "root";
	public static String jdbcPassword = "password";
	Connection conn = null;
	String message = null;
	RequestDispatcher dispatcher = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String job_role = request.getParameter("job_role");
		String job_description = request.getParameter("job_description");
		String job_type = request.getParameter("job_type");
		String job_location = request.getParameter("job_location");
		String job_xp = request.getParameter("job_xp");
		String company_cid1 = request.getParameter("company_cid1");

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			String sql = "INSERT INTO job (job_role, job_description, job_type, job_location, job_xp, company_cid1) values ( ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, job_role);
			preparedStatement.setString(2, job_description);
			preparedStatement.setString(3, job_type);
			preparedStatement.setString(4, job_location);
			preparedStatement.setString(5, job_xp);
			preparedStatement.setString(6, company_cid1);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				request.setAttribute("status", "jobadded");
				dispatcher = request.getRequestDispatcher("comp_job_crud.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("comp_job_crud.jsp");
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
