package Views.GuiElemente;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListBottomMenuForSelection extends JPanel{
	
	public ListBottomMenuForSelection(EventListener controller){
		super();
	
		GridLayout layout = new GridLayout();
		layout.setHgap(5);
		setLayout(layout);
		
		JButton setFlagOnMarkedRows = new JButton("auswählen");
		setFlagOnMarkedRows.setName("setMarkedRow");
		setFlagOnMarkedRows.addMouseListener((MouseListener) controller);
		add(setFlagOnMarkedRows);
		
		setVisible(true);
	}
}
