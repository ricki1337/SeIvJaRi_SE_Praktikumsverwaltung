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
import Views.Interfaces.NaviEditBoxCtrl;
import Views.Interfaces.NaviEditMailPrintBoxCtrl;

public class BoxElementBottomNaviEdit extends JPanel implements BasicBox, MouseListener {

	JButton jbn_edit;
	private NaviEditBoxCtrl controller;
	
	public BoxElementBottomNaviEdit(NaviEditBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
		setToolTip();
	}
	
	@Override
	public void setComponentNames() {
		jbn_edit.setName("edit");
	}

	@Override
	public void setComponentEventHandler() {
		jbn_edit.addMouseListener(this);
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public void initComponents() {
		jbn_edit = new JButton("markierte bearbeiten");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(307, Short.MAX_VALUE)
					.addComponent(jbn_edit)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jbn_edit)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	@Override
	public void setComponentValues() {}
	
	@Override
	public void refreshContent() {}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent() == jbn_edit)
			controller.buttonEditClicked();
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
