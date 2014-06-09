package Views;

import Controller.Controller;
import Models.Datenbank.SqlTableCompanies;

public class CompanieEmptySingle extends EmptySingleView{

	public CompanieEmptySingle(Controller controller) {
		super(controller);
		this.setTitle("Firmendetails");
	}

	@Override
	public void setElemente() {
		addTextfieldWithSqlReference("Firmenname", SqlTableCompanies.TableNameDotFirmenname, 1, 1);
		addTextfieldWithSqlReference("Straﬂe", SqlTableCompanies.TableNameDotStrasse, 1, 3);
		addTextfieldWithSqlReference("PLZ", SqlTableCompanies.TableNameDotPLZ, 1, 4);
		addTextfieldWithSqlReference("Ort", SqlTableCompanies.TableNameDotOrt, 1, 5);
		addTextfieldWithSqlReference("Land", SqlTableCompanies.TableNameDotLand, 1, 6);
		addTextfieldWithSqlReference("Tel.", SqlTableCompanies.TableNameDotTelefonnummer, 4, 3);
		addTextAreaWithSqlReference("Bemerkung", SqlTableCompanies.TableNameDotBemerkung, 4, 6);
		
		setBottomMenu(12);
	}

	

}
