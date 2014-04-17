import javax.swing.*;
import java.awt.*;

public class Prakt_ViewBetreuer extends JInternalFrame{
	JPanel PanelTop;
	public Prakt_ViewBetreuer(){
		super("Betreuer",true,true,true,true);
		setSize(500,500);
		setLayout(new BorderLayout());
		
		
		PanelTop = new JPanel();
		PanelTop.setLayout(new FlowLayout());
		PanelTop.add(new JLabel("Suchen"));
		PanelTop.add(new JTextField(20));
		PanelTop.add(new JButton("Anlegen"));
		PanelTop.add(new JButton("Bearbeiten"));
		add(PanelTop,BorderLayout.NORTH);
		add(new JEditorPane(),BorderLayout.CENTER);
		pack();		
		setVisible(true);
	}
}
