package Views.GuiElemente;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JPanel;

import ConfigParser.Debug;
import Views.Interfaces.BasicBox;
import Views.Interfaces.ContractDetailsStudentNewBoxCtrl;

/**
 * Implementiert eine BasicBox mit einem Button "Hinzufügen".
 */
public class BoxElementContractDetailsStudentNew extends JPanel implements BasicBox, MouseListener{
		
		private javax.swing.JButton jb_add;
	    
	    private ContractDetailsStudentNewBoxCtrl controller;
	
	/**
	 * Initialisiert die Box und bringt sie zur Anzeige.
	 * @param controller	ContractDetailsStudentNewBoxCtrl Objekt, welche auf die Nutzereingaben reagiert.
	 */
    public BoxElementContractDetailsStudentNew(ContractDetailsStudentNewBoxCtrl controller) {
    	this.controller = controller;
        initComponents();
        setComponentEventHandler();
        setToolTip();
    }

    @Override                   
    public void initComponents() {

    	jb_add = new javax.swing.JButton();

        jb_add.setText("Hinzuf\u00FCgen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jb_add, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jb_add, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
        			.addContainerGap())
        );
        this.setLayout(layout);
    }  
    
    @Override
	public void setComponentEventHandler() {
		jb_add.addMouseListener(this);
	}

    @Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jb_add)
			controller.buttonAddStudentOnContractClicked();
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
	public void refreshContent() {}

	
	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
