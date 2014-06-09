package Controller;

import java.awt.event.WindowEvent;

import Views.Dialog.ErrorMessageDialog;
import Views.Interfaces.ErrorManagerCtrl;

public class ErrorManager extends ControllerNew implements ErrorManagerCtrl{

	private Exception exception;
	private ErrorMessageDialog errorDialog;
	public boolean retry = false;
	
	public ErrorManager(Exception error){
		exception = error;
		errorDialog = new ErrorMessageDialog(this);
		errorDialog.setErrorMessage(exception.getMessage().toString());
		errorDialog.setErrorDetails(error);
		errorDialog.setTitle("Ein Fehler ist aufgetreten.");
		display();
	}
	
	@Override
	public void display() {
		errorDialog.showErrorDialog();
	}

	@Override
	public void buttonRepeatClicked() {
		retry = true;
		System.out.println("wiederholen und schlieﬂen");
		errorDialog.setVisible(false);
		errorDialog.dispatchEvent(new WindowEvent(errorDialog, WindowEvent.WINDOW_CLOSING));
	}

	@Override
	public void buttonOkClicked() {
		System.out.println("schlieﬂen");
		errorDialog.setVisible(false);
		errorDialog.dispatchEvent(new WindowEvent(errorDialog, WindowEvent.WINDOW_CLOSING));
	}

}
