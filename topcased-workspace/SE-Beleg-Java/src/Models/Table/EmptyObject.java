package Models.Table;

/**
 * Hilfklasse um leere Elemente in der Tabelle zu f�llen.
 */
public class EmptyObject extends Object{
	
	/**
	 * Initialisiert ein EmptyObject.
	 */
	public EmptyObject(){
		super();
	}
	
	@Override
	public String toString(){
		return new String(" ");
	}
}
