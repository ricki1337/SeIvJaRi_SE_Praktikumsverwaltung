package Views;

import Controller.Controller;
import Models.Datenbank.SqlTableContacts;

public class ContactSingle extends SingleView{

	public ContactSingle(Controller controller) {
		super(controller);
		this.setTitle("Betreuerdetails");
	}

	@Override
	public void setElemente() {
		addTextfieldWithSqlReference("Name","Name", SqlTableContacts.TableNameDotName, 1, 1);
		addTextfieldWithSqlReference("Telefon","Tel.", SqlTableContacts.TableNameDotTelefonnummer, 1, 2);
		addTextfieldWithSqlReference("Bemerkung","Bemerkung", SqlTableContacts.TableNameDotBemerkung, 1, 3);
		
		addBottomMenu(6);
	}

}
