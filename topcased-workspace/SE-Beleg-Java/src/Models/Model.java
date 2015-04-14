package Models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Datenbank.Database;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableProfs;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.TabellenFilter;
import Models.Interfaces.DatabaseFilterCtrl;
import Models.Interfaces.DatatypeFilter;
import Models.Interfaces.Observer;
import Models.Table.TableData;
import Views.UpdateView;

/**
 * Implementiert die Datenhaltung.
 */
public class Model implements Observer{

		private Database db;
		private String srcQuery;
		protected ArrayList<String> srcTables;
		private ResultSet result;
		private String tableNameForUpdateOrInsert; 
		private String primaryKeyColumnName;
		protected TableData tableRowData;
		private int columnLimit;
		protected String order;
		protected int rowPosition = 0;
		
		private DatabaseFilterCtrl filter;
	
		private UpdateView view;
		
	protected Model () {}
	
	/**
	 * Initialisiert eine Datenbasis auf Grundlage des srcQuery.<br>
	 * Die Sql-Abfrage wird ausgef�hrt und das Datenmodel mit 20 Datens�tzen gef�llt.
	 * @param srcQuery						Sql-Abfrage als Grundlage f�r Datenmodel.
	 * @param tableNameForUpdateOrInsert	Sql-Tabellennamen, in welchem die Insert- und Updatebefehle augef�hrt werden.
	 * @param primaryKeyColumnName			Sql-Spaltennamen, welcher den PrimaryKey der Tabelle darstellt.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public Model(String srcQuery,String tableNameForUpdateOrInsert, String primaryKeyColumnName){
		this(srcQuery,20,tableNameForUpdateOrInsert,primaryKeyColumnName);
	}
	
	/**
	 * Initialisiert eine Datenbasis auf Grundlage des srcQuery.<br>
	 * Die Sql-Abfrage wird ausgef�hrt und das Datenmodel mit variabel vielen Datens�tzen gef�llt.
	 * @param srcQuery						Sql-Abfrage als Grundlage f�r Datenmodel.
	 * @param columnLimit					Anzahl der Datens�tze die maximal geladen und angezeigt werden sollen.
	 * @param tableNameForUpdateOrInsert	Sql-Tabellennamen, in welchem die Insert- und Updatebefehle augef�hrt werden.
	 * @param primaryKeyColumnName			Sql-Spaltennamen, welcher den PrimaryKey der Tabelle darstellt.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public Model(String srcQuery, int columnLimit,String tableNameForUpdateOrInsert, String primaryKeyColumnName){
		this(tableNameForUpdateOrInsert,primaryKeyColumnName);
		setSrcQuery(srcQuery);
		setColumnLimit(columnLimit);
		setResult();
	}
	
	/**
	 * Initialisiert ein {@link Model} Objekt ohne eine Datenbasis.<br>
	 * Die Datenbankverbindung wird gestartet, die Anzahl der Datens�tze auf 20 gesetzt<br>
	 * und alle n�tigen Initialisierungen vorgenommen.
	 * @param tableNameForUpdateOrInsert	Sql-Tabellennamen, in welchem die Insert- und Updatebefehle augef�hrt werden.
	 * @param primaryKeyColumnName			Sql-Spaltennamen, welcher den PrimaryKey der Tabelle darstellt.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
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
	
	/**
	 * �bernimmt ein srcQuery und extrahiert die Tabellen, von welchen die srcQuery abh�ngig ist.<br>
	 * Um bei deren Aktualisierung die Daten neu zu beziehen.
	 * 
	 * @param srcQuery	Sql-Abfrage als Grundlage f�r Datenmodel.
	 */
	public void setSrcQuery(String srcQuery) {
		this.srcQuery = srcQuery;
		setSrcTables();
	}
	
	/**
	 * Gibt das srcQuery zur�ck.
	 * 
	 * @return srcQuery des Models.
	 */
	private String getSrcQuery() {
		return srcQuery;
	}
		
	/**
	 * Setzt die Anzahl der Datens�tze die angezeigt oder geladen werden sollen neu<br>
	 * und aktualisiert die internen Daten. Die registrierten Views werden informiert.
	 * @param columnLimit	Neuer Wert f�r die Anzahl der Datens�tze
	 */
	public void setColumnLimitAndRefreshViews(int columnLimit) {
		setColumnLimit(columnLimit);
		setResult();
		informView();
	}
	
