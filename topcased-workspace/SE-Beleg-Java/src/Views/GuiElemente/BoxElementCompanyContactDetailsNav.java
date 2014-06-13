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
import Views.Interfaces.CompanyDetailsContactCtrl;


public class BoxElementCompanyContactDetailsNav extends JPanel implements BasicBox, MouseListener{
	 	private JButton jb_bearbeiten;
	    private JButton jb_next;
	    private JButton jb_prev;
	    private JButton jb_add;
	    
	    private CompanyDetailsContactCtrl controller;
	    
    public BoxElementCompanyContactDetailsNav(CompanyDetailsContactCtrl controller) {
    	this.controller = controller;
        initComponents();
        setComponentEventHandler();
        setToolTip();
    }

    @Override                   
    public void initComponents() {
    	jb_bearbeiten = new javax.swing.JButton();
        jb_prev = new javax.swing.JButton();
        jb_next = new javax.swing.JButton();

        jb_bearbeiten.setText("Bearbeiten");

        jb_prev.setText("<<");

        jb_next.setText(">>");
        
        jb_add = new JButton("Hinzufügen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jb_prev)
        			.addGap(22)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jb_bearbeiten, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        				.addComponent(jb_add, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addGap(18)
        			.addComponent(jb_next)
        			.addGap(4))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        					.addComponent(jb_next, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
        					.addComponent(jb_prev, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jb_bearbeiten, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jb_add)))
        			.addContainerGap(25, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }

	@Override
	public void setComponentEventHandler() {
		jb_next.addMouseListener(this);
		jb_bearbeiten.addMouseListener(this);
		jb_prev.addMouseListener(this);
		jb_add.addMouseListener(this);
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == jb_next)
			controller.buttonCompanyContactNextClicked();
		
		if(e.getComponent() == jb_bearbeiten)
			controller.buttonCompanyContactEditClicked();
		
		if(e.getComponent() == jb_prev)
			controller.buttonCompanyContactPreviusClicked();
		
		if(e.getComponent() == jb_add)
			controller.buttonCompanyContactAddNewClicked();
		
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
