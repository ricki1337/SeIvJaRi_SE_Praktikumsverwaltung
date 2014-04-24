package Views.Elemente;

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
		
		setLayout(layout);
		
		JButton setFlagOnMarkedRows = new JButton("markierte auswählen");
		setFlagOnMarkedRows.setName("setFlagOnMarkedRows");
		setFlagOnMarkedRows.addMouseListener((MouseListener) controller);
		add(setFlagOnMarkedRows);
		
		add(new JLabel(""));
		
		add(new JLabel(""));

		
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
