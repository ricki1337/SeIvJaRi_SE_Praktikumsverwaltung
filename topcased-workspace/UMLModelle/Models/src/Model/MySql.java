package Model;

//Start of user code for imports

//End of user code for imports


public class MySql implements DatabaseFunction
{
public MySql ()
{
// Start of user code for the default constructor
super();
// End of user code for the default constructor
}

// Start of user code for parameterized constructors
// End of user code for parameterized constructors
// Methods for implemented interfaces
 

public Connection connect(String host, int port, String user, String pw, String db)
{
// Start of user code for MySql.connect(String,int,String,String,String):Connection
// TODO
return (Connection) new Object(); 
// End of user code
}


public int setQuery(String Query)
{
// Start of user code for MySql.setQuery(String):int
// TODO
return 0; 
// End of user code
}


public ResultSet getQuery(String query)
{
// Start of user code for MySql.getQuery(String):ResultSet
// TODO
return (ResultSet) new Object(); 
// End of user code
}


public void disconnect()
{
// Start of user code for MySql.disconnect()
// TODO
// End of user code
}


// Start of user code for extra methods
// End of user code for extra methods

