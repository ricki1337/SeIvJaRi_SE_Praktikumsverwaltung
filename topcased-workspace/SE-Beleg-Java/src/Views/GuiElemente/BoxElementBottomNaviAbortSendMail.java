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
import javax.swing.LayoutStyle.ComponentPlacement;

import ConfigParser.Debug;
import Views.Interfaces.BasicBox;
import Views.Interfaces.NaviAbortSaveBoxCtrl;
import Views.Interfaces.NaviAbortSendMailBoxCtrl;

public class BoxElementBottomNaviAbortSendMail extends JPanel implements BasicBox, MouseListener {
	private JButton jb_sendmail;
	private JButton jb_abbrechen;
	private NaviAbortSendMailBoxCtrl controller;
	
	public BoxElementBottomNaviAbortSendMail(NaviAbortSendMailBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
		setToolTip();
	}
	
	@Override
	public void setComponentNames() {
		jb_sendmail.setName("sendmail");
		jb_abbrechen.setName("close");
	}

	@Override
	public void setComponentEventHandler() {
		jb_sendmail.addMouseListener(this);
		jb_abbrechen.addMouseListener(this);
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public void initComponents() {
		jb_sendmail = new JButton("senden");
		
		jb_abbrechen = new JButton("abbrechen");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(313, Short.MAX_VALUE)
					.addComponent(jb_abbrechen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jb_sendmail))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jb_sendmail)
						.addComponent(jb_abbrechen)))
		);
		setLayout(groupLayout);
		
	}
	
	@Override
	public void setComponentValues() {}
	
	@Override
	public void refreshContent() {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jb_sendmail){
			controller.buttonSendMailClicked();
		}
		if(e.getComponent() == jb_abbrechen){
			controller.buttonAbortClicked();
		}
	}
	
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}
