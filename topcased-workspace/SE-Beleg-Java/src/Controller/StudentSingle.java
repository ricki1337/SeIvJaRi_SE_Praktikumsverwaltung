package Controller;

import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSave;
import Views.GuiElemente.BoxElementBottomNaviPrevSaveNext;
import Views.GuiElemente.BoxElementStudentDetails;
import Views.Interfaces.NaviAbortSaveBoxCtrl;
import Views.Interfaces.NaviPrevSaveNextBoxCtrl;
import Views.Interfaces.EditBoxCtrl;

public class StudentSingle extends ControllerNew implements EditBoxCtrl, NaviAbortSaveBoxCtrl, NaviPrevSaveNextBoxCtrl{
	
	private String srcSqlQuery = "select " +
									SqlTableStudent.MatrikelNummer + " as Matrikelnr, " +
									SqlTableStudent.Vorname + " as Vorname, " +
									SqlTableStudent.Nachname + " as Nachname, " +
									SqlTableStudent.EMail + " as 'E-Mail', " +
									SqlTableStudent.Studiengruppe + " as Studiengruppe, " +
									SqlTableStudent.Bemerkung + " as Bemerkung " +
								"from " +
									SqlTableStudent.tableName;
	
	
	private Views.ViewNew view;
	
	public StudentSingle(){
		setModel(new Models.Model(SqlTableStudent.tableName,SqlTableStudent.TableNameDotPrimaryKey));
		setView(view = new Views.ViewNew(this));
		view.setTitle("Student anlegen");
		setElementsForNewData();
	}
	
	
	public StudentSingle(Object primaryKeys){
		super();
		
		
		Models.Model model = new Models.Model(srcSqlQuery,SqlTableStudent.tableName,SqlTableStudent.TableNameDotPrimaryKey);
				
		if(primaryKeys instanceof Object[]){		
			for(Object item:(Object[])primaryKeys){
				model.setOrFilter(SqlTableStudent.TableNameDotMatrikelNummer, new IntFilter(Integer.parseInt(item.toString())));
			}
		} else{
			model.setAndFilter(SqlTableStudent.TableNameDotMatrikelNummer, new IntFilter(Integer.parseInt(primaryKeys.toString())));
		}
		model.setResult();
		setModel(model);
		setView((view = new Views.ViewNew(this)));
		view.setTitle("Student editieren");
		setElements();
	}
	
	@Override
	public void setElements() {
		view.addComponentToView(new BoxElementStudentDetails(this));
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToLeftSide(new BoxElementBottomNaviPrevSaveNext(this));
		view.addComponentToView(navi);
	}

	@Override
	public void setElementsForNewData() {
		view.addComponentToView(new BoxElementStudentDetails(this,true));
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
	public String getCurrentPos() {
		return String.valueOf(model.rowPosition+1);
	}


	@Override
	public String getPosSum() {
		return String.valueOf(model.tableRowData.getRowCount());
	}
}
