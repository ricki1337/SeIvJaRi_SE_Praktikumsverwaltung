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
import Models.Datenbank.SqlTableStudent;
import Models.Filter.StringFilter;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.ViewNew;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSelect;
import Views.GuiElemente.BoxElementBottomNaviEditMailPrint;
import Views.GuiElemente.BoxElementBottomNaviMailPrint;
import Views.GuiElemente.BoxElementBottomNaviMark;
import Views.GuiElemente.BoxElementTable;
import Views.GuiElemente.BoxElementSearchMenu;
import Views.GuiElemente.SearchPanel.ExtendedSearchPanel;
import Views.GuiElemente.SearchPanel.ExtendedSearchPanelStudentNew;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.NaviAbortSelectBoxCtrl;
import Views.Interfaces.NaviEditMailPrintBoxCtrl;
import Views.Interfaces.NaviMarkBoxCtrl;
import Views.Interfaces.ExtendedSearchBoxCtrl;
import Views.Interfaces.SearchBoxCtrl;
import Views.Interfaces.TableBoxCtrl;
import Views.Table.TableData;

public class StudentList extends ControllerNew implements BasicBoxCtrl, TableBoxCtrl, SearchBoxCtrl, ExtendedSearchBoxCtrl, NaviEditMailPrintBoxCtrl, NaviMarkBoxCtrl, NaviAbortSelectBoxCtrl{
	
	private String srcSqlQuery = "select " +
									SqlTableStudent.MatrikelNummer + " as Matrikelnr, " +
									SqlTableStudent.Vorname + " as Vorname, " +
									SqlTableStudent.Nachname + " as Nachname, " +
									SqlTableStudent.EMail + " as 'E-Mail', " +
									SqlTableStudent.Studiengruppe + " as Studiengruppe, " +
									SqlTableStudent.Bemerkung + " as Bemerkung " +
								"from " +
									SqlTableStudent.tableName;
	
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
		Object studentId;
		if((studentId = table.getColumnValueFromSelectedRow("Matrikelnr")) != null){
			StudentSingleNew newFrame = new StudentSingleNew(studentId);
			Praktikumsverwaltung.addFrameToForeground(newFrame);
		}
		
	}

	@Override
	public void buttonEditClicked() {
		Object[] studentList = table.getColumnValuesFromSelectedRows("Matrikelnr");
		StudentSingleNew newFrame = new StudentSingleNew(studentList);
		Praktikumsverwaltung.addFrameToForeground(newFrame);
	}

	@Override
	public void buttonMailToClicked() {
		Object[] studentListForMailing = table.getColumnValuesFromSelectedRows("Matrikelnr");
		Mailing newMailing = new Mailing("Matrikelnr",studentListForMailing);
		Praktikumsverwaltung.addFrameToForeground(newMailing);
	}

	@Override
	public void buttonPrintClicked() {
		Object[] studentListForPrint = table.getColumnValuesFromSelectedRows("Matrikelnr");
		Print printDlg = new Print("Matrikelnr",studentListForPrint);
		printDlg.display();
	}

	@Override
	public void buttonSearchSpecificClicked() {
		model.setSearchFilter(extendedSearch.getSearchFilter());		
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
		StudentSingleNew newEmptyFrame = new StudentSingleNew();
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
}