package Models.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Datenbank.SqlTableContracts;
/**
 * Implementiert Methoden zur Verwaltung der Listendaten aus {@link java.sql.ResultSet}s und bildet die Grundlage jedes {@link Models.Model}s.
 */
public class TableData {
	
	protected TableColumnNames columnNames;
	protected ResultSet sqlResultSet;
	protected Object[][] data;
	protected int rowCount;
	
	/**
	 * Verborgender Konstruktor für Tests.
	 */
	protected TableData(){}
	
	/**
	 * Initialisiert ein Tabelle mit den Daten des {@link java.sql.ResutSet} und füllt, wenn nötig die Tabelle auf die übergebene Anzahl der Datensätze auf.
	 * @param sqlResultSet	ResultSet einer Abfrage
	 * @param rowCount		Anzahl der anzuzeigenden Datensätze
	 */
	public TableData(ResultSet sqlResultSet, int rowCount){
		setSqlResultSet(sqlResultSet);
		setRowCount(rowCount);
		setColumnNames();
		setDataFromResultSet();
		setTableRowsUpToRowCount();
	}
	
	/**
	 * Übernimmt das übergebene ResultSet in eine Klassenvariable.
	 * @param sqlResultSet	zu übernehmendes ResultSet
	 */
	protected void setSqlResultSet(ResultSet sqlResultSet){
		if(sqlResultSet == null) throw new IllegalArgumentException();
		this.sqlResultSet = sqlResultSet;	
	}
	
	/**
	 * setzt die Anzahl der anzuzeigenden Datensätze.
	 * @param rowCount	Anzahl anzuzeigender Datensätze.
	 */
	protected void setRowCount(int rowCount){
		if(rowCount < 1) throw new IllegalArgumentException();
		this.rowCount = rowCount;
	}
	
	/**
	 * setzt die Anzahl der anzuzeigenden Datensätze und aktualisiert die Daten
	 * @param rowCount	Anzahl der anzuzeigenden Datensätze
	 */
	public void setNewRowCountAndRefreshData(int rowCount){
		setRowCount(rowCount);
		setDataFromResultSet();
		setTableRowsUpToRowCount();
	}
	
