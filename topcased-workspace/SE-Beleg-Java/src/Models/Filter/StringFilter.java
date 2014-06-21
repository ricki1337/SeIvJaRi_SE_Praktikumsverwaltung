package Models.Filter;

import Models.Interfaces.DatatypeFilter;

/**
 * Implementiert einen Filter für String Werte.
 */
public class StringFilter implements DatatypeFilter{
	private String wert;
	private String praefix = "'";
	private String suffix = "'";
	
	/**
	 * Initialisiert einen Stringfilter,<br>
	 * nimmt ein Object Wert entgegen und versucht diesen in einen String umzuwandeln.
	 * 
	 * @param wert Wert für den Stringfilter.
	 */
	public StringFilter(Object wert){
		this(wert.toString());
	}
	
	/**
	 * Initialisiert einen Stringfilter mit einem übergebenen Wert.
	 * 
	 * @param wert Wert für den Stringfilter.
	 */
	public StringFilter(String wert){
		this.wert = wert;
	}
	
	/**
	 * Bietet die Möglichkeit ein Präfix für den String zu setzen.<br>
	 * z.B.: "%" für beliebige Zeichen vor dem Filterwert.
	 * 
	 * @param praefix Wert für den Präfix.
	 */
	public void setFilterPraefix(String praefix){
		this.praefix = "'" + praefix;
	}
	
	/**
	 * Bietet die Möglichkeit ein Suffix für den String zu setzen.<br>
	 * z.B.: "%" für beliebige Zeichen nach dem Filterwert.
	 * @param suffix	Wert für den Suffix
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
