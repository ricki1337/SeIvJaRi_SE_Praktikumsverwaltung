package Controller;

import java.awt.event.WindowEvent;

import Views.Dialog.ErrorMessageDialog;
import Views.Interfaces.ErrorManagerCtrl;

public class ErrorManager extends ControllerNew implements ErrorManagerCtrl{

	private Exception exception;
	private ErrorMessageDialog errorDialog;
	public boolean retry = false;
	
	/**
	 * Initialisiert die Ansicht der geworfenen Exception.<br>
	 * Übernimmt die Exception und bringt sie in einem Dialig zur Ausgabe.
	 * 
	 * @param error	Geworfene Exception.
	 */
	public ErrorManager(Exception error){
		exception = error;
		errorDialog = new ErrorMessageDialog(this);
		errorDialog.setErrorMessage(exception.getMessage().toString());
		errorDialog.setErrorDetails(error);
		errorDialog.setTitle("Ein Fehler ist aufgetreten.");
		display();
	}
	
	/**
	 * Initialisiert die Ansicht der geworfenen Exception.<br>
	 * Übernimmt die Exception und eine definierbare Errormeldung, bringt sie in einem Dialig zur Ausgabe.
	 * 
	 * @param error				Geworfene Exception.
 	 * @param myErrorMessage	Eigene, spezifische Errormeldung.
	 */
	public ErrorManager(Exception error, String myErrorMessage){
		if(myErrorMessage == null) {new ErrorManager(error); return;}
		exception = error;
		errorDialog = new ErrorMessageDialog(this);
		errorDialog.setErrorMessage(myErrorMessage);
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
		errorDialog.setVisible(false);
		errorDialog.dispatchEvent(new WindowEvent(errorDialog, WindowEvent.WINDOW_CLOSING));
	}

	@Override
	public void buttonOkClicked() {
		errorDialog.setVisible(false);
		errorDialog.dispatchEvent(new WindowEvent(errorDialog, WindowEvent.WINDOW_CLOSING));
	}

}
