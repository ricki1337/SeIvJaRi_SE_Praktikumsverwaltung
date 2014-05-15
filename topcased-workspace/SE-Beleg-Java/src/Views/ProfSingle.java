package Views;

import Controller.Controller;

public class ProfSingle extends SingleView{

	public ProfSingle(Controller controller) {
		super(controller);
		this.setTitle("Betreuerdetails");
	}

	@Override
	public void setElemente() {
		addTextfieldWithSqlReference("Name", "Name", "Name", 1, 1);
		addTextfieldWithSqlReference("E-Mail", "NameID", "NameID", 1, 2);
		
		addBottomMenu(6);
	}

}
