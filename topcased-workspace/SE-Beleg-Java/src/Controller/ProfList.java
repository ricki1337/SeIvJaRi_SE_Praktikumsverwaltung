package Controller;

import javax.swing.SortOrder;

import Controller.Interfaces.SelectedValueCallbackCtrl;
import Models.Datenbank.SqlTableProfs;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.Dialog.OkDialog;
import Views.GuiElemente.BoxElementBottomNavi;
import Views.GuiElemente.BoxElementBottomNaviAbortSelect;
import Views.GuiElemente.BoxElementBottomNaviEdit;
import Views.GuiElemente.BoxElementBottomNaviMark;
import Views.GuiElemente.BoxElementTable;
import Views.GuiElemente.BoxElementSearchMenu;
import Views.GuiElemente.SearchPanel.BoxElementExtendedSearchProf;
import Views.Interfaces.BasicBoxCtrl;
import Views.Interfaces.NaviAbortSelectBoxCtrl;
import Views.Interfaces.NaviEditBoxCtrl;
import Views.Interfaces.NaviMarkBoxCtrl;
import Views.Interfaces.ExtendedSearchBoxCtrl;
import Views.Interfaces.SearchBoxCtrl;
import Views.Interfaces.TableBoxCtrl;

/**
 * Verwaltet die Listenansicht der Betreuer.
 */
public class ProfList extends Controller implements BasicBoxCtrl, 
														TableBoxCtrl, 
														SearchBoxCtrl, 
														ExtendedSearchBoxCtrl, 
														NaviEditBoxCtrl, 
														NaviMarkBoxCtrl, 
														NaviAbortSelectBoxCtrl{
	
	private String srcSqlQuery = "select " +
									SqlTableProfs.Name + " as Name, " +
									SqlTableProfs.Id + " as 'E-Mail' " +
								"from " +
									SqlTableProfs.tableName;
	
	private Models.ListModel model;
	private Views.View view;
		
	private BoxElementTable table;
	private BoxElementExtendedSearchProf extendedSearch;
	private BoxElementSearchMenu searchMenu;
	
	private SelectedValueCallbackCtrl callback = null;
	
	/**
	 * Initialisiert die Ansicht der Betreuerliste.<br>
	 * Erstellt das {@link ListModel}.<br>
	 * Erstellt die {@link View} und setzt den Fensternamen auf "Betreuer".
	 */
	public ProfList(){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableProfs.tableName,SqlTableProfs.PrimaryKey);
		
		setModel(model);
		setView(view = new Views.View(this));
		view.setTitle("Betreuer");
		setElements();
	}
	
	/**
	 * Initialisiert die Ansicht der Betreuerliste mit der Möglichkeit Daten an den Aufrufer zurückzugeben.<br>
	 * Erstellt das {@link ListModel}.<br>
	 * Erstellt die {@link View} und setzt den Fensternamen auf "Betreuer auswählen".
	 * 
	 * @param callback Controller, welcher {@link SelectedValueCallbackCtrl} implementiert.
	 */
	public ProfList(SelectedValueCallbackCtrl callback){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableProfs.tableName,SqlTableProfs.PrimaryKey);
		
		setModel(model);
		setView(view = new Views.View(this));
		this.callback = callback;
		view.setTitle("Betreuer auswählen");
		setElementsForCallback();
	}
	
	/**
	 * Setzt die Viewelemente bei Initialisierung mit Callback.
	 */
	public void setElementsForCallback(){
		searchMenu = new BoxElementSearchMenu(this);
		view.addComponentToView(searchMenu);
		extendedSearch = new BoxElementExtendedSearchProf(this);
		extendedSearch.setVisible(false);
		view.addComponentToView(extendedSearch);
		table = new BoxElementTable(this);
		table.setColumnSelectionToOnlyOne();
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
	public void setElements(){
		searchMenu = new BoxElementSearchMenu(this);
		view.addComponentToView(searchMenu);
		extendedSearch = new BoxElementExtendedSearchProf(this);
		extendedSearch.setVisible(false);
		view.addComponentToView(extendedSearch);
		table = new BoxElementTable(this);
		view.addComponentToView(table);
		BoxElementBottomNavi navi = new BoxElementBottomNavi(this);
		navi.addBoxToRightSide(new BoxElementBottomNaviEdit(this));
		navi.addBoxToLeftSide(new BoxElementBottomNaviMark(this));
		view.addComponentToView(navi);
	}
		
	@Override
	public Object[][] getTableData() {
		return model.getTableData();
	}

	@Override
	public Object[] getTableHeader() {
		return model.getColumnAliasNames();
	}

	@Override
	public Object getValueFromPosition(int row, String column) {
		return model.getValueFromPosition(row, column);
	}

	@Override
	public void setValueAtPosition(int row, String column, Object value) {
		model.setValueAtPosition(row, column, value);
	}

	@Override
	public int getColumnIndex(String columnName) {
		return model.getColumnAliasIndex(columnName);
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
	public void setOrderByColumn(int columnIndex, SortOrder orderDirection) {
		model.setOrder(columnIndex, orderDirection);
	}

	@Override
	public void buttonEditClicked() {
		Object[] profList;
		if(table.getFlaggedRowCount() != 0)
			profList = table.getColumnValuesFromFlaggedRows("E-Mail");
		else
			profList = table.getColumnValuesFromSelectedRows("E-Mail");
		
		if(profList.length == 0){
			new OkDialog("Bitte w\u00E4hlen Sie mindestens einen Eintrag aus.");
			return;
		}
		
		
		ProfSingle newFrame = new ProfSingle(profList);
		Praktikumsverwaltung.addFrameToForeground(newFrame);
	}

	@Override
	public void buttonMarkClicked() {
		table.setFlagOnSelectedRows();
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
}
