package Views.GuiElemente;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ConfigParser.Debug;
import Views.Interfaces.BasicBox;
import Views.Interfaces.BasicBoxCtrl;

public class BoxElementBottomNavi extends JPanel implements BasicBox{
	JPanel jpnl_right;
	JPanel jpnl_left;
	BasicBoxCtrl parent;
	BasicBox rightBox = null;
	BasicBox leftBox = null;
	
	public BoxElementBottomNavi(BasicBoxCtrl parent){
		this.parent = parent;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
		setToolTip();
	}
	
	public void initComponents(){
		jpnl_right = new JPanel();
		
		jpnl_left = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(jpnl_left, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(jpnl_right, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jpnl_left, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addComponent(jpnl_right, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		setLayout(groupLayout);
	}
	
	public void addBoxToLeftSide(BasicBox box){
		JPanel newPanel = (JPanel)box.getJComponent();
		GroupLayout layout = (GroupLayout) this.getLayout();
		layout.replace(jpnl_left, newPanel);
		leftBox = box;
	}
	
	public void addBoxToRightSide(BasicBox box){
		JPanel newPanel = (JPanel)box.getJComponent();
		GroupLayout layout = (GroupLayout) this.getLayout();
		layout.replace(jpnl_right , newPanel);
		rightBox = box;
	}	
	
	@Override
	public JComponent getJComponent() {
		return this;
	}
	
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}
	
	@Override
	public void setComponentNames() {}
	
	@Override
	public void setComponentValues() {}
	
	@Override
	public void setComponentEventHandler() {}
	
	@Override
	public void refreshContent() {
		if(leftBox != null)
			leftBox.refreshContent();
		if(rightBox != null)
			rightBox.refreshContent();
	}
	
	
}
