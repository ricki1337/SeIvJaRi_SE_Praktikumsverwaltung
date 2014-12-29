package Views;

/**
 * Definiert Methoden für die Schnittstelle zwischen Modell und View
 */
public interface UpdateView {
	/**
	 * Info das sich Daten des Models geändert haben.
	 */
	public void modelHasChanged();
	
	/**
	 * Zugriffspunkt des Models auf die in der View eingegebenen Daten.
	 * @return	Array, welches eine Zuordnung von Spaltenname und neuen Wert enthält.
	 */
	public Object[][] getInputValues();
}
