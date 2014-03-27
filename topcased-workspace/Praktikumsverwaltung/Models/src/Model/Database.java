package Model;

//Start of user code for imports

//End of user code for imports


public abstract class Database 
{
protected Database ()
{
// Start of user code for the default constructor
super();
// Attributes TODO
// End of user code for the default constructor
}

// Start of user code for parameterized constructors
// End of user code for parameterized constructors

// Methods 

public abstract result getQuery(String query);

public abstract void setQuery(String query);

private abstract void connect();

private abstract void disconnect();

private void informModels()
{
// Start of user code for Database.informModels()
// TODO
// End of user code
}

public void login(observer observer)
{
// Start of user code for Database.login(observer)
// TODO
// End of user code
}

public void logout(observer observer)
{
// Start of user code for Database.logout(observer)
// TODO
// End of user code
}

public static Database init()
{
// Start of user code for Database.init():Database
// TODO
return new Database();
// End of user code
}


// Start of user code for extra methods
// End of user code for extra methods

