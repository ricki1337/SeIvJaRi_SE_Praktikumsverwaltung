package Views.Interfaces;

/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link BoxElementBottomNaviEditMailPrintBox}-Element aufnimmt.
 */
public interface NaviEditMailPrintBoxCtrl  extends NaviEditBoxCtrl{
	/**
	 * Aufruf, wenn Button "mailen" gedrückt wurde.
	 */
	public void buttonMailToClicked();
	
	/**
	 * Aufruf, wenn Button "drucken" gedrückt wurde.
	 */
	public void buttonPrintClicked();
}
