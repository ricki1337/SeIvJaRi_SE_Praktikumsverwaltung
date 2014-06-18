package Views.GuiElemente;

import java.awt.Color;
import java.awt.SystemColor;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import ConfigParser.Debug;
import Models.Datenbank.SqlTableContacts;
import Views.Interfaces.BasicBox;
import Views.Interfaces.CompanyContactDetailsBoxCtrl;



public class BoxElementCompanyContactDetails extends JPanel implements BasicBox{
	
	    private JLabel jl_ansprechpartner;
	    private JLabel jl_ansprechpartner_value;
	    private JLabel jl_bemerkung;
	    private JLabel jl_telefon;
	    private JLabel jl_telefon_value;
	    private JTextArea jtf_bemerkung_value;
	    private JPanel jpnl_navi;
	    private GroupLayout groupLayout;
	    
	    private CompanyContactDetailsBoxCtrl controller;
	    
    public BoxElementCompanyContactDetails(CompanyContactDetailsBoxCtrl controller) {
    	this.controller = controller;
        initComponents();
        setComponentNames();
        setComponentValues();
        setToolTip();
    }
    
    @Override
    public void initComponents() {

    	jpnl_navi = new JPanel();
        
        jl_ansprechpartner = new javax.swing.JLabel();
        
		jl_ansprechpartner.setText("Ansprechpartner:");
		jl_ansprechpartner_value = new javax.swing.JLabel();

        jl_ansprechpartner_value.setText("leer");
        jl_telefon = new javax.swing.JLabel();

        jl_telefon.setText("Telefonummer:");
        jl_telefon_value = new javax.swing.JLabel();

        jl_telefon_value.setText("leer");
        jl_bemerkung = new javax.swing.JLabel();

        jl_bemerkung.setText("Bemerkung:");
        jtf_bemerkung_value = new JTextArea();
        jtf_bemerkung_value.setColumns(20);
        jtf_bemerkung_value.setRows(4);
        jtf_bemerkung_value.setBackground(Color.white);
        jtf_bemerkung_value.setDisabledTextColor(Color.DARK_GRAY);
        jtf_bemerkung_value.setEnabled(false);
        jtf_bemerkung_value.setText("leer");
        groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(jl_ansprechpartner)
        			.addGap(10)
        			.addComponent(jl_ansprechpartner_value, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(jl_telefon)
        			.addGap(23)
        			.addComponent(jl_telefon_value, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addComponent(jl_bemerkung)
        			.addGap(38)
        			.addComponent(jtf_bemerkung_value, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE))
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jpnl_navi, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(11)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jl_ansprechpartner)
        				.addComponent(jl_ansprechpartner_value))
        			.addGap(11)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jl_telefon)
        				.addComponent(jl_telefon_value))
        			.addGap(11)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jl_bemerkung)
        				.addComponent(jtf_bemerkung_value, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jpnl_navi, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
        			.addGap(12))
        );
        setLayout(groupLayout);
        setVisible(true);
    }

	@Override
	public void setComponentNames() {
		jl_ansprechpartner_value.setName(SqlTableContacts.Name);
		jl_telefon_value.setName(SqlTableContacts.Telefonnummer);
	    jtf_bemerkung_value.setName(SqlTableContacts.Bemerkung);	
	}

	@Override
	public void setComponentValues() {
		jl_ansprechpartner_value.setText(controller.getStringValueForContactBoxElement(SqlTableContacts.Name));
		jl_telefon_value.setText(controller.getStringValueForContactBoxElement(SqlTableContacts.Telefonnummer));
	    jtf_bemerkung_value.setText(controller.getStringValueForContactBoxElement(SqlTableContacts.Bemerkung));
	}

	@Override
	public void setComponentEventHandler() {}

	@Override
	public void refreshContent() {
		setComponentValues();
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}
	
	public void setContactNaviBox(BoxElementCompanyContactDetailsNav naviBox){
		JPanel newPanel = (JPanel)naviBox.getJComponent();
		groupLayout.replace(jpnl_navi, newPanel);
		jpnl_navi = newPanel;
	}
	
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}
}
