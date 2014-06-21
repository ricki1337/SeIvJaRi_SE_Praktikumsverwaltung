package Controller;

import javax.swing.SortOrder;

import Controller.Interfaces.SelectedValueCallbackCtrl;
import Models.Datenbank.SqlTableCompanies;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.Dialog.OkDialog;
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

/**
 *	Verwaltet die Listenansicht der Firmen. 
 */
public class CompanyList extends Controller implements 	BasicBoxCtrl, 
															TableBoxCtrl, 
															SearchBoxCtrl, 
															ExtendedSearchBoxCtrl, 
															NaviMarkBoxCtrl, 
															NaviAbortSelectBoxCtrl, 
															NaviEditBoxCtrl{
	/**
	 * Enthält den Sql-Query-String für die Tabellenspalten und Daten.<br>
	 * Spaltennamen = Alias.
	 */
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
	private Views.View view;
		
	private BoxElementTable table;
	private BoxElementExtendedSearchCompany extendedSearch;
	private BoxElementSearchMenu searchMenu;
	
	private SelectedValueCallbackCtrl callback = null;
	
	/**
	 * Erstellt die Listenansicht der Firmen<br>
	 * Kreiert das Datenmodel auf Grundlage des angegebenen Sql-Query und füllt die Tabelle mit den in der Datenbank hinterlegten Daten,<br>
	 * legt den Fensternamen fest<br>
	 * und definiert das Aussehen der View.
	 */
	public CompanyList(){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableCompanies.tableName,SqlTableCompanies.PrimaryKey);
		
		setModel(model);
		setView(view = new Views.View(this));
		view.setTitle("Firmen");
		setElements();
	}
	
	
	/**
	 * Erstellt die Listenansicht der Firmen, mit der Möglichkeit einen {@link SelectedValueCallbackCtrl} aufzurufen.
	 * Kreiert das Datenmodel auf Grundlage des angegebenen Sql-Query und füllt die Tabelle mit den in der Datenbank hinterlegten Daten,<br>
	 * legt den Fensternamen fest, <br>
	 * stellt die Verbindung zum {@link SelectedValueCallbackCtrl} her<br>
	 * und definiert das Aussehen der View.
	 * @param callback
	 */
	public CompanyList(SelectedValueCallbackCtrl callback){
		super();
		model = new Models.ListModel(srcSqlQuery,SqlTableCompanies.tableName,SqlTableCompanies.PrimaryKey);
		
		setModel(model);
		setView(view = new Views.View(this));
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
	
	/**
	 * Anordnung der Box-Elemente in der View bei Aufruf des Controllers mit einem {@link SelectedValueCallbackCtrl}.
	 * Die Elemente werden der Reihenfolge nach von oben nach unten angeordnet.
	 */
	public void setElementsForCallback(){
		searchMenu = new BoxElementSearchMenu(this);
		view.addComponentToView(searchMenu);
		
		extendedSearch = new BoxElementExtendedSearchCompany(this);
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
	public Object[][] getTableData() {
		
		return model.getTableData();
	}

	@Override
	public Object[] getTableHeader() {
		return model.getTableHeader();
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
	public void setOrderByColumn(int columnIndex, SortOrder orderDirection) {
		model.setOrder(columnIndex, orderDirection);
	}

	@Override
	public int getSqlRecordLimit() {
		return model.getColumnLimit();
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
	public void buttonEditClicked() {
		Object[] companyList;
		if(table.getFlaggedRowCount() != 0)
			companyList = table.getColumnValuesFromFlaggedRows("ID");
		else
			companyList = table.getColumnValuesFromSelectedRows("ID");
		
		if(companyList.length == 0){
			new OkDialog("Bitte w\u00E4hlen Sie mindestens einen Eintrag aus.");
			return;
		}
			
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
}
