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
import Views.Interfaces.NaviAbortSelectBoxCtrl;

/**
 * Implementiert eine BasicBox mit den Button "abbrechen" und "auswählen".
 *
 */
public class BoxElementBottomNaviAbortSelect extends JPanel implements BasicBox, MouseListener {
		private JButton jbn_select;
		private JButton jbn_abort;
		private NaviAbortSelectBoxCtrl controller;
	
	/**
	 * Initialisiert die Box und bringt sie zur Anzeige.
	 * @param controller	NaviAbortSelectBoxCtrl Objekt, welches die Nutzereingaben verarbeitet.
	 */
	public BoxElementBottomNaviAbortSelect(NaviAbortSelectBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
		setToolTip();
	}
	
	@Override
	public void setComponentNames() {
		jbn_select.setName("select");
		jbn_abort.setName("close");
	}

	@Override
	public void setComponentEventHandler() {
		jbn_select.addMouseListener(this);
		jbn_abort.addMouseListener(this);
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public void initComponents() {
		jbn_select = new JButton("ausw\u00E4hlen");
				
		jbn_abort = new JButton("abbrechen");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(268, Short.MAX_VALUE)
					.addComponent(jbn_abort)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jbn_select)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbn_select)
						.addComponent(jbn_abort))
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
		if(e.getComponent() == jbn_select){
			controller.buttonSelectClicked();
		}
		if(e.getComponent() == jbn_abort){
			controller.buttonAbortClicked();
		}
	}
	@Override
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
