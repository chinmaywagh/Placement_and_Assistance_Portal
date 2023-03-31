package user;

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
public class UserEduUpdateServlet extends HttpServlet {

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
		String sscper = request.getParameter("sscper");
		String sscpy = request.getParameter("sscpy");
		String hscstream = request.getParameter("hscstream");
		String hscper = request.getParameter("hscper");
		String hscpy = request.getParameter("hscpy");
		String uguni = request.getParameter("uguni");
		String ugcourse = request.getParameter("ugcourse");
		String ugper = request.getParameter("ugper");
		String ugpy = request.getParameter("ugpy");
		String pguni = request.getParameter("pguni");
		String pgcourse = request.getParameter("pgcourse");
		String pgper = request.getParameter("pgper");
		String pgpy = request.getParameter("pgpy");
		String user_uid = request.getParameter("user_uid");

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			String sql = "update user_edu set   sscper = ? , sscpy = ? , hscstream = ? , hscper = ? , hscpy = ? , uguni = ?  , ugcourse = ? , ugper = ? , ugpy = ? , pguni = ?  , pgcourse= ? , pgper= ? , pgpy= ?  where user_uid = ?;";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, sscper);
			preparedStatement.setString(2, sscpy);
			preparedStatement.setString(3, hscstream);
			preparedStatement.setString(4, hscper);
			preparedStatement.setString(5, hscpy);
			preparedStatement.setString(6, uguni);
			preparedStatement.setString(7, ugcourse);
			preparedStatement.setString(8, ugper);
			preparedStatement.setString(9, ugpy);
			preparedStatement.setString(10, pguni);
			preparedStatement.setString(11, pgcourse);
			preparedStatement.setString(12, pgper);
			preparedStatement.setString(13, pgpy);
			preparedStatement.setString(14, user_uid);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				request.setAttribute("status", "success1");
				dispatcher = request.getRequestDispatcher("user_section.jsp");

			} else {
				request.setAttribute("status", "failed1");
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
