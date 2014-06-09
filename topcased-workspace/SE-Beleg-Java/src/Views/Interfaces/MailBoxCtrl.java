package Views.Interfaces;

public interface MailBoxCtrl extends BasicBoxCtrl{

		public void buttonSendMailClicked();
		public String[][] getMailData();
		public void setSendFlag(int index, boolean status);
		public void setBerichtFlag(int index, boolean selected);
		public void setZeugnisFlag(int index, boolean selected);
}
