package company;

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

public class CompLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String c_uname = request.getParameter("c_uname");
		String c_pass = request.getParameter("c_pass");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		if (c_uname == null || c_uname.equals("")) {
			request.setAttribute("status", "invalidCuname");
			dispatcher = request.getRequestDispatcher("comp_signin.jsp");
			dispatcher.forward(request, response);
		}
		if (c_pass == null || c_pass.equals("")) {
			request.setAttribute("status", "invalidCpass");
			dispatcher = request.getRequestDispatcher("comp_signin.jsp");
			dispatcher.forward(request, response);
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/placementcell?useSSL=false", "root", "password");
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from company where c_uname = ? and c_pass = ? ");
			preparedStatement.setString(1, c_uname);
			preparedStatement.setString(2, c_pass);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				session.setAttribute("cid", rs.getString("cid"));
				session.setAttribute("c_name", rs.getString("c_name"));
				session.setAttribute("c_uname", rs.getString("c_uname"));
				session.setAttribute("c_email", rs.getString("c_email"));
				session.setAttribute("c_pass", rs.getString("c_pass"));
				session.setAttribute("c_address", rs.getString("c_address"));
				session.setAttribute("c_mobno", rs.getString("c_mobno"));
				request.setAttribute("status", "loggedin");
				dispatcher = request.getRequestDispatcher("comp_signin1.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("comp_signin.jsp");
			}
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}