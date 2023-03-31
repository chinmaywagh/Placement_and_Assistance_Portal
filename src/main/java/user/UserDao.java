package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

	public static final String SELECT_USER_BY_ID = "select uid, u_name, u_uname, u_gender, u_dob, u_pass, u_email, u_mobno, u_address, u_status from user where uid =?";
	public static final String SELECT_ALL_USER = "select * from user";

	public static final String DELETE_USER_SQL = "delete from user where uid = ?;";
	public static String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?allowPublicKeyRetrieval=true&useSSL=false";
	public static String jdbcUsername = "root";
	public static String jdbcPassword = "password";

	public static boolean deleteUser(int uid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL);) {
			statement.setInt(1, uid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public static Connection getConnection() {
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

	public static void printSQLException(SQLException ex) {
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

	public UserDao() {
	}

	public List<User> selectAllUser() {
		List<User> user = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String u_name = rs.getString("u_name");
				String u_email = rs.getString("u_email");
				String u_mobno = rs.getString("u_mobno");
				String u_status = rs.getString("u_status");
				user.add(new User(uid, u_name, u_email, u_mobno, u_status));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	public User selectUser(int uid) {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, uid);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String u_name = rs.getString("u_name");
				String u_email = rs.getString("u_email");
				String u_mobno = rs.getString("u_mobno");
				String u_status = rs.getString("u_status");
				user = new User(uid, u_name, u_email, u_mobno, u_status);
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (NullPointerException e) {
		}
		return user;
	}

}