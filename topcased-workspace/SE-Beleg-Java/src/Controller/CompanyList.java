package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import Controller.Interfaces.CallbackSelectedValue;
import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableProfs;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.StringFilter;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.ViewNew;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviEditMailPrint;
import Views.GuiElemente.BoxElementBottomNaviMark;
import Views.GuiElemente.BoxElementTable;
import Views.GuiElemente.BoxElementSearchMenu;
import Views.GuiElemente.SearchPanel.BoxElementExtendedSearchProf;
import Views.GuiElemente.SearchPanel.ExtendedSearchPanel;
import Views.GuiElemente.SearchPanel.ExtendedSearchPanelStudentNew;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.NaviEditMailPrintBoxCtrl;
import Views.Interfaces.NaviMarkBoxCtrl;
import Views.Interfaces.ExtendedSearchBoxCtrl;
import Views.Interfaces.SearchBoxCtrl;
import Views.Interfaces.TableBoxCtrl;
import Views.Table.TableData;

public class CompanyList extends ControllerNew implements BasicBoxCtrl, TableBoxCtrl, SearchBoxCtrl, ExtendedSearchBoxCtrl, NaviEditMailPrintBoxCtrl, NaviMarkBoxCtrl{
	
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
	private BoxElementExtendedSearchProf extendedSearch;
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
		setElements();
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
		return model.tableRowData.getColumnNames();
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
		Object[] companyList = table.getColumnValuesFromSelectedRows("ID");
		CompanySingle newFrame = new CompanySingle(companyList);
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
		table.setFlagOnSelectedRow();
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
		CompanieEmptySingle newEmptyFrame = new CompanieEmptySingle();
		Praktikumsverwaltung.addFrameToForeground(newEmptyFrame);
	}
}
