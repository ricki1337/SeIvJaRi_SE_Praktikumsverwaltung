import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Prakt_Startseite extends JInternalFrame{
	
	public Prakt_Startseite(final Prakt_MainWindow MainWindow){
		super("Start",true,true,true,true);
		GridBagLayout g = new GridBagLayout();
		JPanel p = new JPanel();
		p.setLayout(g);
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		
		JButton button = new JButton("Studenten");
		
		c.weightx = 0.1;
		c.weighty = 0.1;
		g.setConstraints(button, c);
		p.add(button);
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainWindow.addInternalFrame(new Prakt_ViewStudent());
				
			}});
		c.gridwidth = GridBagConstraints.REMAINDER;
		button = new JButton("Firmen");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainWindow.addInternalFrame(new Prakt_ViewFirmen());
				
			}});
		g.setConstraints(button, c);
		p.add(button);
		
		c.gridwidth = GridBagConstraints.BOTH;
		button = new JButton("Betreuer");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainWindow.addInternalFrame(new Prakt_ViewBetreuer());
				
			}});
		button.setSize(100, 100);
		g.setConstraints(button, c);
		p.add(button);
		
		button = new JButton("Verträge");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MainWindow.addInternalFrame(new Prakt_ViewVertrag());
				
			}});
		g.setConstraints(button, c);
		p.add(button);
		add(p);
		//pack();
		setSize(400,400);
		setVisible(true);
	}

}
