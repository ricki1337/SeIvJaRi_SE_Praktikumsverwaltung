package Views.GuiElemente;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ConfigParser.Debug;
import Views.Interfaces.BasicBox;
import Views.Interfaces.NaviPrevSaveNextBoxCtrl;

/**
 * Implementiert eine BasicBox mit den Buttons "<<", "speichern" und ">>".
 */
public class BoxElementBottomNaviPrevSaveNext extends JPanel implements BasicBox, MouseListener{
		
		private JButton jbn_previus;
		private JButton jbn_save;
		private JButton jbn_next;
		private  JLabel jl_count;
		private NaviPrevSaveNextBoxCtrl controller;
	
	/**
	 * Initialisiert die Box und bringt sie zur Anzeige.
	 * @param controller	NaviPrevSaveNextBoxCtrl Objekt, welches die Nutzereingaben verarbeitet.
	 */
	public BoxElementBottomNaviPrevSaveNext(NaviPrevSaveNextBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentValues();
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
	
	@Override
	public void initComponents(){
		jbn_previus = new JButton("<<");
		
		jbn_save = new JButton("speichern");
		
		jbn_next = new JButton(">>");
		JLabel lblDatensatz = new JLabel("Datensatz:");
		
		jl_count = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(jbn_previus)
							.addGap(6)
							.addComponent(jbn_save)
							.addGap(6)
							.addComponent(jbn_next))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDatensatz)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jl_count, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(166, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDatensatz)
						.addComponent(jl_count, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jbn_previus)
						.addComponent(jbn_save)
						.addComponent(jbn_next))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	@Override
	public JComponent getJComponent() {
		return this;
	}
	
	@Override
	public void refreshContent() {
		setComponentValues();
	}

	@Override
	public void setComponentValues() {
		jl_count.setText(controller.getCurrentPos() + " von " + controller.getPosSum());
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jbn_previus)
			controller.buttonPreviusClicked();
		
		if(e.getComponent() == jbn_save)
			controller.buttonSaveChangesClicked();
		
		if(e.getComponent() == jbn_next)
			controller.buttonNextClicked();
		
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
