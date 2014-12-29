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
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Views.Interfaces.ContractDetailsCompanyBox;
import Views.Interfaces.ContractDetailsCompanyBoxCtrl;

/**
 * Implementiert die Anzeige der Firmeninformationen eines Vertrags und ermöglicht die Bearbeitung der Firmeninformationen.
 */
public class BoxElementContractDetailsCompany extends JPanel implements ContractDetailsCompanyBox, MouseListener{
	 	private JButton jb_aendern;
	    private JButton jb_bearbeiten;
	    private JLabel jl_firmenname;
	    private JLabel jl_firmenname_value;
	    private JLabel jl_plz;
	    private JLabel jl_plz_value;
	    private JLabel jl_strasse;
	    private JLabel jl_strasse_value;

	    private ContractDetailsCompanyBoxCtrl controller;	
	
	/**
	 * Initialisiert die Box und bringt sie zur Anzeige.
	 * @param controller	ContractDetailsCompanyBoxCtrl Objekt, welche Informationen für die Box bereitstellt und auf Nutzeraktionen reagiert.
	 */
    public BoxElementContractDetailsCompany(ContractDetailsCompanyBoxCtrl controller) {
    	this.controller = controller;
        initComponents();
        setComponentNames();
        setComponentValues();
        setComponentEventHandler();
        setToolTip();
    }

    @Override                
    public void initComponents() {

    	jl_firmenname = new javax.swing.JLabel();
        jl_strasse = new javax.swing.JLabel();
        jl_firmenname_value = new javax.swing.JLabel();
        jl_strasse_value = new javax.swing.JLabel();
        jl_plz = new javax.swing.JLabel();
        jl_plz_value = new javax.swing.JLabel();
        jb_aendern = new javax.swing.JButton();
        jb_bearbeiten = new javax.swing.JButton();

        jl_firmenname.setText("Firmenname:");

        jl_strasse.setText("Stra\u00DFe:");

        jl_firmenname_value.setText("leer");

        jl_strasse_value.setText("leer");

        jl_plz.setText("Plz:");

        jl_plz_value.setText("leer");

        jb_aendern.setText("\u00E4ndern");

        jb_bearbeiten.setText("bearbeiten");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jl_firmenname)
        						.addComponent(jl_strasse)
        						.addComponent(jl_plz))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jl_firmenname_value, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
        						.addComponent(jl_strasse_value, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
        						.addComponent(jl_plz_value, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))
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
        				.addComponent(jl_firmenname)
        				.addComponent(jl_firmenname_value))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_strasse)
        				.addComponent(jl_strasse_value))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_plz_value)
        				.addComponent(jl_plz))
        			.addGap(32)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jb_aendern)
        				.addComponent(jb_bearbeiten))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }                     


	@Override
	public void setComponentNames() {
		jl_firmenname_value.setName(SqlTableCompanies.Firmenname);
	    jl_plz_value.setName(SqlTableCompanies.Firmenname);
	    jl_strasse_value.setName(SqlTableCompanies.Firmenname);
	}


	@Override
	public void setComponentValues() {
		jl_firmenname_value.setText(controller.getStringValueForBoxElementEdit("Firmenname"));
	    jl_plz_value.setText(controller.getStringValueForBoxElementEdit("PLZ"));
	    jl_strasse_value.setText(controller.getStringValueForBoxElementEdit("Strasse"));
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
			controller.buttonChangeCompanyOnContractDetailsClicked();
		
		if(e.getComponent() == jb_bearbeiten)
			controller.buttonEditCompanyOnContractDetailsClicked();
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
		inputValues.put(SqlTableContracts.FK_Firma, controller.getStringValueForBoxElementEdit("FirmenID"));
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
