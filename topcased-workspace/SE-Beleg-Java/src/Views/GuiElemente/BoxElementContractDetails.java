package Views.GuiElemente;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;

import ConfigParser.Debug;
import Models.Datenbank.SqlTableContracts;
import Views.Interfaces.BasicBox;
import Views.Interfaces.EditBox;
import Views.Interfaces.EditBoxCtrl;


public class BoxElementContractDetails extends JPanel implements EditBox{
		private JCheckBox jcb_erfolg;
	    private JCheckBox jcb_empfehlung;
	    private JCheckBox jcb_zeugnis;
	    private JCheckBox jcb_bericht;
	    private JLabel jl_beginnt;
	    private JLabel jl_endet;
	    private JLabel jl_typ;
	    private JLabel jl_student;
	    private JTextField jtf_beginnt_value;
	    private JTextField jtf_endet_value;
	    private JTextField jtf_typ_value;
	    private JPanel pnl_student;
	    private JPanel pnl_prof;
	    private JPanel pnl_company;
	    private JLabel lblFirma;
	    private JLabel lblBetreuer;
	    
	    private EditBoxCtrl controller;
	    private boolean addNewContract = false;
	    
    public BoxElementContractDetails(EditBoxCtrl controller) {
    	this.controller = controller;
        initComponents();
        setComponentNames();
        setComponentValues();
        setToolTip();
    }
    
    public BoxElementContractDetails(EditBoxCtrl controller, boolean addNewContract) {
    	this.controller = controller;
    	this.addNewContract = addNewContract;
        initComponents();
        setComponentNames();
        setToolTip();        
    }
    
