package Views.Interfaces;

/**
 * Muss vom Controller implementiert werden, wenn es Elemente auf der View gibt, welche Daten aus dem Model benötigen.
 *
 */
public interface EditBoxCtrl extends BasicBoxCtrl{
	/**
	 * Setzt die View für die Eingabe eines neuen Datensatzes.
	 */
	public void setElementsForNewData();
	
	/**
	 * Übergibt die {@link String}-Repräsentation einer angeforderten SqlTable-Spalte.
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
	 * Übergibt die {@link int}-Repräsentation einer angeforderten SqlTable-Spalte.
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
	 * Übergibt die {@link boolean}-Repräsentation einer angeforderten SqlTable-Spalte.
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
