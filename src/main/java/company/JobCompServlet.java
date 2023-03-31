package company;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JobCompServlet extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	private JobDao jobDao;

	private void deleteJob(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int job_id = Integer.parseInt(request.getParameter("job_id"));
		jobDao.deleteJob(job_id);
		response.sendRedirect("comp_job_crud.jsp");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {

			case "/JobCompServletDelete":
				deleteJob(request, response);
				break;
			case "/JobCompServletEdit":
				showEditForm(request, response);
				break;
			case "/JobCompServletEdit1":
				showEditForm1(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		} catch (NumberFormatException e) {
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void init() {
		jobDao = new JobDao();
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int job_id = Integer.parseInt(request.getParameter("job_id"));
		Job existingJob = jobDao.selectJob(job_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("comp_job_profile.jsp");
		request.setAttribute("job", existingJob);
		dispatcher.forward(request, response);
	}

	private void showEditForm1(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int job_id = Integer.parseInt(request.getParameter("job_id"));
		Job existingJob = jobDao.selectJob(job_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("comp_job_update.jsp");
		request.setAttribute("job", existingJob);
		dispatcher.forward(request, response);
	}

}
