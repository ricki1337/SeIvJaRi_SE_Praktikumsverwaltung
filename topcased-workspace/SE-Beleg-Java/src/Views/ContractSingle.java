package Views;

import javax.swing.JLabel;

import Controller.Controller;

public class ContractSingle extends SingleView{

	public ContractSingle(Controller controller) {
		super(controller);
		this.setTitle("Verträge");
	}

	@Override
	public void setElemente() {
				
		
		if(isStudentSet())
			setStudentFields();
		else
			setAddStudentButton();
		
		if(isCompanieSet())
			setCompanieFields();
		else
			setAddCompanieButton();
		
		setLabel("                 ",1,6);
		addContractIdToComponentList();
		addTextfieldWithSqlReference("Betreuer", "Betreuer","Prof", 1, 7);
		addTextfieldWithSqlReference("beginnt am", "beginnt am","BegPr", 1, 8);
		addTextfieldWithSqlReference("endet am", "endet am","EndPr", 1, 9);
		addTextfieldWithSqlReference("Typ", "Typ","Type", 1, 10);
		
		addCheckboxWithSqlReference("Bericht", "Bericht","Bericht", 3, 7);
		addCheckboxWithSqlReference("Zeugnis", "Zeugnis","Zeugnis", 3, 8);
		addCheckboxWithSqlReference("Empfehlung", "Empfehlung","Empfehlung", 3, 9);
		
		addBottomMenu(12);
	}
	
	private void setCompanieFields(){
		addTextfield("Firmenname", "Firmenname", 3, 1);
		addTextfield("Straße", "Strasse", 3, 2);
		addTextfield("PLZ", "PLZ", 3, 3);
		addTextfield("Ort", "Ort", 3, 4);
		setModifyCompanieButton();
	}
	
	private void setAddCompanieButton(){
		addAddButton("Firma hinzufügen", "addCompanie", 3, 1, 2, 4);
	}
	
	private void setModifyCompanieButton(){
		addAddButton("Firma ändern", "changeCompanie", 3, 5, 2, 1);
		addAddButton("Firma bearbeiten", "modifyCompanie", 3, 6, 2, 1);
	}
	
	
	private void setStudentFields(){
		addTextfield("Vorname", "Vorname", 1, 1);
		addTextfield("Nachname", "Nachname", 1, 2);
		addTextfield("Matrikelnr.", "Matrikelnr.", 1, 3);
		addTextfield("E-Mail", "E-Mail", 1, 4);
		setModifyStudentButton();
	}
	
	private void setAddStudentButton(){
		addAddButton("Student hinzufügen", "addStudent", 1, 1, 2, 4);
	}
	
	private void setModifyStudentButton(){
		addAddButton("Student ändern", "changeStudent", 1, 5, 2, 1);
	}
	
	private boolean isStudentSet(){
		try {
			String matr = tableRowData.getStringValueFromPosition(rowPosition, "Matrikelnr.");
			if(matr.isEmpty()) return false;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	private boolean isCompanieSet(){
		try {
			String companie = tableRowData.getStringValueFromPosition(rowPosition, "Firmenname");
			if(companie.isEmpty()) return false;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	private void addContractIdToComponentList(){
		try {
			JLabel hiddenContractId = new JLabel(tableRowData.getStringValueFromPosition(rowPosition, "ID"));
			hiddenContractId.setVisible(false);
			
			addComponentToView(hiddenContractId,"ID");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
