package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Controller.Interfaces.CallbackSelectedValue;
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableProfs;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSave;
import Views.GuiElemente.BoxElementBottomNaviPrevSaveNext;
import Views.GuiElemente.BoxElementContractDetails;
import Views.GuiElemente.BoxElementContractDetailsCompany;
import Views.GuiElemente.BoxElementContractDetailsCompanyNew;
import Views.GuiElemente.BoxElementContractDetailsProf;
import Views.GuiElemente.BoxElementContractDetailsProfNew;
import Views.GuiElemente.BoxElementContractDetailsStudent;
import Views.GuiElemente.BoxElementContractDetailsStudentNew;
import Views.GuiElemente.BoxElementStudentDetails;
import Views.GuiElemente.BoxElementStudentDetailsNew;
import Views.Interfaces.ContractDetailsCompanyBoxCtrl;
import Views.Interfaces.ContractDetailsCompanyNewBoxCtrl;
import Views.Interfaces.ContractDetailsProfBoxCtrl;
import Views.Interfaces.ContractDetailsProfNewBoxCtrl;
import Views.Interfaces.ContractDetailsStudentBoxCtrl;
import Views.Interfaces.ContractDetailsStudentNewBoxCtrl;
import Views.Interfaces.NaviAbortSaveBoxCtrl;
import Views.Interfaces.NaviPrevSaveNextBoxCtrl;
import Views.Interfaces.EditBoxCtrl;
import Views.Table.EmptyObject;

