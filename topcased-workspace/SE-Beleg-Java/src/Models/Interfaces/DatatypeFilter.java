package Models.Interfaces;

/**
 * Definiert die Standardfunktionen eines Filters.
 */
public interface DatatypeFilter {
	/**
	 * Gibt den Wert des Filters zurück.
	 * @return	Filterwert.
	 */
	public Object getWert() ;

	/**
	 * Setzt den Wert des Filters auf den übergebenen Wert.
	 * @param wert	Neuer Wert des Filters.
	 */
	public void setWert(Object wert) ;
}
