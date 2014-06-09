package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import Controller.Interfaces.ChangeableController;
import Models.Datenbank.SqlTableCompanies;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.GuiElemente.SearchPanel.ExtendedSearchPanel;

public class CompanyListToContract extends ListController{
	
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
	private Views.CompanieListToContract view;
	private ChangeableController calledController;
	
	public CompanyListToContract(ChangeableController calledController){
		super();
		this.calledController = calledController;
		setModel(new Models.ListModel(srcSqlQuery,SqlTableCompanies.tableName,SqlTableCompanies.TableNameDotPrimaryKey));
		
		setView(view = new Views.CompanieListToContract(this));
	}
	

	@Override
	public void display() {
		view.display();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(e.getComponent().getName()){
		case "table": 	if (e.getClickCount() == 2) {
							Object companieId;
							
							if((companieId = view.getColumnValueFromSelectedRow("ID")) != null){
							CompanySingle newFrame = new CompanySingle(companieId);
							Praktikumsverwaltung.addFrameToForeground(newFrame);
							}
						}else{
							view.setFlag();
						}
						break;
						
		case "setMarkedRow": 	calledController.change("company",view.getColumnValueFromSelectedRow("ID").toString());
								view.dispose();
								break;
										
		case "anlegen":					CompanieEmptySingle newEmptyFrame = new CompanieEmptySingle();
										Praktikumsverwaltung.addFrameToForeground(newEmptyFrame);
										break;
		}
	}


	@Override
	public void changedUpdate(DocumentEvent arg0) {
		Document searchField = arg0.getDocument();
		
		try {
			String searchValue = searchField.getText(0,searchField.getLength());
			((Models.ListModel)model).setSearchFilter(searchValue);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}



	@Override
	public void insertUpdate(DocumentEvent arg0) {
		Document searchField = arg0.getDocument();
		try {
			String searchValue = searchField.getText(0,searchField.getLength());
			((Models.ListModel)model).setSearchFilter(searchValue);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
	}



	@Override
	public void removeUpdate(DocumentEvent arg0) {
		Document searchField = arg0.getDocument();
		try {
			String searchValue = searchField.getText(0,searchField.getLength());
			((Models.ListModel)model).setSearchFilter(searchValue);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() instanceof JTextField){
			JTextField anzDatensaetze = (JTextField)(arg0.getSource());
			if(anzDatensaetze.getName().equals("anzDatensaetze")){
				model.setColumnLimitAndRefreshViews(Integer.parseInt(anzDatensaetze.getText()));
				view.setTableRowsCount(Integer.parseInt(anzDatensaetze.getText()));
			}
			
		}
		if(arg0.getSource() instanceof JButton){
			JButton searchButton = (JButton)(arg0.getSource());
			ExtendedSearchPanel searchPanel = (ExtendedSearchPanel)searchButton.getParent();
			((Models.ListModel)model).setSearchFilter(searchPanel.getSearchValues());
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}



	@Override
	public void mouseExited(MouseEvent e) {}



	@Override
	public void mousePressed(MouseEvent e) {}



	@Override
	public void mouseReleased(MouseEvent e) {}
	


}
