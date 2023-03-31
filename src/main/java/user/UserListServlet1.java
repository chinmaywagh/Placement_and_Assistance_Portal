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

public class UserListServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserListServlet1() {
        super();
    }
    public static String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?useSSL=false";
	public static String jdbcUsername = "root";
	public static String jdbcPassword = "password";
	Connection conn = null;
	ResultSet rs = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
	

	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/placementcell?useSSL=false", "root", "password");
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from user ");

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				 rs.getInt("uid");
				   RequestDispatcher dispatcher = request.getRequestDispatcher("user_internship.jsp");
			        dispatcher.forward(request, response);

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

	}
}