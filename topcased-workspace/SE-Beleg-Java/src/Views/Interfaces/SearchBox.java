package Views.Interfaces;

/**
 * Definiert die Standardfunktionen f�r das Suchmen�.
 */
public interface SearchBox  extends BasicBox{
	
	/**
	 * Stellt den eingegeben Wert der anzuzeigenden Zeilen zur Verf�gung.
	 * @return	Eingegebener Wert der anzuzeigenden Zeilen der Tabelle.
	 */
	public int getValueOfRecordLimitField();
	
	/**
	 * Stellt den eingegebenen Suchwert zur Verf�gung.
	 * @return	Suchwert.
	 */
	public String getValueOfSearchField();
}
