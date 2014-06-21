package Views.Interfaces;

import javax.swing.SortOrder;


/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementTable}-Element aufnimmt.<br>
 */
public interface TableBoxCtrl {

	/**
	 * Übermittelt die Tabellendateninformationen in Form eines 2D-Array 
	 * @return	Datenarray mit den Informationen aus dem Model.
	 */
	public Object[][] getTableData();
	
	/**
	 * Übermittelt die Tabellenüberschriften in Form eines Array. 
	 * @return	Datenarray mit den Tabellenüberschriften aus dem Model.
	 */
	public Object[] getTableHeader();
	
	/**
	 * Aufruf, wenn auf eine Zeile einfach geklickt wurde.
	 */
	public void tableRowClicked();
	
	/**
	 * Aufruf, wenn auf eine Zeile doppelt geklickt wurde.
	 */
	public void tableRowDoubleClicked();
	
	/**
	 * Übermittelt den Wert einer abgerufenen Spalte und Zeile des Tabellenmodels.
	 * @param row		Aus welcher Zeile soll das Datum kommen.
	 * @param column	Aus welcher Spalte soll das Datum kommen, Angabe aus SqlTable-Definition 
	 * @return			Datum der Position.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public Object getValueFromPosition(int row, String column);
	
	/**
	 * Setzt den übergebenen Wert in der übergebenen Spalte und Zeile des Tabellenmodels.
	 * @param row		In welche Zeile soll das Datum geschrieben werden.
	 * @param column	In welche Spalte soll das Datum geschrieben werden, Angabe aus SqlTable-Definition 
	 * @param value		Datum, welches geschrieben werden soll.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void setValueAtPosition(int row, String column, Object value);
	
	/**
	 * Ermittelt die Indexposition eines abgefragten Spaltennamens. 
	 * @param columnName	Spaltenname des Models, Angabe aus SqlTable-Definition
	 * @return				Index der Spalte im Model.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public int getColumnIndex(String columnName);
	
	/**
	 * Setzt die Sortierung der Daten der Tabelle.
	 * @param columnIndex		Spaltenindex, nach welchem sortiert werden soll.
	 * @param orderDirection	Sortierreihenfolge
	 * @see						javax.swing.SortOrder
	 */
	public void setOrderByColumn(int columnIndex, SortOrder orderDirection);

}

