package Views.GuiElemente;

import java.util.HashMap;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import Models.Datenbank.SqlTableProfs;
import Views.Interfaces.EditBox;
import Views.Interfaces.EditBoxCtrl;

public class BoxElementProfDetails extends JPanel implements EditBox{
		private JTextField jtf_name;
		private JTextField jtf_email;

		private EditBoxCtrl parent;
		
	public BoxElementProfDetails(EditBoxCtrl parent){
		this.parent = parent;
		initComponents();
		setComponentNames();
		setComponentValues();
		setComponentEventHandler();
	}
	
	@Override
	public void setComponentNames() {
		jtf_email.setName(SqlTableProfs.Id);
		jtf_name.setName(SqlTableProfs.Name);
	}

	@Override
	public void setComponentValues() {
		jtf_email.setText(parent.getStringValueForBoxElementEdit(SqlTableProfs.Id));
		jtf_name.setText(parent.getStringValueForBoxElementEdit(SqlTableProfs.Name));
	}

	

	@Override
	public void refreshContent() {
		jtf_email.setText(parent.getStringValueForBoxElementEdit(SqlTableProfs.Id));
		jtf_name.setText(parent.getStringValueForBoxElementEdit(SqlTableProfs.Name));
	}

	@Override
	public JComponent getJComponent() {
		return this;
	}

	@Override
	public Map<String, Object> getInputValues() {
		Map<String, Object> inputValues = new HashMap<String, Object>();
		
		inputValues.put(jtf_email.getName(), jtf_email.getText());
		inputValues.put(jtf_name.getName(), jtf_name.getText());
		
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
}
