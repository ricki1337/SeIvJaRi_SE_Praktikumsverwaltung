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
import Views.Interfaces.ContractDetailsCompanyBox;
import Views.Interfaces.ContractDetailsProfBox;
import Views.Interfaces.ContractDetailsStudentBox;
import Views.Interfaces.EditBox;
import Views.Interfaces.EditBoxCtrl;


public class BoxElementContractDetails extends JPanel implements EditBox{
	 	private JCheckBox jcb_bericht;
	    private JCheckBox jcb_empfehlung;
	    private JCheckBox jcb_zeugnis;
	    private JLabel jl_beginnt;
	    private JLabel jl_bericht;
	    private JLabel jl_empfehlung;
	    private JLabel jl_endet;
	    private JLabel jl_typ;
	    private JLabel jl_zeugnis;
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

    	jl_beginnt = new JLabel();
        jl_typ = new JLabel();
        jl_endet = new JLabel();
        jtf_beginnt_value = new JTextField();
        jtf_endet_value = new JTextField();
        jtf_typ_value = new JTextField();
        jl_bericht = new JLabel();
        jl_zeugnis = new JLabel();
        jl_empfehlung = new JLabel();
        jcb_empfehlung = new JCheckBox();
        jcb_zeugnis = new JCheckBox();
        jcb_bericht = new JCheckBox();

        jl_beginnt.setText("beginnt am:");

        jl_typ.setText("Typ:");

        jl_endet.setText("endet am:");

        
        jl_bericht.setText("Bericht:");

        jl_zeugnis.setText("Zeugnis:");

        jl_empfehlung.setText("Empfehlung:");
        
        pnl_prof = new JPanel();
        pnl_prof.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
        
        pnl_company = new JPanel();
        pnl_company.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
        
        pnl_student = new JPanel();
        pnl_student.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
        
        JLabel jl_student = new JLabel("Student");
        
        lblFirma = new JLabel("Firma");
        
        lblBetreuer = new JLabel("Betreuer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(pnl_student, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jl_student))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblFirma)
        						.addComponent(pnl_company, GroupLayout.PREFERRED_SIZE, 373, GroupLayout.PREFERRED_SIZE)))
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
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jl_empfehlung)
        						.addComponent(jl_zeugnis)
        						.addComponent(jl_bericht))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jcb_bericht)
        						.addComponent(jcb_zeugnis)
        						.addComponent(jcb_empfehlung))
        					.addGap(54)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblBetreuer)
        						.addComponent(pnl_prof, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE))))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(20)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jl_beginnt)
        								.addComponent(jtf_beginnt_value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jl_endet)
        								.addComponent(jtf_endet_value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(layout.createParallelGroup(Alignment.LEADING)
        								.addComponent(jl_typ)
        								.addComponent(jtf_typ_value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        						.addGroup(layout.createSequentialGroup()
        							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jl_bericht)
        								.addComponent(jcb_bericht))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jl_zeugnis)
        								.addComponent(jcb_zeugnis))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        								.addComponent(jl_empfehlung)
        								.addComponent(jcb_empfehlung)))))
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(lblBetreuer)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(pnl_prof, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
        			.addGap(5)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(13)
        					.addComponent(jl_student))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addComponent(lblFirma)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(pnl_student, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
        				.addComponent(pnl_company, GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE))
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
	}

	@Override
	public void setComponentValues() {
	    jtf_beginnt_value.setText(controller.getStringValueForBoxElementEdit(SqlTableContracts.Beginn));
	    jtf_endet_value.setText(controller.getStringValueForBoxElementEdit(SqlTableContracts.Ende));
	    jtf_typ_value.setText(controller.getStringValueForBoxElementEdit(SqlTableContracts.Typ));
	    jcb_empfehlung.setSelected(controller.getBooleanValueForBoxElementEdit(SqlTableContracts.Empfehlung));
	    jcb_zeugnis.setSelected(controller.getBooleanValueForBoxElementEdit(SqlTableContracts.Zeugnis));
	    jcb_bericht.setSelected(controller.getBooleanValueForBoxElementEdit(SqlTableContracts.Bericht));
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
