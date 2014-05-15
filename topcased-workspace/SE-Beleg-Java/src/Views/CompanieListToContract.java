package Views;

import Controller.ListController;

public class CompanieListToContract extends ListView{

	public CompanieListToContract(ListController controller) {
		super(controller);
		this.setTitle("Firmen");
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
