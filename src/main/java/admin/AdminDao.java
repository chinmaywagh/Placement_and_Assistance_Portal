package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
	private static final String SELECT_ADMIN_BY_ID = "select aid,a_name,a_uname,a_pass,a_email from admin where aid =?";
	private static final String SELECT_ALL_ADMIN = "select * from admin";
	private static final String DELETE_ADMIN_SQL = "delete from admin where aid = ?;";
	private String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?allowPublicKeyRetrieval=true&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	public AdminDao() {
	}

	public boolean deleteAdmin(int aid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_SQL);) {
			statement.setInt(1, aid);
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

	public Admin selectAdmin(int aid) {
		Admin admin = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADMIN_BY_ID);) {
			preparedStatement.setInt(1, aid);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String a_name = rs.getString("a_name");
				String a_uname = rs.getString("a_uname");
				String a_pass = rs.getString("a_pass");
				String a_email = rs.getString("a_email");
				admin = new Admin(aid, a_name, a_uname, a_pass, a_email);
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (NullPointerException e) {
		}
		return admin;
	}

	public List<Admin> selectAllAdmin() {
		List<Admin> admin = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADMIN);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int aid = rs.getInt("aid");
				String a_name = rs.getString("a_name");
				String a_uname = rs.getString("a_uname");
				String a_pass = rs.getString("a_pass");
				String a_email = rs.getString("a_email");
				admin.add(new Admin(aid, a_name, a_uname, a_pass, a_email));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return admin;
	}

}