	/**
	 * Setzt die Spaltennamen auf Grundlage der Daten des internen ResultSet.<br>
	 * Sql-Aliasnamen = Spaltennamen
	 */
	protected void setColumnNames(){
		try {
			columnNames = new TableColumnNames(sqlResultSet.getMetaData());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Extrahiert die anzuzeigenden Inhalte aus dem internen ResultSet in ein zweidimensionales Array.<br>
	 * Definiert die Klassen der Inhalte. Wenn eine Spalte im ResultSet nicht vorkommt oder die ermittelte Spaltenlänge gleich eins ist,<br>
	 * so wird davon ausgegangen das es sich um einen Boolean Wert handelt.
	 */
	protected void setDataFromResultSet(){
		
		try {
			if(!sqlResultSet.last()){
				return;
			}else{
				sqlResultSet.last();
				
				data = new Object[sqlResultSet.getRow()][columnNames.getCountOfColumns()];
				sqlResultSet.first();
				int dataRow = 0;
				do{
					for(int dataColumn = 0, resultColumn = 1;dataColumn<columnNames.getCountOfColumns();dataColumn++,resultColumn++){
						Object object = sqlResultSet.getObject(resultColumn);
						
						//workaround contract.Erfolg true == X in Datenbank
						if(columnNames.getColumnNameByIndex(dataColumn).equals(SqlTableContracts.Erfolg)){
							if(((String)object).equals("X"))
								object = new Boolean(true);
							else
								object = new Boolean(false);
						}else if(object == null || columnNames.getDefinedColumnLength(resultColumn) == 1){
							object = sqlResultSet.getBoolean(resultColumn);
						}
						data[dataRow][dataColumn] = object;
					}
					dataRow++;
				}while(sqlResultSet.next());
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * Füllt die Zeilen auf die geforderte Anzahl auf.<br>
	 * Gefüllt werden die Zeilen mit Instanzen der Klasse {@link Models.Table.EmptyObject}.
	 */
	protected void setTableRowsUpToRowCount(){
		int rows = rowCount;
		int columns = columnNames.getCountOfColumns();
		Object[][] tmp_data = new Object[rows][columns];
		Object[] emptyRow = new Object[columns];
		
		for(int column = 0; column < columns;column++) 
			emptyRow[column] = new EmptyObject();
		
		for(int row = 0; row < rows;row++){
			try{
				tmp_data[row] = data[row];
			}
			catch(IndexOutOfBoundsException exception){
				tmp_data[row] = emptyRow;
			}
			catch(NullPointerException exception){
				tmp_data[row] = emptyRow;
			}
		}
		data = tmp_data;
	}

	/**
	 * Fügt eine Spalte am Anfang der Tabelle ein und setzte in jeder Zeile den Wert aus data.
	 * @param columnName	Spaltenüberschrift
	 * @param data			Zeilenwert
	 */
	public void addColumnAtBegin(String columnName,Object data){
		addColumnAtPosition(columnName,0,data);
	}
	
	/**
	 * Fügt eine Spalte an der gewünschten Position ein und setzt den übergebenen Wert in jeder Zeile.
	 * @param columnName	Spaltenüberschrift
	 * @param position		Position der neuen Spalte
	 * @param data			Zeilenwert
	 */
	public void addColumnAtPosition(String columnName,int position, Object data){
		addColumnName(columnName,position);
		addColumn(position,data);
	}
	
	/**
	 * Fügt den übergebenen Spaltennamen an der übermittelten Position ein.
	 * @param name		Neuer Spaltenname
	 * @param position	Position der Spalte
	 */
	protected void addColumnName(String name, int position){
		if(position < 0 || position > columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		
		if(name == null | name.length() == 0)
			throw new IllegalArgumentException();
		
		columnNames.addColumn(name, position);
	}
	
	/**
	 * Fügt eine neue Spalte an der übermittelten Position in den Daten ein und setzt den Zeilenwert.
	 * @param position	Position der Spalte
	 * @param data		Zeilenwert
	 */
	protected void addColumn(int position, Object data){
		if(position < 0 || position > columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		
		if(data == null)
			throw new IllegalArgumentException();
		
		Object[][] tmp_data = new Object[this.data.length][columnNames.getCountOfColumns()];
		
		for(int row = 0;row < this.data.length;row++){
			for(int column = columnNames.getCountOfColumns()-1;column>=0;column--){
				tmp_data[row][column] = column==position?data:this.data[row][column-1];
			}
		}
		this.data = tmp_data;
	}
	
	/**
	 * Ermittelt die Spaltennamen und gibt diese als Array zurück.
	 * @return	Spaltennamen
	 */
	public String[] getColumnNames(){
		return columnNames.getColumnNames();
	}
	
	/**
	 * Ermittelt die Spaltenaliasnamen (Spaltenüberschriften) und gibt diese als Array zurück.
	 * @return	Spaltenaliasnamen (Spaltenüberschriften)
	 */
	public Object[] getColumnAliasNames(){
		return columnNames.getColumnAliasNames();
	}
	
	/**
	 * Gibt die Tabellendaten zurück.<br>
	 * Achtung! Spaltennamen sind nicht enthalten.
	 * @return	zweidimenionales Array mit den Daten der Tabelle
	 */
	public Object[][] getTableData(){
		return data;
	}

	/**
	 * Ermittelt den Wert einer Zeile einer Spalte und gibt diesen zurück.
	 * @param row		Zeilenindex
	 * @param column	Spaltenname
	 * @return			Wert der übermittelten Position
	 */
	public Object getValueFromPosition(int row, String column){
		int index = columnNames.getColumnIndex(column);
		return getValueFromPosition(row,index);
	}
	
	/**
	 * Ermittelt den Wert einer Zeile einer Spalte und gibt diesen zurück.
	 * @param row		Zeilenindex
	 * @param column	Spaltenindex
	 * @return			Wert der übermittelten Position
	 */
	public Object getValueFromPosition(int row, int column){
		if(row < 0 || row >= data.length)
			throw new IndexOutOfBoundsException();
		
		if(column < 0 || column >= columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		
		return data[row][column];
	}
	
	/**
	 * Ermittelt den String-Wert einer Zeile einer Spalte und gibt diesen zurück.
	 * @param row		Zeilenindex
	 * @param column	Spaltenname
	 * @return			String-Wert der übermittelten Position
	 * @throws Exception
	 */
	public String getStringValueFromPosition(int row, String column) throws Exception{
		String valueFromPosition = null;
		try{
			int index = columnNames.getColumnIndex(column);
			valueFromPosition = getStringValueFromPosition(row,index);
		}catch(Exception e){
			
		}
		return valueFromPosition;
	}
	
	/**
	 * Ermittelt den String-Wert einer Zeile einer Spalte und gibt diesen zurück.
	 * @param row		Zeilenindex
	 * @param column	Spaltenindex
	 * @return			String-Wert der übermittelten Position
	 * @throws Exception
	 */
	public String getStringValueFromPosition(int row, int column) throws Exception{
		Object value = getValueFromPosition(row,column);
		
		if(value instanceof Long){
			return Long.toString((Long) value);
		}
		
		if(value instanceof Boolean){
			return Boolean.toString((Boolean) value);
		}
		
		if(value instanceof Double){
			return Double.toString((Double) value);
		}
		
		if(value instanceof Integer){
			return Integer.toString((Integer) value);
		}
		
		if(value instanceof String){
			return (String) value;
		}
		
		throw new IndexOutOfBoundsException("Element an Position row: "+row+", column: "+column+" wurde nicht gefunden.");
	}
	
	/**
	 * Ermittelt den Boolean-Wert einer Zeile einer Spalte und gibt diesen zurück.
	 * @param row		Zeilenindex
	 * @param column	Spaltenname
	 * @return			Boolean-Wert der übermittelten Position
	 * @throws Exception
	 */
	public boolean getBooleanValueFromPosition(int row, String column) throws Exception{
		int index = columnNames.getColumnIndex(column);
		
		
		if(column.equals(SqlTableContracts.Erfolg) || column.equals(SqlTableContracts.TableNameDotErfolg))
			return getStringValueFromPosition(row,column).equals("X")?true:false;
		
		
		return getBooleanValueFromPosition(row,index);
	}
	
	/**
	 * Ermittelt den Boolean-Wert einer Zeile einer Spalte und gibt diesen zurück.
	 * @param row		Zeilenindex
	 * @param column	Spaltenindex
	 * @return			Boolean-Wert der übermittelten Position
	 * @throws Exception
	 */
	public boolean getBooleanValueFromPosition(int row, int column) throws Exception{
		Object value = getValueFromPosition(row,column);
		
		if(value instanceof Boolean){
			return ((Boolean) value);
		}
		
		if(value instanceof String){
			return Boolean.parseBoolean((String)value);
		}
		
		if(value instanceof Integer){
			return (((Integer)value)<=0)?false:true;
		}
		
		throw new Exception();
	}	
	
	/**
	 * Setzt einen übergebenen Wert an der gewünschten Position der Tabelle
	 * @param row		Zeilenindex
	 * @param column	Spaltenname
	 * @param value		Neuer Wert der Zelle
	 */
	public void setValueAtPosition(int row, String column, Object value){
		int columnIndex = columnNames.getColumnIndex(column);
		setValueAtPosition(row,columnIndex,value);
	}
	
	/**
	 * Setzt einen übergebenen Wert an der gewünschten Position der Tabelle
	 * @param row		Zeilenindex
	 * @param column	Spaltenindex
	 * @param value		Neuer Wert der Zelle
	 */
	public void setValueAtPosition(int row, int column, Object value){
		if(row < 0 || row >= data.length)
			throw new IndexOutOfBoundsException();
		if(column < 0 || column >= columnNames.getCountOfColumns())
			throw new IndexOutOfBoundsException();
		data[row][column] = convertPrimitiveToWrapperClass(value);
	}
	
	/**
	 * Konvertiert einen primitiven Datentyp in die entsprechende Wrapperklasse.
	 * @param value
	 * @return	Wrapperklasse
	 */
	
	private Object convertPrimitiveToWrapperClass(Object value){
		Object object = null;
		
		if(value instanceof Long){
			object = (Long) value;
		}
		
		if(value instanceof Boolean){
			object =  (Boolean) value;
		}
		
		if(value instanceof Double){
			object =  (Double) value;
		}
		
		if(value instanceof Integer){
			object =  (Integer) value;
		}
		
		if(value instanceof String){
			object =  (String) value;
		}
		return object;
	}
	
	/**
	 * Ermittelt den Index eines Spaltennamens
	 * @param columnName	Spaltenname oder Spaltenaliasname oder Spaltenname mit führendem Tabellenname
	 * @return				Spaltenindex zum Spaltenname
	 */
	public int getColumnIndex(String columnName){
		return columnNames.getColumnIndex(columnName);
	}
	
	/**
	 * Ermittelt den Index eines Spaltenaliasnamens.
	 * @param columnName	Spaltenaliasname
	 * @return				Spaltenindex zum Spaltenaliasname
	 */
	public int getColumnAliasIndex(String columnName){
		return columnNames.getColumnAliasNameIndex(columnName);
	}
	
	/**
	 * Ermittelt den Index eines Spaltennamens.
	 * @param columnName	Spaltenname
	 * @return				Spaltenindex zum Spaltenname
	 */
	public int getColumnNameIndex(String columnName){
		return columnNames.getColumnNameIndex(columnName);
	}
	
	/**
	 * Ermittelt die Anzahl der wirklich gefüllten Zeilen.<br>
	 * Zeilen, welche mit {@link Models.Table.EmptyObject} gefüllt sind werden ignoriert. 
	 * @return	Anzahl der wirklich gefüllter Zeilen
	 */
	public int getRowCount(){
		int count=0;
		for(Object[] object:data){
			if(object[0] instanceof EmptyObject | object[columnNames.getCountOfColumns()-1] instanceof EmptyObject)
				continue;
			count++;
		}
		return count;
	}
	
	/**
	 * Ermittelt den Datentyp der Spalte.
	 * @param columnName	Spaltenname, Spaltenaliasname oder Spaltenname mit führendem Tabellenname
	 * @return				Datentyp der Spalte, z.B. java.lang.Boolean
	 */
	public String getColumnClass(String columnName){
		return columnNames.getColumnClass(columnName);
	}
	
}
