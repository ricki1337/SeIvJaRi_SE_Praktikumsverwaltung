package Views.Interfaces;

/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementBottomNaviMailPrintBox}-Element aufnimmt.
 */
public interface NaviMailPrintBoxCtrl {
	
	/**
	 * Aufruf, wenn Button "mailen" geklickt wurde.
	 */
	public void buttonMailToClicked();
	
	/**
	 * Aufruf, wenn Button "drucken" geklickt wurde.
	 */
	public void buttonPrintClicked();
}
