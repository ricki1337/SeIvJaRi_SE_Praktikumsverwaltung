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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ConfigParser.Debug;
import Models.Datenbank.SqlTableProfs;
import Views.Interfaces.EditBox;
import Views.Interfaces.EditBoxCtrl;

/**
 * Implementiert eine EditBox zur Darstellung von Informationen eines Betreuers.
 */
public class BoxElementProfDetails extends JPanel implements EditBox{
		private JTextField jtf_name;
		private JTextField jtf_email;

		private EditBoxCtrl controller;
		private boolean addNewContract = false;
	
		
	/**
	 * Initialisiert die Box und bringt sie zur Anzeige.
	 * @param controller	EditBoxCtrl Objekt, welches Daten für die Box bereitstellt und die Nutzereingaben verarbeitet.
	 */
	public BoxElementProfDetails(EditBoxCtrl controller){
		this.controller = controller;
		initComponents();
		setComponentNames();
		setComponentValues();
		setComponentEventHandler();
		setToolTip();
	}
	
	/**
	 * Initialisiert die Box für die Neuanlage von Datensätzen.
	 * @param controller		EditBoxCtrl Objekt, welches die eingegebenen Daten verarbeitet.
	 * @param addNewContract	Flag zur Signalisierung von einer Neuanlage.
	 */
	public BoxElementProfDetails(EditBoxCtrl controller, boolean addNewContract){
		this.controller = controller;
		this.addNewContract = addNewContract;
		initComponents();
		setComponentNames();
		setComponentEventHandler();
		setToolTip();
	}
	
	/**
	 * Löscht alle Eingaben.
	 */
	public void clearComponentValues() {
		jtf_email.setText("");
		jtf_name.setText("");
	}

		
	@Override
	public void setComponentNames() {
		jtf_email.setName(SqlTableProfs.Id);
		jtf_name.setName(SqlTableProfs.Name);
	}

	@Override
	public void setComponentValues() {
		jtf_email.setText(controller.getStringValueForBoxElementEdit("E-Mail"));
		jtf_name.setText(controller.getStringValueForBoxElementEdit(SqlTableProfs.Name));
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
		
		inputValues.put(jtf_email.getName(), getSubstringFromInputString(jtf_email.getText(),20));
		inputValues.put(jtf_name.getName(), getSubstringFromInputString(jtf_name.getText(),25));
		
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
		
		jtf_email = new JTextField();
		jtf_email.setColumns(20);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(27, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(jtf_name, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtf_email, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(6)
					.addComponent(jtf_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jtf_email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblName = new JLabel("Name");
		
		JLabel lblEmail = new JLabel("E-Mail");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(7))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblName)
					.addGap(7)
					.addComponent(lblEmail)
					.addContainerGap(80, Short.MAX_VALUE))
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
