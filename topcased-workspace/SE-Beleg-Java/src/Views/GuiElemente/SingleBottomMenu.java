package Views.GuiElemente;

import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SingleBottomMenu  extends JPanel{

	public SingleBottomMenu(EventListener controller){
		super();
		
		GridLayout layout = new GridLayout();
		layout.setHgap(5);
		setLayout(layout);
		
		JButton previus = new JButton("<< vorheriger");
		previus.setName("previus");
		previus.addMouseListener((MouseListener) controller);
		add(previus);
		
		
		
		
		JButton save = new JButton("speichern");
		save.setName("save");
		save.addMouseListener((MouseListener) controller);
		add(save);
		
		
		JButton next = new JButton("nächster >>");
		next.setName("next");
		next.addMouseListener((MouseListener) controller);
		add(next);
		
		

		
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
