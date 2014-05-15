package Views.GuiElemente;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExtendedSearchPanel extends JPanel{

	public ExtendedSearchPanel(JPanel parent){
		super();
		setLayout(new GridLayout(5,2));
		JLabel label1 = new JLabel("Suchen nach:");
		JLabel label2 = new JLabel("Suchen nach:");
		JLabel label3 = new JLabel("Suchen nach:");
		JLabel label4 = new JLabel("Suchen nach:");
		JLabel label5 = new JLabel("Suchen nach:");

		add(label1);
		add(label2);
		add(label3);
		add(label4);
		add(label5);
		
		setVisible(true);
	}
	
	
}
