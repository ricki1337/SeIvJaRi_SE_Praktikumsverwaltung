package Views.Interfaces;

import java.util.Map;

/**
 * Definiert die Standardfunktionen für die Erweiterte Suche.
 *
 */
public interface ExtendedSearchBox extends BasicBox{
	/**
	 * Übermittelt die eingegebenen Filterinformationen.
	 * @return	{@link Map} mit der Zuordnung von SqlTable-Definition zu Wert des Filters.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public Map<String,Object> getSearchFilter();
	
	/**
	 * Wird aufgerufen, wenn alle Filter entfernt werden.
	 */
	public void clearFields();
}
