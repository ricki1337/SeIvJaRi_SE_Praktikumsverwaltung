package Models.Datenbank;


import java.sql.Connection;
import java.sql.ResultSet;


public interface DatabaseFunction {
	
	public ResultSet getQuery(String query);

	public int setQuery(String query);

	public Connection connect(String host, int port, String user, String pw, String db) throws Exception;

	public void disconnect();
}