public class ContractSingleNew extends ControllerNew implements EditBoxCtrl, 
																NaviAbortSaveBoxCtrl, 
																NaviPrevSaveNextBoxCtrl, 
																ContractDetailsCompanyBoxCtrl, 
																ContractDetailsStudentBoxCtrl, 
																ContractDetailsProfBoxCtrl, 
																CallbackSelectedValue,
																ContractDetailsStudentNewBoxCtrl,
																ContractDetailsCompanyNewBoxCtrl,
																ContractDetailsProfNewBoxCtrl
																{
	
		private String srcSqlQuery = "SELECT " +
										SqlTableContracts.TableNameDotId + " as ID," +
										SqlTableStudent.TableNameDotMatrikelNummer + " as 'Matrikelnr.'," +
										SqlTableStudent.TableNameDotVorname + " as Vorname, " +
										SqlTableStudent.TableNameDotNachname + " as Nachname, " +
										SqlTableStudent.TableNameDotEMail + " as 'E-Mail', " +
										SqlTableStudent.TableNameDotStudiengruppe + " as Studiengruppe, " +
										SqlTableCompanies.TableNameDotId + " as FirmenID, " +
										SqlTableCompanies.TableNameDotFirmenname + " as Firmenname, " +
										SqlTableCompanies.TableNameDotStrasse + " as Strasse, " +
										SqlTableCompanies.TableNameDotPLZ + " as PLZ, " +
										SqlTableCompanies.TableNameDotOrt + " as Ort, " +
										SqlTableContracts.TableNameDotFK_Betreuer + " as Betreuer, " +
										SqlTableProfs.TableNameDotName + " as BetreuerName, " +
										SqlTableContracts.TableNameDotTyp + " as Typ, " +
										SqlTableContracts.TableNameDotBeginn + " as 'beginnt am', " +
										SqlTableContracts.TableNameDotEnde + " as 'endet am', " +
										SqlTableContracts.TableNameDotBericht + " as Bericht, " +
										SqlTableContracts.TableNameDotZeugnis + " as Zeugnis, " +
										SqlTableContracts.TableNameDotEmpfehlung + " as Empfehlung " +
									"FROM "+ SqlTableContracts.tableName +
										" LEFT JOIN " + SqlTableStudent.tableName + " ON " +
											SqlTableContracts.TableNameDotFK_Student + " = " + SqlTableStudent.TableNameDotPrimaryKey + 
										" LEFT JOIN " + SqlTableCompanies.tableName + " ON " +
											SqlTableContracts.TableNameDotFK_Firma + " = "+ SqlTableCompanies.TableNameDotPrimaryKey +
										" LEFT JOIN " + SqlTableProfs.tableNameWithAlias + " ON " +
											SqlTableContracts.TableNameDotFK_Betreuer + " = " + SqlTableProfs.TableNameDotPrimaryKey;
	
	
		private Views.ViewNew view;
		private BoxElementContractDetails detailsBox;
		private boolean newData = false;
	
	public ContractSingleNew(){
		newData = true;
		setModel(new Models.Model(SqlTableContracts.tableName,SqlTableContracts.TableNameDotPrimaryKey));
		model.setSrcQuery(srcSqlQuery);
		model.setAndFilter(SqlTableContracts.TableNameDotPrimaryKey, new IntFilter(0));
		model.setResult();
		
		model.tableRowData.setValueAtPosition(model.rowPosition, "Typ", "");
		model.tableRowData.setValueAtPosition(model.rowPosition, "beginnt am", "");
		model.tableRowData.setValueAtPosition(model.rowPosition, "endet am", "");
		model.tableRowData.setValueAtPosition(model.rowPosition, "Bericht", false);
		model.tableRowData.setValueAtPosition(model.rowPosition, "Zeugnis", false);
		model.tableRowData.setValueAtPosition(model.rowPosition, "Empfehlung", false);
		
		setView(view = new Views.ViewNew(this));
		view.setTitle("Vertrag anlegen");
		setElementsForNewData();
	}
	
	
	public ContractSingleNew(Object primaryKeys){
		super();
		
		
		Models.Model model = new Models.Model(srcSqlQuery,SqlTableContracts.tableName,SqlTableContracts.TableNameDotPrimaryKey);
				
		if(primaryKeys instanceof Object[]){		
			for(Object item:(Object[])primaryKeys){
				model.setOrFilter(SqlTableContracts.TableNameDotPrimaryKey, new IntFilter(Integer.parseInt(item.toString())));
			}
		} else{
			model.setAndFilter(SqlTableContracts.TableNameDotPrimaryKey, new IntFilter(Integer.parseInt(primaryKeys.toString())));
		}
		model.setResult();
		setModel(model);
		setView((view = new Views.ViewNew(this)));
		view.setTitle("Vertrag editieren");
		setElements();
	}
	
	@Override
	public void setElements() {
		detailsBox = new BoxElementContractDetails(this); 
		detailsBox.setStudentBox(new BoxElementContractDetailsStudent(this));
		detailsBox.setCompanyBox(new BoxElementContractDetailsCompany(this));
		detailsBox.setProfBox(new BoxElementContractDetailsProf(this));
		view.addComponentToView(detailsBox);
		
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToLeftSide(new BoxElementBottomNaviPrevSaveNext(this));
		view.addComponentToView(navi);
	}

	@Override
	public void setElementsForNewData() {
		detailsBox = new BoxElementContractDetails(this,true); 
		detailsBox.setStudentBox(new BoxElementContractDetailsStudentNew(this));
		detailsBox.setCompanyBox(new BoxElementContractDetailsCompanyNew(this));
		detailsBox.setProfBox(new BoxElementContractDetailsProfNew(this));
		
		view.addComponentToView(detailsBox);
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToRightSide(new BoxElementBottomNaviAbortSave(this));
		view.addComponentToView(navi);
	}
	
	@Override
	public void display() {
		view.display();
	}

	@Override
	public String getStringValueForBoxElementEdit(String sqlColumnName) {
		try {
			return model.tableRowData.getStringValueFromPosition(model.rowPosition, sqlColumnName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int getIntValueForBoxElementEdit(String sqlColumnName) {
		try {
			return Integer.parseInt(model.tableRowData.getStringValueFromPosition(model.rowPosition, sqlColumnName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public boolean getBooleanValueForBoxElementEdit(String sqlColumnName) {
		try {
			return model.tableRowData.getBooleanValueFromPosition(model.rowPosition, sqlColumnName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public void buttonAbortClicked() {
		view.dispose();
	}


	@Override
	public void buttonSaveClicked() {
		model.insertIntoDatabase();
		view.modelHasChanged();
	}
	
	@Override
	public void buttonSaveChangesClicked() {
		model.updateDatabase();
	}

	@Override
	public void buttonPreviusClicked() {
		model.previusRow();
		view.modelHasChanged();		
	}


	@Override
	public void buttonNextClicked() {
		model.nextRow();
		view.modelHasChanged();		
	}


	@Override
	public void buttonChangeCompanyOnContractDetailsClicked() {
		Praktikumsverwaltung.addFrameToForeground(new CompanyList(this));	
		
	}


	@Override
	public void buttonEditCompanyOnContractDetailsClicked() {
		Praktikumsverwaltung.addFrameToForeground(new CompanySingle(model.tableRowData.getValueFromPosition(model.rowPosition, "FirmenID")));
	}


	@Override
	public void buttonChangeStudentOnContractClicked() {
		Praktikumsverwaltung.addFrameToForeground(new StudentList(this));		
	}


	@Override
	public void buttonEditStudentOnContractClicked() {
		Praktikumsverwaltung.addFrameToForeground(new StudentSingle(model.tableRowData.getValueFromPosition(model.rowPosition, "Matrikelnr.")));
	}


	@Override
	public void buttonChangeProfOnContractDetailsClicked() {
		Praktikumsverwaltung.addFrameToForeground(new ProfList(this));
		
	}


	@Override
	public void buttonEditProfOnContractDetailsClicked() {
		Praktikumsverwaltung.addFrameToForeground(new ProfSingle(model.tableRowData.getValueFromPosition(model.rowPosition, "Betreuer")));
	}


	@Override
	public void setSelectedValue(String valueName, Object value) {
		if(valueName.equals(SqlTableStudent.PrimaryKey))
			changeStudentInformation(value);
		
		if(valueName.equals(SqlTableCompanies.PrimaryKey))
			changeCompanyInformation(value);
		
		if(valueName.equals(SqlTableProfs.PrimaryKey))
			changeProfInformation(value);
		if(!newData)
			view.modelHasChanged();
	}
	
	private void changeStudentInformation(Object matrikelNr){
		boolean newData = false;
		if(model.tableRowData.getValueFromPosition(model.rowPosition, "Matrikelnr.") instanceof EmptyObject){
			newData = true;
		}
		model.tableRowData.setValueAtPosition(model.rowPosition, SqlTableContracts.FK_Student, matrikelNr);
		StudentSingle studentSingleController = new StudentSingle(matrikelNr);
		try {
			String studentFirstname = studentSingleController.model.tableRowData.getStringValueFromPosition(studentSingleController.model.rowPosition, SqlTableStudent.Vorname);
			model.tableRowData.setValueAtPosition(model.rowPosition, "Vorname", studentFirstname);
			String studentName = studentSingleController.model.tableRowData.getStringValueFromPosition(studentSingleController.model.rowPosition, SqlTableStudent.Nachname);
			model.tableRowData.setValueAtPosition(model.rowPosition, "Nachname", studentName);
			String studentEmail = studentSingleController.model.tableRowData.getStringValueFromPosition(studentSingleController.model.rowPosition, SqlTableStudent.EMail);
			model.tableRowData.setValueAtPosition(model.rowPosition, "E-Mail", studentEmail);
			String studentStudienGruppe = studentSingleController.model.tableRowData.getStringValueFromPosition(studentSingleController.model.rowPosition, SqlTableStudent.Studiengruppe);
			model.tableRowData.setValueAtPosition(model.rowPosition, "Studiengruppe", studentStudienGruppe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(newData){
			detailsBox.setStudentBox(new BoxElementContractDetailsStudent(this));
		}
		
	}
	
	private void changeCompanyInformation(Object companyId){
		boolean newData = false;
		if(model.tableRowData.getValueFromPosition(model.rowPosition, "FirmenID") instanceof EmptyObject){
			newData = true;
		}
		model.tableRowData.setValueAtPosition(model.rowPosition, "FirmenID", companyId);
		CompanySingle studentSingleController = new CompanySingle(companyId);
		try {
			String companyName = studentSingleController.model.tableRowData.getStringValueFromPosition(studentSingleController.model.rowPosition, SqlTableCompanies.Firmenname);
			model.tableRowData.setValueAtPosition(model.rowPosition, "Firmenname", companyName);
			String companyStreet = studentSingleController.model.tableRowData.getStringValueFromPosition(studentSingleController.model.rowPosition, SqlTableCompanies.Strasse);
			model.tableRowData.setValueAtPosition(model.rowPosition, "Strasse", companyStreet);
			String companyZIP = studentSingleController.model.tableRowData.getStringValueFromPosition(studentSingleController.model.rowPosition, SqlTableCompanies.PLZ);
			model.tableRowData.setValueAtPosition(model.rowPosition, "PLZ", companyZIP);
			String companyTown = studentSingleController.model.tableRowData.getStringValueFromPosition(studentSingleController.model.rowPosition, SqlTableCompanies.Ort);
			model.tableRowData.setValueAtPosition(model.rowPosition, "Ort", companyTown);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(newData){
			detailsBox.setCompanyBox(new BoxElementContractDetailsCompany(this));
		}
		
	}
	
	private void changeProfInformation(Object profId){
		boolean newData = false;
		if(model.tableRowData.getValueFromPosition(model.rowPosition, "Betreuer") instanceof EmptyObject){
			newData = true;
		}
		model.tableRowData.setValueAtPosition(model.rowPosition, "Betreuer", profId);
		ProfSingle studentSingleController = new ProfSingle(profId);
		try {
			String profName = studentSingleController.model.tableRowData.getStringValueFromPosition(studentSingleController.model.rowPosition, SqlTableCompanies.Firmenname);
			model.tableRowData.setValueAtPosition(model.rowPosition, "BetreuerName", profName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(newData){
			detailsBox.setProfBox(new BoxElementContractDetailsProf(this));
		}
	}


	@Override
	public void buttonAddProfOnContractClicked() {
		Praktikumsverwaltung.addFrameToForeground(new ProfList(this));
	}


	@Override
	public void buttonAddCompanyOnContractClicked() {
		Praktikumsverwaltung.addFrameToForeground(new CompanyList(this));
	}


	@Override
	public void buttonAddStudentOnContractClicked() {
		Praktikumsverwaltung.addFrameToForeground(new StudentList(this));
	}


}
