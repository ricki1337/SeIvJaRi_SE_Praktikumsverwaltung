package Datenbank;

import java.sql.*;
import java.util.*;

public class Database
{
	private Connection connection;
	
	//instance von Database wird beim laden(!!) der klasse erzeugt
	private static Database instance = new Database();
	
	//ist die eigentliche anbindung an die datenbank
	private DatabaseFunction db;
	
	//enthält alle beobachter
	private Vector<observer> observerList;


	private Database (){
		//beobachterliste init
		observerList = new Vector<observer>();
	}
	
	public Database clone(){ 
		try {
		throw new Exception("Von Database darf nur eine Instanz existieren!");
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} 
		return null;
	}
	
	private void informModels()
	{
		//gehe die beobachterliste durch und rufe jeweils refresh() auf
		for(int i=0;i<observerList.size();i++)
			observerList.elementAt(i).refresh();
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
		informModels();
		return tmp; 
	}

	//datenbankverbindung setzen
	public void connect(String dbType, String host, int port, String user, String pw, String db) {
		if(this.db == null){
			switch(dbType){
				case "MySql": 	this.db = new MySql();
								break;		
			}
			
			this.db.connect(host, port, user, pw, db);
		}
	}

	
	public void disconnect() {
		if(this.db != null) this.db.disconnect();		
	}

}
