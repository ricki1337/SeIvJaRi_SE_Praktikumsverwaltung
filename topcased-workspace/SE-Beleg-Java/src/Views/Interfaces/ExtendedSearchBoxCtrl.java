package Views.Interfaces;

/**
 * Muss vom Controller implemtiert werden, wenn eine erweiterte Suche auf der View platziert wird.
 * 
 */
public interface ExtendedSearchBoxCtrl extends BasicBoxCtrl{
	
	/**
	 * Aufruf, wenn der Button "Suchen" geklickt wurde.
	 */
	public void buttonSearchSpecificClicked();
	
	/**
	 * Aufruf, wenn der Button "Filter entfernen" gedrückt wurde
	 */
	public void buttonDeleteFilterClicked();
}
