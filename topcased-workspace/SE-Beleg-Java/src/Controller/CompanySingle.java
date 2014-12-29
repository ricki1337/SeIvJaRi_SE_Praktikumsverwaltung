package Controller;

import Controller.Interfaces.SelectedValueCallbackCtrl;
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContacts;
import Models.Filter.IntFilter;
import Models.Filter.StringFilter;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSave;
import Views.GuiElemente.BoxElementBottomNaviPrevSaveNext;
import Views.GuiElemente.BoxElementCompanyContactDetails;
import Views.GuiElemente.BoxElementCompanyContactDetailsNav;
import Views.GuiElemente.BoxElementCompanyDetails;
import Views.Interfaces.CompanyContactDetailsBoxCtrl;
import Views.Interfaces.CompanyContactDetailsBoxNavCtrl;
import Views.Interfaces.EditBoxCtrl;
import Views.Interfaces.NaviAbortSaveBoxCtrl;
import Views.Interfaces.NaviPrevSaveNextBoxCtrl;

public class CompanySingle extends Controller implements 	EditBoxCtrl, 
																NaviAbortSaveBoxCtrl, 
																NaviPrevSaveNextBoxCtrl, 
																SelectedValueCallbackCtrl,
																CompanyContactDetailsBoxCtrl,
																CompanyContactDetailsBoxNavCtrl{
	
		private String srcSqlQuery = "select " +
										SqlTableCompanies.Id + " as ID, " +
										SqlTableCompanies.Firmenname + " as Name, " +
										SqlTableCompanies.Strasse + " as Strasse, " +
										SqlTableCompanies.PLZ + " as PLZ, " +
										SqlTableCompanies.Ort + " as Ort, " +
										SqlTableCompanies.Land + " as Land, " +
										SqlTableCompanies.Telefonnummer + " as 'Tel.', " +
										SqlTableCompanies.Bemerkung + " as Bemerkung " +
									"from "+ SqlTableCompanies.tableName;
		
		private String contactSrcSqlQuery = "select " +
											SqlTableContacts.Id + " as ID, " +
											SqlTableContacts.Name + " as Name, " +
											SqlTableContacts.Telefonnummer + " as 'Tel.', "+
											SqlTableContacts.Bemerkung + " as Bemerkung, "+
											SqlTableContacts.ZuordnungFirma + " as FirmenID " +
										" from " +
											SqlTableContacts.tableName;
		
		
		private Views.View view;
		private Models.Model contactModel;
		private BoxElementCompanyContactDetails companyContactDetailsBox;
	
	/**
	 * Initialisiert die Einzelansicht einer Firma für die Neuanlage.<br>
	 * Erstellt das Datenmodel.<br>
	 * Erstellt die View und legt den Namen des Fensters mit "Firma anlegen" fest.
	 */
	public CompanySingle(){
		setModel(new Models.Model(SqlTableCompanies.tableName,SqlTableCompanies.PrimaryKey));
		setView(view = new Views.View(this));
		model.setView(view);
		view.setModel(model);
		view.setTitle("Firma anlegen");
		setElementsForNewData();
	}
	
	/**
	 * Initialisiert die Einzelansicht einer Firma für ausgewählte Firmen.<br>
	 * Erstellt das Datenmodel und legt die übergebenen Filter an.<br>
	 * Erstellt das Model für die Anzeige der Ansprechpartner auf Grundlage der jeweiligen Firmen ID.<br>
	 * Erstellt die View und legt den Namen des Fensters mit "Firma editieren" fest.
	 */
	public CompanySingle(Object primaryKeys){
		super();
				
		Models.Model model = new Models.Model(srcSqlQuery,SqlTableCompanies.tableName,SqlTableCompanies.PrimaryKey);
		
		if(primaryKeys instanceof Object[]){		
			for(Object item:(Object[])primaryKeys){
				model.setOrFilter(SqlTableCompanies.Id, new IntFilter(Integer.parseInt(item.toString())));
			}
		} else{
			model.setAndFilter(SqlTableCompanies.Id, new IntFilter(Integer.parseInt(primaryKeys.toString())));
		}
		model.setResult();
		setModel(model);
		setView((view = new Views.View(this)));
		model.setView(view);
		view.setModel(model);
		view.setTitle("Firma editieren");
		
		contactModel = new Models.Model(contactSrcSqlQuery,SqlTableContacts.tableName,SqlTableContacts.TableNameDotPrimaryKey);
		contactModel.setAndFilter(SqlTableContacts.ZuordnungFirma, new StringFilter(getStringValueForBoxElementEdit(SqlTableCompanies.PrimaryKey)));
		contactModel.setResult();
		
		setElements();
	}

	@Override
	public void display() {
		view.display();
	}
	
	@Override
	public void setElements() {
		BoxElementCompanyDetails companyDetailsBox = new BoxElementCompanyDetails(this);
		companyContactDetailsBox = new BoxElementCompanyContactDetails(this);
		companyContactDetailsBox.setContactNaviBox(new BoxElementCompanyContactDetailsNav(this));
		companyDetailsBox.setContactBox(companyContactDetailsBox);
		view.addComponentToView(companyDetailsBox);
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToLeftSide(new BoxElementBottomNaviPrevSaveNext(this));
		view.addComponentToView(navi);
	}

	@Override
	public void setElementsForNewData() {
		view.addComponentToView(new BoxElementCompanyDetails(this,true));
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
		model.insertIntoDatabase();
		view.modelHasChanged();
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
	public void buttonCompanyContactNextClicked() {
		contactModel.nextRow();
		view.modelHasChanged();
	}


	@Override
	public void buttonCompanyContactEditClicked() {
		Praktikumsverwaltung.addFrameToForeground(new ContactSingle(contactModel.getValueFromPosition(SqlTableContacts.PrimaryKey)));		
	}


	@Override
	public void buttonCompanyContactPreviusClicked() {
		contactModel.previusRow();
		view.modelHasChanged();
	}


	@Override
	public String getStringValueForContactBoxElement(String bemerkung) {
		try {
			return contactModel.getStringValueFromPosition(bemerkung);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void buttonCompanyContactAddNewClicked() {
		ContactSingle cs = new ContactSingle(getIntValueForBoxElementEdit(SqlTableCompanies.PrimaryKey));
		Praktikumsverwaltung.addFrameToForeground(cs);
		cs.setCallbackvalueController(this);
	}


	
	@Override
	public void setSelectedValue(String valueName, Object value) {
		companyContactDetailsBox.refreshContent();
		
	}

}
