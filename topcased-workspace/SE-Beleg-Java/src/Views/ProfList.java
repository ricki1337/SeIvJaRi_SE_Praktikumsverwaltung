package Views;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ListController;

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

	@Override
	public void getExtendedSearchInputFields(JPanel target) {
		target.setLayout(new GridLayout(5,2));
		target.add(new JLabel("Name:")); target.add(new JTextField(20));
		target.add(new JLabel("E-Mail:")); target.add(new JTextField(20));
		target.setVisible(true);
		
	}

	

}
