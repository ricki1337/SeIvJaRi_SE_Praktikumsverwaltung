package Views.Interfaces;

/**
 * 
 * Muss vom Controller implementiert werden, wenn er einen {@link ErrorMessageDialog} als View nutzt.
 *
 */
public interface ErrorManagerCtrl {
	/**
	 * Aufruf, wenn der Button "Wiederholen" geklickt wurde.
	 */
	public void buttonRepeatClicked();
	
	/**
	 * Aufruf, wenn der Button "Ok" geklickt wurde.
	 */
	public void buttonOkClicked();
}
