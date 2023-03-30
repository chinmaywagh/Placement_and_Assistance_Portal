package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompDao {
	private static final String SELECT_COMP_BY_ID = "select cid,c_name, c_email, c_address, c_uname,c_pass,c_mobno from company where cid =?";
	private static final String SELECT_ALL_COMP = "select * from company";

	private static final String DELETE_COMP_SQL = "delete from company where cid = ?;";

	private String jdbcURL = "jdbc:mysql://localhost:3306/placementcell?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	public CompDao() {
	}

	public boolean deleteCompany(int cid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_COMP_SQL);) {
			statement.setInt(1, cid);
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
			e.printStackTrace();
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

	public List<Comp> selectAllComp() {
		List<Comp> comp = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMP);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int cid = rs.getInt("cid");
				String c_name = rs.getString("c_name");
				String c_email = rs.getString("c_email");
				String c_address = rs.getString("c_address");
				String c_mobno = rs.getString("c_mobno");
				comp.add(new Comp(cid, c_name, c_email, c_address, c_mobno));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return comp;
	}

	public Comp selectCompany(int cid) {
		Comp comp = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMP_BY_ID);) {
			preparedStatement.setInt(1, cid);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String c_name = rs.getString("c_name");
				String c_email = rs.getString("c_email");
				String c_address = rs.getString("c_address");
				String c_mobno = rs.getString("c_mobno");
				comp = new Comp(cid, c_name, c_email, c_address, c_mobno);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return comp;
	}

}