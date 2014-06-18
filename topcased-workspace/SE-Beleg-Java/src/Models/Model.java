package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Datenbank.Database;
import Models.Datenbank.Observer;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.FilterTyp;
import Models.Filter.ObjectFilter;
import Models.Filter.TabellenFilter;
import Models.Table.TableData;
import Views.UpdateView;


public class Model implements Observer{

		private Database db;
		private String srcQuery;
		private ArrayList<String> srcTables;
		private ResultSet result;
		private String tableNameForUpdateOrInsert; 
		private String primaryKeyColumnName;
		public TableData tableRowData;
		private int columnLimit;
		protected String order;
		public int rowPosition = 0;
		
		private ObjectFilter filter;
	
		private UpdateView view;
		
	protected Model () {}
	
	public Model(String srcQuery,String tableNameForUpdateOrInsert, String primaryKeyColumnName){
		this(srcQuery,20,tableNameForUpdateOrInsert,primaryKeyColumnName);
	}
	
	public Model(String srcQuery, int columnLimit,String tableNameForUpdateOrInsert, String primaryKeyColumnName){
		this(tableNameForUpdateOrInsert,primaryKeyColumnName);
		setSrcQuery(srcQuery);
		setColumnLimit(columnLimit);
		setResult();
	}
	
	public Model(String tableNameForUpdateOrInsert, String primaryKeyColumnName){
		this.tableNameForUpdateOrInsert = tableNameForUpdateOrInsert;
		this.primaryKeyColumnName = primaryKeyColumnName;
		filter = new TabellenFilter();
		srcTables = new ArrayList<String>();
		db = Database.getInstance();
		db.login(this);
		setColumnLimit(20);
		order = new String();
		
	}	
	
	public void setSrcQuery(String srcQuery) {
		this.srcQuery = srcQuery;
		setSrcTables();
	}
	
	private String getSrcQuery() {
		return srcQuery;
	}
		
	public void setColumnLimitAndRefreshViews(int columnLimit) {
		setColumnLimit(columnLimit);
		setResult();
		informView();
	}
	
	private void setColumnLimit(int columnLimit){
		this.columnLimit = columnLimit;
	}
	
	public int getColumnLimit() {
		return columnLimit;
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
	
	public void setResult() {
		if(getSrcQuery() == null){
			try {
				throw new Exception("srcQuery im Model ist nicht gesetzt.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		String where = new String();
		String filter = this.filter.getFilter();
		String order = this.order;
		where = (getSrcQuery().contains("where")) ? " and " : " where ";
		filter = (filter.length()>0) ? filter : " 1 ";
		String query = getSrcQuery() + where + filter + order + " limit " + columnLimit;
		ResultSet result = db.getQuery(query);
		this.result = result;
		setTableRowData();
	}
	
	public ResultSet getResult(){
		if(this.result == null && this.srcQuery != null)
			setResult();
		return this.result;
	}
	
	protected void informView(){
		if(view != null) 
			view.modelHasChanged();
	}
	
	public void setView(UpdateView view){
		this.view = view;
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

	
	private void setTableRowData(){
		tableRowData = new TableData(result,getColumnLimit());
		tableRowData.addColumnAtBegin("Auswahl", (boolean)false);
	}
	
	public TableData getTableRowData(){
		return tableRowData;
	}

	private void setSrcTables() {
			ResultSet tables = db.getQuery("show tables;");
			
			String srcQuery = getSrcQuery().toLowerCase();
			try {
				while(tables.next()){
					if(srcQuery.contains(tables.getString(1).toLowerCase()))
						srcTables.add(tables.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void updateDatabaseAndInformOtherModels(){
		String sqlTableName = getTableNameForUpdateOrInsert();
		Object[][] newData = view.getInputValues();
		
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
		try {
			int indexOfDot = getPrimaryKeyColumnName().indexOf(".");
			String primaryKey = getPrimaryKeyColumnName().substring(indexOfDot+1);
			
			int index = tableRowData.getColumnNameIndex(primaryKey);
			String primaryKeyValue;
		
			primaryKeyValue = tableRowData.getStringValueFromPosition(rowPosition, index);
			sqlUpdateQuery += " WHERE " + primaryKey + " = '" + primaryKeyValue+ "'";

			updateDatabaseAndInformOtherModels(sqlUpdateQuery);		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void updateDatabaseAndInformOtherModels(String sqlQuery){
		db.setQueryandInformModels(sqlQuery);	
	}
	
	public void updateDatabase(String sqlQuery){
		db.setQuery(sqlQuery);	
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
	
	
	
	public void insertIntoDatabase(){
		String sqlTableName = getTableNameForUpdateOrInsert();
		
		Object[][] newData = view.getInputValues();
		
		String sqlInsertQuery = new String("INSERT INTO " + sqlTableName + " ");
		String sqlColumns = new String("(");
		String sqlValues = new String("(");
		String columnClass = tableRowData.getColumnClass(getPrimaryKeyColumnName());
		if(columnClass != null && !getTableNameForUpdateOrInsert().contains(SqlTableStudent.tableName) && columnClass.contains(Integer.class.getName()) || columnClass.contains(Long.class.getName())){
			sqlColumns += getPrimaryKeyColumnName() + ", ";
			sqlValues += getNewPrimaryKeyValue() + ", ";
		}
		
		
		
		
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
		
		db.setQueryandInformModels(sqlInsertQuery);		
	}
	
	protected String getNewPrimaryKeyValue(){
		String newPrimaryKeyValue = new String();
		Model model = new Model("select max("+ getPrimaryKeyColumnName() +") as lastPK FROM "+getTableNameForUpdateOrInsert(),getTableNameForUpdateOrInsert(),getPrimaryKeyColumnName());
		int lastPK;
		try {
			ResultSet result = model.getResult();
			if(result.first()){
				lastPK = result.getInt("lastPK");
				newPrimaryKeyValue = String.valueOf(lastPK+1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newPrimaryKeyValue;
	}
	
	protected String getPrimaryKeyColumnName(){
		return primaryKeyColumnName;
	}
	
	public String getTableNameForUpdateOrInsert(){
		return tableNameForUpdateOrInsert;
	}
	
	public void nextRow(){
		if(rowPosition < (tableRowData.getRowCount()-1))
			rowPosition++;
	}
	
	public void previusRow(){
		if(rowPosition > 0)
			rowPosition--;
	}
}
