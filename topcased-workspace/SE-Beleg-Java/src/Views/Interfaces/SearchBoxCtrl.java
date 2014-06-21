package Views.Interfaces;



/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementSearchMenu}-Element aufnimmt.
 */
public interface SearchBoxCtrl extends BasicBoxCtrl{
	/**
	 * Aufruf, wenn das Feld "Suche" ge�ndert wird,<br>
	 * wird bei jeder �nderung des Feldes aufgerufen.
	 */
	public void searchFieldChanged();
	
	/**
	 * Aufruf, wenn das Feld "Anzahl Datens�tze" ge�ndert wurde und mit Enter best�tigt wird.
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
	 * �bergibt das aktuell im Modell festgelegten Datensatzlimit.
	 * @return	Limit der Datens�tze die angezeigt werden sollen.
	 */
	public int getSqlRecordLimit();
}
