package Views;

import Controller.Controller;
import Models.Datenbank.SqlTableContacts;

public class ContactEmptySingle extends EmptySingleView{
	String companyId;
	public ContactEmptySingle(Controller controller) {
		super(controller);
		this.setTitle("Betreuerdetails");
	}

	@Override
	public void setElemente() {
		/*
		 * TODO
		 * 
		 */
		this.addHiddenFieldWithSqlReference(SqlTableContacts.TableNameDotZuordnungFirma, companyId);
		addTextfieldWithSqlReference("Name", SqlTableContacts.TableNameDotName, 1, 1);
		addTextfieldWithSqlReference("Telefon", SqlTableContacts.TableNameDotTelefonnummer, 1, 2);
		addTextfieldWithSqlReference("Bemerkung", SqlTableContacts.TableNameDotBemerkung, 1, 3);
		
		setBottomMenu(6);
	}

	public void setCompany(String companyId){
		this.companyId = companyId;
	}

}
