package Controller;

import java.util.Map;

import javax.swing.SortOrder;

import Controller.Interfaces.CallbackSelectedValue;
import Models.Datenbank.SqlTableContracts;
import Models.Datenbank.SqlTableStudent;
import Models.Filter.SqlListFilter;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.Dialog.OkDialog;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSelect;
import Views.GuiElemente.BoxElementBottomNaviEditMailPrint;
import Views.GuiElemente.BoxElementBottomNaviMark;
import Views.GuiElemente.BoxElementTable;
import Views.GuiElemente.BoxElementSearchMenu;
import Views.GuiElemente.SearchPanel.ExtendedSearchPanelStudentNew;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.ExtendedStudentSearchBoxCtrl;
import Views.Interfaces.NaviAbortSelectBoxCtrl;
import Views.Interfaces.NaviEditMailPrintBoxCtrl;
import Views.Interfaces.NaviMarkBoxCtrl;
import Views.Interfaces.ExtendedSearchBoxCtrl;
import Views.Interfaces.SearchBoxCtrl;
import Views.Interfaces.TableBoxCtrl;

public class StudentList extends ControllerNew implements	BasicBoxCtrl, 
															TableBoxCtrl, 
															SearchBoxCtrl, 
															ExtendedSearchBoxCtrl, 
															NaviEditMailPrintBoxCtrl, 
															NaviMarkBoxCtrl, 
															NaviAbortSelectBoxCtrl, 
															ExtendedStudentSearchBoxCtrl{
	
	private String srcSqlQuery = "select " +
									SqlTableStudent.TableNameDotMatrikelNummer + " as Matrikelnr, " +
									SqlTableStudent.TableNameDotVorname + " as Vorname, " +
									SqlTableStudent.TableNameDotNachname + " as Nachname, " +
									SqlTableStudent.TableNameDotEMail + " as 'E-Mail', " +
									SqlTableStudent.TableNameDotStudiengruppe + " as Studiengruppe, " +
									SqlTableStudent.TableNameDotBemerkung + " as Bemerkung, " +
									"CASE (SELECT count(*) FROM "+ SqlTableContracts.tableNameWithAlias + " WHERE " + SqlTableContracts.TableNameDotFK_Student + " = " + SqlTableStudent.TableNameDotMatrikelNummer +") " +
										"WHEN '0' THEN CAST(0 as CHAR(1)) " +
										"ELSE CAST(1 as CHAR(1)) " +
									"END as Vertrag " +
 
								"from " +
									SqlTableStudent.tableNameWithAlias;
	
	private SqlListFilter hasNoContractFilter = null;
	private SqlListFilter hasContractFilter = null;
	private String srcSqlQueryContract = "SELECT " +
											SqlTableContracts.TableNameDotFK_Student + 
										" FROM " + 
											SqlTableContracts.tableNameWithAlias + 
										" WHERE " + SqlTableContracts.TableNameDotFK_Student + " IS NOT NULL";
	
	
	private Models.ListModel model;
	private Views.ViewNew view;
		
	private BoxElementTable table;
	private BoxElementSearchMenu searchMenu;
	private ExtendedSearchPanelStudentNew extendedSearch;
	
	private CallbackSelectedValue callback = null;
	
	public StudentList(){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableStudent.tableName,SqlTableStudent.PrimaryKey);
		
		setModel(model);
		setView(view = new Views.ViewNew(this));
		view.setTitle("Studenten");
		setElements();
	}
	
	public StudentList(CallbackSelectedValue callback){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableStudent.tableName,SqlTableStudent.PrimaryKey);
		setModel(model);
		setView(view = new Views.ViewNew(this));
		view.setTitle("Student auswählen");
		this.callback = callback;
		setElementsForCallback();
	}
	
	
	public void setElementsForCallback() {
		searchMenu = new BoxElementSearchMenu(this);
		view.addComponentToView(searchMenu);
		extendedSearch = new ExtendedSearchPanelStudentNew(this);
		extendedSearch.setVisible(false);
		view.addComponentToView(extendedSearch);
		table = new Views.GuiElemente.BoxElementTable(this);
		//TODO
		//tabellenfunktion zum einstellen, das nur ein datensatz auswählbar ist
		view.addComponentToView(table);
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToRightSide(new BoxElementBottomNaviAbortSelect(this));
		view.addComponentToView(navi);
	}

	@Override
	public void setElements(){
		searchMenu = new BoxElementSearchMenu(this);
		view.addComponentToView(searchMenu);
		extendedSearch = new ExtendedSearchPanelStudentNew(this);
		extendedSearch.setVisible(false);
		view.addComponentToView(extendedSearch);
		table = new Views.GuiElemente.BoxElementTable(this);
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
		Object studentId;
		if((studentId = table.getColumnValueFromSelectedRow("Matrikelnr")) != null){
			StudentSingle newFrame = new StudentSingle(studentId);
			Praktikumsverwaltung.addFrameToForeground(newFrame);
		}
		
	}

	@Override
	public void buttonEditClicked() {
		Object[] studentList;
		
		if(table.getFlaggedRowCount() != 0)
			studentList = table.getColumnValuesFromFlaggedRows("Matrikelnr");
		else
			studentList = table.getColumnValuesFromSelectedRows("Matrikelnr");

		if(studentList.length==0){
			OkDialog okdialog = new OkDialog(Praktikumsverwaltung.getFrame(),true, "Bitte w\u00E4hlen Sie mindestens einen Eintrag aus.");
			return;
		}
		
		
		StudentSingle newFrame = new StudentSingle(studentList);
		Praktikumsverwaltung.addFrameToForeground(newFrame);
	}

	@Override
	public void buttonMailToClicked() {
		Object[] studentListForMailing;
		
		if(table.getFlaggedRowCount() != 0){ //per checkbox selektierte Zeilen
			studentListForMailing = table.getColumnValuesFromFlaggedRows("Matrikelnr");
			System.out.println("flagged: " + table.getFlaggedRowCount());}
		
		else //per Mousedrag ausgewählte Zeilen
			studentListForMailing = table.getColumnValuesFromSelectedRows("Matrikelnr");
			System.out.println("selectedrows length: " + studentListForMailing.length);
		
		if(studentListForMailing.length==0){
			OkDialog okdialog = new OkDialog(Praktikumsverwaltung.getFrame(),true, "Bitte w\u00E4hlen Sie mindestens einen Eintrag aus.");
			return;
		}
		
		
		Mailing newMailing = new Mailing(SqlTableStudent.TableNameDotPrimaryKey,studentListForMailing);
		Praktikumsverwaltung.addFrameToForeground(newMailing);
	}

	@Override
	public void buttonPrintClicked() {
		Object[] studentListForPrint;
		if(table.getFlaggedRowCount() != 0)
			studentListForPrint = table.getColumnValuesFromFlaggedRows("Matrikelnr");
		else
			studentListForPrint = table.getColumnValuesFromSelectedRows("Matrikelnr");
		
		if(studentListForPrint.length == 0){
			OkDialog okdialog = new OkDialog(Praktikumsverwaltung.getFrame(),true, "Bitte w\u00E4hlen Sie mindestens einen Eintrag aus.");
			return;
			}
		
		Print printDlg = new Print(SqlTableStudent.TableNameDotPrimaryKey,studentListForPrint);
		printDlg.display();
	}

	@Override
	public void buttonSearchSpecificClicked() {
		
		Map<String,Object> filterElements = extendedSearch.getSearchFilter();
		
		if(hasNoContractFilter != null)
			filterElements.put(SqlTableStudent.TableNameDotMatrikelNummer, hasNoContractFilter);
		
		if(hasContractFilter != null)
			filterElements.put(SqlTableStudent.TableNameDotMatrikelNummer, hasContractFilter);
		
		
		model.setSearchFilter(filterElements);		
	}

	@Override
	public void buttonDeleteFilterClicked() {
		model.deleteSearchFilter();
		model.setResult();
		extendedSearch.clearFields();
		view.modelHasChanged();
	}

	@Override
	public void buttonMarkClicked() {
		table.setFlagOnSelectedRows();
	}

	@Override
	public void searchFieldChanged() {
		String valueOfSearchField = searchMenu.getValueOfSearchField();
		if(valueOfSearchField.length() == 0)
			((Models.ListModel)model).deleteSearchFilter();
		else
			((Models.ListModel)model).setSearchFilter(valueOfSearchField);
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
		StudentSingle newEmptyFrame = new StudentSingle();
		Praktikumsverwaltung.addFrameToForeground(newEmptyFrame);
	}

	@Override
	public void buttonAbortClicked() {
		view.dispose();
		model.modelClose();
	}

	@Override
	public void buttonSelectClicked() {
		callback.setSelectedValue(SqlTableStudent.PrimaryKey, getValueFromPosition(table.getSelectedRow(),SqlTableStudent.PrimaryKey));
		view.dispose();
		model.modelClose();
		
	}

	@Override
	public void setHasNoContractFilterOff() {
		hasNoContractFilter = null;
	}

	@Override
	public void setHasNoContractFilterOn() {
		hasNoContractFilter = new SqlListFilter(srcSqlQueryContract, false);
	}

	@Override
	public void setHasContractFilterOn() {
		hasContractFilter = new SqlListFilter(srcSqlQueryContract, true);
	}

	@Override
	public void setHasContractFilterOff() {
		hasContractFilter = null;
	}

	@Override
	public void setOrderByColumn(int columnIndex, SortOrder orderDirection) {
		model.setOrder(columnIndex, orderDirection);
	}

	
}