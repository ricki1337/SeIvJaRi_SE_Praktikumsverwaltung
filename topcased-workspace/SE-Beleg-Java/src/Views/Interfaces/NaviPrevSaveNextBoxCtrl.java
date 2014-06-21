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
	 * �bermittelt die aktuelle Datensatzposition an die View.
	 * @return	Aktuelle Datensatzposition des Models.
	 */
	public String getCurrentPos();
	
	/**
	 * �bermittelt die Gesamtanzahl der Datens�tze an die View.
	 * @return	Gesamtanzahl Datens�tze.
	 */
	public String getPosSum();
}
