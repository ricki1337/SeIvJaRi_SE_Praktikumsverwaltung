package Controller;

import Controller.Interfaces.SelectedValueCallbackCtrl;
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableProfs;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;
import Models.Table.EmptyObject;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.Dialog.OkDialog;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSave;
import Views.GuiElemente.BoxElementBottomNaviMailPrint;
import Views.GuiElemente.BoxElementBottomNaviPrevSaveNext;
import Views.GuiElemente.BoxElementContractDetails;
import Views.GuiElemente.BoxElementContractDetailsCompany;
import Views.GuiElemente.BoxElementContractDetailsCompanyNew;
import Views.GuiElemente.BoxElementContractDetailsProf;
import Views.GuiElemente.BoxElementContractDetailsProfNew;
import Views.GuiElemente.BoxElementContractDetailsStudent;
import Views.GuiElemente.BoxElementContractDetailsStudentNew;
import Views.Interfaces.ContractDetailsCompanyBoxCtrl;
import Views.Interfaces.ContractDetailsCompanyNewBoxCtrl;
import Views.Interfaces.ContractDetailsProfBoxCtrl;
import Views.Interfaces.ContractDetailsProfNewBoxCtrl;
import Views.Interfaces.ContractDetailsStudentBoxCtrl;
import Views.Interfaces.ContractDetailsStudentNewBoxCtrl;
import Views.Interfaces.NaviAbortSaveBoxCtrl;
import Views.Interfaces.NaviMailPrintBoxCtrl;
import Views.Interfaces.NaviPrevSaveNextBoxCtrl;
import Views.Interfaces.EditBoxCtrl;

