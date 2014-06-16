package Controller;

import java.util.Map;

import javax.swing.SortOrder;

import Models.Datenbank.SqlTableCompanies;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableProfs;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.IntFilter;
import Models.Filter.StringFilter;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviEditMailPrint;
import Views.GuiElemente.BoxElementBottomNaviMark;
import Views.GuiElemente.BoxElementSearchMenu;
import Views.GuiElemente.BoxElementTable;
import Views.GuiElemente.SearchPanel.BoxElementExtendedSearchContracts;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.ExtendedContractsSearchBoxCtrl;
import Views.Interfaces.ExtendedSearchBoxCtrl;
import Views.Interfaces.NaviEditMailPrintBoxCtrl;
import Views.Interfaces.NaviMarkBoxCtrl;
import Views.Interfaces.SearchBoxCtrl;
import Views.Interfaces.TableBoxCtrl;

public class ContractList extends ControllerNew implements 	BasicBoxCtrl, 
															TableBoxCtrl, 
															SearchBoxCtrl, 
															ExtendedSearchBoxCtrl, 
															NaviEditMailPrintBoxCtrl, 
															NaviMarkBoxCtrl, 
															ExtendedContractsSearchBoxCtrl{
	
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
									SqlTableProfs.TableNameDotName + " as Betreuer, " +
									SqlTableContracts.TableNameDotBericht + " as Bericht, " +
									SqlTableContracts.TableNameDotZeugnis + " as Zeugnis, " +
									SqlTableContracts.TableNameDotEmpfehlung + " as Empfehlung, " +
									SqlTableContracts.TableNameDotErfolg + " as Erfolg " +
									"FROM "+ SqlTableContracts.tableName +
										" JOIN "+ SqlTableStudent.tableName +" ON " +
											SqlTableContracts.TableNameDotFK_Student + " = " + SqlTableStudent.TableNameDotPrimaryKey +
										" JOIN "+ SqlTableCompanies.tableName +" ON " +
										SqlTableContracts.TableNameDotFK_Firma + " = " + SqlTableCompanies.TableNameDotPrimaryKey + 
										" JOIN "+ SqlTableProfs.tableName +" ON " +
										SqlTableContracts.TableNameDotFK_Betreuer + " = " + SqlTableProfs.TableNameDotPrimaryKey;
	
	private Models.ListModel model;
	private Views.ViewNew view;
		
	private BoxElementTable table;
	private BoxElementExtendedSearchContracts extendedSearch;
	private BoxElementSearchMenu searchMenu;
	
	
	private IntFilter successfulInternshipFilter = null;
	private StringFilter internationalInternshipFilter = null;
	
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
		Object contractId;
		if((contractId = table.getColumnValueFromSelectedRow("ID")) != null){
			ContractSingle newFrame = new ContractSingle(contractId);
			Praktikumsverwaltung.addFrameToForeground(newFrame);
		}
	}

	@Override
	public void buttonEditClicked() {
		Object[] contractList;
		if(table.getFlaggedRowCount() != 0)
			contractList = table.getColumnValuesFromFlaggedRows("ID");
		else
			contractList = table.getColumnValuesFromSelectedRows("ID");
		
		ContractSingle newFrame = new ContractSingle(contractList);
		Praktikumsverwaltung.addFrameToForeground(newFrame);
	}

	@Override
	public void buttonMailToClicked() {
		Object[] contractListForMailing;
		if(table.getFlaggedRowCount() != 0)
			contractListForMailing = table.getColumnValuesFromFlaggedRows("ID");
		else
			contractListForMailing = table.getColumnValuesFromSelectedRows("ID");
		
		Mailing newMailing = new Mailing(SqlTableContracts.TableNameDotId,contractListForMailing);
		Praktikumsverwaltung.addFrameToForeground(newMailing);
	}

	@Override
	public void buttonPrintClicked() {
		Object[] contractListForPrint;
		if(table.getFlaggedRowCount() != 0)
			contractListForPrint = table.getColumnValuesFromFlaggedRows("ID");
		else
			contractListForPrint = table.getColumnValuesFromSelectedRows("ID");
		
		Print printDlg = new Print(SqlTableContracts.TableNameDotId,contractListForPrint);
		printDlg.display();
		
		buttonMailToClicked();
	}

	@Override
	public void buttonMarkClicked() {
		table.setFlagOnSelectedRows();
	}

	@Override
	public void buttonSearchSpecificClicked() {
		Map<String,Object> filterElements = extendedSearch.getSearchFilter();
		
		if(internationalInternshipFilter != null)
			filterElements.put(SqlTableContracts.TableNameDotTyp, internationalInternshipFilter);
		
		if(successfulInternshipFilter != null)
			filterElements.put(SqlTableContracts.TableNameDotErfolg, successfulInternshipFilter);
		
		model.setSearchFilter(filterElements);
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
		ContractSingle newEmptyFrame = new ContractSingle();
		Praktikumsverwaltung.addFrameToForeground(newEmptyFrame);
	}

	@Override
	public void setInternationalInternshipFilterOn() {
		internationalInternshipFilter = new StringFilter("PS");
	}

	@Override
	public void setInternationalInternshipFilterOff() {
		internationalInternshipFilter = null;
	}

	@Override
	public void setInternshipSuccessfulFilterOn() {
		successfulInternshipFilter = new IntFilter(1);
		
	}

	@Override
	public void setInternshipSuccessfulFilterOff() {
		successfulInternshipFilter = null;
	}

	@Override
	public void setOrderByColumn(int columnIndex, SortOrder orderDirection) {
		model.setOrder(columnIndex, orderDirection);
	}

	
}
