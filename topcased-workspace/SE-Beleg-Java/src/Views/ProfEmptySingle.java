package Views;

import Controller.Controller;

public class ProfEmptySingle extends EmptySingleView{

	public ProfEmptySingle(Controller controller) {
		super(controller);
		this.setTitle("Betreuerdetails");
	}

	@Override
	public void setElemente() {
		addTextfieldWithSqlReference("Name", "Name", 1, 1);
		addTextfieldWithSqlReference("E-Mail", "NameID", 1, 2);
		setBottomMenu(6);
	}

	

}
