package Views.Interfaces;


/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementBottomNaviAbortSelectBox}-Element aufnimmt.
 */
public interface NaviAbortSelectBoxCtrl {
	/**
	 * Aufruf, wenn Button "abbrechen" geklickt wurde.
	 */
	public void buttonAbortClicked();
	
	/**
	 * Aufruf, wenn Button "auswählen" geklickt wurde.
	 */
	public void buttonSelectClicked();
}
