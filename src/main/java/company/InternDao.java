package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InternDao {
	private static final String SELECT_INTERN_BY_ID = "select  intern_role,  intern_description, intern_type, intern_duration, intern_stipend, intern_location, company_name from internship where intern_id =?;";
	private static final String DELETE_INTERN_SQL = "delete from internship where intern_id = ?;";
	private String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	public InternDao() {
	}

	public boolean deleteIntern(int intern_id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_INTERN_SQL);) {
			statement.setInt(1, intern_id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	public Intern selectIntern(int intern_id) {
		Intern intern = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INTERN_BY_ID);) {
			preparedStatement.setInt(1, intern_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String intern_role = rs.getString("intern_role");
				String intern_description = rs.getString("intern_description");
				String intern_type = rs.getString("intern_type");
				String intern_duration = rs.getString("intern_duration");
				String intern_stipend = rs.getString("intern_stipend");
				String intern_location = rs.getString("intern_location");
				String company_name = rs.getString("company_name");


				intern = new Intern(intern_id, intern_role, intern_description, intern_type, intern_duration,
						intern_stipend, intern_location, company_name);
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (NullPointerException e) {
		}
		return intern;
	}

}