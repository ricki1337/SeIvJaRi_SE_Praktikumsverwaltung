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
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.NaviPrevSaveNextBoxCtrl;

public class BoxElementBottomNaviPrevSaveNext extends JPanel implements BasicBox, MouseListener{
		
		private JButton jbn_previus;
		private JButton jbn_save;
		private JButton jbn_next;
		
		private NaviPrevSaveNextBoxCtrl controller;
	
	public BoxElementBottomNaviPrevSaveNext(NaviPrevSaveNextBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
		setToolTip();
	}
	
	
	@Override
	public void setComponentNames() {
		jbn_previus.setName("previus");
		jbn_save.setName("save");
		jbn_next.setName("next");
		
	}
	
	@Override
	public void setComponentEventHandler() {
		jbn_previus.addMouseListener(this);
		jbn_save.addMouseListener(this);
		jbn_next.addMouseListener(this);		
	}
	
	public void initComponents(){
		jbn_previus = new JButton("<< vorheriger");
		
		jbn_save = new JButton("speichern");
		
		jbn_next = new JButton("n\u00E4chster >>");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jbn_previus)
					.addGap(6)
					.addComponent(jbn_save)
					.addGap(6)
					.addComponent(jbn_next)
					.addContainerGap(155, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jbn_previus)
						.addComponent(jbn_save)
						.addComponent(jbn_next))
					.addContainerGap(266, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		setVisible(true);
	}
	
	@Override
	public JComponent getJComponent() {
		return this;
	}
	
	@Override
	public void refreshContent() {}

	@Override
	public void setComponentValues() {}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jbn_previus)
			controller.buttonPreviusClicked();
		
		if(e.getComponent() == jbn_save)
			controller.buttonSaveChangesClicked();
		
		if(e.getComponent() == jbn_next)
			controller.buttonNextClicked();
		
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
