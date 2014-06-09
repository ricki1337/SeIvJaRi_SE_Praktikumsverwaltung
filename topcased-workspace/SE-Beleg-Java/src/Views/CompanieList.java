package Views;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.ListController;

public class CompanieList extends ListView{

	public CompanieList(ListController controller) {
		super(controller);
		this.setTitle("Firmen");
	}

}
