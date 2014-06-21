package Models.Interfaces;

/**
 * Definiert die Beobachterfunktionen,<br>
 * welche vom {@link Database} bei Änderungen der Datenbank aufgerufen werden um die Models zu informieren.
 */
public interface Observer{

	/**
	 * Übermittelt alle von einer Änderung betroffenen Tabellennamen gemäß SqlTable-Definition.
	 * @param infoAboutChanged	Liste mit allen gänderten Tabellennamen.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void refresh(String[] infoAboutChanged);

}