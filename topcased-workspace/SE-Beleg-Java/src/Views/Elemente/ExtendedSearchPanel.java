package Views.Elemente;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExtendedSearchPanel extends JPanel{

	public ExtendedSearchPanel(JPanel parent){
		super();
		JLabel label = new JLabel("Suchen nach:");
		parent.add(label);
		parent.setSize(parent.getWidth(),parent.getHeight()+100);
		
		setVisible(true);
	}
	
	
}
