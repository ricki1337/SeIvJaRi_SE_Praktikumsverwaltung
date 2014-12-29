package Models.Datenbank;

import java.sql.*;
import java.util.*;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import Controller.ErrorManager;
import Models.Interfaces.DatabaseTypeFunctions;
import Models.Interfaces.Observer;

/**
 * Stellt die zentrale Schnittstelle zur Datenbank dar.<br>
 * �bernimmt s�mtliche Kommunikation mit der Datenbank.<br>
 * Ist als Singleton implementiert.
 *
 */
public class Database
{
	private static Database instance = new Database();
	
	protected DatabaseTypeFunctions db;
	
	private String dbHost = null;
	private int dbPort = 0;
	private String dbName = null;
	private String dbPassword = null;
	private String dbUser = null;
	private String dbType = null;
	
	
	private static Vector<Observer> observerList;
	
	protected ArrayList<String> tables;
	
	/**
	 * Initialisiert die wichtigsten Klassenvariablen.
	 */
	private Database (){
		observerList = new Vector<Observer>();
		tables = new ArrayList<String>();
	}
	
	/**
	 * Die Instanz darf nicht kopiert werden.
	 */
	public Database clone(){ 
		try {
		throw new AssertionError("Von Database darf nur eine Instanz existieren!");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * Informiert alle registrierten Observer, dass sich Daten ge�ndert haben.<br>
	 * Die ge�nderten Tabellen werden mit �bergeben und m�ssen von den Observern abgeglichen werden.
	 * 
	 * @param changedTables	Tabellennamen der aktualisierten Tabellen.
	 */
	private void informModels(String[] changedTables)
	{
		for(int i=0;i<observerList.size();i++)
			observerList.elementAt(i).refresh(changedTables);
	}
	
	/**
	 * Registriert den �bergebenen {@link Models.Interfaces.Observer} in interner Liste.<br>
	 * Der Observer wird bei �nderungen in der Datenbank informiert.
	 * 
	 * @param observer	Objekt, welches das Interface Observer implementiert
	 * @see 	Models.Interfaces.Observer
	 */
	public void login(Observer observer)
	{
		observerList.add(observer);
	}
	
	/**
	 * Entfernt den �bergebenen {@link Models.Interfaces.Observer} aus der interner Liste.<br>
	 * Der Observer bekommt keine Benachrichtigungen bei �nderungen der Datenbank mehr.
	 * 
	 * @param observer	Objekt, welches das Interface Observer implementiert
	 * @see 			Models.Interfaces.Observer
	 */
	public void logout(Observer observer)
	{
		observerList.remove(observer);
	}
	
	/**
	 * Liefert die Singleton Instanz von Database
	 * 
	 * @return	Instanz von Database
	 */
	public static Database getInstance()
	{
		if(instance == null)
			instance = new Database();
		return instance;
	}
	
	/**
	 * F�hrt den �bergebenen Sqlquery auf der Datenbank aus und liefert das Ergebnis in einem {@link ResultSet} zur�ck.
	 * @param sqlQuery	Sqlquery, welches auf der Datenbank ausgef�hrt werden soll.
	 * @return		Ergebnis des Sqlquery.
	 * @see	ResulSet
	 */
	public ResultSet getQuery(String sqlQuery) {
		ResultSet result = null;
		try{
			if(sqlQuery.toLowerCase().contains("update") || sqlQuery.toLowerCase().contains("insert") || sqlQuery.toLowerCase().contains("delete"))
				throw new Exception("Es d�rfen hier nur SELECT Querys ausgef�hrt werden.");
			result = this.db.getQuery(sqlQuery);
		}
		catch(CommunicationsException e) {
			this.db = null;
			try {
				connect(dbType, dbHost, dbPort, dbUser, dbPassword, dbName);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return getQuery(sqlQuery);
		}
		catch(Exception e){
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				result = getQuery(sqlQuery);
			
		}
		return result;
	}

	/**
	 * F�hrt den �bergebenen INSERT oder UPDATE Sqlquery auf der Datenbank aus<br>
	 * und liefert die Zahl der betroffenen Datens�tze zur�ck.<br>
	 * Nach der erfolgreichen �nderung werden alle Models informiert.
	 * 
	 * @param sqlQuery	Sqlquery, welches auf der Datenbank ausgef�hrt werden soll.
	 * @return			Anzahl der von der Sqlquery betroffenen Datens�tze.
	 */
	public int setQueryandInformModels(String sqlQuery) {
		int tmp = 0;
		try{
			if(sqlQuery.toLowerCase().contains("select") || sqlQuery.toLowerCase().contains("show"))
				throw new Exception("Es d�rfen hier keine SELECT Querys ausgef�hrt werden.");
			tmp = this.db.setQuery(sqlQuery);
		}
		catch(CommunicationsException e) {
			this.db = null;
			connect(dbType, dbHost, dbPort, dbUser, dbPassword, dbName);
			return setQueryandInformModels(sqlQuery);
		}
		catch(Exception e){
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				tmp = setQueryandInformModels(sqlQuery);
			
		}
		finally {
			informModels(getChangedTables(sqlQuery));
			return tmp;
		}
	}
	
	/**
	 * F�hrt den �bergebenen INSERT oder UPDATE Sqlquery auf der Datenbank aus<br>
	 * und liefert die Zahl der betroffenen Datens�tze zur�ck.<br>
	 * Betroffene Models werden NICHT informiert.
	 * 
	 * @param sqlQuery	Sqlquery, welches auf der Datenbank ausgef�hrt werden soll.
	 * @return			Anzahl der von der Sqlquery betroffenen Datens�tze.
	 */
	public int setQuery(String query) {
		int tmp = 0;
		try{
			if(query.toLowerCase().contains("select"))
				throw new Exception("Es d�rfen hier keine SELECT Querys ausgef�hrt werden.");
			tmp = this.db.setQuery(query);
		}
		catch(CommunicationsException e) {
			this.db = null;
			connect(dbType, dbHost, dbPort, dbUser, dbPassword, dbName);
			return setQuery(query);
		}
		catch(Exception e){
			e.printStackTrace();
			/*ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				tmp = setQuery(query);
			*/
		}
		finally {
			return tmp;
		}
	}
	
	/**
	 * Extrahiert die betroffenen Tabellen bei einer �nderung und gibt diese in einem Array zur�ck.
	 * @param query	Sqlquery, welches Daten ver�ndert.
	 * @return		Stringarray, welches die Tabellennamen enth�lt.
	 */
	protected String[] getChangedTables(String query){
		ArrayList<String> changedTables = new ArrayList<String>();
		for(String table:tables){
			if(query.toLowerCase().contains(table.toLowerCase()))
				changedTables.add(table);
		}
		
		String[] changedTablesArray = new String[changedTables.size()];
		for(int index=0;index<changedTables.size();index++){
			changedTablesArray[index] = changedTables.get(index);
		}
		
		return changedTablesArray;
	}
	
	/**
	 * Stellt die Verbindung zur Datenbank her.<br>
	 * @param dbType	Datenbanktyp, derzeitig ist nur "MySql" implementiert.
	 * @param host		IP oder DNS Name des Datenbankservers.
	 * @param port		Port, auf welchem der Datenbankserver auf eine Verbindung wartet.
	 * @param user		Benutzername f�r die Anmeldung am Datenbankserver.
	 * @param pw		Passwort f�r die Anmeldung am Datenbankserver.
	 * @param db		Datenbankname, welche ge�ffnet werden soll.
	 * @throws Exception
	 */
	public void connect(String dbType, String host, int port, String user, String pw, String db) throws Exception {
		dbHost = host;
		dbPort = port;
		dbUser = user;
		dbName = db;
		dbPassword = pw;
		this.dbType = dbType;
		
		if(this.db == null){
			switch(dbType){
				case "MySql": 	this.db = new MySql();
								break;
			}
			try{
				this.db.connect(dbHost, dbPort, dbUser, dbPassword, dbName);
			}
			catch(Exception e){
				ErrorManager errorManager = new ErrorManager(e);
				if(errorManager.retry)
					this.db.connect(dbHost, dbPort, dbUser, dbPassword, dbName);
			}
		}
		setTables();
	}
	
	/**
	 * Fragt alle Tabellennamen der Datenbank ab und speichert sie Intern.<br>
	 * Wird f�r den Vergleich der Sqlquery nach einer �nderung der Datenbank ben�tigt.<br>Bildet also die Vergleichsgrundlage.
	 * @throws SQLException
	 */
	protected void setTables() throws SQLException{
		if(!tables.isEmpty())
			return;
		ResultSet sqlTables = this.db.getQuery("show tables;");

		while(sqlTables.next()){
			String tableName = null;
			try{
				tableName = sqlTables.getString(1);
			}
			catch(SQLException e){
				try{
					tableName = sqlTables.getString("Tables_in_"+ dbName);
				}
				catch(SQLException ex){
					ErrorManager errorManager = new ErrorManager(ex);
					if(errorManager.retry)
						setTables();
				}
			}
			tables.add(tableName);
		}
	}
	
	/**
	 * Beendet die Datenbankverbindung und die Instanz.<br>
	 * Nach dem Aufruf muss die Datenbank erst wieder neu Verbunden werden um Abfragen auszuf�hren.
	 */
	public void disconnect() {
		if(this.db != null) this.db.disconnect();
		for(int i=observerList.size();i>0;i--) {
			observerList.remove(i);			
		}
		Database.instance = null;
	}

}
