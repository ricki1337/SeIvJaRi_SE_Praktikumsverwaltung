package Views.Interfaces;


public interface MailBox extends BasicBox{

		public String getSenderEmailAdress();
		public char[] getSenderEmailPassword();
		public void setMailsSend(boolean status);
		public String getRecipientEmailAdress(int index);
}
