package Views;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Controller.Controller;
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableStudent;
import Views.Table.TableData;

public class ContractEmptySingle extends EmptySingleView{

	private Models.Model company = null;
	private Models.Model student = null;
	private TableData companyData = null;
	private TableData studentData = null;
	
	public ContractEmptySingle(Controller controller) {
		super(controller);
		this.setTitle("Vertrag hinzufügen");
		
	}

	/**
	 * TODO
	 * auslagerung von student & company single in controller
	 */
	public void setStudent(String studentId){
		String sourceQuery = new String("select * from " + SqlTableStudent.tableName + " where " + SqlTableStudent.TableNameDotMatrikelNummer + " = " + studentId);
		student = new Models.Model(sourceQuery,SqlTableStudent.tableName,SqlTableStudent.TableNameDotPrimaryKey);
		studentData = new TableData(student.getResult());
		
		deleteComponentFromView("addStudent");
		setStudentFields();
		this.getLayout().layoutContainer(this);
		pack();
	}
	
	public void setCompany(String companyId){
		String sourceQuery = new String("select * from " + SqlTableCompanies.tableName + " where " + SqlTableCompanies.TableNameDotId + " = " + companyId);
		
		company = new Models.Model(sourceQuery,SqlTableCompanies.tableName,SqlTableCompanies.TableNameDotPrimaryKey);
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

		addTextfieldWithSqlReference("Betreuer", SqlTableContracts.TableNameDotFK_Betreuer, 1, 7);
		addTextfieldWithSqlReference("beginnt am", SqlTableContracts.TableNameDotBeginn, 1, 8);
		addTextfieldWithSqlReference("endet am",SqlTableContracts.TableNameDotEnde, 1, 9);
		addTextfieldWithSqlReference("Typ", SqlTableContracts.TableNameDotTyp, 1, 10);
		
		addCheckboxWithSqlReference("Bericht",SqlTableContracts.TableNameDotBericht, 3, 7);
		addCheckboxWithSqlReference("Zeugnis",SqlTableContracts.TableNameDotZeugnis, 3, 8);
		addCheckboxWithSqlReference("Empfehlung",SqlTableContracts.TableNameDotEmpfehlung, 3, 9);
		
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
	
	
}
