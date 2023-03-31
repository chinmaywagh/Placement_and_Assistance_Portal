package admin;

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
public class AdminCompRegisterServlet extends HttpServlet {

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
		String c_name = request.getParameter("c_name");
		String c_uname = request.getParameter("c_uname");
		String c_pass = request.getParameter("c_pass");
		String c_pass2 = request.getParameter("c_pass2");
		String c_email = request.getParameter("c_email");
		String c_mobno = request.getParameter("c_mobno");
		String c_address = request.getParameter("c_address");

		if (c_name == null || c_name.equals("")) {
			request.setAttribute("status", "invalidUname");
			dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
			dispatcher.forward(request, response);
		}
		if (c_uname == null || c_uname.equals("")) {
			request.setAttribute("status", "invalidUuname");
			dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
			dispatcher.forward(request, response);
		}

		if (c_pass == null || c_pass.equals("")) {
			request.setAttribute("status", "invalidUpass");
			dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
			dispatcher.forward(request, response);
		} else if (!c_pass.equals(c_pass2)) {
			request.setAttribute("status", "invalidUpass2");
			dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
			dispatcher.forward(request, response);
		}
		if (c_email == null || c_email.equals("")) {
			request.setAttribute("status", "invalidUemail");
			dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
			dispatcher.forward(request, response);
		}
		if (c_address == null || c_address.equals("")) {
			request.setAttribute("status", "invalidUaddress");
			dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
			dispatcher.forward(request, response);
		}

		if (c_mobno == null || c_mobno.equals("")) {
			request.setAttribute("status", "invalidUmobno");
			dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
			dispatcher.forward(request, response);
		} else if (c_mobno.length() > 10) {
			request.setAttribute("status", "invalidUmobnolength");
			dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
			dispatcher.forward(request, response);
		}

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			String sql = "INSERT INTO company (c_name, c_uname, c_pass, c_email, c_mobno, c_address) values ( ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, c_name);
			preparedStatement.setString(2, c_uname);
			preparedStatement.setString(3, c_pass);
			preparedStatement.setString(4, c_email);
			preparedStatement.setString(5, c_mobno);
			preparedStatement.setString(6, c_address);

			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("admin_comp_crud.jsp");
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
