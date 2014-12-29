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
import Models.Datenbank.SqlTableStudent;
import Views.Interfaces.ContractDetailsStudentBox;
import Views.Interfaces.ContractDetailsStudentBoxCtrl;

/**
 * Implementiert die Anzeige der Studenteninformationen eines Vertrags und ermöglicht die Bearbeitung der Studenteninformationen.
 */
public class BoxElementContractDetailsStudent extends JPanel implements ContractDetailsStudentBox, MouseListener{
		private JButton jb_aendern;
	    private JButton jb_bearbeiten;
	    private JLabel jl_email;
	    private JLabel jl_email_value;
	    private JLabel jl_matrikelnr;
	    private JLabel jl_matrikelnr_value;
	    private JLabel jl_name;
	    private JLabel jl_name_value;
	    private JLabel jl_vorname;
	    private JLabel jl_vorname_value;
	    private JLabel lblSt;
	    private JLabel jl_stdgrp_value;
	    
	    private ContractDetailsStudentBoxCtrl controller;
	    
    /**
	 * Initialisiert die Box und bringt sie zur Anzeige.
	 * @param controller	ContractDetailsStudentBoxCtrl Objekt, welche Informationen für die Box bereitstellt und auf Nutzeraktionen reagiert.
	 */
    public BoxElementContractDetailsStudent(ContractDetailsStudentBoxCtrl controller) {
    	this.controller = controller;
        initComponents();
        setComponentNames();
        setComponentValues();
        setComponentEventHandler();
        setToolTip();
    }

    @Override                   
    public void initComponents() {

    	jl_vorname = new javax.swing.JLabel();
        jl_name = new javax.swing.JLabel();
        jl_email = new javax.swing.JLabel();
        jl_vorname_value = new javax.swing.JLabel();
        jl_name_value = new javax.swing.JLabel();
        jl_email_value = new javax.swing.JLabel();
        jl_matrikelnr = new javax.swing.JLabel();
        jl_matrikelnr_value = new javax.swing.JLabel();
        jb_aendern = new javax.swing.JButton();
        jb_bearbeiten = new javax.swing.JButton();

        jl_vorname.setText("Vorname:");

        jl_name.setText("Name:");

        jl_email.setText("Email:");

        jl_vorname_value.setText("leer");

        jl_name_value.setText("leer");

        jl_email_value.setText("leer");

        jl_matrikelnr.setText("Matrikelnr:");

        jl_matrikelnr_value.setText("leer");

        jb_aendern.setText("Ändern");

        jb_bearbeiten.setText("Bearbeiten");
        
        lblSt = new JLabel();
        lblSt.setText("Studiengruppe:");
        
        jl_stdgrp_value = new JLabel();
        jl_stdgrp_value.setText("leer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        						.addComponent(lblSt, 0, 0, Short.MAX_VALUE)
        						.addComponent(jl_vorname, Alignment.LEADING)
        						.addComponent(jl_name, Alignment.LEADING)
        						.addComponent(jl_email, Alignment.LEADING)
        						.addComponent(jl_matrikelnr, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jl_vorname_value, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        								.addComponent(jl_name_value, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        								.addComponent(jl_matrikelnr_value, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        								.addComponent(jl_email_value, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
        						.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        							.addGap(10)
        							.addComponent(jl_stdgrp_value, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jb_aendern)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jb_bearbeiten)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_vorname)
        				.addComponent(jl_vorname_value))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_name)
        				.addComponent(jl_name_value))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_matrikelnr_value)
        				.addComponent(jl_matrikelnr))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_email)
        				.addComponent(jl_email_value))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblSt)
        				.addComponent(jl_stdgrp_value))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jb_aendern)
        				.addComponent(jb_bearbeiten))
        			.addContainerGap(20, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }                    

	@Override
	public void setComponentNames() {
		jl_vorname_value.setName(SqlTableStudent.Vorname);
        jl_name_value.setName(SqlTableStudent.Nachname);
        jl_email_value.setName(SqlTableStudent.EMail);
        jl_matrikelnr_value.setName(SqlTableStudent.MatrikelNummer);
        jl_stdgrp_value.setName(SqlTableStudent.Studiengruppe);
	}

	@Override
	public void setComponentValues() {
		jl_vorname_value.setText(controller.getStringValueForBoxElementEdit(SqlTableStudent.Vorname));
        jl_name_value.setText(controller.getStringValueForBoxElementEdit(SqlTableStudent.Nachname));
        jl_email_value.setText(controller.getStringValueForBoxElementEdit(SqlTableStudent.EMail));
        jl_matrikelnr_value.setText(controller.getStringValueForBoxElementEdit(SqlTableStudent.MatrikelNummer));
        jl_stdgrp_value.setText(controller.getStringValueForBoxElementEdit(SqlTableStudent.Studiengruppe));
	}

	@Override
	public void setComponentEventHandler() {
		jb_aendern.addMouseListener(this);
        jb_bearbeiten.addMouseListener(this);
		
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
		if(e.getComponent() == jb_aendern)
			controller.buttonChangeStudentOnContractClicked();
		
		if(e.getComponent() == jb_bearbeiten)
			controller.buttonEditStudentOnContractClicked();
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
		inputValues.put(SqlTableContracts.FK_Student, controller.getStringValueForBoxElementEdit("Matrikelnr."));
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
