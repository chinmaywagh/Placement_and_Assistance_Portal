package user;

import java.io.IOException;
import java.io.InputStream;
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
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215)
public class UserRegisterServlet extends HttpServlet {

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
		String u_name = request.getParameter("u_name");
		String u_uname = request.getParameter("u_uname");
		String u_gender = request.getParameter("u_gender");
		String u_dob = request.getParameter("u_dob");
		String u_pass = request.getParameter("u_pass");
		String u_pass2 = request.getParameter("u_pass2");
		String u_email = request.getParameter("u_email");
		String u_mobno = request.getParameter("u_mobno");
		String u_address = request.getParameter("u_address");
		String u_status = request.getParameter("u_status");

		InputStream inputStream = null;

		Part u_resume = request.getPart("u_resume");
		if (u_resume != null) {

			inputStream = u_resume.getInputStream();
		}
		if (u_name == null || u_name.equals("")) {
			request.setAttribute("status", "invalidUname");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		}
		if (u_uname == null || u_uname.equals("")) {
			request.setAttribute("status", "invalidUuname");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		}
		if (u_gender == null || u_gender.equals("")) {
			request.setAttribute("status", "invalidUgender");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		}
		if (u_dob == null || u_dob.equals("")) {
			request.setAttribute("status", "invalidUdob");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		}
		if (u_pass == null || u_pass.equals("")) {
			request.setAttribute("status", "invalidUpass");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		} else if (!u_pass.equals(u_pass2)) {
			request.setAttribute("status", "invalidUpass2");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		}
		if (u_email == null || u_email.equals("")) {
			request.setAttribute("status", "invalidUemail");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		}
		if (u_address == null || u_address.equals("")) {
			request.setAttribute("status", "invalidUaddress");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		}
		if (u_status == null || u_status.equals("")) {
			request.setAttribute("status", "invalidUstatus");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		}
		if (u_mobno == null || u_mobno.equals("")) {
			request.setAttribute("status", "invalidUmobno");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		} else if (u_mobno.length() > 10) {
			request.setAttribute("status", "invalidUmobnolength");
			dispatcher = request.getRequestDispatcher("user_signup.jsp");
			dispatcher.forward(request, response);
		}

		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

			String sql = "INSERT INTO user (u_name, u_uname, u_gender, u_dob, u_pass, u_email, u_mobno, u_address, u_status, u_resume) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, u_name);
			preparedStatement.setString(2, u_uname);
			preparedStatement.setString(3, u_gender);
			preparedStatement.setString(4, u_dob);
			preparedStatement.setString(5, u_pass);
			preparedStatement.setString(6, u_email);
			preparedStatement.setString(7, u_mobno);
			preparedStatement.setString(8, u_address);
			preparedStatement.setString(9, u_status);
			if (inputStream != null) {
				preparedStatement.setBlob(10, inputStream);
			}
			int row = preparedStatement.executeUpdate();
			if (row > 0) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("user_signin.jsp");
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("home.jsp");
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
