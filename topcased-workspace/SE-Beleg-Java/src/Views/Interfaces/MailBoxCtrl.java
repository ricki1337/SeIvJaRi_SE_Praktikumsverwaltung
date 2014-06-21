package Views.Interfaces;

/**
 * Muss vom Controller implentiert werden, wenn die View ein {@link MailBox}-Element aufnimmt.
 *
 */
public interface MailBoxCtrl extends BasicBoxCtrl{
	
	/**
	 * Übermittelt alle erforderlichen E-Mail-Daten an das {@link MailBox}-Element.
	 * @return	String[][] Array.
	 */
	public String[][] getMailData();
	
	/**
	 * Setzt die Information ob die Mail an den Empfänger versendet werden soll oder nicht.
	 * @param index		Index des Datensatzes
	 * @param status	True = Mail wird versendet, False = Mail wird nicht versendet. 
	 */
	public void setSendFlag(int index, boolean status);
	
	/**
	 * Setzt die Information ob ein Bericht vorliegt oder nicht.
	 * @param index		Index des Datensatzes
	 * @param status	True = Bericht liegt vor, False = Bericht liegt nicht vor. 
	 */
	public void setBerichtFlag(int index, boolean selected);
	
	/**
	 * Setzt die Information ob ein Zeugnis vorliegt oder nicht.
	 * @param index		Index des Datensatzes
	 * @param status	True = Zeugnis liegt vor, False = Zeugnis liegt nicht vor. 
	 */
	public void setZeugnisFlag(int index, boolean selected);
}
