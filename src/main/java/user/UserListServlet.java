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

public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserListServlet() {
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
		String uid = request.getParameter("uid");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/placementcell?useSSL=false", "root", "password");
			PreparedStatement preparedStatement = connection.prepareStatement("select * from user where uid=?");
			preparedStatement.setString(1, uid);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				session.setAttribute("uid", rs.getString("uid"));
				session.setAttribute("u_name", rs.getString("u_name"));
				session.setAttribute("u_uname", rs.getString("u_uname"));
				session.setAttribute("u_gender", rs.getString("u_gender"));
				session.setAttribute("u_dob", rs.getString("u_dob"));
				session.setAttribute("u_pass", rs.getString("u_pass"));
				session.setAttribute("u_email", rs.getString("u_email"));
				session.setAttribute("u_mobno", rs.getString("u_mobno"));
				session.setAttribute("u_address", rs.getString("u_address"));
				session.setAttribute("u_status", rs.getString("u_status"));
				dispatcher = request.getRequestDispatcher("user_profile.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("user_section.jsp");
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