package Views;

import Controller.*;

public class StudentList extends ListView{

	public StudentList(ListController controller) {
		super(controller);
		this.setTitle("Studenten");
	}

	@Override
	public void setElemente() {
		addListTopMenu();
		addListTableWithDataFromModel();
		addListBottomMenu();
	}

	@Override
	public void modelHasChanged() {
		deleteTable();
		addListTableWithDataFromModel();
		this.revalidate();
		pack();
	}

	

}
