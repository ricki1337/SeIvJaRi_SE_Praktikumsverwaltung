package Models.Datenbank;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public interface DatabaseFunction {
	
	public ResultSet getQuery(String query) throws SQLException;

	public int setQuery(String query) throws SQLException;

	public Connection connect(String host, int port, String user, String pw, String db) throws Exception;

	public void disconnect();
}
