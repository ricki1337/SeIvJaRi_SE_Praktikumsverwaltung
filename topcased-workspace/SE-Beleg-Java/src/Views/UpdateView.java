package Views;

/**
 * Definiert Methoden f�r die Schnittstelle zwischen Modell und View
 */
public interface UpdateView {
	/**
	 * Info das sich Daten des Models ge�ndert haben.
	 */
	public void modelHasChanged();
	
	/**
	 * Zugriffspunkt des Models auf die in der View eingegebenen Daten.
	 * @return	Array, welches eine Zuordnung von Spaltenname und neuen Wert enth�lt.
	 */
	public Object[][] getInputValues();
}
