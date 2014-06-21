package Models.Filter;

import Models.Interfaces.DatatypeFilter;

/**
 * Implementiert einen Filter f�r String Werte.
 */
public class StringFilter implements DatatypeFilter{
	private String wert;
	private String praefix = "'";
	private String suffix = "'";
	
	/**
	 * Initialisiert einen Stringfilter,<br>
	 * nimmt ein Object Wert entgegen und versucht diesen in einen String umzuwandeln.
	 * 
	 * @param wert Wert f�r den Stringfilter.
	 */
	public StringFilter(Object wert){
		this(wert.toString());
	}
	
	/**
	 * Initialisiert einen Stringfilter mit einem �bergebenen Wert.
	 * 
	 * @param wert Wert f�r den Stringfilter.
	 */
	public StringFilter(String wert){
		this.wert = wert;
	}
	
	/**
	 * Bietet die M�glichkeit ein Pr�fix f�r den String zu setzen.<br>
	 * z.B.: "%" f�r beliebige Zeichen vor dem Filterwert.
	 * 
	 * @param praefix Wert f�r den Pr�fix.
	 */
	public void setFilterPraefix(String praefix){
		this.praefix = "'" + praefix;
	}
	
	/**
	 * Bietet die M�glichkeit ein Suffix f�r den String zu setzen.<br>
	 * z.B.: "%" f�r beliebige Zeichen nach dem Filterwert.
	 * @param suffix	Wert f�r den Suffix
	 */
	public void setFilterSuffix(String suffix){
		this.suffix =  suffix + "'";
	}
	
	@Override
	public Object getWert() {
		return " like "+ praefix + wert + suffix;
	}

	@Override
	public void setWert(Object wert) {
		this.wert = (String)wert;
	}
	
	

}
