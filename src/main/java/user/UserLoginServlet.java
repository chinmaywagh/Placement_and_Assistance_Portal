package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String u_uname = request.getParameter("u_uname");
		String u_pass = request.getParameter("u_pass");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;

		if (u_uname == null || u_uname.equals("")) {
			request.setAttribute("status", "invalidUname");
			dispatcher = request.getRequestDispatcher("user_signin.jsp");
			dispatcher.forward(request, response);
		}
		if (u_pass == null || u_pass.equals("")) {
			request.setAttribute("status", "invalidUpass");
			dispatcher = request.getRequestDispatcher("user_signin.jsp");
			dispatcher.forward(request, response);
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/placementcell?useSSL=false", "root", "password");
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from user where u_uname = ? and u_pass = ? ");
			preparedStatement.setString(1, u_uname);
			preparedStatement.setString(2, u_pass);

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
				request.setAttribute("status", "loggedin");
				dispatcher = request.getRequestDispatcher("user_section.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("user_signin.jsp");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}