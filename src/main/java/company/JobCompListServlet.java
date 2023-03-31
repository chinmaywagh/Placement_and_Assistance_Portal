package company;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class JobCompListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JobCompListServlet() {
		super();
	}

	public static String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?useSSL=false";
	public static String jdbcUsername = "root";
	public static String jdbcPassword = "password";
	Connection conn = null;
	ResultSet rs = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String company_cid = request.getParameter("company_cid1");
		int company_cid1 = Integer.parseInt(company_cid);
		List<Job> job = retrieveJobs(company_cid1);
		session.setAttribute("jobs", job);
		request.getRequestDispatcher("comp_job_crud.jsp").forward(request, response);

	}

	private List<Job> retrieveJobs(int company_cid1) {
		List<Job> jobs = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/placementcell?useSSL=false", "root", "password");
			PreparedStatement preparedStatement = connection.prepareStatement("select * from job where company_cid1=?");

			preparedStatement.setInt(1, company_cid1);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int job_id = rs.getInt("job_id");
				String job_role = rs.getString("job_role");
				String job_type = rs.getString("job_type");
				String job_description = rs.getString("job_description");
				String job_location = rs.getString("job_location");
				String job_xp = rs.getString("job_xp");

				Job job = new Job(job_id, job_role, job_description, job_type, job_location, job_xp, company_cid1);
				jobs.add(job);
			}

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
		return jobs;

	}
}