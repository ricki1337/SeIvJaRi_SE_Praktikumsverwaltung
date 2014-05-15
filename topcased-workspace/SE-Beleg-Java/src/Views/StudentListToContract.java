package Views;

import Controller.ListController;

public class StudentListToContract extends ListView{

	public StudentListToContract(ListController controller) {
		super(controller);
		this.setTitle("Studenten");
	}

	@Override
	public void setElemente() {
		addListTopMenu();
		addListBottomMenuForSelection();
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
