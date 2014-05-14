package Controller;

import Mail_1version.guiMail;
import Praktikumsverwaltung.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import drucken.MainTest;

public class ContractList extends ListController{
	
	private String srcSqlQuery = "SELECT c.id as ID," +
			"s.matrnr as 'Matrikelnr.'," +
			" s.firstname as Vorname," +
			" s.name as Nachname," +
			" s.stgr as Studiengruppe," +
			" co.name as Firmenname," +
			" type as Typ," +
			" begpr as 'beginnt am'," +
			" endpr as 'endet am'," +
			" Prof as Betreuer," +
			" bericht as Bericht," +
			" zeugnis as Zeugnis," +
			" empfehlung as Empfehlung" +
			"  FROM contract c JOIN student s ON c.MatrNr = s.MatrNr JOIN companies co ON c.compid = co.id";
	private Views.ContractList view;
	
	
	public ContractList(){
		super();

		setModel(new Models.ContractList(srcSqlQuery));
		
		setView(view = new Views.ContractList(this));
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
							Object contractId;
							
							if((contractId = view.getColumnValueFromSelectedRow("ID")) != null){
								ContractSingle newFrame = new ContractSingle(contractId);
								Praktikumsverwaltung.addFrameToForeground(newFrame);
							}
						}else{
							view.setFlag();
						}
						break;
						
		case "setFlagOnMarkedRows": 	view.setFlagOnSelectedRow();
										break;
										
		case "modifySelectedRows":		Object[] ContractList = view.getColumnValuesFromSelectedRows("ID");
										ContractSingle newFrame = new ContractSingle(ContractList);
										Praktikumsverwaltung.addFrameToForeground(newFrame);
										break;
		case "anlegen":					ContractEmptySingle newEmptyFrame = new ContractEmptySingle();
										Praktikumsverwaltung.addFrameToForeground(newEmptyFrame);
										break;
		case "mailTo":						String array [] []  = { {"hans", "wurst", "34049" , "beck", "043", "wurstbetrieb","0","1","s68551"},
										{"jakob", "heltzig", "34050" , "beck", "043", "prbetrieb","1","1","s68551"}, 
										{"seppus", "härtelus", "33350" , "beck", "043", "prbetrieb","0","0","s68551"} };
						
						
										guiMail bl = new guiMail(array);
										bl.setVisible(true);
										break;
										
										
		case "print" :				    String[][] testary;
										testary = new String[][] {
										{ "65878", "Hans Detlef", "muh@web.de", "11/43/01", "sap" },
										{ "98564", "Rolf Golf", "blub@freenet.de", "12/13/14","Microsoft" },
										{ "45879", "Franz Lenz", "tag@morgen.com", "00/11/22", "Himmel" },
										{ "25874", "Lisa Müller", "hallo@wasgeht.de", "10/024/34","Golfplatz" },
										{ "97854", "Hans Wurst", "langweilig@langweildich.net","09/034/61", "Imbiss" },

										};

										MainTest testding = null;
										try {
											testding = new MainTest();
										} catch (Exception e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									testding.setData(testary, "Titel","Überschrift");
									try {
									testding.printFile("output.html");
									} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
									}
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
			((Models.ContractList)model).setSearchFilter(searchValue);
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
			((Models.ContractList)model).setSearchFilter(searchValue);
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
			((Models.ContractList)model).setSearchFilter(searchValue);
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
				model.setcolumnLimit(Integer.parseInt(anzDatensaetze.getText()));
				view.setTableRowsCount(Integer.parseInt(anzDatensaetze.getText()));
			}
			
		}
	}
	
	


}
