package Controller;

import Controller.Interfaces.SelectedValueCallbackCtrl;
import Models.Datenbank.SqlTableContacts;
import Models.Filter.StringFilter;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSave;
import Views.GuiElemente.BoxElementBottomNaviPrevSaveNext;
import Views.GuiElemente.BoxElementContactDetails;
import Views.Interfaces.NaviAbortSaveBoxCtrl;
import Views.Interfaces.NaviPrevSaveNextBoxCtrl;
import Views.Interfaces.EditBoxCtrl;

public class ContactSingle extends Controller implements 	EditBoxCtrl, 
															NaviAbortSaveBoxCtrl, 
															NaviPrevSaveNextBoxCtrl {
	
	private String srcSqlQuery = "select " +
									SqlTableContacts.TableNameDotId + " as ID, " +
									SqlTableContacts.TableNameDotName + " as Name, " +
									SqlTableContacts.TableNameDotTelefonnummer + " as 'Tel.', "+
									SqlTableContacts.TableNameDotBemerkung + " as Bemerkung, "+
									SqlTableContacts.TableNameDotZuordnungFirma + " as FirmenID " +
								" from " +
									SqlTableContacts.tableName;
	
	private SelectedValueCallbackCtrl cs;
	private Views.View view;
	private int companyId = -1;

	/**
	 * Initialisiert die Einzelansicht der Ansprechpartner einer Firma für die Neuanlage.<br>
	 * Übernimmt die zugehörige Firmen ID.
	 * 
	 * @param companyId	Zuordnung des Neuen Ansprechpartners zur Firma.
	 */
	public ContactSingle(int companyId){
		super();
		this.companyId = companyId;
		setModel(new Models.Model(SqlTableContacts.tableName,SqlTableContacts.TableNameDotPrimaryKey));
		setView(view = new Views.View(this));
		view.setTitle("Ansprechpartner anlegen");
		setElementsForNewData();
	}
	
	/**
	 * Initialisiert die Ansicht der Ansprechpartner einer Firma zur Bearbeitung.<br>
	 * Übernimmt eine Liste von {@link SqlTableContacts.PrimaryKey}.
	 * @param primaryKeys	{@link SqlTableContacts.PrimaryKey}'s zur Bearbeitung.
	 */
	public ContactSingle(Object primaryKeys){
		super();

		Models.Model model = new Models.Model(srcSqlQuery,SqlTableContacts.tableName,SqlTableContacts.TableNameDotPrimaryKey);
				

		if(primaryKeys instanceof Object[]){		
			for(Object item:(Object[])primaryKeys){
				model.setOrFilter(SqlTableContacts.PrimaryKey, new StringFilter(item.toString()));
			}
		} else{
			model.setAndFilter(SqlTableContacts.PrimaryKey, new StringFilter(primaryKeys.toString()));
		}
		model.setResult();
		setModel(model);
		setView((view = new Views.View(this)));
		view.setTitle("Ansprechpartner editieren");
		setElements();
	}
	
	/**
	 * Setzen der Callbackfunktion, welche nach der Neuanlage von Datensätzen aufgerufen wird,<br>
	 * um einen anderen Controller zu informieren.
	 * 
	 * @param cs	Controller, welcher {@link SelectedValueCallbackCtrl} implementiert.
	 */
	public void setCallbackvalueController(SelectedValueCallbackCtrl cs){
		this.cs = cs;
	}
	
	@Override
	public void display() {
		view.display();
	}

	@Override
	public void setElements() {
		view.addComponentToView(new BoxElementContactDetails(this));
		
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToLeftSide(new BoxElementBottomNaviPrevSaveNext(this));
		view.addComponentToView(navi);
	}

	@Override
	public void setElementsForNewData() {
		view.addComponentToView(new BoxElementContactDetails(this,true,companyId));
		
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToRightSide(new BoxElementBottomNaviAbortSave(this));
		view.addComponentToView(navi);
	}
	
	@Override
	public String getStringValueForBoxElementEdit(String sqlColumnName) {
		try {
			return model.getStringValueFromPosition(model.getRowPosition(), sqlColumnName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int getIntValueForBoxElementEdit(String sqlColumnName) {
		try {
			return Integer.parseInt(model.getStringValueFromPosition(model.getRowPosition(), sqlColumnName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public boolean getBooleanValueForBoxElementEdit(String sqlColumnName) {
		try {
			return model.getBooleanValueFromPosition(model.getRowPosition(), sqlColumnName);
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
		cs.setSelectedValue(null, null);
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
	
}
