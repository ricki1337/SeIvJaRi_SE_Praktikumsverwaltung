package Views.Interfaces;

/**
 * Erweitert die Definition der {@link BasicBox} um Funktionen zur Anzeige von Maildaten.
 * 
 *
 */
public interface MailBox extends BasicBox{
		
		/**
		 * Gibt die angegebene Absender-E-Mailadresse f�r die Anmeldung am Postfach zur�ck.
		 * @return	Absender-E-Mailadresse.
		 */
		public String getSenderEmailAdress();
		
		/**
		 * Gibt das eingegebene Passwort f�r die Anmeldung am Postfach zur�ck.
		 * @return 	Passwort f�r Anmeldung am Postfach.
		 */
		public char[] getSenderEmailPassword();
		
		/**
		 * �bernimmt den Status der gesendeten Mail und die Indexposition.
		 * @param statusLabelIndex	Index in der intern verwalteten E-Mail-Liste.
		 * @param status			Status der gesendeten Mail. True = erfolgreich versendet, False = nicht versendet.
		 */
		public void setMailSend(int statusLabelIndex, boolean status);
		
		/**
		 * Gibt die Empf�ngeradresse an der angeforderten Position der intern verwalteten E-Mail-Liste zur�ck.
		 * @param index	Indexposition der geforderten Empf�ngeradresse.
		 * @return		Empf�ngeradresse.
		 */
		public String getRecipientEmailAdress(int index);
		
		/**
		 * �bernimmt den Status des Mailvorgangs.
		 * @param statusText	Status in Stringform.
		 */
		public void setMailingStatus(String statusText);
}
