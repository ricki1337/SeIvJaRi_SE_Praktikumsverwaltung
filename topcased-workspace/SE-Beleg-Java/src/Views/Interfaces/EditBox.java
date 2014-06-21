package Views.Interfaces;

import java.util.Map;

/**
 * 
 * Stellt die Definition der Elemente dar, welche Daten an den Controller zurückgeben.
 *
 */
public interface EditBox  extends BasicBox{
	
	/**
	 * Wird von Controller aufgerufen, um Model mit Daten zu versorgen.
	 * @return	{@link Map} mit Zuordnung von SqlTable-Spaltenname und Wert.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public Map<String,Object> getInputValues();
}
