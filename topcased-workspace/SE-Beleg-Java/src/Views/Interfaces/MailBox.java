package Views.Interfaces;


public interface MailBox extends BasicBox{

		public String getSenderEmailAdress();
		public char[] getSenderEmailPassword();
		public void setMailSend(int statusLabelIndex, boolean status);
		public String getRecipientEmailAdress(int index);
		public void setMailingStatus(String statusText);
}
