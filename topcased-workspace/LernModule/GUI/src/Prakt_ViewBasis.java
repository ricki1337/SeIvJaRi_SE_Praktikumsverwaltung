import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Prakt_ViewBasis extends JInternalFrame {
	Prakt_MainWindow MainWindow;
	JPanel PanelTop;
	Container mContainer;
	Prakt_ViewBasis mViewBasis;
	
	/**
	 * Create the frame.
	 */
	public Prakt_ViewBasis(Prakt_MainWindow mWindow, String type) {
		super(type,true,true,true,true);
		this.MainWindow = mWindow;		
		mContainer = this.getContentPane();
		mViewBasis = this;
		
		//Setzen des Layouts of Y Box (Vertikale Unterteilung)
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(500,200);
		PanelTop = new JPanel();
		PanelTop.setLayout(new FlowLayout());
		PanelTop.add(new JLabel("Suchen"));
		PanelTop.add(new JTextField(20));
		PanelTop.add(new JButton("Anlegen"));
		
		PanelTop.add(new JButton("Erweiterte Suche") {{
			addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent ae) {
					mContainer.add(new Prakt_PanelSearch(mViewBasis));
		}});}});

		
		PanelTop.add(new JButton("Mail/Drucken"));
		getContentPane().add(PanelTop);
		pack();		
		setVisible(true);
	}


}