    @Override
    public void initComponents() {

    	jl_beginnt = new javax.swing.JLabel();
        jl_typ = new javax.swing.JLabel();
        jl_endet = new javax.swing.JLabel();
        jtf_beginnt_value = new javax.swing.JTextField();
        jtf_endet_value = new javax.swing.JTextField();
        jtf_typ_value = new javax.swing.JTextField();

        jl_beginnt.setText("beginnt am:");

        jl_typ.setText("Typ:");

        jl_endet.setText("endet am:");

        pnl_prof = new JPanel();
        pnl_prof.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
        
        pnl_company = new JPanel();
        pnl_company.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
        
        pnl_student = new JPanel();
        pnl_student.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
        
        jl_student = new JLabel("Student");
        
        lblFirma = new JLabel("Firma");
        
        lblBetreuer = new JLabel("Betreuer");
        
        jcb_erfolg = new JCheckBox("Erfolg");
        
        jcb_empfehlung = new JCheckBox("Empfehlung");
        
        jcb_zeugnis = new JCheckBox("Zeugnis");
        
        jcb_bericht = new JCheckBox("Bericht");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jl_student)
        						.addComponent(pnl_student, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
        					.addGap(58)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(pnl_prof, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
        						.addComponent(pnl_company, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblBetreuer)
        						.addComponent(lblFirma))
        					.addGap(187))
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jl_beginnt)
        						.addComponent(jl_typ)
        						.addComponent(jl_endet))
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
        						.addGroup(layout.createSequentialGroup()
        							.addGap(17)
        							.addComponent(jtf_typ_value, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
        						.addGroup(layout.createSequentialGroup()
        							.addGap(18)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jtf_beginnt_value, Alignment.TRAILING)
        								.addComponent(jtf_endet_value))))
        					.addGap(82)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jcb_zeugnis)
        						.addComponent(jcb_erfolg)
        						.addComponent(jcb_bericht)
        						.addComponent(jcb_empfehlung))
        					.addGap(588)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(24)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jl_beginnt)
        						.addComponent(jtf_beginnt_value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jcb_bericht))
        					.addGap(6)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jl_endet)
        						.addComponent(jtf_endet_value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jcb_zeugnis))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jl_typ)
        						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        							.addComponent(jcb_empfehlung)
        							.addComponent(jtf_typ_value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jcb_erfolg))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(16)
        					.addComponent(lblBetreuer)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pnl_prof, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
        			.addGap(35)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jl_student)
        				.addComponent(lblFirma))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(pnl_student, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        				.addComponent(pnl_company, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
        			.addContainerGap())
        );
        this.setLayout(layout);
    }                 

         
	@Override
	public void setComponentNames() {
	     jtf_beginnt_value.setName(SqlTableContracts.Beginn);
	     jtf_endet_value.setName(SqlTableContracts.Ende);
	     jtf_typ_value.setName(SqlTableContracts.Typ);
	     jcb_empfehlung.setName(SqlTableContracts.Empfehlung);
	     jcb_zeugnis.setName(SqlTableContracts.Zeugnis);
	     jcb_bericht.setName(SqlTableContracts.Bericht);
	     jcb_erfolg.setName(SqlTableContracts.Erfolg);
	}

	@Override
	public void setComponentValues() {
	    jtf_beginnt_value.setText(controller.getStringValueForBoxElementEdit(SqlTableContracts.Beginn));
	    jtf_endet_value.setText(controller.getStringValueForBoxElementEdit(SqlTableContracts.Ende));
	    jtf_typ_value.setText(controller.getStringValueForBoxElementEdit(SqlTableContracts.Typ));
	    jcb_empfehlung.setSelected(controller.getBooleanValueForBoxElementEdit(SqlTableContracts.Empfehlung));
	    jcb_zeugnis.setSelected(controller.getBooleanValueForBoxElementEdit(SqlTableContracts.Zeugnis));
	    jcb_bericht.setSelected(controller.getBooleanValueForBoxElementEdit(SqlTableContracts.Bericht));
	    jcb_erfolg.setSelected(controller.getBooleanValueForBoxElementEdit(SqlTableContracts.Erfolg));
	}

	@Override
	public void refreshContent() {
		if(!addNewContract)
			setComponentValues();
	    
	    if(pnl_student instanceof EditBox){
			((EditBox)pnl_student).refreshContent();
		}
		
		if(pnl_company instanceof EditBox){
			((EditBox)pnl_company).refreshContent();
		}
		
		if(pnl_prof instanceof EditBox){
			((EditBox)pnl_prof).refreshContent();
		}
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public Map<String, Object> getInputValues() {
		
		Map<String, Object> inputValues = new HashMap<String, Object>();
		inputValues.put(jtf_beginnt_value.getName(), jtf_beginnt_value.getText());
		inputValues.put(jtf_endet_value.getName(), jtf_endet_value.getText());
		inputValues.put(jtf_typ_value.getName(), jtf_typ_value.getText());
		inputValues.put(jcb_empfehlung.getName(), jcb_empfehlung.isSelected());
		inputValues.put(jcb_zeugnis.getName(), jcb_zeugnis.isSelected());
		inputValues.put(jcb_bericht.getName(), jcb_bericht.isSelected());
		inputValues.put(jcb_erfolg.getName(), jcb_erfolg.isSelected());
		
		if(pnl_student instanceof EditBox){
			inputValues.putAll(((EditBox)pnl_student).getInputValues());
		}
		
		if(pnl_company instanceof EditBox){
			inputValues.putAll(((EditBox)pnl_company).getInputValues());
		}
		
		if(pnl_prof instanceof EditBox){
			inputValues.putAll(((EditBox)pnl_prof).getInputValues());
		}
		
		
		return inputValues;
	}
	
	public void setStudentBox(BasicBox studentBox){
		JPanel newPanel = (JPanel)studentBox.getJComponent();
		GroupLayout layout = (GroupLayout) this.getLayout();
		layout.replace(pnl_student , newPanel);
		pnl_student = newPanel;
		pnl_student.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
	}
	
	public void setCompanyBox(BasicBox companyBox){
		JPanel newPanel = (JPanel)companyBox.getJComponent();
		GroupLayout layout = (GroupLayout) this.getLayout();
		layout.replace(pnl_company , newPanel);
		pnl_company = newPanel;
		pnl_company.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
	}
	
	public void setProfBox(BasicBox profBox){
		JPanel newPanel = (JPanel)profBox.getJComponent();
		GroupLayout layout = (GroupLayout) this.getLayout();
		layout.replace(pnl_prof , newPanel);
		pnl_prof = newPanel;
		pnl_prof.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
	}
	
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}
	
	@Override
	public void setComponentEventHandler() {}
}
