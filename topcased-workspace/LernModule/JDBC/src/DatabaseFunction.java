import java.sql.*;


public interface DatabaseFunction {
	
	public ResultSet getQuery(String query);

	public int setQuery(String query);

	public Connection connect(String host, int port, String user, String pw, String db);

	public void disconnect();
}