	/**
	 * Setzt den Wert f�r die Anzahl der Datens�tze
	 * @param columnLimit	Neue Anzahl der Datens�tze
	 */
	private void setColumnLimit(int columnLimit){
		this.columnLimit = columnLimit;
	}
	
	/**
	 * Gibt den Wert der maximal angezeigten Datens�tze zur�ck.
	 * @return	gesetztes Maximum der angezeigten Datens�tze.
	 */
	public int getColumnLimit() {
		return columnLimit;
	}
	
	/**
	 * Beendet die Datenbankverbindung des Models.<br>
	 * Das Model meldet sich als Observer von der Datenbankverbindung ab<br>
	 * und bekommt keine Aktualisierungsinformationen mehr.<br>
	 */
	public void modelClose(){
		db.logout(this);
	}
	

	/**
	 * Implemenation des {@link Observer} Interfaces<br>
	 * Gleicht die aktualisierten Tabellen mit den vom Model genutzten ab<br>
	 * und aktualisiert sich wenn notwendig.
	 */
	public void refresh(String[] changedTables) {
		for(String table:changedTables){
			if(srcTables.contains(table)){
				setResult();
				informView();
				break;
			}
		}
		
	}
	
	/**
	 * Setzt den srcQuery, Filter, maximale Anzahl angezeigter Datens�tze und Sortierung zusammen<br>
	 * und l�sst sich von der Datenbank das Ergebnis liefern.<br>
	 * Erzeugt die Tabellenstruktur mit den Daten der Datenbank.
	 * @see Models.Datenbank
	 * @see	ResultSet
	 * @see Models.Filter
	 */
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
	
	/**
	 * Gibt das {@link ResultSet} zur�ck, welches auf Grundlage<br>
	 * des srcQuery,<br>
	 * der Filter,<br>
	 * der maximalen Anzahl angezeigter Datens�tze<br>
	 * und der Sortierung erzeugt wurde.
	 * 
	 * @return	{@link ResultSet} des srcQuery inkl. aller einwirkenden Faktoren.
	 * @see ResultSet
	 */
	public ResultSet getResult(){
		if(this.result == null && this.srcQuery != null)
			setResult();
		return this.result;
	}
	
	/**
	 * Informiert alle angeschlossenen Views, dass sich das Model ge�ndert hat.
	 */
	protected void informView(){
		if(view != null) 
			view.modelHasChanged();
	}
	
	/**
	 * Registriert eine {@link View} welche das Interface {@link UpdateView} implementiert.
	 * @param view	{@link UpdateView}
	 */
	public void setView(UpdateView view){
		this.view = view;
	}
	
	/**
	 * Setzt einen Filter auf dem Model mit der "oder" Verkn�pfung.
	 * @param spaltenName	SqlTable-Definition eines Spaltennamens.
	 * @param spaltenWert	Spaltenwert, nach dem gefiltert werden soll.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void setOrFilter(String spaltenName, DatatypeFilter spaltenWert){
		filter.setOrFilter(spaltenName,spaltenWert);
	}

	/**
	 * Setzt einen Filter auf dem Model mit der "und" Verkn�pfung.
	 * @param spaltenName	SqlTable-Definition eines Spaltennamens.
	 * @param spaltenWert	Spaltenwert, nach dem gefiltert werden soll.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void setAndFilter(String spaltenName, DatatypeFilter spaltenWert){
		filter.setAndFilter(spaltenName,spaltenWert);
	}
	
	/**
	 * Entfernt einen Filter auf dem Model mit dem angegebenen Namen.
	 * @param spaltenName	SqlTable-Definition eines Spaltennamens.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void deleteFilter(String spaltenName){
		filter.deleteFilter(spaltenName);
	}

	/**
	 * Erzeugt das eigentliche {@link TableData}-Objekt, welches die Daten verwaltet.
	 * @see	TableData
	 */
	protected void setTableRowData(){
		tableRowData = new TableData(result,getColumnLimit());
	}
	
	/**
	 * Ermittelt die Tabellen, auf welcher die srcQuery basiert<br>
	 * und extrahiert sie zur sp�teren Verarbeitung.
	 */
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
	
