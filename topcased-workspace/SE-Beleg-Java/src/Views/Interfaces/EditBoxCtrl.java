package Views.Interfaces;

/**
 * Muss vom Controller implementiert werden, wenn es Elemente auf der View gibt, welche Daten aus dem Model ben�tigen.
 *
 */
public interface EditBoxCtrl extends BasicBoxCtrl{
	/**
	 * Setzt die View f�r die Eingabe eines neuen Datensatzes.
	 */
	public void setElementsForNewData();
	
	/**
	 * �bergibt die {@link String}-Repr�sentation einer angeforderten SqlTable-Spalte.
	 * @param sqlAlias	Der SqlTable-Spaltenname.
	 * @return 			Spaltenwert als {@link String}.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public String getStringValueForBoxElementEdit(String sqlAlias);
	
	/**
	 * �bergibt die {@link int}-Repr�sentation einer angeforderten SqlTable-Spalte.
	 * @param sqlAlias	Der SqlTable-Spaltenname.
	 * @return 			Spaltenwert als {@link int}.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public int getIntValueForBoxElementEdit(String sqlAlias);
	
	/**
	 * �bergibt die {@link boolean}-Repr�sentation einer angeforderten SqlTable-Spalte.
	 * @param sqlAlias	Der SqlTable-Spaltenname.
	 * @return 			Spaltenwert als {@link boolean}.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public boolean getBooleanValueForBoxElementEdit(String sqlAlias);
}
