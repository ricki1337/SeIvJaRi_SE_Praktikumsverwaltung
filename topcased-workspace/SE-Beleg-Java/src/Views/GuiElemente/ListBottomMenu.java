package Views.GuiElemente;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListBottomMenu extends JPanel{
	
	public ListBottomMenu(EventListener controller){
		super();
	
		GridLayout layout = new GridLayout();
		layout.setHgap(5);
		setLayout(layout);
		
		JButton setFlagOnMarkedRows = new JButton("markierte auswählen");
		setFlagOnMarkedRows.setName("setFlagOnMarkedRows");
		setFlagOnMarkedRows.addMouseListener((MouseListener) controller);
		add(setFlagOnMarkedRows);
		
		
		
		
		JButton modifySelectedRows = new JButton("markierte bearbeiten");
		modifySelectedRows.setName("modifySelectedRows");
		modifySelectedRows.addMouseListener((MouseListener) controller);
		add(modifySelectedRows);
		
		

		
		JButton print = new JButton("Drucken"); 
		print.setName("print");
		print.addMouseListener((MouseListener) controller);
		add(print);

		JButton mailTo = new JButton("Mail");
		mailTo.setName("mailTo");
		mailTo.addMouseListener((MouseListener) controller);
		add(mailTo);
		
		setVisible(true);
	}
}
