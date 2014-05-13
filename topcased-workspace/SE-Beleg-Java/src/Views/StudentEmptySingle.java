package Views;

import Controller.Controller;

public class StudentEmptySingle extends EmptySingleView{

	public StudentEmptySingle(Controller controller) {
		super(controller);
		this.setTitle("Studentendetails");
	}

	@Override
	public void setElemente() {
		addTextfieldWithSqlReference("Matrikelnr.", "MatrNr", 1, 1);
		addTextfieldWithSqlReference("Vorname", "Firstname", 1, 2);
		addTextfieldWithSqlReference("Nachname", "Name", 1, 3);
		addTextfieldWithSqlReference("E-Mail", "Email", 1, 4);
		addTextfieldWithSqlReference("Studiengruppe", "StGr", 1, 5);
		addTextAreaWithSqlReference("Bemerkung", "Note", 6, 3);
		
		setBottomMenu(6);
	}

	

}
