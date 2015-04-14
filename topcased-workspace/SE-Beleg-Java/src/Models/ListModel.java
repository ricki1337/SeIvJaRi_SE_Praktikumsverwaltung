package Models;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.SortOrder;

import Models.Filter.StringFilter;
import Models.Interfaces.DatatypeFilter;
import Models.Table.TableData;

/**
 * Stellt Methoden zur Arbeit mit tabellenbasierten Daten bereit.<br>
 * Wird von allen *List Controllern benutzt.
 */
public class ListModel extends Model{
	
	
	protected ListModel(){}
	
	/**
	 * Initialisiert eine Datenbasis auf Grundlage des srcQuery.<br>
	 * Die Sql-Abfrage wird ausgeführt und das Datenmodel gefüllt.
	 * @param srcQuery						Sql-Abfrage als Grundlage für Datenmodel.
	 * @param tableNameForUpdateOrInsert	Sql-Tabellennamen, in welchem die Insert- und Updatebefehle augeführt werden.
	 * @param primaryKeyColumnName			Sql-Spaltennamen, welcher den PrimaryKey der Tabelle darstellt.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public ListModel(String srcQuery,String tableNameForUpdateOrInsert, String primaryKeyColumnName) {
		super(srcQuery,tableNameForUpdateOrInsert,primaryKeyColumnName);
	}

	/**
	 * Legt einen allgemeinen unscharfen Filter über alle Tabellenspalten an, aktualisiert das Ergebnis und informiert die angeschlossene View<br>
	 * Es wird mittels 'like' gesucht und dem Filterwert werden '%' voran und nachgestellt.<br>
	 * Ausgeschlossen sind Spalten vom Typ Boolean und Spalten, welche nur via Alias benannt sind.<br>
	 * z.B. Spalten aus verschachtelten Select Abfragen oder Funktionen wie Max(*), Count(*) etc.
	 * 
	 * @param searchValue	Wert, nach dem gefiltert wird. 
	 */
	public void setSearchFilter(Object searchValue){ 
		
		deleteSearchFilter();
		
		if(!searchValue.equals("")){
		
			String[] columnNames = getSqlResultColumnNames();

			ResultSet result = getResult();
			ResultSetMetaData metaData;

			try {
				metaData = result.getMetaData();
	
				for(String columnName:columnNames){
					if(columnName.startsWith(".") || !columnName.contains(".")) continue;
					for(int index = 1; index <= metaData.getColumnCount(); index++){
						String currentColumn = (metaData.getTableName(index) + '.' + metaData.getColumnName(index)).toLowerCase();
						
						if(currentColumn.startsWith(".")) continue;
						
						if (columnName.toLowerCase().equals(currentColumn.toLowerCase())) {
							if(getSqlResultColumnNamesClassType(columnName.toLowerCase()).equals("java.lang.Boolean")) continue;
							
							StringFilter filter = new StringFilter(searchValue);
							filter.setFilterPraefix("%");
							filter.setFilterSuffix("%");
							setOrFilter(columnName,filter);
						}
						else continue;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		setResult();
		informView();
	}
	
	/**
	 * Legt einen unscharfen Filter für die übergebenen Spalten an, aktualisiert das Ergebnis und informiert die angeschlossene View.<br>
	 * Es wird mittels 'like' gesucht und dem Filterwert werden '%' voran und nachgestellt.<br>
	 * Ausgeschlossen sind Spalten vom Typ Boolean und Spalten, welche nur via Alias benannt sind.<br>
	 * z.B. Spalten aus verschachtelten Select Abfragen oder Funktionen wie Max(*), Count(*) etc. 
	 * @param searchValues	Zuordung von Spaltenname -> Suchwert.
	 * 
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void setSearchFilter(Map<String,Object> searchValues){
		
		deleteSearchFilter();
		
		for(Map.Entry<String, Object> entry:searchValues.entrySet()){
			if(entry.getValue() instanceof DatatypeFilter){
				setAndFilter(entry.getKey(),(DatatypeFilter)(entry.getValue()));
				continue;
			}
			StringFilter filter = new StringFilter((String)entry.getValue());
			filter.setFilterPraefix("%");
			filter.setFilterSuffix("%");
			setAndFilter(entry.getKey(),filter);

		}
		setResult();
		informView();
	}
	
	/**
	 * Entfernt alle gesetzten Filter.
	 */
	public void deleteSearchFilter(){
		String[] columnNames = getSqlResultColumnNames();
		
		for(String columnName:columnNames){
			deleteFilter(columnName);
		}
		
	}
	
	/**
	 * Entfernt alle gesetzten Filter.
	 */
	public void deleteSearchFilterAndInformViews(){
		String[] columnNames = getSqlResultColumnNames();
		
		for(String columnName:columnNames){
			deleteFilter(columnName);
		}
		setResult();
		informView();
	}
	
	/**
	 * Übermittelt den Datentyp der angegebenen Spalte.
	 * @param columnName	SqlTable-Spaltenname
	 * @return				Datentyp der Spalte.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public String getSqlResultColumnNamesClassType(String columnName){
		try{
			ResultSet result = getResult();
			ResultSetMetaData metaData = result.getMetaData();
			
			for(int index = 1; index <= metaData.getColumnCount();index++){
				if((metaData.getTableName(index) + '.' + metaData.getColumnName(index)).toLowerCase().equals(columnName)){
					return metaData.getColumnClassName(index);
				}
			}
	
		}catch(SQLException exception){
			exception.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Übermittelt alle Spaltennamen, ohne die Angabe der Tabelle.<br>
	 * z.B.: Tabelle.Spalte => Spalte
	 * @return	Spaltennamen ohne die Angabe der Tabelle.
	 */
	private String[] getSqlResultColumnNames(){
		return tableRowData.getColumnNames();
	}
	
	/**
	 * Setzt die Sortierung der Daten.<br>
	 * Ausgeschlossen sind Spalten vom Typ Boolean und Spalten, welche nur via Alias benannt sind.<br>
	 * z.B. Spalten aus verschachtelten Select Abfragen oder Funktionen wie Max(*), Count(*) etc.
	 * @param columnIndex	Spaltennamenindex des Models.
	 * @param direction		Richtung, in welche sortiert werden soll.
	 * @see	javax.swing.SortOrder
	 */
	public void setOrder(int columnIndex, SortOrder direction){
		String columnName = tableRowData.getColumnNames()[columnIndex];
		if(columnName.startsWith(".")) return;
	
		this.order = " order by " + columnName + " " + (direction==SortOrder.ASCENDING?"asc":"desc") + " ";
		setResult();
		informView();
	}
	
	/**
	 * Gibt die in der srcQuery Übermittelten Alias-Spaltennamen zurück.
	 * @return	Alias-Spaltennamen
	 */
	public Object[] getColumnAliasNames(){
		return tableRowData.getColumnAliasNames();
	}
	
	/**
	 * Ermittelt den Index einer Spalte.
	 * @param columnName	Spaltenname aus SqlTable-Definition oder Aliasname.
	 * @return				Index der Spalte im Model.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public int getColumnIndex(String columnName){
		return tableRowData.getColumnIndex(columnName);
	}
	
	/**
	 * Fügt eine Spalte an den Anfang der Tabelle hinzu.
	 * @param columnName	Spaltenname
	 * @param data			Standardwert
	 */
	public void addColumnAtBegin(String columnName,Object data){
		tableRowData.addColumnAtBegin(columnName, data);
	}
	
	/**
	 * Erzeugt das eigentliche {@link TableData}-Objekt, welches die Daten verwaltet.<br>
	 * Zusätzlich wird die Spalte "Auswahl" an den Anfang hinzugefügt
	 */
	@Override
	protected void setTableRowData(){
		tableRowData = new TableData(getResult(),getColumnLimit());
		tableRowData.addColumnAtBegin("Auswahl", (boolean)false);
	}
}
