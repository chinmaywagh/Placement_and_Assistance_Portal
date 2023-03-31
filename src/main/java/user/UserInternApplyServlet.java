package user;

import java.io.IOException;
import java.io.InputStream;
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


public class UserInternApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;

	public static String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?useSSL=false";
	public static String jdbcUsername = "root";
	public static String jdbcPassword = "password";
	Connection conn = null;
	String message = null;
	RequestDispatcher dispatcher = null;   
    public UserInternApplyServlet() {
        super();
    }


	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");

		try {
			conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		    
		    String sql = "SELECT uid, u_name, u_email, u_resume FROM user WHERE uid=?;";
		    PreparedStatement preparedStatement = conn.prepareStatement(sql);
			System.out.println(preparedStatement);

			preparedStatement.setString(1, uid);
		   
		    ResultSet result  = preparedStatement.executeQuery();
		    
            if (result.next()) {
		    	String u_name = result .getString("u_name");
		    	String u_email = result .getString("u_email");
                InputStream inputStream = result.getBinaryStream("file");

		        
		        sql = "INSERT INTO internship (user_uid, user_u_name, user_u_email, user_u_resume) values (?, ?, ?, ?);";
		        PreparedStatement insertStmt = conn.prepareStatement(sql);
				System.out.println(insertStmt);

		        insertStmt.setString(1, uid);
		        insertStmt.setString(2, u_name);
		        insertStmt.setString(3 ,u_email);
		        if (inputStream != null) {
		        	insertStmt.setBlob(4, inputStream);
                }		    
		        insertStmt.executeUpdate();
		        request.setAttribute("status", "applied");
				dispatcher = request.getRequestDispatcher("user_internship.jsp");
		    }
		    
		    conn.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		
}
	}
