package Views.Interfaces;


/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementBottomNaviAbortSaveBox}-Element aufnimmt.
 */
public interface NaviAbortSaveBoxCtrl {
	/**
	 * Aufruf, wenn Button "abbrechen" geklickt wurde
	 */
	public void buttonAbortClicked();
	
	/**
	 * Aufruf, wenn Button "speichern" geklickt wurde
	 */
	public void buttonSaveClicked();
}
