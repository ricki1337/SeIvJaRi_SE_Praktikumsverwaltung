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
import Views.Interfaces.CompanyDetailsContactCtrl;


public class BoxElementCompanyContactDetailsNav extends JPanel implements BasicBox, MouseListener{
	 	private JButton jb_bearbeiten;
	    private JButton jb_next;
	    private JButton jb_prev;
	    
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jb_prev)
        			.addGap(22)
        			.addComponent(jb_bearbeiten, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        			.addGap(18)
        			.addComponent(jb_next)
        			.addGap(8))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jb_next)
        				.addComponent(jb_bearbeiten, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jb_prev))
        			.addContainerGap(54, Short.MAX_VALUE))
        );
        this.setLayout(layout);
        layout.preferredLayoutSize(this);
        setVisible(true);
    }

	@Override
	public void setComponentEventHandler() {
		jb_next.addMouseListener(this);
		jb_bearbeiten.addMouseListener(this);
		jb_prev.addMouseListener(this);
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
