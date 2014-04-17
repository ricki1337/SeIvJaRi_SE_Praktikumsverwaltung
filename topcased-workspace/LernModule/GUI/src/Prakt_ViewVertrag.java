import javax.swing.*;
import java.awt.*;

public class Prakt_ViewVertrag extends JInternalFrame{
	JPanel PanelTop;
	public Prakt_ViewVertrag(){
		super("Verträge",true,true,true,true);
		setSize(500,500);
		setLayout(new BorderLayout());
		
		
		PanelTop = new JPanel();
		PanelTop.setLayout(new FlowLayout());
		PanelTop.add(new JLabel("Suchen"));
		PanelTop.add(new JTextField(20));
		PanelTop.add(new JButton("Anlegen"));
		PanelTop.add(new JButton("Bearbeiten"));
		PanelTop.add(new JButton("Mail senden"));
		add(PanelTop,BorderLayout.NORTH);
		add(new JEditorPane(),BorderLayout.CENTER);
		pack();		
		setVisible(true);
	}
}
