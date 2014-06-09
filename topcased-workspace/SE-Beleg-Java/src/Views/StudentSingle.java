package Views;

import Controller.Controller;
import Models.Datenbank.SqlTableStudent;

public class StudentSingle extends SingleView{

	public StudentSingle(Controller controller) {
		super(controller);
		this.setTitle("Studentendetails");
	}

	@Override
	public void setElemente() {
		
		addTextfieldWithSqlReference("Matrikelnr.", "Matrikelnr", SqlTableStudent.TableNameDotMatrikelNummer, 1, 1);
		addTextfieldWithSqlReference("Vorname", "Vorname", SqlTableStudent.TableNameDotVorname, 1, 2);
		addTextfieldWithSqlReference("Nachname", "Nachname", SqlTableStudent.TableNameDotNachname, 1, 3);
		addTextfieldWithSqlReference("E-Mail", "E-Mail", SqlTableStudent.TableNameDotEMail, 1, 4);
		addTextfieldWithSqlReference("Studiengruppe", "Studiengruppe", SqlTableStudent.TableNameDotStudiengruppe, 1, 5);
		addTextAreaWithSqlReference("Bemerkung", "Bemerkung", SqlTableStudent.TableNameDotBemerkung, 6, 3);
		
		addBottomMenu(6);
	}

}
