package Controller.Interfaces;

/**
 * Ermöglicht eine Datenverbindung zwischen zwei Controllern. 
 */
public interface SelectedValueCallbackCtrl {
	
	/**
	 * Übernimmt ein Wertepaar zur weiteren Verarbeitung.
	 * @param valueName	Name der Variablen.
	 * @param value		Der Wert der Variablen.
	 */
	public void setSelectedValue(String valueName, Object value);
}
