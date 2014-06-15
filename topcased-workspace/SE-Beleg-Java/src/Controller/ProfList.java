package Controller;

import javax.swing.SortOrder;

import Controller.Interfaces.CallbackSelectedValue;
import Models.Datenbank.SqlTableProfs;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSelect;
import Views.GuiElemente.BoxElementBottomNaviEditMailPrint;
import Views.GuiElemente.BoxElementBottomNaviMark;
import Views.GuiElemente.BoxElementTable;
import Views.GuiElemente.BoxElementSearchMenu;
import Views.GuiElemente.SearchPanel.BoxElementExtendedSearchProf;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.NaviAbortSelectBoxCtrl;
import Views.Interfaces.NaviEditMailPrintBoxCtrl;
import Views.Interfaces.NaviMarkBoxCtrl;
import Views.Interfaces.ExtendedSearchBoxCtrl;
import Views.Interfaces.SearchBoxCtrl;
import Views.Interfaces.TableBoxCtrl;

public class ProfList extends ControllerNew implements BasicBoxCtrl, 
														TableBoxCtrl, 
														SearchBoxCtrl, 
														ExtendedSearchBoxCtrl, 
														NaviEditMailPrintBoxCtrl, 
														NaviMarkBoxCtrl, NaviAbortSelectBoxCtrl{
	
	private String srcSqlQuery = "select " +
									SqlTableProfs.Name + " as Name, " +
									SqlTableProfs.Id + " as 'E-Mail' " +
								"from " +
									SqlTableProfs.tableName;
	
	private Models.ListModel model;
	private Views.ViewNew view;
		
	private BoxElementTable table;
	private BoxElementExtendedSearchProf extendedSearch;
	private BoxElementSearchMenu searchMenu;
	
	private CallbackSelectedValue callback = null;
	
	
	public ProfList(){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableProfs.tableName,SqlTableProfs.PrimaryKey);
		
		setModel(model);
		setView(view = new Views.ViewNew(this));
		view.setTitle("Betreuer");
		setElements();
	}
	
	public ProfList(CallbackSelectedValue callback){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableProfs.tableName,SqlTableProfs.PrimaryKey);
		
		setModel(model);
		setView(view = new Views.ViewNew(this));
		this.callback = callback;
		view.setTitle("Betreuer auswählen");
		setElementsForCallback();
	}
	
	@Override
	public void setElements(){
		searchMenu = new BoxElementSearchMenu(this);
		view.addComponentToView(searchMenu);
		extendedSearch = new BoxElementExtendedSearchProf(this);
		extendedSearch.setVisible(false);
		view.addComponentToView(extendedSearch);
		table = new BoxElementTable(this);
		view.addComponentToView(table);
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToRightSide(new BoxElementBottomNaviEditMailPrint(this));
		navi.addBoxToLeftSide(new BoxElementBottomNaviMark(this));
		view.addComponentToView(navi);
	}
	
	public void setElementsForCallback(){
		searchMenu = new BoxElementSearchMenu(this);
		view.addComponentToView(searchMenu);
		extendedSearch = new BoxElementExtendedSearchProf(this);
		extendedSearch.setVisible(false);
		view.addComponentToView(extendedSearch);
		table = new BoxElementTable(this);
		view.addComponentToView(table);
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToRightSide(new BoxElementBottomNaviAbortSelect(this));
		view.addComponentToView(navi);
	}
	
	@Override
	public void display() {
		view.display();
	}

	
	@Override
	public Object[][] getTableData() {
		
		return model.tableRowData.getTableData();
	}

	@Override
	public Object[] getTableHeader() {
		return model.tableRowData.getColumnAliasNames();
	}

	@Override
	public Object getValueFromPosition(int row, String column) {
		return model.tableRowData.getValueFromPosition(row, column);
	}

	@Override
	public void setValueAtPosition(int row, String column, Object value) {
		model.tableRowData.setValueAtPosition(row, column, value);
	}

	@Override
	public int getColumnAliasIndex(String columnName) {
		return model.tableRowData.getColumnAliasIndex(columnName);
	}


	@Override
	public int getSqlRecordLimit() {
		return model.getColumnLimit();
	}

	@Override
	public void tableRowClicked() {
		table.setFlag();
	}

	@Override
	public void tableRowDoubleClicked() {
		Object profId;
		if((profId = table.getColumnValueFromSelectedRow("E-Mail")) != null){
			ProfSingle newFrame = new ProfSingle(profId);
			Praktikumsverwaltung.addFrameToForeground(newFrame);
		}
	}

	@Override
	public void buttonEditClicked() {
		Object[] profList = table.getColumnValuesFromSelectedRows("E-Mail");
		ProfSingle newFrame = new ProfSingle(profList);
		Praktikumsverwaltung.addFrameToForeground(newFrame);
	}

	@Override
	public void buttonMailToClicked() {
		// TODO
		
	}

	@Override
	public void buttonPrintClicked() {
		// TODO
		
	}

	@Override
	public void buttonMarkClicked() {
		table.setFlagOnSelectedRows();
	}

	@Override
	public void buttonSearchSpecificClicked() {
		model.setSearchFilter(extendedSearch.getSearchFilter());
	}

	@Override
	public void buttonDeleteFilterClicked() {
		model.deleteSearchFilter();
		model.setResult();
		view.modelHasChanged();
		extendedSearch.clearFields();
	}

	@Override
	public void searchFieldChanged() {
		((Models.ListModel)model).setSearchFilter(searchMenu.getValueOfSearchField());
	}

	@Override
	public void RecordLimitFieldChanged() {
		model.setColumnLimitAndRefreshViews(searchMenu.getValueOfRecordLimitField());
	}

	@Override
	public void buttonShowExtendedSearchClicked() {
		boolean isVisible = extendedSearch.isVisible();
		extendedSearch.setVisible(!isVisible);
		view.getLayout().layoutContainer(view);
		view.pack();
	}

	@Override
	public void buttonAddNewDataClicked() {
		ProfSingle newEmptyFrame = new ProfSingle();
		Praktikumsverwaltung.addFrameToForeground(newEmptyFrame);
	}

	@Override
	public void buttonAbortClicked() {
		view.dispose();
		model.modelClose();
	}

	@Override
	public void buttonSelectClicked() {
		callback.setSelectedValue(SqlTableProfs.PrimaryKey, getValueFromPosition(table.getSelectedRow(),SqlTableProfs.PrimaryKey));
		view.dispose();
		model.modelClose();
	}

	@Override
	public void setOrderByColumn(int columnIndex, SortOrder orderDirection) {
		model.setOrder(columnIndex, orderDirection);
	}

	
}
