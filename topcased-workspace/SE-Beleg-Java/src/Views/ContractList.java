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

	

}
