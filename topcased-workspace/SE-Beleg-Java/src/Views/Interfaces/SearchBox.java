package Views.Interfaces;

/**
 * Definiert die Standardfunktionen für das Suchmenü.
 */
public interface SearchBox  extends BasicBox{
	
	/**
	 * Stellt den eingegeben Wert der anzuzeigenden Zeilen zur Verfügung.
	 * @return	Eingegebener Wert der anzuzeigenden Zeilen der Tabelle.
	 */
	public int getValueOfRecordLimitField();
	
	/**
	 * Stellt den eingegebenen Suchwert zur Verfügung.
	 * @return	Suchwert.
	 */
	public String getValueOfSearchField();
}
