package Views;

import Controller.*;

public class StudentList extends ListView{

	public StudentList(ListController controller) {
		super(controller);
	}

	@Override
	public void setElemente() {
		setTopMenu();
		setTable();
	}

	

}
