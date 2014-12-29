package Views.GuiElemente;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;

import ConfigParser.Debug;
import Views.Interfaces.BasicBox;
import Views.Interfaces.NaviProgressBoxCtrl;

/**
 * Implementiert eine BasicBox, welche eine JProgressBar enthält.
 */
public class BoxElementBottomNaviProgress extends JPanel implements BasicBox{
		private JProgressBar progressBar;
		private NaviProgressBoxCtrl controller;
	
	/**
	 * Initialisiert die Box und bringt sie zur Anzeige.
	 * @param controller	NaviProgressBoxCtrl Objekt, welches für die Datenzufuhr der Progressbar zuständig ist.
	 */
	public BoxElementBottomNaviProgress(NaviProgressBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentValues();
		setToolTip();
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public void initComponents() {
		progressBar = new JProgressBar();
		
		JLabel lblNewLabel = new JLabel("Fortschritt");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(progressBar, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}
	
	@Override
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}

	@Override
	public void setComponentValues() {
		progressBar.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
//				System.out.println("geändert....");
			}
		});
		progressBar.setMaximum(controller.getValueEqualToHundretPercent());
		progressBar.setMinimum(controller.getValueEqualToNullPercent());
		refreshContent();
	}
	
	@Override
	public void refreshContent() {
		System.out.println(controller.getCurrentValue());
		progressBar.setValue(controller.getCurrentValue());
		progressBar.repaint();
		repaint();
	}

	@Override
	public void setComponentNames() {}
	
	@Override
	public void setComponentEventHandler() {}

}
