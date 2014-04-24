package Views.Elemente;



import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

public class ListTopMenu extends JPanel{
	

	public ListTopMenu(EventListener controller){
		super();
//		final JPanel instance = this;
		setLayout(new FlowLayout());
		add(new JLabel("Suchen"));
		
		JTextField suchFeld = new JTextField(20);
		suchFeld.setName("suchFeld");
		suchFeld.getDocument().addDocumentListener((DocumentListener)controller);
		add(suchFeld);
		
		JButton anlegen = new JButton("Anlegen");
		anlegen.setName("anlegen");
		anlegen.addMouseListener((MouseListener)controller);
		add(anlegen);
		
		JButton erweiterteSuche = new JButton("Erweiterte Suche");
		//funktioniert noch nicht...
		/*
		erweiterteSuche.addActionListener(
				new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent ae) {
							System.out.println("hall");
							add(new ExtendedSearchPanel(instance));
						}
				});
		*/
		add(erweiterteSuche);

		setVisible(true);
	}

}
