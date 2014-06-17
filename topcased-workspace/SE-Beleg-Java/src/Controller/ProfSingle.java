package Controller;

import Models.Datenbank.SqlTableProfs;
import Models.Filter.StringFilter;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSave;
import Views.GuiElemente.BoxElementBottomNaviPrevSaveNext;
import Views.GuiElemente.BoxElementProfDetails;
import Views.Interfaces.NaviAbortSaveBoxCtrl;
import Views.Interfaces.NaviPrevSaveNextBoxCtrl;
import Views.Interfaces.EditBoxCtrl;

public class ProfSingle extends ControllerNew implements EditBoxCtrl, NaviAbortSaveBoxCtrl, NaviPrevSaveNextBoxCtrl{
	
	private String srcSqlQuery = "select " +
									SqlTableProfs.TableNameDotName + " as Name, " +
									SqlTableProfs.TableNameDotId + " as 'E-Mail' " +
								"from " +
									SqlTableProfs.tableName;
	
	
	private Views.ViewNew view;
	
	public ProfSingle(){
		setModel(new Models.Model(SqlTableProfs.tableName,SqlTableProfs.TableNameDotPrimaryKey));
		setView(view = new Views.ViewNew(this));
		view.setTitle("Betreuer anlegen");
		setElementsForNewData();
	}
	
	
	public ProfSingle(Object primaryKeys){
		super();
		
		
		Models.Model model = new Models.Model(srcSqlQuery,SqlTableProfs.tableName,SqlTableProfs.TableNameDotPrimaryKey);
				
		if(primaryKeys instanceof Object[]){		
			for(Object item:(Object[])primaryKeys){
				model.setOrFilter(SqlTableProfs.TableNameDotPrimaryKey, new StringFilter(item.toString()));
			}
		} else{
			model.setAndFilter(SqlTableProfs.TableNameDotPrimaryKey, new StringFilter(primaryKeys.toString()));
		}
		model.setResult();
		setModel(model);
		setView((view = new Views.ViewNew(this)));
		view.setTitle("Betreuer editieren");
		setElements();
	}
	
	@Override
	public void setElements() {
		view.addComponentToView(new BoxElementProfDetails(this));
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToLeftSide(new BoxElementBottomNaviPrevSaveNext(this));
		view.addComponentToView(navi);
	}

	@Override
	public void setElementsForNewData() {
		view.addComponentToView(new BoxElementProfDetails(this,true));
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
		return String.valueOf(model.rowPosition+1);
	}


	@Override
	public String getPosSum() {
		return String.valueOf(model.tableRowData.getRowCount());
	}
}
