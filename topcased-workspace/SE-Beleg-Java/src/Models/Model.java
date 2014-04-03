package Models;

import java.sql.*;
import java.util.ArrayList;

import Controller.*;
import Models.Datenbank.*;
import Models.Filter.*;
import Views.*;


public abstract class Model implements observer{
	
	private String srcQuery;
	private ArrayList<String> srcTables;
	private Filter filter;
	private int columnLimit;
	private View view;
	
	private ResultSet result;
	private Database db;
	
	private Model(){
		Filter filter = new TabellenFilter();
		ArrayList<String> srcTables = new ArrayList<String>();
	}
	
	public void setView(View view){
		this.view = view;
	}
	
	public Model(String srcQuery, int columnLimit){
		this();
		setSrcQuery(srcQuery);
		setcolumnLimit(columnLimit);
	}
	
	/**
	 * Implementation des observer interface<br>
	 * wenn ein Update auf einer Tabelle ausgeführt wird,<br>
	 * informiert das Database Objekt alle Observer
	 * 
	 * @param changedTables String[]
	 * @author Rick Hermenau
	 */
	public void refresh(String[] changedTables) {
		// vergleiche alle info aus changedTables mit srcTables
		for(String table:changedTables){
			// wenn ein vergleich true, hole neue daten aus Database
			if(srcTables.contains(table)){
				setResult();
				break;
			}
		}
		
	}
	/**
	 * TODO
	 * views müssen ein interface implementieren...
	 */
	private void informViews(){}
	
	/**
	 * fügt dem Result einen "or" Filter hinzu
	 * 
	 * @param spaltenName String
	 * @param spaltenWert FilterTyp
	 * @author Rick Hermenau
	 */
	public void setOrFilter(String spaltenName, FilterTyp spaltenWert){
		filter.setOrFilter(spaltenName,spaltenWert);
	}
	/**
	 * fügt dem Result einen "and" Filter hinzu
	 * 
	 * @param spaltenName String
	 * @param spaltenWert FilterTyp
	 * @author Rick Hermenau
	 */
	public void setAndFilter(String spaltenName, FilterTyp spaltenWert){
		filter.setAndFilter(spaltenName,spaltenWert);
	}
	
	/**
	 * löscht den Filter der Spalte spaltenName
	 * 
	 * @param spaltenName String
	 * @author Rick Hermenau
	 */
	public void deleteFilter(String spaltenName){
		filter.deleteFilter(spaltenName);
	}
	
	
	//get & set start
	
	private String getSrcQuery() {
		return srcQuery;
	}

	/**
	 * setzt den srcQuery und füllt das srcTables Array
	 * 
	 * @param srcQuery String
	 * 
	 * @author Rick Hermenau
	 */
	private void setSrcQuery(String srcQuery) {
		this.srcQuery = srcQuery;
		//setze die srcTables
		setSrcTables();
	}

	public int getcolumnLimit() {
		return columnLimit;
	}

	public void setcolumnLimit(int columnLimit) {
		this.columnLimit = columnLimit;
	}

	public ResultSet getResult(){
		return this.result;
	}
	
	/**
	 * füllt das Result auf Grundlage des srcQuery, der filter und des columnCount
	 * 
	 * @author Rick Hermenau
	 */
	private void setResult() {
		//srcQuery mit filter und columnLimit ergänzen
		String where = new String();
		where = (getSrcQuery().contains("where"))?" where ":" and ";
		
		String query = getSrcQuery() + where + filter.getFilter() + " limit " + columnLimit;
		
		//abfrage ausführen
		this.result = db.getQuery(query);
		
		//informiere die Views über neue Daten
		informViews();
	}

	/**
	 * liest alle für die MySql-Abfrage nötigen Tabellen aus der srcQuery<br>
	 * und speichert sie in srcTables
	 * 
	 * @author Rick Hermenau
	 * 
	 */
	private void setSrcTables() {
		//lese tabellen aus db aus
		//mysql> SHOW TABLES;
			ResultSet tables = db.getQuery("show tables;");
			
		//vergleiche srcQuery mit Tabellen und schreibe diese in srcTables
			String srcQuery = getSrcQuery();
			try {
				while(tables.next()){
					if(srcQuery.contains(tables.getString(0)))
						srcTables.add(tables.getString(0));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	//get & set ende
}
