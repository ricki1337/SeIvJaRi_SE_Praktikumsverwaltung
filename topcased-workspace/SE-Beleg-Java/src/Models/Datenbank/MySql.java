package Models.Datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySql implements DatabaseFunction{
	private Connection connection;
	
	public MySql(){}

	public ResultSet getQuery(String query) {
		try{
		Statement stmnt = connection.createStatement();
		System.out.println("MySql-Class: "+query);
		return stmnt.executeQuery(query);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int setQuery(String query) {
		try{
			Statement stmnt = connection.createStatement();
			System.out.println("MySql-Class: "+query);
			return stmnt.executeUpdate(query);
			}
			catch(Exception e){
				e.printStackTrace();
				return 0;
			}
	}

	@Override
	public Connection connect(String host, int port, String user, String pw, String db) {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String connectionCommand = "jdbc:mysql://"+host+"/"+db+"?user="+user+"&password="+pw;
			connection = DriverManager.getConnection(connectionCommand);
			return connection;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void disconnect() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
