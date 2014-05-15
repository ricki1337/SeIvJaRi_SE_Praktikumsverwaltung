package Models.Datenbank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class Database
{
	//instance von Database wird beim laden(!!) der klasse erzeugt
	private static Database instance = new Database();
	
	//ist die eigentliche anbindung an die datenbank
	private DatabaseFunction db;
	
	//enthält alle beobachter
	private Vector<observer> observerList;
	
	private ArrayList<String> tables;

	private Database (){
		//beobachterliste init
		observerList = new Vector<observer>();
		tables = new ArrayList<String>();
	}
	
	public Database clone(){ 
		try {
		throw new AssertionError("Von Database darf nur eine Instanz existieren!");
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 
		return null;
	}
	
	private void informModels(String[] changedTables)
	{
		//gehe die beobachterliste durch und rufe jeweils refresh() auf
		for(int i=0;i<observerList.size();i++)
			observerList.elementAt(i).refresh(changedTables);
	}
	
	public void login(observer observer)
	{
		//beobachter registriert sich in der beobachterliste
		observerList.add(observer);
	}
	
	public void logout(observer observer)
	{
		//beobachter trägt sich aus der beobachterliste aus
				observerList.remove(observer);
	}
	
	public static Database getInstance()
	{
		return instance;
	}


	public ResultSet getQuery(String query) {
		return this.db.getQuery(query);
	}

	
	public int setQuery(String query) {

		int tmp = this.db.setQuery(query);
	
		informModels(getChangedTables(query));
		return tmp; 
	}
	
	private String[] getChangedTables(String query){
		ArrayList<String> changedTables = new ArrayList<String>();
		for(String table:tables){
			if(query.contains(table))
				changedTables.add(table);
		}
		
		String[] test = new String[changedTables.size()];
		for(int index=0;index<changedTables.size();index++){
			test[index] = changedTables.get(index);
		}
		
		return test;
	}
	

	//datenbankverbindung setzen
	public void connect(String dbType, String host, int port, String user, String pw, String db) {
		if(this.db == null){
			switch(dbType){
				case "MySql": 	this.db = new MySql();
								break;
								
				case "test":	this.db = new MySqlMock();
								break;
			}
			
			this.db.connect(host, port, user, pw, db);
		}
		setTables();
	}
	
	protected void setTables(){
		ResultSet sqlTables = getQuery("show tables;");
		
		try {
			while(sqlTables.next()){
					tables.add(sqlTables.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		if(this.db != null) this.db.disconnect();		
	}

}
