package Models.Interfaces;

/**
 * Definiert die Möglichkeiten Filter und Sql-Tabellenspalten miteinander zu verknüpfen.
 */
public interface DatabaseFilterCtrl {
	/**
	 * Verbindet eine Sql-Tabellenspalte mit einem Filterwert.<br>
	 * Wenn es mehrere Filter gibt, wird dieser mittels "OR" verknüpft.
	 * 
	 * @param spaltenName	SqlTable-Definition einer Tabellenspalte.
	 * @param spaltenWert	Ein {@link Models.Filter}.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void setOrFilter(String spaltenName, DatatypeFilter spaltenWert);
	
	/**
	 * Verbindet eine Sql-Tabellenspalte mit einem Filterwert.<br>
	 * Wenn es mehrere Filter gibt, wird dieser mittels "AND" verknüpft.
	 * 
	 * @param spaltenName	SqlTable-Definition einer Tabellenspalte.
	 * @param spaltenWert	Ein {@link Models.Filter}.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void setAndFilter(String spaltenName, DatatypeFilter spaltenWert);

	/**
	 * Gibt den Filter als Kombination aus Filtertyp (And, Or), Tabellenspaltenname und Wert des Filters zurück.
	 * @return	Zusammengesetzter String Repräsentation des Filters.
	 */
	public String getFilter() ;

	/**
	 * Löscht einen gesetzten Filter mit dem entsprechenden Tabellenspaltennamen.
	 * 
	 * @param spaltenName SqlTable-Definition eines Tabellenspaltennamens.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void deleteFilter(String spaltenName) ;
}
