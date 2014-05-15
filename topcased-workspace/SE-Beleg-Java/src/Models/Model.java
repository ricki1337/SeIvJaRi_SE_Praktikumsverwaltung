package Models;

import java.sql.*;
import java.util.ArrayList;

import Models.Datenbank.*;
import Models.Filter.*;
import Views.*;


public abstract class Model implements observer{

	private String srcQuery;

	private ArrayList<String> srcTables;

	private ObjectFilter filter;

	private int columnLimit;
	
	private UpdateView view;
	
	private ResultSet result;
	private Database db;
	
	public Model(){
		filter = new TabellenFilter();
		srcTables = new ArrayList<String>();
		db = Database.getInstance();
		db.login(this);
	}
	
	public Model(String srcQuery){
		this(srcQuery,20);
	}
	
	public Model(String srcQuery, int columnLimit){
		this();
		setSrcQuery(srcQuery);
		setColumnLimit(columnLimit);
		setResult();
	}
	
	public void modelClose(){
		db.logout(this);
	}
	
	public void refresh(String[] changedTables) {
		for(String table:changedTables){
			if(srcTables.contains(table)){
				setResult();
				informView();
				break;
			}
		}
		
	}
	
	public void setView(UpdateView view){
		this.view = view;
	}

	protected void informView(){
		if(view != null) 
			view.modelHasChanged();
	}

	public void setOrFilter(String spaltenName, FilterTyp spaltenWert){
		filter.setOrFilter(spaltenName,spaltenWert);
	}

	public void setAndFilter(String spaltenName, FilterTyp spaltenWert){
		filter.setAndFilter(spaltenName,spaltenWert);
	}

	public void deleteFilter(String spaltenName){
		filter.deleteFilter(spaltenName);
	}

	private String getSrcQuery() {
		return srcQuery;
	}

	private void setSrcQuery(String srcQuery) {
		this.srcQuery = srcQuery;
		setSrcTables();
	}

	public int getcolumnLimit() {
		return columnLimit;
	}
	
	private void setColumnLimit(int columnLimit){
		this.columnLimit = columnLimit;
	}

	public void setcolumnLimitAndRefreshViews(int columnLimit) {
		setColumnLimit(columnLimit);
		setResult();
		informView();
	}

	public ResultSet getResult(){
		return this.result;
	}

	protected void setResult() {
		String where = new String();
		String filter = this.filter.getFilter();
		where = (getSrcQuery().contains("where")) ? " and " : " where ";
		filter = (filter.length()>0) ? filter : " 1 ";
		String query = getSrcQuery() + where + filter + " limit " + columnLimit;
		ResultSet result = db.getQuery(query);
		this.result = result;
	}

	private void setSrcTables() {
			ResultSet tables = db.getQuery("show tables;");
			
			String srcQuery = getSrcQuery();
			try {
				while(tables.next()){
					if(srcQuery.contains(tables.getString(1)))
						srcTables.add(tables.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void updateDatabase(){
		String sqlTableName = getTableNameForUpdateOrInsert();
		Object[][] newData = ((View) view).getEingabeWerte();
		
		String sqlUpdateQuery = new String("UPDATE " + sqlTableName + " SET ");
		
		int counter = newData.length;
		for(Object[] row:newData){
			String columnName = (String)row[0];
			String columnValue = convertToString(row[1]);
			if(counter > 1){
				sqlUpdateQuery += columnName + " = " + columnValue + ", ";
			}else{
				sqlUpdateQuery += columnName + " = " + columnValue;
			}
			counter--;
		}
		
		String primaryKey = getPrimaryKeyColumnName();
		String primaryKeyValue = (String)((View)view).getValueFromCurrentItem(primaryKey);
		
		sqlUpdateQuery += " WHERE " + primaryKey + " = '" + primaryKeyValue+ "'";

		updateDatabase(sqlUpdateQuery);		
	}
	
	private String convertToString(Object value){
		if(value instanceof Integer){
			return ((Integer)value).toString();
		}
		
		if(value instanceof String){
			return "'"+((String)value)+"'";
		}
		
		if(value instanceof Boolean){
			boolean tmp = ((Boolean)value).booleanValue();
			return (tmp)?new String("true"):new String("false");
		}
		return new String();
	}
	
	public void updateDatabase(String sqlQuery){
		db.setQuery(sqlQuery);	
	}
	
	public void insertIntoDatabase(){
		String sqlTableName = getTableNameForUpdateOrInsert();
		
		Object[][] newData = ((View) view).getEingabeWerte();
		
		String sqlInsertQuery = new String("INSERT INTO " + sqlTableName + " ");
		String sqlColumns = new String("(");
		String sqlValues = new String("(");
		
		int counter = newData.length;
		for(Object[] row:newData){
			String columnName = (String)row[0];
			String columnValue = convertToString(row[1]);
			if(counter > 1){
				sqlColumns += columnName + ", ";
				sqlValues += columnValue + ", ";
			}else{
				sqlColumns += columnName + ")";
				sqlValues += columnValue + ")";
			}
			counter--;
		}
		sqlInsertQuery += sqlColumns + " VALUES " + sqlValues + ";";
		
		db.setQuery(sqlInsertQuery);		
	
	}
	
	protected abstract String getPrimaryKeyColumnName();
	
	public abstract String getTableNameForUpdateOrInsert();
}
