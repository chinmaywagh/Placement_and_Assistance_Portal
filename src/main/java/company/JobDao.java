package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobDao {
	private static final String SELECT_JOB_BY_ID = "select  job_role,  job_description, job_type, job_location, job_xp from job where job_id =?";

	private static final String DELETE_JOB_SQL = "delete from job where job_id = ?;";
	private String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	public JobDao() {
	}

	public boolean deleteJob(int job_id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_JOB_SQL);) {
			statement.setInt(1, job_id);
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

	public Job selectJob(int job_id) {
		Job job = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_JOB_BY_ID);) {
			preparedStatement.setInt(1, job_id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String job_role = rs.getString("job_role");
				String job_description = rs.getString("job_description");
				String job_type = rs.getString("job_type");
				String job_location = rs.getString("job_location");
				String job_xp = rs.getString("job_xp");
				job = new Job(job_id, job_role, job_description, job_type, job_location, job_xp);
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (NullPointerException e) {
		}
		return job;
	}

}