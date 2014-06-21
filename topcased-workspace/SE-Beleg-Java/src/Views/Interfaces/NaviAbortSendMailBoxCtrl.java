package Views.Interfaces;

/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementBottomNaviSendMailBox}-Element aufnimmt.
 */
public interface NaviAbortSendMailBoxCtrl extends BasicBoxCtrl{

	/**
	 * Aufruf, wenn Button "Mails senden" geklickt wurde.
	 */
	public void buttonSendMailClicked();
	
	/**
	 * Aufruf, wenn Button "abbrechen"  geklickt wurde.
	 */
	public void buttonAbortClicked();
	
}
