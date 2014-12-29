package Models.Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Models.Interfaces.DatabaseTypeFunctions;

/**
 * Implementiert den Adapter für die Anbindung an eine MySql Datenbank.
 */
public class MySql implements DatabaseTypeFunctions{
	private Connection connection;
	
	@Override
	public ResultSet getQuery(String query) throws SQLException {
		System.out.println(query);
		Statement stmnt = connection.createStatement();
		return stmnt.executeQuery(query);
	}

	@Override
	public int setQuery(String query) throws SQLException {
		System.out.println(query);
		Statement stmnt = connection.createStatement();
		return stmnt.executeUpdate(query);
	}

	@Override
	public Connection connect(String host, int port, String user, String pw, String db) throws Exception {
		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionCommand = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pw;
			connection = DriverManager.getConnection(connectionCommand);
			return connection;
		

	}

	@Override
	public void disconnect() {
		try {
			connection.close();
		} catch (Exception e) {}
	}

}
