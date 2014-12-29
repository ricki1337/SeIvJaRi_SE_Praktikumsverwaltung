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

/**
 * Implementiert eine BasicBox mit den Button "Abbrechen" und "Speichern".
 */
public class BoxElementBottomNaviAbortSave extends JPanel implements BasicBox, MouseListener {
		private JButton jbn_save;
		private JButton jbn_abort;
		private NaviAbortSaveBoxCtrl controller;
	
	/**
	 * Initialisiert die Box.
	 * @param controller	NaviAbortSaveBoxCtrl Objekt, welche die Nutzerinteraktionenbehandelt.
	 */
	public BoxElementBottomNaviAbortSave(NaviAbortSaveBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
		setToolTip();
	}
	

	
	@Override
	public void initComponents() {
		jbn_save = new JButton("speichern");
		jbn_abort = new JButton("abbrechen");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(268, Short.MAX_VALUE)
					.addComponent(jbn_abort)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jbn_save)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbn_save)
						.addComponent(jbn_abort))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jbn_save){
			controller.buttonSaveClicked();
		}
		if(e.getComponent() == jbn_abort){
			controller.buttonAbortClicked();
		}
	}


	@Override
	public void setComponentNames() {
		jbn_save.setName("insert");
		jbn_abort.setName("close");
	}

	@Override
	public void setComponentEventHandler() {
		jbn_save.addMouseListener(this);
		jbn_abort.addMouseListener(this);
	}
	
	@Override
	public JComponent getJComponent() {
		return this;
	}

	
	@Override
	public void setComponentValues() {}
	
	@Override
	public void refreshContent() {}

	
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
