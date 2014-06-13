package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContacts;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;
import Models.Filter.StringFilter;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSave;
import Views.GuiElemente.BoxElementBottomNaviPrevSaveNext;
import Views.GuiElemente.BoxElementCompanyContactDetails;
import Views.GuiElemente.BoxElementCompanyContactDetailsNav;
import Views.GuiElemente.BoxElementCompanyDetails;
import Views.GuiElemente.BoxElementStudentDetails;
import Views.Interfaces.CompanyContactDetailsBoxCtrl;
import Views.Interfaces.CompanyDetailsContactCtrl;
import Views.Interfaces.NaviAbortSaveBoxCtrl;
import Views.Interfaces.NaviPrevSaveNextBoxCtrl;
import Views.Interfaces.EditBoxCtrl;

public class CompanySingle extends ControllerNew implements 	EditBoxCtrl, 
																NaviAbortSaveBoxCtrl, 
																NaviPrevSaveNextBoxCtrl, 
																CompanyDetailsContactCtrl, 
																CompanyContactDetailsBoxCtrl{
	
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
		
		
		private Views.ViewNew view;
		private Models.Model contactModel;
	
	public CompanySingle(){
		setModel(new Models.Model(SqlTableCompanies.tableName,SqlTableCompanies.PrimaryKey));
		setView(view = new Views.ViewNew(this));
		view.setTitle("Firma anlegen");
		setElementsForNewData();
	}
	
	
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
		setView((view = new Views.ViewNew(this)));
		view.setTitle("Firma editieren");
		
		contactModel = new Models.Model(contactSrcSqlQuery,SqlTableContacts.tableName,SqlTableContacts.TableNameDotPrimaryKey);
		contactModel.setAndFilter(SqlTableContacts.ZuordnungFirma, new StringFilter(getStringValueForBoxElementEdit(SqlTableCompanies.PrimaryKey)));
		contactModel.setResult();
		
		setElements();
	}
	
	@Override
	public void setElements() {
		BoxElementCompanyDetails companyDetailsBox = new BoxElementCompanyDetails(this);
		BoxElementCompanyContactDetails companyContactDetailsBox = new BoxElementCompanyContactDetails(this);
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
	public void buttonCompanyContactNextClicked() {
		contactModel.nextRow();
		view.modelHasChanged();
	}


	@Override
	public void buttonCompanyContactEditClicked() {
		Praktikumsverwaltung.addFrameToForeground(new ContactSingle(contactModel.tableRowData.getValueFromPosition(contactModel.rowPosition, SqlTableContacts.PrimaryKey)));		
	}


	@Override
	public void buttonCompanyContactPreviusClicked() {
		contactModel.previusRow();
		view.modelHasChanged();
	}


	@Override
	public String getStringValueForContactBoxElement(String bemerkung) {
		try {
			return contactModel.tableRowData.getStringValueFromPosition(contactModel.rowPosition, bemerkung);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void buttonCompanyContactAddNewClicked() {
		Praktikumsverwaltung.addFrameToForeground(new ContactSingle(getIntValueForBoxElementEdit(SqlTableCompanies.PrimaryKey)));
	}
}
