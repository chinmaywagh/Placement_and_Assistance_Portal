package user;

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

public class UserEduListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserEduListServlet() {
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
		String user_uid = request.getParameter("user_uid");

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			String sql = "select * from user_edu where user_uid=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, user_uid);

			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				session.setAttribute("sscper", rs.getString("sscper"));
				session.setAttribute("sscpy", rs.getString("sscpy"));
				session.setAttribute("hscstream", rs.getString("hscstream"));
				session.setAttribute("hscper", rs.getString("hscper"));
				session.setAttribute("hscpy", rs.getString("hscpy"));
				session.setAttribute("uguni", rs.getString("uguni"));
				session.setAttribute("ugcourse", rs.getString("ugcourse"));
				session.setAttribute("ugper", rs.getString("ugper"));
				session.setAttribute("ugpy", rs.getString("ugpy"));
				session.setAttribute("pguni", rs.getString("pguni"));
				session.setAttribute("pgcourse", rs.getString("pgcourse"));
				session.setAttribute("pgper", rs.getString("pgper"));
				session.setAttribute("pgpy", rs.getString("pgpy"));
				session.setAttribute("user_uid", rs.getString("user_uid"));
				dispatcher = request.getRequestDispatcher("user_profile2.jsp");
			} else {
				request.setAttribute("status", "infofirst");
				dispatcher = request.getRequestDispatcher("user_edu_register.jsp");
			}
			dispatcher.forward(request, response);

		} catch (SQLException ex) {
			ex.printStackTrace();
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