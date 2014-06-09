package Views;

//import Controller.Controller;

//import Controller.Controller;
import java.awt.GridBagConstraints;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Models.Model;
import Models.Datenbank.SqlTableCompanies;

public class CompanySingle extends SingleView{
	
	private Models.Model contactModel;
	JTextField contactName;
	JTextField contactTel;
	JTextField contactNote;
	
	
	public CompanySingle(Controller.Controller controller) {
		super(controller);
		this.setTitle("Firmendetails");
		contactModel = ((Controller.CompanySingle) controller).getContactModel();
	}

	@Override
	public void setElemente() {
		
		addTextfieldWithSqlReference("Firmenname", "Name", SqlTableCompanies.TableNameDotFirmenname, 1, 1);
		addTextfieldWithSqlReference("Straße", "Strasse", SqlTableCompanies.TableNameDotStrasse, 1, 3);
		addTextfieldWithSqlReference("PLZ", "PLZ", SqlTableCompanies.TableNameDotPLZ, 1, 4);
		addTextfieldWithSqlReference("Ort", "Ort", SqlTableCompanies.TableNameDotOrt, 1, 5);
		addTextfieldWithSqlReference("Land", "Land", SqlTableCompanies.TableNameDotLand, 1, 6);
		addTextfieldWithSqlReference("Tel.", "Tel.", SqlTableCompanies.TableNameDotTelefonnummer, 4, 3);
		addTextAreaWithSqlReference("Bemerkung", "Bemerkung", SqlTableCompanies.TableNameDotBemerkung, 4, 6);
		addContacts();
		addBottomMenu(12);
	}
	
	/**
	 * TODO
	 * Panel mit allen feldern und funktionen zum anzeigen und iterieren anlegen
	 * das verhalten implementiert der controller...
	 */
	
	private void addContacts(){

		
		
		try {
			setLabel("Ansprechpartner:",7,1);
			contactName = new JTextField(10);
			contactName.setName("contactName");
			contactName.setText(contactModel.getTableRowData().getStringValueFromPosition(contactModel.rowPosition, "Name"));
			GridBagConstraints gbc = getGridBagConstraint();
			gbc.gridx = 8;
			gbc.gridy = 1;
			addComponentToView(contactName);
			
			setLabel("Tel.:",7,2);
			contactTel = new JTextField(10);
			contactTel.setName("contactTel");
			contactTel.setText(contactModel.getTableRowData().getStringValueFromPosition(contactModel.rowPosition, "Tel."));
			gbc = getGridBagConstraint();
			gbc.gridx = 8;
			gbc.gridy = 2;
			addComponentToView(contactTel);
			
			setLabel("Bemerkung:",7,3);
			contactNote = new JTextField(10);
			contactNote.setName("contactNote");
			contactNote.setText(contactModel.getTableRowData().getStringValueFromPosition(contactModel.rowPosition, "Bemerkung"));
		
			gbc = getGridBagConstraint();
			gbc.gridx = 8;
			gbc.gridy = 3;
			addComponentToView(contactNote);
			
			JButton prevContact = new JButton("<--");
			prevContact.setName("previusContact");
			prevContact.addMouseListener((MouseListener) controller);
			gbc = getGridBagConstraint();
			gbc.gridx = 7;
			gbc.gridy = 4;
			addComponentToView(prevContact);
			
			JButton nextContact = new JButton("-->");
			nextContact.setName("nextContact");
			nextContact.addMouseListener((MouseListener) controller);
			gbc = getGridBagConstraint();
			gbc.gridx = 8;
			gbc.gridy = 4;
			addComponentToView(nextContact);
			
			JButton newContact = new JButton("Kontakt hinzufügen");
			newContact.setName("newContact");
			newContact.addMouseListener((MouseListener) controller);
			gbc = getGridBagConstraint();
			gbc.gridx = 7;
			gbc.gridy = 5;
			addComponentToView(newContact);
			
			JButton deleteContact = new JButton("Kontakt bearbeiten");
			deleteContact.setName("modifyContact");
			deleteContact.addMouseListener((MouseListener) controller);
			gbc = getGridBagConstraint();
			gbc.gridx = 8;
			gbc.gridy = 5;
			addComponentToView(deleteContact);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void refreshContacts(){
		try {
			contactName.setText(contactModel.getTableRowData().getStringValueFromPosition(contactModel.rowPosition, "Name"));
			contactTel.setText(contactModel.getTableRowData().getStringValueFromPosition(contactModel.rowPosition, "Tel."));
			contactNote.setText(contactModel.getTableRowData().getStringValueFromPosition(contactModel.rowPosition, "Bemerkung"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
