package Views;

import Controller.Controller;
import Models.Datenbank.SqlTableStudent;

public class StudentEmptySingle extends EmptySingleView{

	public StudentEmptySingle(Controller controller) {
		super(controller);
		this.setTitle("Studentendetails");
	}

	@Override
	public void setElemente() {
		addTextfieldWithSqlReference("Matrikelnr.", SqlTableStudent.TableNameDotMatrikelNummer, 1, 1);
		addTextfieldWithSqlReference("Vorname", SqlTableStudent.TableNameDotVorname, 1, 2);
		addTextfieldWithSqlReference("Nachname", SqlTableStudent.TableNameDotNachname, 1, 3);
		addTextfieldWithSqlReference("E-Mail", SqlTableStudent.TableNameDotEMail, 1, 4);
		addTextfieldWithSqlReference("Studiengruppe", SqlTableStudent.TableNameDotStudiengruppe, 1, 5);
		addTextAreaWithSqlReference("Bemerkung", SqlTableStudent.TableNameDotBemerkung, 6, 3);
		
		setBottomMenu(6);
	}

	

}
