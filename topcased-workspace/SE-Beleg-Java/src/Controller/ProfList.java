package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.GuiElemente.SearchPanel.ExtendedSearchPanel;

public class ProfList extends ListController{
	
private String srcSqlQuery = "select Name as Name, NameID as 'E-Mail' from profs";
	private Views.ProfList view;
	
	
	public ProfList(){
		super();

		setModel(new Models.ProfList(srcSqlQuery));
		
		setView(view = new Views.ProfList(this));
	}

	

	@Override
	public void display() {
		// TODO Auto-generated method stub
		view.display();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(e.getComponent().getName()){
		case "table": 	if (e.getClickCount() == 2) {
							Object profId;
							
							if((profId = view.getColumnValueFromSelectedRow("E-Mail")) != null){
							ProfSingle newFrame = new ProfSingle(profId);
							Praktikumsverwaltung.addFrameToForeground(newFrame);
							}
						}else{
							view.setFlag();
						}
						break;
						
		case "setFlagOnMarkedRows": 	view.setFlagOnSelectedRow();
										break;
										
		case "modifySelectedRows":		Object[] profList = view.getColumnValuesFromSelectedRows("E-Mail");
										ProfSingle newFrame = new ProfSingle(profList);
										Praktikumsverwaltung.addFrameToForeground(newFrame);
										break;
		case "anlegen":					ProfEmptySingle newEmptyFrame = new ProfEmptySingle();
										Praktikumsverwaltung.addFrameToForeground(newEmptyFrame);
										break;
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		Document searchField = arg0.getDocument();
		
		try {
			String searchValue = searchField.getText(0,searchField.getLength());
			((Models.ProfList)model).setSearchFilter(searchValue);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@Override
	public void insertUpdate(DocumentEvent arg0) {
		Document searchField = arg0.getDocument();
		try {
			String searchValue = searchField.getText(0,searchField.getLength());
			((Models.ProfList)model).setSearchFilter(searchValue);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public void removeUpdate(DocumentEvent arg0) {
		Document searchField = arg0.getDocument();
		try {
			String searchValue = searchField.getText(0,searchField.getLength());
			((Models.ProfList)model).setSearchFilter(searchValue);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() instanceof JTextField){
			JTextField anzDatensaetze = (JTextField)(arg0.getSource());
			if(anzDatensaetze.getName().equals("anzDatensaetze")){
				model.setcolumnLimitAndRefreshViews(Integer.parseInt(anzDatensaetze.getText()));
				view.setTableRowsCount(Integer.parseInt(anzDatensaetze.getText()));
			}
			
		}
		if(arg0.getSource() instanceof JButton){
			JButton searchButton = (JButton)(arg0.getSource());
			ExtendedSearchPanel searchPanel = (ExtendedSearchPanel)searchButton.getParent();
			((Models.ProfList)model).setSearchFilter(searchPanel.getSearchValues());
		}
	}
	
	


}
