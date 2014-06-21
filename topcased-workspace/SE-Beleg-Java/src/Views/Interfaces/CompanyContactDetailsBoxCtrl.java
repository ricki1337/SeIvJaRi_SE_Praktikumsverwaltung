package Views.Interfaces;

/**
 * Controller-Interface für die Ansprechpartner-Box in der Firmen-Detailansicht.
 */
public interface CompanyContactDetailsBoxCtrl extends BasicBoxCtrl{

	/**
	 * Liefert den Wert für ein Element in der Box.
	 * @param valueName	Sql-Spaltenname entsprechend einer SqlTable-Definition.
	 * @return			Den Wert der Spalte an der aktuellen Position im Model.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public String getStringValueForContactBoxElement(String valueName);

}
