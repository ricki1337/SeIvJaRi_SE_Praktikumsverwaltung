package Model;

//Start of user code for imports

//End of user code for imports

public interface DatabaseFunction 
{

// Methods

public Connection connect(String host, int port, String user, String pw, String db) ;

public int setQuery(String Query) ;

public ResultSet getQuery(String query) ;

public void disconnect() ;

// Start of user code for extra methods
// End of user code for extra methods

}