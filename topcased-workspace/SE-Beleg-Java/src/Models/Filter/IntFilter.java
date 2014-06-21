package Models.Filter;

import Models.Interfaces.DatatypeFilter;

/**
 * Implementiert einen Filter für Integer Werte.
 *
 */
public class IntFilter implements DatatypeFilter{

	private int filterValue;
	private int max;
	private boolean between = false;
	
	/**
	 * Initialisiert einen Integerfilter mit einem Wert.
	 * @param wert	Wert für den Integerfilter.
	 */
	public IntFilter(int wert){
		this.filterValue = wert;
	}
	
	/**
	 * Initialisiert einen Integerfilter mit dem Wert "0".
	 */
	public IntFilter(){
		filterValue = 0;
	}
	
	/**
	 * Initialisiert einen Integerfilter,<br>
	 * nimmt einen {@link String} entgegen und versucht diesen {@link String} in einen {@link Integer} zu konvertieren.
	 * @param value		{@link String}-Wert, welcher als Integer gesetzt werden soll.
	 */
	public IntFilter(String value) {
		filterValue = Integer.parseInt(value);
	}
	
	/**
	 * Initialisiert einen Integerfilter,<br>
	 * nimmt ein {@link Object} entgegen und versucht dieses {@link Object} in einen {@link Integer} zu konvertieren. 
	 * @param value		{@link Object}-Wert, welcher als {@link Integer} gesetzt werden soll.
	 */
	public IntFilter(Object value) {
		if(value instanceof String)
			filterValue = Integer.parseInt((String)value);
		if(value instanceof Integer)
			filterValue = (int)value;
		
		throw new IllegalArgumentException();
	}

	/**
	 * Wandelt den Filter in einen Range-Filter.<br>
	 * Nimmt einen je einen Wert für min und max entgegen.
	 * @param min	Wert, welcher das Minimum darstellt.
	 * @param max	Wert, welcher das Maximum darstellt.
	 */
	public void setRange(int min, int max){
		if(min > max){
			int tmp = max;
			min = max;
			max = tmp;
		}
		between = true;
		filterValue = min;
		this.max = max;
	}
	
	@Override
	public Object getWert() {
		if(between)
			return " between " + filterValue + " and " + max;
		
		return " = " + filterValue;
	}

	@Override
	public void setWert(Object wert) {
		this.filterValue = (int)wert;
	}
	

}
