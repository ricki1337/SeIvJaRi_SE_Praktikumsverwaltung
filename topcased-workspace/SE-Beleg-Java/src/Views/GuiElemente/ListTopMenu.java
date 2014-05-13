package Views.GuiElemente;



import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import Views.ListView;

public class ListTopMenu extends JPanel{
	

	public ListTopMenu(EventListener controller, final ListView view){
		super();
		final JPanel instance = this;
		GridLayout layout = new GridLayout(2,2);
		layout.setHgap(5);
		setLayout(layout);
		setName("topmenu");
		
		
		JPanel oben = new JPanel();
		oben.setLayout(new FlowLayout());
		
		final JPanel unten = new JPanel();
		unten.setLayout(new GridLayout(1,1));
		unten.setName("unten");
		
		
		JPanel suche = new JPanel(new FlowLayout());
		suche.add(new JLabel("Suchen"));
		
		JTextField suchFeld = new JTextField(20);
		suchFeld.setName("suchFeld");
		suchFeld.getDocument().addDocumentListener((DocumentListener)controller);
		suche.add(suchFeld);
		
		oben.add(suche);
		
		JButton anlegen = new JButton("Anlegen");
		anlegen.setName("anlegen");
		anlegen.addMouseListener((MouseListener)controller);
		

		oben.add(anlegen);
		
		
		ExtendedSearchPanel searchPanel = new ExtendedSearchPanel(this);
		searchPanel.setName("searchPanel");

		searchPanel.setVisible(true);
		unten.add(searchPanel);
		
		JButton erweiterteSuche = new JButton("Erweiterte Suche");

		erweiterteSuche.addActionListener(
				new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent ae) {
							
							unten.setVisible(unten.isVisible()?false:true);
							instance.setSize(620,unten.isVisible()?160:50);
							instance.repaint();
							view.refreshViewComponents();
							
							System.out.println(instance.getSize());
						}
				});
		

		oben.add(erweiterteSuche);
		
		oben.add(new JLabel("Anz. Datensätze: "));
		
		JTextField anzDatensaetze = new JTextField("20");
		anzDatensaetze.setName("anzDatensaetze");
		anzDatensaetze.setColumns(3);
		anzDatensaetze.addActionListener((ActionListener)controller);
		oben.add(anzDatensaetze);
		
		
		oben.setVisible(true);
		unten.setVisible(true);
		add(oben);
		add(unten);
		setVisible(true);
	}

}
