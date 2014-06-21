package Models.Interfaces;

/**
 * Definiert die Beobachterfunktionen,<br>
 * welche vom {@link Database} bei �nderungen der Datenbank aufgerufen werden um die Models zu informieren.
 */
public interface Observer{

	/**
	 * �bermittelt alle von einer �nderung betroffenen Tabellennamen gem�� SqlTable-Definition.
	 * @param infoAboutChanged	Liste mit allen g�nderten Tabellennamen.
	 * @see				Models.Datenbank.SqlTableProfs
	 * @see				Models.Datenbank.SqlTableStudent
	 * @see				Models.Datenbank.SqlTableContracts
	 * @see				Models.Datenbank.SqlTableCompanies
	 * @see				Models.Datenbank.SqlTableContacts
	 */
	public void refresh(String[] infoAboutChanged);

}