public class ContractSingle extends Controller implements EditBoxCtrl, 
																NaviAbortSaveBoxCtrl, 
																NaviPrevSaveNextBoxCtrl, 
																ContractDetailsCompanyBoxCtrl, 
																ContractDetailsStudentBoxCtrl, 
																ContractDetailsProfBoxCtrl, 
																SelectedValueCallbackCtrl,
																ContractDetailsStudentNewBoxCtrl,
																ContractDetailsCompanyNewBoxCtrl,
																ContractDetailsProfNewBoxCtrl, 
																NaviMailPrintBoxCtrl
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
										SqlTableContracts.TableNameDotErfolg + " as Erfolg, " +
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
	
	
		private Views.View view;
		private BoxElementContractDetails detailsBox;
		private boolean newData = false;
	
		/**
		 * Initialisiert den Controller für die Anlage eines neuen Vertrags.<br>
		 * Setzt das {@link Model} als leeren Container.<br>
		 * Setzt die View für die Neuanlage und setzt den Fensternamen auf "Vertrag anlegen".
		 */
	public ContractSingle(){
		super();
		newData = true;
		setModel(new Models.Model(SqlTableContracts.tableName,SqlTableContracts.TableNameDotPrimaryKey));
		model.setSrcQuery(srcSqlQuery);
		model.setAndFilter(SqlTableContracts.TableNameDotPrimaryKey, new IntFilter(0));
		model.setResult();
		for(int index=0;index < model.getTableData()[0].length;index++){
			model.setValueAtPosition(model.getRowPosition(), index, new EmptyObject());
		}
		model.setValueAtPosition("Bericht", false);
		model.setValueAtPosition("Typ", new String(" "));
		model.setValueAtPosition("beginnt am", new String(" "));
		model.setValueAtPosition("endet am", new String(" "));
	
		model.setValueAtPosition("Zeugnis", false);
		model.setValueAtPosition("Empfehlung", false);
		
		setView(view = new Views.View(this));
		view.setTitle("Vertrag anlegen");
		setElementsForNewData();
	}
	
	/**
	 * Initialisiert die Ansicht einzelner Verträge auf Grundlage übergebener {@link SqlTableContracts.PrimaryKey}s.<br>
	 * Setzt das Model mit den übergebenen Filtern.<br>
	 * Setzt die View für die Änderung der Daten und setzt den Fensternamen auf "Vertrag editieren".
	 * @param primaryKeys
	 */
	public ContractSingle(Object primaryKeys){
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
		setView((view = new Views.View(this)));
		view.setTitle("Vertrag editieren");
		setElements();
	}
	
	/**
	 * Ändert die Studentendaten im Model auf Grundlage der Auswahl der View.<br>
	 * Wird aufgerufen wenn der Student auf der View ausgetauscht oder neu gesetzt wird.
	 * 
	 * @param matrikelNr	Neue {@link SqlTableContracts.FK_Student} Zuordnung für den Vertrag.
	 */
	private void changeStudentInformation(Object matrikelNr){
		boolean newData = false;
		if(model.getValueFromPosition("Matrikelnr.") instanceof EmptyObject){
			newData = true;
		}
		model.setValueAtPosition(SqlTableContracts.FK_Student, matrikelNr);
		StudentSingle studentSingleController = new StudentSingle(matrikelNr);
		try {
			String studentFirstname = studentSingleController.model.getStringValueFromPosition(SqlTableStudent.Vorname);
			model.setValueAtPosition("Vorname", studentFirstname);
			String studentName = studentSingleController.model.getStringValueFromPosition(SqlTableStudent.Nachname);
			model.setValueAtPosition("Nachname", studentName);
			String studentEmail = studentSingleController.model.getStringValueFromPosition(SqlTableStudent.EMail);
			model.setValueAtPosition("E-Mail", studentEmail);
			String studentStudienGruppe = studentSingleController.model.getStringValueFromPosition(SqlTableStudent.Studiengruppe);
			model.setValueAtPosition("Studiengruppe", studentStudienGruppe);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(newData){
			detailsBox.setStudentBox(new BoxElementContractDetailsStudent(this));
		}
		
	}
	
	/**
	 * Ändert die Firmendaten im Model auf Grundlage der Auswahl der View.<br>
	 * Wird aufgerufen wenn die Firma auf der View ausgetauscht oder neu gesetzt wird.
	 * 
	 * @param companyId	Neue {@link SqlTableContracts.FK_Firma} Zuordnung für den Vertrag.
	 */
	private void changeCompanyInformation(Object companyId){
		boolean newData = false;
		if(model.getValueFromPosition("FirmenID") instanceof EmptyObject){
			newData = true;
		}
		model.setValueAtPosition("FirmenID", companyId);
		CompanySingle studentSingleController = new CompanySingle(companyId);
		try {
			String companyName = studentSingleController.model.getStringValueFromPosition(SqlTableCompanies.Firmenname);
			model.setValueAtPosition("Firmenname", companyName);
			String companyStreet = studentSingleController.model.getStringValueFromPosition(SqlTableCompanies.Strasse);
			model.setValueAtPosition("Strasse", companyStreet);
			String companyZIP = studentSingleController.model.getStringValueFromPosition(SqlTableCompanies.PLZ);
			model.setValueAtPosition("PLZ", companyZIP);
			String companyTown = studentSingleController.model.getStringValueFromPosition(SqlTableCompanies.Ort);
			model.setValueAtPosition("Ort", companyTown);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(newData){
			detailsBox.setCompanyBox(new BoxElementContractDetailsCompany(this));
		}
		
	}
	
	/**
	 * 
	 * Ändert die Betreuerdaten im Model auf Grundlage der Auswahl der View.<br>
	 * Wird aufgerufen wenn der Betreuer auf der View ausgetauscht oder neu gesetzt wird.
	 * @param profId	Neue {@link SqlTableContracts.FK_Betreuer} Zuordnung für den Vertrag.
	 */
	private void changeProfInformation(Object profId){
		boolean newData = false;
		if(model.getValueFromPosition("Betreuer") instanceof EmptyObject){
			newData = true;
		}
		model.setValueAtPosition("Betreuer", profId);
		ProfSingle studentSingleController = new ProfSingle(profId);
		try {
			String profName = studentSingleController.model.getStringValueFromPosition(SqlTableCompanies.Firmenname);
			model.setValueAtPosition("BetreuerName", profName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(newData){
			detailsBox.setProfBox(new BoxElementContractDetailsProf(this));
		}
	}
	
	/**
	 * Prüft ob alle Notwendigen Informationen für einen neuen Datensatz angegeben wurden.
	 * @return	True wenn alle Informationen gesetzt wurden, sonst False
	 */
	protected boolean notAllInformationsAreSet() {
		if(model.getValueFromPosition("Matrikelnr.") instanceof EmptyObject) return true;
		if(model.getValueFromPosition("FirmenID") instanceof EmptyObject) return true;
		if(model.getValueFromPosition("Betreuer") instanceof EmptyObject) return true;
		return false;
	}
	
	@Override
	public void display() {
		view.display();
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
		navi.addBoxToRightSide(new BoxElementBottomNaviMailPrint(this));
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
	public String getStringValueForBoxElementEdit(String sqlColumnName) {
		try {
			return model.getStringValueFromPosition(sqlColumnName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int getIntValueForBoxElementEdit(String sqlColumnName) {
		try {
			return Integer.parseInt(model.getStringValueFromPosition(sqlColumnName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public boolean getBooleanValueForBoxElementEdit(String sqlColumnName) {
		try {
			return model.getBooleanValueFromPosition(sqlColumnName);
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
		if(notAllInformationsAreSet()){
			new OkDialog("Bitte geben Sie einen Betreuer, eine Firma und einen Studenten an.");
			return;
		}
		model.insertIntoDatabase();
		view.modelHasChanged();
		if(newData){
			detailsBox.setStudentBox(new BoxElementContractDetailsStudentNew(this));
			detailsBox.setCompanyBox(new BoxElementContractDetailsCompanyNew(this));
			detailsBox.setProfBox(new BoxElementContractDetailsProfNew(this));
		}
			
	}
	
	

	@Override
	public void buttonSaveChangesClicked() {
		model.updateDatabaseAndInformOtherModels();
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
	public String getCurrentPos() {
		return String.valueOf(model.getRowPosition()+1);
	}


	@Override
	public String getPosSum() {
		return String.valueOf(model.getTableRowCount());
	}


	@Override
	public void buttonChangeCompanyOnContractDetailsClicked() {
		Praktikumsverwaltung.addFrameToForeground(new CompanyList(this));	
		
	}


	@Override
	public void buttonEditCompanyOnContractDetailsClicked() {
		Praktikumsverwaltung.addFrameToForeground(new CompanySingle(model.getValueFromPosition("FirmenID")));
	}
	
	@Override
	public void buttonAddCompanyOnContractClicked() {
		Praktikumsverwaltung.addFrameToForeground(new CompanyList(this));
	}


	@Override
	public void buttonChangeStudentOnContractClicked() {
		Praktikumsverwaltung.addFrameToForeground(new StudentList(this));		
	}


	@Override
	public void buttonEditStudentOnContractClicked() {
		Praktikumsverwaltung.addFrameToForeground(new StudentSingle(model.getValueFromPosition("Matrikelnr.")));
	}

	@Override
	public void buttonAddStudentOnContractClicked() {
		Praktikumsverwaltung.addFrameToForeground(new StudentList(this));
	}

	@Override
	public void buttonChangeProfOnContractDetailsClicked() {
		Praktikumsverwaltung.addFrameToForeground(new ProfList(this));
		
	}


	@Override
	public void buttonEditProfOnContractDetailsClicked() {
		Praktikumsverwaltung.addFrameToForeground(new ProfSingle(model.getValueFromPosition("Betreuer")));
	}
	
	@Override
	public void buttonAddProfOnContractClicked() {
		Praktikumsverwaltung.addFrameToForeground(new ProfList(this));
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
	
	
	@Override
	public void buttonMailToClicked() {
		Mailing newMailing = new Mailing(SqlTableContracts.TableNameDotId,model.getValueFromPosition("ID"));
		Praktikumsverwaltung.addFrameToForeground(newMailing);
	}


	@Override
	public void buttonPrintClicked() {
		Print printDlg = new Print(SqlTableContracts.TableNameDotId,model.getValueFromPosition("ID"));
		printDlg.display();
		//automatischer emailversand
		Mailing newMailing = new Mailing(SqlTableContracts.TableNameDotId,model.getValueFromPosition("ID"));
		Praktikumsverwaltung.addFrameToForeground(newMailing);
		
	}


}
