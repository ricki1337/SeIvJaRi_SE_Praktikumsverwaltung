package Views.Interfaces;

/**
 * Erweitert die Definition der {@link BasicBox} um Funktionen zur Anzeige von Maildaten.
 * 
 *
 */
public interface MailBox extends BasicBox{
		
		/**
		 * Gibt die angegebene Absender-E-Mailadresse für die Anmeldung am Postfach zurück.
		 * @return	Absender-E-Mailadresse.
		 */
		public String getSenderEmailAdress();
		
		/**
		 * Gibt das eingegebene Passwort für die Anmeldung am Postfach zurück.
		 * @return 	Passwort für Anmeldung am Postfach.
		 */
		public char[] getSenderEmailPassword();
		
		/**
		 * Übernimmt den Status der gesendeten Mail und die Indexposition.
		 * @param statusLabelIndex	Index in der intern verwalteten E-Mail-Liste.
		 * @param status			Status der gesendeten Mail. True = erfolgreich versendet, False = nicht versendet.
		 */
		public void setMailSend(int statusLabelIndex, boolean status);
		
		/**
		 * Gibt die Empfängeradresse an der angeforderten Position der intern verwalteten E-Mail-Liste zurück.
		 * @param index	Indexposition der geforderten Empfängeradresse.
		 * @return		Empfängeradresse.
		 */
		public String getRecipientEmailAdress(int index);
		
		/**
		 * Übernimmt den Status des Mailvorgangs.
		 * @param statusText	Status in Stringform.
		 */
		public void setMailingStatus(String statusText);
}
