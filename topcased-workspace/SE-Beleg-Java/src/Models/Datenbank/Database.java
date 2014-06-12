package Models.Datenbank;

import java.sql.*;
import java.util.*;

import ConfigParser.Config;
import Controller.ErrorManager;

public class Database
{
	private static Database instance = new Database();
	
	protected DatabaseFunction db;
	
	private String dbHost = null;
	private int dbPort = 0;
	private String dbName = null;
	private String dbPassword = null;
	private String dbUser = null;
	
	
	private Vector<observer> observerList;
	
	protected ArrayList<String> tables;

	private Database (){
		observerList = new Vector<observer>();
		tables = new ArrayList<String>();
	}
	
	public Database clone(){ 
		try {
		throw new AssertionError("Von Database darf nur eine Instanz existieren!");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	private void informModels(String[] changedTables)
	{
		for(int i=0;i<observerList.size();i++)
			observerList.elementAt(i).refresh(changedTables);
	}
	
	public void login(observer observer)
	{
		observerList.add(observer);
	}
	
	public void logout(observer observer)
	{
		observerList.remove(observer);
	}
	
	public static Database getInstance()
	{
		if(instance == null)
			instance = new Database();
		return instance;
	}


	public ResultSet getQuery(String query) {
		ResultSet result = null;
		try{
			result = this.db.getQuery(query);
		}
		catch(Exception e){
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				result = getQuery(query);
		}
		return result;
	}

	
	public int setQuery(String query) {
		int tmp = 0;
		try{
			tmp = this.db.setQuery(query);
		}
		catch(Exception e){
			ErrorManager errorManager = new ErrorManager(e);
			if(errorManager.retry)
				tmp = setQuery(query);
		}
		informModels(getChangedTables(query));
		return tmp; 
	}
	
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
	

	public void connect(String dbType, String host, int port, String user, String pw, String db) throws Exception {
		dbHost = host;
		dbPort = port;
		dbUser = user;
		dbName = db;
		dbPassword = pw;
		
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
	
	protected void setTables() throws SQLException{
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
	
	public void disconnect() {
		if(this.db != null) this.db.disconnect();
		Database.instance = null;
	}

}
