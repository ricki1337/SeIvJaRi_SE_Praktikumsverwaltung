package Views.Interfaces;

/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementBottomNaviPrevSaveNextBox}-Element aufnimmt.
 */
public interface NaviPrevSaveNextBoxCtrl {
	/**
	 * Aufruf, wenn Button "<<" geklickt wurde.
	 */
	public void buttonPreviusClicked();
	
	/**
	 * Aufruf, wenn Button "speichern" geklickt wurde.
	 */
	public void buttonSaveChangesClicked();
	
	/**
	 * Aufruf, wenn Button ">>" geklickt wurde.
	 */
	public void buttonNextClicked();
	
	
	/**
	 * Übermittelt die aktuelle Datensatzposition an die View.
	 * @return	Aktuelle Datensatzposition des Models.
	 */
	public String getCurrentPos();
	
	/**
	 * Übermittelt die Gesamtanzahl der Datensätze an die View.
	 * @return	Gesamtanzahl Datensätze.
	 */
	public String getPosSum();
}
