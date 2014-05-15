package Views;

import Controller.*;

public class ProfList extends ListView{

	public ProfList(ListController controller) {
		super(controller);
		this.setTitle("Betreuer");
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
