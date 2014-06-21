package Views.Interfaces;



/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementSearchMenu}-Element aufnimmt.
 */
public interface SearchBoxCtrl extends BasicBoxCtrl{
	/**
	 * Aufruf, wenn das Feld "Suche" geändert wird,<br>
	 * wird bei jeder Änderung des Feldes aufgerufen.
	 */
	public void searchFieldChanged();
	
	/**
	 * Aufruf, wenn das Feld "Anzahl Datensätze" geändert wurde und mit Enter bestätigt wird.
	 */
	public void RecordLimitFieldChanged();
	
	/**
	 * Aufruf, wenn Button "erweiterte Suche" geklickt wurde.
	 */
	public void buttonShowExtendedSearchClicked();
	
	/**
	 * Aufruf, wenn Button "Neu" geklickt wurde.
	 */
	public void buttonAddNewDataClicked();
	
	/**
	 * Übergibt das aktuell im Modell festgelegten Datensatzlimit.
	 * @return	Limit der Datensätze die angezeigt werden sollen.
	 */
	public int getSqlRecordLimit();
}
