package Controller;

import java.awt.event.MouseEvent;
import java.util.Arrays;

import Models.Model;
import Views.Table.TableData;

public class Print extends SingleController implements ChangeableController {

	Views.Print view;
	public Print(){
		setModel(new Models.Print());
		setView((view = new Views.Print(this,count)));
	}
	
	int count = 0;
	
	public Print(Object primaryKeys, String Primärschlüssel){
		super();
		String sqlFilter = new String();
		if(primaryKeys instanceof Object[]){		
			
			for(Object item:(Object[])primaryKeys){
				
			if(Primärschlüssel.equals("Matrikelnr")|| Primärschlüssel.equals("ID") || Primärschlüssel.equals("IDCompanies")){
				if(count == 0) sqlFilter = item.toString();
				else sqlFilter += " ,"+item.toString();
				count++;}
			
			if(Primärschlüssel.equals("Name")){
				if(count == 0) sqlFilter = "'"+ item.toString()+"'";
				else sqlFilter += " ,'"+item.toString()+"'";
				count++;}
			
			}
		} else{
			if(Primärschlüssel.equals("Matrikelnr") || Primärschlüssel.equals("ID")|| Primärschlüssel.equals("IDCompanies")){
			sqlFilter = primaryKeys.toString();}
			
			if(Primärschlüssel.equals("Name")){
				sqlFilter = "'"+primaryKeys.toString()+"'";}
		}

		
		
		String query = null;
		
		
		if(Primärschlüssel.equals("Matrikelnr")){
			
			query = new String("SELECT s.email as Email," +
					"s.firstname as Vorname,"  +
					" s.name as Nachname," +
					" s.matrnr as 'Matrikelnr.'," +
					" s.StGr as Studiengruppe," +
					" co.Name as Firmenname" +
					" FROM contract c JOIN student s ON c.MatrNr = s.MatrNr JOIN companies co ON c.compid = co.id " +
					" where s.matrnr IN (" + sqlFilter +")");
			setModel(new Models.Print(query,"MatrNr"));}
		
		if(Primärschlüssel.equals("ID")){
			query = new String("SELECT s.email as Email," +
					"s.firstname as Vorname,"  +
					" s.name as Nachname," +
					" s.matrnr as 'Matrikelnr.'," +
					" s.StGr as Studiengruppe," +
					" co.Name as Firmenname" +
					" FROM contract c JOIN student s ON c.MatrNr = s.MatrNr JOIN companies co ON c.compid = co.id JOIN profs pr ON pr.name = Prof" +
					" where c.id IN (" + sqlFilter +")");
			
			setModel(new Models.Print(query,"ID"));
			}
		
		if(Primärschlüssel.equals("IDCompanies")){
			query = new String("SELECT s.email as Email," +
					"s.firstname as Vorname,"  +
					" s.name as Nachname," +
					" s.matrnr as 'Matrikelnr.'," +
					" s.StGr as Studiengruppe," +
					" co.Name as Firmenname" +
					" FROM contract c JOIN student s ON c.MatrNr = s.MatrNr JOIN companies co ON c.compid = co.id JOIN profs pr ON pr.name = Prof" +
					" where co.ID in (" + sqlFilter +")");
			
			setModel(new Models.Mailing(query,"ID"));
			}
		
		if(Primärschlüssel.equals("Name")){
			query = new String("SELECT s.email as Email," +
					"s.firstname as Vorname,"  +
					" s.name as Nachname," +
					" s.matrnr as 'Matrikelnr.'," +
					" s.StGr as Studiengruppe," +
					" co.Name as Firmenname" +
					" FROM contract c JOIN student s ON c.MatrNr = s.MatrNr JOIN companies co ON c.compid = co.id JOIN profs pr ON pr.name = Prof" +
					" where pr.NameID in (" + sqlFilter +")");
			
			setModel(new Models.Mailing(query,"NameID"));
			}
	
		setView((view = new Views.Print(this,count)));
	
		
		
		
	}
	
	
	@Override
	public void display() {
		view.setElemente();
	}
	



	@Override
	public void mouseClicked(MouseEvent e) {
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void change(String valueName, Object value) {
		// TODO Auto-generated method stub
		
	}


}
