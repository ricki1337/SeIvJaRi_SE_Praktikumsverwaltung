package Models.Interfaces;

/**
 * Definiert die M�glichkeiten Filter und Sql-Tabellenspalten miteinander zu verkn�pfen.
 */
public interface DatabaseFilterCtrl {
	/**
	 * Verbindet eine Sql-Tabellenspalte mit einem Filterwert.<br>
	 * Wenn es mehrere Filter gibt, wird dieser mittels "OR" verkn�pft.
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
	 * Wenn es mehrere Filter gibt, wird dieser mittels "AND" verkn�pft.
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
	 * Gibt den Filter als Kombination aus Filtertyp (And, Or), Tabellenspaltenname und Wert des Filters zur�ck.
	 * @return	Zusammengesetzter String Repr�sentation des Filters.
	 */
	public String getFilter() ;

	/**
	 * L�scht einen gesetzten Filter mit dem entsprechenden Tabellenspaltennamen.
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
