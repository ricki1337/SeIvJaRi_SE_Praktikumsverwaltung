package Models.Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySql implements DatabaseFunction{
	private Connection connection;
	
	public MySql(){}

	public ResultSet getQuery(String query) throws SQLException {
		Statement stmnt = connection.createStatement();
		System.out.println("MySql-Class: "+query);

		return stmnt.executeQuery(query);
	}

	@Override
	public int setQuery(String query) throws SQLException {
		Statement stmnt = connection.createStatement();
		System.out.println("MySql-Class: "+query);
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
