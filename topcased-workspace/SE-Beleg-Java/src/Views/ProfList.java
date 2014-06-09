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

}
