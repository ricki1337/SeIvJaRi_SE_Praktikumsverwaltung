package Views.GuiElemente;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ConfigParser.Debug;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableProfs;
import Views.Interfaces.ContractDetailsProfBox;
import Views.Interfaces.ContractDetailsProfBoxCtrl;

/**
 * Implementiert die Anzeige der Betreuerinformationen eines Vertrags und ermöglicht die Bearbeitung der Betreuerinformationen.
 */
public class BoxElementContractDetailsProf extends JPanel implements ContractDetailsProfBox, MouseListener{

		private JLabel jl_ansprechpartner;
	    private JLabel jl_ansprechpartner_value;
	    private JLabel jl_email;
	    private JLabel jl_email_value;
	    private JButton btn_change;
	    private JButton btn_edit;
		
	    private ContractDetailsProfBoxCtrl controller;
	
    /**
	 * Initialisiert die Box und bringt sie zur Anzeige.
	 * @param controller	ContractDetailsProfBoxCtrl Objekt, welche Informationen für die Box bereitstellt und auf Nutzeraktionen reagiert.
	 */
    public BoxElementContractDetailsProf(ContractDetailsProfBoxCtrl controller) {
    	this.controller = controller;
        initComponents();
        setComponentNames();
        setComponentValues();
        setComponentEventHandler();
        setToolTip();
    }

    @Override                   
    public void initComponents() {

    	jl_ansprechpartner = new javax.swing.JLabel();
        jl_email = new javax.swing.JLabel();
        jl_ansprechpartner_value = new javax.swing.JLabel();
        jl_email_value = new javax.swing.JLabel();

        jl_ansprechpartner.setText("Name:");

        jl_email.setText("Email:");

        jl_ansprechpartner_value.setText("leer");

        jl_email_value.setText("leer");
        
        btn_change = new JButton("\u00E4ndern");
        
        btn_edit = new JButton("bearbeiten");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jl_ansprechpartner)
        				.addComponent(jl_email))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(jl_email_value, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(jl_ansprechpartner_value, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        				.addComponent(btn_edit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addComponent(btn_change, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        			.addContainerGap(164, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_ansprechpartner)
        				.addComponent(jl_ansprechpartner_value)
        				.addComponent(btn_change))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_email)
        				.addComponent(jl_email_value)
        				.addComponent(btn_edit)))
        );
        this.setLayout(layout);
    }


	@Override
	public void setComponentNames() {
		jl_ansprechpartner_value.setName(SqlTableProfs.Name);
        jl_email_value.setName(SqlTableProfs.Id);
	}


	@Override
	public void setComponentValues() {
		jl_ansprechpartner_value.setText(controller.getStringValueForBoxElementEdit("BetreuerName"));
        jl_email_value.setText(controller.getStringValueForBoxElementEdit("Betreuer"));
	}


	@Override
	public void setComponentEventHandler() {
		btn_change.addMouseListener(this);
		btn_edit.addMouseListener(this);
	}


	@Override
	public void refreshContent() {
		setComponentValues();
	}


	@Override
	public JComponent getJComponent() {
		return this;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getComponent() == btn_change)
			controller.buttonChangeProfOnContractDetailsClicked();
		
		if(e.getComponent() == btn_edit)
			controller.buttonEditProfOnContractDetailsClicked();
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {}


	@Override
	public void mouseExited(MouseEvent arg0) {}


	@Override
	public void mousePressed(MouseEvent arg0) {}


	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
	public Map<String, Object> getInputValues() {
		Map<String, Object> inputValues = new HashMap<String, Object>();
		inputValues.put(SqlTableContracts.FK_Betreuer, controller.getStringValueForBoxElementEdit("Betreuer"));
		return inputValues;
	}             
	
	@Override
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}
}
