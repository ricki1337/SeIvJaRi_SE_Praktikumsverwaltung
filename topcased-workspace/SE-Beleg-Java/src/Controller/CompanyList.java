package Controller;

import javax.swing.SortOrder;

import Controller.Interfaces.CallbackSelectedValue;
import Models.Datenbank.SqlTableCompanies;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSelect;
import Views.GuiElemente.BoxElementBottomNaviEdit;
import Views.GuiElemente.BoxElementBottomNaviEditMailPrint;
import Views.GuiElemente.BoxElementBottomNaviMark;
import Views.GuiElemente.BoxElementTable;
import Views.GuiElemente.BoxElementSearchMenu;
import Views.GuiElemente.SearchPanel.BoxElementExtendedSearchCompany;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.NaviAbortSelectBoxCtrl;
import Views.Interfaces.NaviEditBoxCtrl;
import Views.Interfaces.NaviEditMailPrintBoxCtrl;
import Views.Interfaces.NaviMarkBoxCtrl;
import Views.Interfaces.ExtendedSearchBoxCtrl;
import Views.Interfaces.SearchBoxCtrl;
import Views.Interfaces.TableBoxCtrl;

public class CompanyList extends ControllerNew implements 	BasicBoxCtrl, 
															TableBoxCtrl, 
															SearchBoxCtrl, 
															ExtendedSearchBoxCtrl, 
															NaviMarkBoxCtrl, 
															NaviAbortSelectBoxCtrl, 
															NaviEditBoxCtrl{
	
	private String srcSqlQuery = "select " +
										SqlTableCompanies.TableNameDotId + " as ID, " +
										SqlTableCompanies.TableNameDotFirmenname + " as Name, " +
										SqlTableCompanies.TableNameDotStrasse + " as Strasse, " +
										SqlTableCompanies.TableNameDotPLZ + " as PLZ, " +
										SqlTableCompanies.TableNameDotOrt + " as Ort, " +
										SqlTableCompanies.TableNameDotLand + " as Land, " +
										SqlTableCompanies.TableNameDotTelefonnummer + " as 'Tel.', " +
										SqlTableCompanies.TableNameDotBemerkung + " as Bemerkung " +
									"from "+ SqlTableCompanies.tableName;
	
	private Models.ListModel model;
	private Views.ViewNew view;
		
	private BoxElementTable table;
	private BoxElementExtendedSearchCompany extendedSearch;
	private BoxElementSearchMenu searchMenu;
	
	private CallbackSelectedValue callback = null;
	
	
	public CompanyList(){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableCompanies.tableName,SqlTableCompanies.PrimaryKey);
		
		setModel(model);
		setView(view = new Views.ViewNew(this));
		view.setTitle("Firmen");
		setElements();
	}
	
	public CompanyList(CallbackSelectedValue callback){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableCompanies.tableName,SqlTableCompanies.PrimaryKey);
		
		setModel(model);
		setView(view = new Views.ViewNew(this));
		this.callback = callback;
		view.setTitle("Firma auswählen");
		setElementsForCallback();
	}
	
	@Override
	public void setElements(){
		searchMenu = new BoxElementSearchMenu(this);
		view.addComponentToView(searchMenu);
		extendedSearch = new BoxElementExtendedSearchCompany(this);
		extendedSearch.setVisible(false);
		view.addComponentToView(extendedSearch);
		table = new BoxElementTable(this);
		view.addComponentToView(table);
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToRightSide(new BoxElementBottomNaviEdit(this));
		navi.addBoxToLeftSide(new BoxElementBottomNaviMark(this));
		view.addComponentToView(navi);
	}
	
	public void setElementsForCallback(){
		searchMenu = new BoxElementSearchMenu(this);
		view.addComponentToView(searchMenu);
		extendedSearch = new BoxElementExtendedSearchCompany(this);
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
		Object companyId;
		if((companyId = table.getColumnValueFromSelectedRow("ID")) != null){
			CompanySingle newFrame = new CompanySingle(companyId);
			Praktikumsverwaltung.addFrameToForeground(newFrame);
		}
	}

	@Override
	public void buttonEditClicked() {
		Object[] companyList;
		if(table.getFlaggedRowCount() != 0)
			companyList = table.getColumnValuesFromFlaggedRows("ID");
		else
			companyList = table.getColumnValuesFromSelectedRows("ID");
		
			
		CompanySingle newFrame = new CompanySingle(companyList);
		Praktikumsverwaltung.addFrameToForeground(newFrame);
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
		CompanySingle newEmptyFrame = new CompanySingle();
		Praktikumsverwaltung.addFrameToForeground(newEmptyFrame);
	}

	@Override
	public void buttonAbortClicked() {
		view.dispose();
		model.modelClose();
	}

	@Override
	public void buttonSelectClicked() {
		callback.setSelectedValue(SqlTableCompanies.PrimaryKey, getValueFromPosition(table.getSelectedRow(),SqlTableCompanies.PrimaryKey));
		view.dispose();
		model.modelClose();
	}

	@Override
	public void setOrderByColumn(int columnIndex, SortOrder orderDirection) {
		model.setOrder(columnIndex, orderDirection);
	}

	
}