	/**
	 * Aktualisiert Daten in der Datenbank.<br>
	 * Die neuen Daten werden von der View bezogen und in ein Sql-Updatequery eingef�gt.<br>
	 * Nach dem erfolgreichen Update werden alle Models, welche die aktualisierte Tabelle nutzen informiert.
	 * @see Models.Interfaces.Observer
	 * @see Models.Datenbank.Database
	 */
	public void updateDatabaseAndInformOtherModels(){
		String sqlTableName = getTableNameForUpdateOrInsert();
		Object[][] newData = view.getInputValues();
		
		String sqlUpdateQuery = new String("UPDATE " + sqlTableName + " SET ");
		
		int counter = newData.length;
		for(Object[] row:newData){
			String columnName = (String)row[0];
			String columnValue = convertToString(row[1]);
			
			//workaround Erfolg true == X in Datenbank
			if(columnName.equals(SqlTableContracts.TableNameDotErfolg) || columnName.equals(SqlTableContracts.Erfolg))
				columnValue = columnValue.equals("true")?"'X'":"'0'";
			
			
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
	
	/**
	 * Gibt die Update-Query an das Datenbanksystem weiter,<br>
	 * nach erfolgreichem Update werden alle betroffenen Models informiert.
	 * @param sqlQuery	Query, welches Daten �ndert.
	 */
	public void updateDatabaseAndInformOtherModels(String sqlQuery){
		db.setQueryandInformModels(sqlQuery);	
	}
	
	/**
	 * Gibt die Update-Query an das Datenbanksystem weiter,<br>
	 * OHNE die abh�ngigen Models zu informieren.
	 * @param sqlQuery	Query, welches Daten �ndert.
	 */
	public void updateDatabase(String sqlQuery){
		db.setQuery(sqlQuery);	
	}
	
	/**
	 * Konvertiert das �bergebene Object in einen String.
	 * @param value	Welches in einen String konvertiert werden soll.
	 * @return		String-Repr�sentation des value
	 */
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
	
	
	/**
	 * F�gt neue Daten in die Datenbank ein.<br>
	 * Die neuen Daten werden von der View bezogen und in ein Sql-Insertquery eingef�gt.<br>
	 * Nach dem erfolgreichen Insert werden alle Models, welche die aktualisierte Tabelle nutzen informiert.<br>
	 * Da die Testdatenbank keine auto_increment Funktion der PrimaryKey Spalten im Sql hat,<br>
	 * wurde ein Workaround konstruiert, welche den neuen PrimaryKey erzeugt.
	 * 
	 * @see Models.Interfaces.Observer
	 * @see Models.Datenbank.Database
	 */
	public void insertIntoDatabase(){
		String sqlTableName = getTableNameForUpdateOrInsert();
		
		Object[][] newData = view.getInputValues();
		
		String sqlInsertQuery = new String("INSERT INTO " + sqlTableName + " ");
		String sqlColumns = new String("(");
		String sqlValues = new String("(");

		if(!getTableNameForUpdateOrInsert().contains(SqlTableStudent.tableName) && !getTableNameForUpdateOrInsert().contains(SqlTableProfs.tableName)){
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
	
	/**
	 * WORKAROUND f�r nicht eingesetzte auto_increment Funktion in Datenbank.<br>
	 * Erzeugt einen neuen PrimaryKey f�r den Insert-Value.
	 * @return	neuen PrimaryKey
	 */
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
	/**
	 * Gibt den Spaltennamen des gesetzten PrimaryKey zur�ck.<br>
	 * Entspricht einer SqlTable-Definition.<br>
	 * Wert wurde bei Initialisierung gesetzt.
	 * 
	 * @return 	gesetzter Prim�rschl�ssel-Spaltenname.
	 * @see		Models.Datenbank.SqlTableProfs
	 * @see		Models.Datenbank.SqlTableStudent
	 * @see		Models.Datenbank.SqlTableContracts
	 * @see		Models.Datenbank.SqlTableCompanies
	 * @see		Models.Datenbank.SqlTableContacts
	 */
	protected String getPrimaryKeyColumnName(){
		return primaryKeyColumnName;
	}
	
	/**
	 * Gibt den Spaltennamen f�r Update oder Insertoperationen an.<br>
	 * Wert wurde bei Initialisierung gesetzt.
	 * @return	gesetzter Tabellenname, entspricht SqlTable-Definition
	 * @see		Models.Datenbank.SqlTableProfs
	 * @see		Models.Datenbank.SqlTableStudent
	 * @see		Models.Datenbank.SqlTableContracts
	 * @see		Models.Datenbank.SqlTableCompanies
	 * @see		Models.Datenbank.SqlTableContacts
	 */
	public String getTableNameForUpdateOrInsert(){
		return tableNameForUpdateOrInsert;
	}
	
	/**
	 * Gibt die aktuelle Datensatz- bzw. Zeilenposition des Models zur�ck.<br>
	 * Alle abgefragten Informationen aus getStringValue(), etc. beziehen sich auf diesen Wert.
	 * @return	aktuelle Zeilenposition in der Datentabelle
	 */
	public int getRowPosition(){
		return rowPosition;
	}
	
	/**
	 * Versucht die aktuelle Datensatz- bzw. Zeilenposition zu inkrementieren.
	 */
	public void nextRow(){
		if(rowPosition < (tableRowData.getRowCount()-1))
			rowPosition++;
	}
	
	/**
	 * Versucht die aktuelle Datensatz- bzw. Zeilenposition zu dekrementieren.
	 */
	public void previusRow(){
		if(rowPosition > 0)
			rowPosition--;
	}
	
	/**
	 * Gibt den Tabelleninhalt zur�ck.
	 * @return	2D Array mit dem aktuellen Tabelleninhalt.
	 */
	public Object[][] getTableData(){
		return tableRowData.getTableData();
	}
	
	/**
	 * Gibt die Tabellen�berschriften, also die Spalten�berschriften zur�ck.<br>
	 * Die �berschriften entsprechen den Spalten-Aliasnamen der srcQuery.
	 *
	 * @return Array mit den Spaltennamen.
	 */
	public Object[] getTableHeader(){
		return tableRowData.getColumnAliasNames();
	}
	
	/**
	 * Gibt den Wert der angegebenen Spalte der aktuellen Zeilenposition zur�ck.
	 * 
	 * @param columnName	Spaltenname, von welcher der Wert abgefragt wird. Muss einer SqlTable-Definition entsprechen.
	 * @return			Wert der Spalte in der aktuellen Zeile.
	 * @see		Models.Datenbank.SqlTableProfs
	 * @see		Models.Datenbank.SqlTableStudent
	 * @see		Models.Datenbank.SqlTableContracts
	 * @see		Models.Datenbank.SqlTableCompanies
	 * @see		Models.Datenbank.SqlTableContacts
	 */
	public Object getValueFromPosition(String columnName) {
		return getValueFromPosition(getRowPosition(), columnName);
	}
	
	/**
	 * Gibt den Wert der angegebenen Spalte der aktuellen Zeilenposition zur�ck.
	 *  
	 * @param rowPosition		Zeile, aus welcher der Wert bezogen werden soll
	 * @param columnName	Spaltenname, von welcher der Wert abgefragt wird. Muss einer SqlTable-Definition entsprechen.
	 * @return			Wert der angegebenen Spalte in der abgefragten Zeile.
	 * @see		Models.Datenbank.SqlTableProfs
	 * @see		Models.Datenbank.SqlTableStudent
	 * @see		Models.Datenbank.SqlTableContracts
	 * @see		Models.Datenbank.SqlTableCompanies
	 * @see		Models.Datenbank.SqlTableContacts
	 */
	public Object getValueFromPosition(int rowPosition, String columnName) {
		return tableRowData.getValueFromPosition(rowPosition, columnName);
	}
	
	/**
	 * Gibt den Wert der angegebenen Spalte der aktuellen Zeilenposition zur�ck.
	 *  
	 * @param columnName	Spaltenname, von welcher der Wert abgefragt wird. Muss einer SqlTable-Definition entsprechen.
	 * @return			Wert der angegebenen Spalte in der aktuellen Zeile.
	 * @see		Models.Datenbank.SqlTableProfs
	 * @see		Models.Datenbank.SqlTableStudent
	 * @see		Models.Datenbank.SqlTableContracts
	 * @see		Models.Datenbank.SqlTableCompanies
	 * @see		Models.Datenbank.SqlTableContacts
	 */
	public String getStringValueFromPosition(String sqlColumnName){
		return getStringValueFromPosition(getRowPosition(), sqlColumnName);
	}
	
	/**
	 * Gibt den Wert von der angegebenen Spalte und Zeilenposition zur�ck.
	 * 
	 * @param rowPosition	Zeilennummer, von welcher der Wert abgefragt wird.
	 * @param sqlColumnName	Spaltenname, von welcher der Wert abgefragt wird. Muss einer SqlTable-Definition entsprechen.
	 * @return				Wert der angegebenen Spalte in der abgefragten Zeile.
	 * @see		Models.Datenbank.SqlTableProfs
	 * @see		Models.Datenbank.SqlTableStudent
	 * @see		Models.Datenbank.SqlTableContracts
	 * @see		Models.Datenbank.SqlTableCompanies
	 * @see		Models.Datenbank.SqlTableContacts
	 */
	public String getStringValueFromPosition(int rowPosition, String sqlColumnName){
		String valueFromPosition = new String();
		try {
			valueFromPosition =  tableRowData.getStringValueFromPosition(rowPosition, sqlColumnName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueFromPosition;
	}
	
	/**
	 * Gibt den Wert der angegebenen Spalte der aktuellen Zeilenposition zur�ck.
	 * @param sqlColumnName	Spaltenname, von welcher der Wert abgefragt wird. Muss einer SqlTable-Definition entsprechen.
	 * @return				Wert der angegebenen Spalte in der aktuellen Zeile.
	 * @see		Models.Datenbank.SqlTableProfs
	 * @see		Models.Datenbank.SqlTableStudent
	 * @see		Models.Datenbank.SqlTableContracts
	 * @see		Models.Datenbank.SqlTableCompanies
	 * @see		Models.Datenbank.SqlTableContacts
	 */
	public boolean getBooleanValueFromPosition(String sqlColumnName){
		return getBooleanValueFromPosition(getRowPosition(), sqlColumnName);
	}
	
	/**
	 * Gibt den Wert von der angegebenen Spalte und Zeilenposition zur�ck.
	 * 
	 * @param rowPosition	Zeilennummer, von welcher der Wert abgefragt wird.
	 * @param sqlColumnName	Spaltenname, von welcher der Wert abgefragt wird. Muss einer SqlTable-Definition entsprechen.
	 * @return				Wert der angegebenen Spalte in der abgefragten Zeile.
	 * @see		Models.Datenbank.SqlTableProfs
	 * @see		Models.Datenbank.SqlTableStudent
	 * @see		Models.Datenbank.SqlTableContracts
	 * @see		Models.Datenbank.SqlTableCompanies
	 * @see		Models.Datenbank.SqlTableContacts
	 */
	public boolean getBooleanValueFromPosition(int rowPosition, String sqlColumnName){
		boolean valueFromPosition = false;
		try {
			valueFromPosition =  tableRowData.getBooleanValueFromPosition(rowPosition, sqlColumnName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valueFromPosition;
	}
	
	/**
	 * Setzt den �bergebenen Wert an der aktuellen Zeilenposition und �bermittelten Spalte.
	 * @param columnName	Spaltenname, in den der Wert value geschrieben werden soll. Muss einer SqlTable-Definition entsprechen.
	 * @param value			Neuer Wert.
	 */
	public void setValueAtPosition(String columnName, Object value) {
		setValueAtPosition(getRowPosition(), columnName, value);
	}
	
	/**
	 * Setzt den �bergebenen Wert an der �bergebenen Zeilenposition und der �bermittelten Spalte.
	 * @param rowPosition	Index der Zeile.
	 * @param columnName	Spaltenname, in den der Wert value geschrieben werden soll. Muss einer SqlTable-Definition entsprechen.
	 * @param value			Neuer Wert.
	 */
	public void setValueAtPosition(int rowPosition, String columnName, Object value) {
		setValueAtPosition(rowPosition, tableRowData.getColumnIndex(columnName), value);
	}
	
	/**
	 * Setzt den �bergebenen Wert an der �bergebenen Zeilenposition und der �bermittelten Spalte.
	 * @param rowPosition	Index der Zeile.
	 * @param columnIndex	Index der Spalte.
	 * @param value			Neuer Wert.
	 */
	public void setValueAtPosition(int rowPosition, int columnIndex, Object value) {
		tableRowData.setValueAtPosition(rowPosition, columnIndex, value);
	}
	
	/**
	 * Gibt den Alias-Spaltennamen anhand des �bermittelten Spaltennamens zur�ck.
	 * @param columnName	Spaltenname, muss einer SqlTable-Definition entsprechen.
	 * @return				Alias-Spaltenname.
	 */
	public int getColumnAliasIndex(String columnName) {
		return tableRowData.getColumnAliasIndex(columnName);
	}
	
	/**
	 * Gibt die Anzahl von wirklich gef�llten Zeilen zur�ck.
	 * @return	Anzahl wirklich gef�llter Zeilen.
	 */
	public int getTableRowCount(){
		return tableRowData.getRowCount();
	}
	
	
}
