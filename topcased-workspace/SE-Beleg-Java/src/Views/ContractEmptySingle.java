package Views;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Views.Table.TableData;

import Controller.Controller;

public class ContractEmptySingle extends EmptySingleView{

	private Models.CompanieSingle company = null;
	private Models.StudentSingle student = null;
	private TableData companyData = null;
	private TableData studentData = null;
	
	public ContractEmptySingle(Controller controller) {
		super(controller);
		this.setTitle("Vertrag hinzufügen");
		
	}

	public void setStudent(String studentId){
		String sourceQuery = new String("select * from student where MatrNr = "+studentId);
		student = new Models.StudentSingle(sourceQuery);
		studentData = new TableData(student.getResult());
		
		deleteComponentFromView("addStudent");
		setStudentFields();
		this.getLayout().layoutContainer(this);
		pack();
	}
	
	public void setCompany(String companyId){
		String sourceQuery = new String("select * from companies where Id = "+companyId);
		
		company = new Models.CompanieSingle(sourceQuery);
		companyData= new TableData(company.getResult());
		
		deleteComponentFromView("addCompany");
		setCompanyFields();
		this.getLayout().layoutContainer(this);
		pack();
	}
	
	@Override
	public void setElemente() {
				
		
		if(isStudentSet())
			setStudentFields();
		else
			setAddStudentButton();
		
		if(isCompanySet())
			setCompanyFields();
		else
			setAddCompanyButton();
		
		setLabel("                 ",1,6);

		addTextfieldWithSqlReference("Betreuer", "Prof", 1, 7);
		addTextfieldWithSqlReference("beginnt am", "BegPr", 1, 8);
		addTextfieldWithSqlReference("endet am","EndPr", 1, 9);
		addTextfieldWithSqlReference("Typ", "Type", 1, 10);
		
		addCheckboxWithSqlReference("Bericht","Bericht", 3, 7);
		addCheckboxWithSqlReference("Zeugnis","Zeugnis", 3, 8);
		addCheckboxWithSqlReference("Empfehlung","Empfehlung", 3, 9);
		
		setBottomMenu(12);
	}
	
	private void setCompanyFields(){
		try {
			addHiddenFieldWithSqlReference("CompID",companyData.getStringValueFromPosition(0, "ID"));
			addTextfieldWithData("Firmenname", companyData.getStringValueFromPosition(0, "Name"), 3, 1);
			addTextfieldWithData("Straße", companyData.getStringValueFromPosition(0, "Street"), 3, 2);
			addTextfieldWithData("PLZ", companyData.getStringValueFromPosition(0, "ZIP"),3 , 3);
			addTextfieldWithData("Ort", companyData.getStringValueFromPosition(0, "Town"), 3, 4);
		} catch (Exception e) {
		// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		setModifyCompanyButton();
	}
	
	private void setAddCompanyButton(){
		addButton("Firma hinzufügen", "addCompany", 3, 1, 2, 4);
	}
	
	private void setModifyCompanyButton(){
		addButton("Firma ändern", "changeCompany", 3, 5, 2, 1);
		addButton("Firma bearbeiten", "modifyCompany", 3, 6, 2, 1);
	}
	
	
	private void setStudentFields(){
		try {
			addHiddenFieldWithSqlReference("MatrNr",studentData.getStringValueFromPosition(0, "MatrNr"));
			addTextfieldWithData("Vorname", studentData.getStringValueFromPosition(0, "Firstname"), 1, 1);
			addTextfieldWithData("Nachname", studentData.getStringValueFromPosition(0, "Name"), 1, 2);
			addTextfieldWithData("Matrikelnr.", studentData.getStringValueFromPosition(0, "MatrNr"), 1, 3);
			addTextfieldWithData("E-Mail", studentData.getStringValueFromPosition(0, "Email"), 1, 4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setModifyStudentButton();
	}
	
	private void setAddStudentButton(){
		addButton("Student hinzufügen", "addStudent", 1, 1, 2, 4);
	}
	
	private void setModifyStudentButton(){
		addButton("Student ändern", "changeStudent", 1, 5, 2, 1);
	}
	
	private boolean isStudentSet(){
		
		if(student == null) return false;
	
		return true;
	}
	
	private boolean isCompanySet(){

		if(company == null) return false;
		
		return true;
	}

	public void clearAllFields() {
		// TODO Auto-generated method stub
		
	}

	
	private void addTextfieldWithData(String labelText, String value, int posX, int posY){
		GridBagConstraints gbc = getGridBagConstraint();
		setLabel(labelText,posX,posY);
		
		JTextField f;
		
		try {
			f = new JTextField(value);
			f.setColumns(20);
			
			gbc.gridx = posX+1;
			addComponentToView(f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void addHiddenFieldWithSqlReference(String sqlSpaltenName, String value){

		JLabel hiddenContractId = new JLabel(value);
		hiddenContractId.setVisible(false);
		
		addComponentToView(hiddenContractId,sqlSpaltenName);

	}
}
