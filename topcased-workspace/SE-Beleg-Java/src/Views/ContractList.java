package Views;

import Controller.*;

public class ContractList extends ListView{

	public ContractList(ListController controller) {
		super(controller);
		this.setTitle("Verträge");
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
