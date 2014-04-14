package Views;


import Controller.Controller;


public class StudentDetails extends SingleView{

	public StudentDetails(Controller controller) {
		super(controller);
		
	}

	@Override
	public void setElemente() {
		setTextfield("Vorname", "Vorname", 1, 1);
		setTextfield("Nachname", "Nachname", 1, 2);
		setTextfield("E-Mail", "E-Mail", 1, 3);
		
	}

}
