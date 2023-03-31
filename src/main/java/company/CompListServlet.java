package company;

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

public class CompListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CompListServlet() {
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
		String cid = request.getParameter("cid");

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/placementcell?useSSL=false", "root", "password");
			PreparedStatement preparedStatement = connection.prepareStatement("select * from company where cid=?");
			preparedStatement.setString(1, cid);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				session.setAttribute("cid", rs.getString("cid"));
				session.setAttribute("c_name", rs.getString("c_name"));
				session.setAttribute("c_uname", rs.getString("c_uname"));
				session.setAttribute("c_pass", rs.getString("c_pass"));
				session.setAttribute("c_email", rs.getString("c_email"));
				session.setAttribute("c_mobno", rs.getString("c_mobno"));
				session.setAttribute("c_address", rs.getString("c_address"));
				dispatcher = request.getRequestDispatcher("comp_profile.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("comp_section.jsp");
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