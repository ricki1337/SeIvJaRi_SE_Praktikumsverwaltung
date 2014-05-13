package Views.GuiElemente;



import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.EventListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TopMenu extends JPanel{
	
//	public TopMenu(InputMethodListener listener){
	public TopMenu(EventListener listener){
		super();
		final JPanel instance = this;
		setLayout(new FlowLayout());
		add(new JLabel("Suchen"));
		JTextField suchFeld = new JTextField(20);
		//suchFeld.addInputMethodListener(listener);
		suchFeld.addKeyListener((KeyListener)listener);
		add(suchFeld);
		JButton anlegen = new JButton("Anlegen");
		anlegen.addMouseListener((MouseListener)listener);
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
		add(new JButton("Mail/Drucken"));
		setVisible(true);
	}

}
