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

/**
 * 	Implementiert eine EditBox, welche die Informationen zu Ansprechpartnern zum bearbeiten darstellt.
 */
public class BoxElementContactDetails extends JPanel implements EditBox{
		private JTextField jtf_name;
		private JTextField jtf_tel;
		private JTextArea jta_bemerkung;

		private EditBoxCtrl controller;
		private boolean addNewContract = false;
		private int companyId = -1;
	
	/**
	 * Initialisiert die Box und bringt sie zur Anzeige.
	 * @param controller	EditBoxCtrl Objekt, welche die eingegebenen Daten verarbeitet und Daten für die Box bereitstellt.
	 */
	public BoxElementContactDetails(EditBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentValues();
		setComponentEventHandler();
		setToolTip();
	}
	
	/**
	 * Initialisiert die Box zum Anlegen neuer Ansprechpartner.
	 * @param controller		EditBoxCtrl Objekt, welche die eingegebenen Daten verarbeitet.
	 * @param addNewContract	Flag zur Signalisierung einer Neuanlage.
	 * @param companyId			FirmenID, zu welchem der Ansprechpartner gehört.
	 */
	public BoxElementContactDetails(EditBoxCtrl controller, boolean addNewContract, int companyId){
		this.controller = controller;
		this.addNewContract = addNewContract;
		this.companyId = companyId;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
		setToolTip();
	}
	
	/**
	 * Löscht alle eingaben.
	 */
	public void clearComponentValues(){
		jtf_tel.setText("");
		jtf_name.setText("");
		jta_bemerkung.setText("");
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
		else
			clearComponentValues();
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public Map<String, Object> getInputValues() {
		Map<String, Object> inputValues = new HashMap<String, Object>();
		
		inputValues.put(jtf_tel.getName(), getSubstringFromInputString(jtf_tel.getText(),15));
		inputValues.put(jta_bemerkung.getName(), getSubstringFromInputString(jta_bemerkung.getText(),50));
		inputValues.put(jtf_name.getName(), getSubstringFromInputString(jtf_name.getText(),15));
		
		if(companyId != -1)
			inputValues.put(SqlTableContacts.ZuordnungFirma, companyId);
		
		return inputValues;
	}
	
	private String getSubstringFromInputString(String inputString, int maxLenght) {
		int length = inputString.length();
		return (String) inputString.subSequence(0, (length<maxLenght)?length:maxLenght);
	}
	
	@Override
	public void initComponents(){
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JLabel jl_bemerkung = new JLabel("Bemerkung");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(jl_bemerkung)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(jl_bemerkung)
					.addContainerGap(133, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		
		jta_bemerkung = new JTextArea();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(jta_bemerkung, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(jta_bemerkung, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
					.addGap(17))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		
		jtf_name = new JTextField();
		jtf_name.setColumns(25);
		
		jtf_tel = new JTextField();
		jtf_tel.setColumns(20);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(jtf_name, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
						.addComponent(jtf_tel, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(jtf_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jtf_tel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(106, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblTel = new JLabel("Tel.");
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
							.addComponent(lblTel, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
							.addGap(4))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTel)
					.addContainerGap(108, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
	}
	
	@Override
	public void setComponentEventHandler() {}
	
	@Override
	public void setToolTip(){
		if(Debug.isDebugMode()){
			setToolTipText(this.getClass().getCanonicalName());
			this.setBackground(Color.getHSBColor(ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255, ThreadLocalRandom.current().nextFloat()%255));
		}
	}
}
