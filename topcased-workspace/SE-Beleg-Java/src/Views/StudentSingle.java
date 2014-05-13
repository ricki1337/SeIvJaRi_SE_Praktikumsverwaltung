package Views;

import Controller.Controller;

public class StudentSingle extends SingleView{

	public StudentSingle(Controller controller) {
		super(controller);
		this.setTitle("Studentendetails");
	}

	@Override
	public void setElemente() {
		addTextfieldWithSqlReference("Matrikelnr.", "MatrNr", "MatrNr", 1, 1);
		addTextfieldWithSqlReference("Vorname", "Firstname", "Firstname", 1, 2);
		addTextfieldWithSqlReference("Nachname", "Name", "Name", 1, 3);
		addTextfieldWithSqlReference("E-Mail", "Email", "Email", 1, 4);
		addTextfieldWithSqlReference("Studiengruppe", "StGr", "StGr", 1, 5);
		addTextAreaWithSqlReference("Bemerkung", "Note", "Note", 6, 3);
		
		addBottomMenu(6);
	}

}
