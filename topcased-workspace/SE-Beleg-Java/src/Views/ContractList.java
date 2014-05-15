package Views;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ListController;

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

	@Override
	public void getExtendedSearchInputFields(JPanel target) {
		target.setLayout(new GridLayout(5,2));
		target.add(new JLabel("Matrikelnr.:")); target.add(new JTextField(20));
		target.add(new JLabel("Vorname:")); target.add(new JTextField(20));
		target.add(new JLabel("Nachname:")); target.add(new JTextField(20));
		target.add(new JLabel("E-Mail:")); target.add(new JTextField(20));
		target.add(new JLabel("Studiengruppe:")); target.add(new JTextField(20));
		
		target.setVisible(true);
	}

	

}
