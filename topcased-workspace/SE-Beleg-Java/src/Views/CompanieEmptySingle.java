package Views;

import Controller.Controller;

public class CompanieEmptySingle extends EmptySingleView{

	public CompanieEmptySingle(Controller controller) {
		super(controller);
		this.setTitle("Firmendetails");
	}

	@Override
	public void setElemente() {
		addTextfieldWithSqlReference("Firmenname", "Name", 1, 1);
		addTextfieldWithSqlReference("Straﬂe", "Street", 1, 3);
		addTextfieldWithSqlReference("PLZ", "ZIP", 1, 4);
		addTextfieldWithSqlReference("Ort", "Town", 1, 5);
		addTextfieldWithSqlReference("Land", "Country", 1, 6);
		addTextfieldWithSqlReference("Tel.", "Phone_Cental", 4, 3);
		addTextAreaWithSqlReference("Bemerkung", "Notes", 4, 6);
		
		setBottomMenu(12);
	}

	

}
