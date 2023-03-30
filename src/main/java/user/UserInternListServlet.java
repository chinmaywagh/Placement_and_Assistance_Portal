package user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import company.Intern;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserInternListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserInternListServlet() {
        super();
    }
    public static String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?useSSL=false";
	public static String jdbcUsername = "root";
	public static String jdbcPassword = "password";
	Connection conn = null;
	ResultSet rs = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List < Intern > listIntern = selectAllIntern();
        request.setAttribute("listIntern", listIntern);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_internship.jsp");
        dispatcher.forward(request, response);

	}

	private List<Intern>  selectAllIntern() {
		List<Intern> interns = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/placementcell?useSSL=false", "root", "password");
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from internship ");

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int intern_id = rs.getInt("intern_id");
				String intern_role = rs.getString("intern_role");
				String intern_description = rs.getString("intern_description");
				String intern_type = rs.getString("intern_type");
				String intern_duration = rs.getString("intern_duration");
				String intern_stipend = rs.getString("intern_stipend");
				String intern_location = rs.getString("intern_location");
				String company_name = rs.getString("company_name");

				Intern intern = new Intern(intern_id, intern_role, intern_description, intern_type, intern_duration,
						intern_stipend, intern_location,company_name);
				interns.add(intern);
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
		return interns;

	}
}