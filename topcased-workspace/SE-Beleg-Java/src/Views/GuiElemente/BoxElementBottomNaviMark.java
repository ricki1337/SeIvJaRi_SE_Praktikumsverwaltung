package Views.GuiElemente;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import Views.Interfaces.BasicBox;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.NaviMarkBoxCtrl;

public class BoxElementBottomNaviMark extends JPanel implements BasicBox , MouseListener{
	JButton jbn_mark;
	
	private NaviMarkBoxCtrl controller;
	
	public BoxElementBottomNaviMark(NaviMarkBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
	}
	
	@Override
	public void setComponentNames() {
		jbn_mark.setName("mark");
	
	}

	@Override
	public void setComponentEventHandler() {
		jbn_mark.addMouseListener(this);
	
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public void initComponents() {
		jbn_mark = new JButton("markierte ausw\u00E4hlen");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jbn_mark)
					.addContainerGap(339, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jbn_mark)
					.addContainerGap(266, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jbn_mark)
			controller.buttonMarkClicked();
		
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
