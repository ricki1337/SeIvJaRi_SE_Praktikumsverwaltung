package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionEvent;

public class StudentList extends ListController{
	
	public StudentList(){
		super();

		setModel(new Models.StudentList("select MatrNr as Matrikelnr, Firstname as Vorname, Name as Nachname, Email as 'E-Mail', StGr as Studiengruppe,Note as Bemerkung from student"));
		
		setView(new Views.StudentList(this));
	}

	

	@Override
	public void display() {
		// TODO Auto-generated method stub
		view.display();
	}


	
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		//view.setMarker(true);		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			//bei doppelklick auf eine zeile...wird die detailansicht der zeile geöffnet
	        System.out.println("Öffne Detailansicht...");
			//MainWindow.addInternalFrame(new Prakt_ViewStudent(MainWindow,table.getSelectedRow()));
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
		Controller c = new StudentList();
		//Praktikumsverwaltung.addFrame(c);
	}



	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyChar());
	}
	

}
