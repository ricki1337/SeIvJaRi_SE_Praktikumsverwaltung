package Controller;

import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableStudent;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviEditMailPrint;
import Views.GuiElemente.BoxElementBottomNaviMark;
import Views.GuiElemente.BoxElementTable;
import Views.GuiElemente.BoxElementSearchMenu;
import Views.GuiElemente.SearchPanel.BoxElementExtendedSearchContracts;

import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.NaviEditMailPrintBoxCtrl;
import Views.Interfaces.NaviMarkBoxCtrl;
import Views.Interfaces.ExtendedSearchBoxCtrl;
import Views.Interfaces.SearchBoxCtrl;
import Views.Interfaces.TableBoxCtrl;

public class ContractList extends ControllerNew implements BasicBoxCtrl, TableBoxCtrl, SearchBoxCtrl, ExtendedSearchBoxCtrl, NaviEditMailPrintBoxCtrl, NaviMarkBoxCtrl{
	
	private String srcSqlQuery = "SELECT " +
									SqlTableContracts.TableNameDotId + " as ID, " +
									SqlTableStudent.TableNameDotMatrikelNummer + " as 'Matrikelnr.', " +
									SqlTableStudent.TableNameDotVorname + " as Vorname, " +
									SqlTableStudent.TableNameDotNachname + " as Nachname, " +
									SqlTableStudent.TableNameDotStudiengruppe + " as Studiengruppe, " +
									SqlTableCompanies.TableNameDotFirmenname + " as Firmenname, " +
									SqlTableContracts.TableNameDotTyp + " as Typ, " +
									SqlTableContracts.TableNameDotBeginn + " as 'beginnt am', " +
									SqlTableContracts.TableNameDotEnde + " as 'endet am', " +
									SqlTableContracts.TableNameDotFK_Betreuer + " as Betreuer, " +
									SqlTableContracts.TableNameDotBericht + " as Bericht, " +
									SqlTableContracts.TableNameDotZeugnis + " as Zeugnis, " +
									SqlTableContracts.TableNameDotEmpfehlung + " as Empfehlung " +
									"FROM "+ SqlTableContracts.tableName +
										" JOIN "+ SqlTableStudent.tableName +" ON " +
											SqlTableContracts.TableNameDotFK_Student + " = " + SqlTableStudent.TableNameDotPrimaryKey +
										" JOIN "+ SqlTableCompanies.tableName +" ON " +
										SqlTableContracts.TableNameDotFK_Firma + " = " + SqlTableCompanies.TableNameDotPrimaryKey;
	
	private Models.ListModel model;
	private Views.ViewNew view;
		
	private BoxElementTable table;
	private BoxElementExtendedSearchContracts extendedSearch;
	private BoxElementSearchMenu searchMenu;
		
	public ContractList(){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableContracts.tableName,SqlTableContracts.PrimaryKey);
		
		setModel(model);
		setView(view = new Views.ViewNew(this));
		view.setTitle("Verträge");
		setElements();
	}
	
	@Override
	public void setElements(){
		searchMenu = new BoxElementSearchMenu(this);
		view.addComponentToView(searchMenu);
		extendedSearch = new BoxElementExtendedSearchContracts(this);
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
		Object contractId;
		if((contractId = table.getColumnValueFromSelectedRow("ID")) != null){
			ContractSingle newFrame = new ContractSingle(contractId);
			Praktikumsverwaltung.addFrameToForeground(newFrame);
		}
	}

	@Override
	public void buttonEditClicked() {
		Object[] contractList = table.getColumnValuesFromSelectedRows("ID");
		ContractSingle newFrame = new ContractSingle(contractList);
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
		System.out.println("huhu");
		ContractSingle newEmptyFrame = new ContractSingle();
		Praktikumsverwaltung.addFrameToForeground(newEmptyFrame);
	}
}
