package Views.GuiElemente;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import ConfigParser.Debug;
import Views.Interfaces.BasicBox;
import Views.Interfaces.NaviMailPrintBoxCtrl;

public class BoxElementBottomNaviMailPrint extends JPanel implements BasicBox, MouseListener {
	JButton jbn_mail;
	JButton jbn_print;
	private NaviMailPrintBoxCtrl controller;
	
	public BoxElementBottomNaviMailPrint(NaviMailPrintBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
		setToolTip();
	}
	
	@Override
	public void setComponentNames() {
		jbn_mail.setName("mailTo");
		jbn_print.setName("print");
	}

	@Override
	public void setComponentEventHandler() {
		jbn_mail.addMouseListener(this);
		jbn_print.addMouseListener(this);
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public void initComponents() {
		jbn_print = new JButton("drucken");
		
		jbn_mail = new JButton("mailen");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(369, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jbn_mail, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(jbn_print))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jbn_mail)
					.addGap(6)
					.addComponent(jbn_print)
					.addContainerGap(237, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jbn_mail)
			controller.buttonMailToClicked();
		
		if(e.getComponent() == jbn_print)
			controller.buttonPrintClicked();
	}
	
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}

	@Override
	public void setComponentValues() {}
	
	@Override
	public void refreshContent() {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
