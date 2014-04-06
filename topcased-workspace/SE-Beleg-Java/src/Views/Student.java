package Views;

import Controller.Controller;

public class Student extends View{

	public Student(Controller controller) {
		super(controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setElemente() {
		// TODO Auto-generated method stub
//		setTextfield("Vorname", "Firstname", 1, 1);
//		setTextfield("Nachname", "Name", 1, 2);
//		setTextfield("E-Mail", "Email", 1, 3);
//		
		setTable();
	}

	

}
