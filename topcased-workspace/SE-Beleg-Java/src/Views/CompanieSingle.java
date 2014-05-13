package Views;

import Controller.Controller;

public class CompanieSingle extends SingleView{

	public CompanieSingle(Controller controller) {
		super(controller);
		this.setTitle("Firmendetails");
	}

	@Override
	public void setElemente() {
		
		addTextfieldWithSqlReference("Firmenname", "Name", "Name", 1, 1);
		addTextfieldWithSqlReference("Straﬂe", "Street", "Street", 1, 3);
		addTextfieldWithSqlReference("PLZ", "ZIP", "ZIP", 1, 4);
		addTextfieldWithSqlReference("Ort", "Town", "Town", 1, 5);
		addTextfieldWithSqlReference("Land", "Country", "Country", 1, 6);
		addTextfieldWithSqlReference("Tel.", "Phone_Cental", "Phone_Cental", 4, 3);
		addTextAreaWithSqlReference("Bemerkung", "Notes", "Notes", 4, 6);
		
		addBottomMenu(12);
	}

}
