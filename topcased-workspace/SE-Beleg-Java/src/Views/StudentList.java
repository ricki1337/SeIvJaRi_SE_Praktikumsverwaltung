package Views;

import Controller.ListController;

public class StudentList extends ListView{

	public StudentList(ListController controller) {
		super(controller);
		this.setTitle("Studenten");
	}

	@Override
	public void setElemente() {
		addListTopMenu();
		addListBottomMenu();
		addListTableWithDataFromModel();
		
	}

	@Override
	public void modelHasChanged() {
		refreshListTableWithDataFromModel();
	}

	@Override
	public Object[][] getEingabeWerte() {
		return null;
	}

	@Override
	public String getValueFromCurrentItem(String sqlColumnName) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
