package Controller;

import Praktikumsverwaltung.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class StudentListToContract extends ListController{
	
	private String srcSqlQuery = "select MatrNr as Matrikelnr," +
									" Firstname as Vorname," +
									" Name as Nachname," +
									" Email as 'E-Mail'," +
									" StGr as Studiengruppe," +
									" Note as Bemerkung" +
									" from student";
	private Views.StudentListToContract view;
	private ChangeableController calledController;
	
	public StudentListToContract(ChangeableController calledController){
		super();
		this.calledController = calledController;
		setModel(new Models.StudentList(srcSqlQuery));
		
		setView(view = new Views.StudentListToContract(this));
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
							Object studentId;
							
							if((studentId = view.getColumnValueFromSelectedRow("Matrikelnr")) != null){
							StudentSingle newFrame = new StudentSingle(studentId);
							Praktikumsverwaltung.addFrameToForeground(newFrame);
							}
						}else{
							view.setFlag();
						}
						break;
						
		case "setMarkedRow": 	calledController.change("student",view.getColumnValueFromSelectedRow("Matrikelnr").toString());
										view.dispose();
										break;
										
		case "anlegen":					StudentEmptySingle newEmptyFrame = new StudentEmptySingle();
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
			((Models.StudentList)model).setSearchFilter(searchValue);
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
			((Models.StudentList)model).setSearchFilter(searchValue);
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
			((Models.StudentList)model).setSearchFilter(searchValue);
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
	}
	
	


}
