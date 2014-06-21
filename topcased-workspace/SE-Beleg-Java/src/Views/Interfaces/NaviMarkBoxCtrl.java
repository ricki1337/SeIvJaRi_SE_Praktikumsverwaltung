package Views.Interfaces;

/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementBottomNaviMarkBox}-Element aufnimmt.
 */
public interface NaviMarkBoxCtrl {
	/**
	 * Aufruf, wenn Button "ausgewählte markieren" geklickt wurde.
	 */
	public void buttonMarkClicked();
}
