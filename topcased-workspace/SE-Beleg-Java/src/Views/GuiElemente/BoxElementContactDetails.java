package Views.GuiElemente;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ConfigParser.Debug;
import Models.Datenbank.SqlTableContacts;
import Views.Interfaces.EditBox;
import Views.Interfaces.EditBoxCtrl;

public class BoxElementContactDetails extends JPanel implements EditBox{
		private JTextField jtf_name;
		private JTextField jtf_tel;
		private JTextArea jta_bemerkung;
		

		private EditBoxCtrl controller;
		private boolean addNewContract = false;
		private int companyId = -1;
		
	public BoxElementContactDetails(EditBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentValues();
		setComponentEventHandler();
		setToolTip();
	}
	
	public BoxElementContactDetails(EditBoxCtrl controller, boolean addNewContract, int companyId){
		this.controller = controller;
		this.addNewContract = addNewContract;
		this.companyId = companyId;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
		setToolTip();
	}
	
		
	@Override
	public void setComponentNames() {
		jtf_tel.setName(SqlTableContacts.Telefonnummer);
		jtf_name.setName(SqlTableContacts.Name);
		jta_bemerkung.setName(SqlTableContacts.Bemerkung);
	}

	@Override
	public void setComponentValues() {
		jtf_tel.setText(controller.getStringValueForBoxElementEdit(SqlTableContacts.Telefonnummer));
		jtf_name.setText(controller.getStringValueForBoxElementEdit(SqlTableContacts.Name));
		jta_bemerkung.setText(controller.getStringValueForBoxElementEdit(SqlTableContacts.Bemerkung));
	}

	

	@Override
	public void refreshContent() {
		if(!addNewContract)
			setComponentValues();
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public Map<String, Object> getInputValues() {
		Map<String, Object> inputValues = new HashMap<String, Object>();
		
		inputValues.put(jtf_tel.getName(), jtf_tel.getText());
		inputValues.put(jta_bemerkung.getName(), jta_bemerkung.getText());
		inputValues.put(jtf_name.getName(), jtf_name.getText());
		
		if(companyId != -1)
			inputValues.put(SqlTableContacts.ZuordnungFirma, companyId);
		
		return inputValues;
	}
	
	public void initComponents(){
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
					.addGap(117))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		jtf_name = new JTextField();
		jtf_name.setColumns(25);
		
		jtf_tel = new JTextField();
		jtf_tel.setColumns(20);
		
		jta_bemerkung = new JTextArea();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(jta_bemerkung, Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addComponent(jtf_name, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
							.addComponent(jtf_tel, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(jtf_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jtf_tel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jta_bemerkung, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(131, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblTel = new JLabel("Tel.");
		
		JLabel jl_bemerkung = new JLabel("Bemerkung");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(jl_bemerkung)
							.addContainerGap(28, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTel, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
							.addGap(4))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTel)
					.addGap(7)
					.addComponent(jl_bemerkung)
					.addContainerGap(207, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
	}
	
	@Override
	public void setComponentEventHandler() {}
	
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}
}
