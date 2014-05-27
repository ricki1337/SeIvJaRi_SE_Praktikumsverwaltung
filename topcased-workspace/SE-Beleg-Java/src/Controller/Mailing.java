package Controller;

import java.awt.event.MouseEvent;
import java.util.Arrays;

import Models.Model;
import Views.Table.TableData;

public class Mailing extends SingleController implements ChangeableController {

	Views.Mailing view;
	public Mailing(){
		setModel(new Models.Mailing());
		setView((view = new Views.Mailing(this,count)));
	}
	
	int count = 0;
	
	public Mailing(Object primaryKeys, String Primärschlüssel){
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
			
			
			query = new String("SELECT c.id as ID," +
					"s.firstname as Vorname,"  +
					" s.name as Nachname," +
					" s.matrnr as 'Matrikelnr.'," +
					" Prof as Betreuer," +
					" s.StGr as Studiengruppe," +
					" co.Name as Firmenname," +
					" bericht as Bericht," +
					" zeugnis as Zeugnis," +
					" pr.NameID"
					+ " as email" +
					" FROM contract c JOIN student s ON c.MatrNr = s.MatrNr JOIN companies co ON c.compid = co.id JOIN profs pr ON pr.Name = prof" +
					" where s.matrnr IN (" + sqlFilter +")");
			setModel(new Models.Mailing(query,"MatrNr"));
			}
	
	
	if(Primärschlüssel.equals("Name")){
		query = new String("SELECT c.id as ID," +
				"s.firstname as Vorname,"  +
				" s.name as Nachname," +
				" s.matrnr as 'Matrikelnr.'," +
				" Prof as Betreuer," +
				" s.StGr as Studiengruppe," +
				" co.Name as Firmenname," +
				" bericht as Bericht," +
				" zeugnis as Zeugnis," +
				" pr.NameID"
				+ " as email" +
				" FROM contract c JOIN student s ON c.MatrNr = s.MatrNr JOIN companies co ON c.compid = co.id JOIN profs pr ON pr.name = Prof" +
				" where pr.NameID in (" + sqlFilter +")");
		
		setModel(new Models.Mailing(query,"NameID"));
		System.out.println(sqlFilter);
		}
	
	if(Primärschlüssel.equals("ID")){
		query = new String("SELECT c.id as ID," +
				"s.firstname as Vorname,"  +
				" s.name as Nachname," +
				" s.matrnr as 'Matrikelnr.'," +
				" Prof as Betreuer," +
				" s.StGr as Studiengruppe," +
				" co.Name as Firmenname," +
				" bericht as Bericht," +
				" zeugnis as Zeugnis," +
				" pr.NameID"
				+ " as email" +
				" FROM contract c JOIN student s ON c.MatrNr = s.MatrNr JOIN companies co ON c.compid = co.id JOIN profs pr ON pr.name = Prof" +
				" where c.id IN (" + sqlFilter +")");
		
		setModel(new Models.Mailing(query,"ID"));
		System.out.println(sqlFilter);
		}
	
	if(Primärschlüssel.equals("IDCompanies")){
		query = new String("SELECT c.id as ID," +
				"s.firstname as Vorname,"  +
				" s.name as Nachname," +
				" s.matrnr as 'Matrikelnr.'," +
				" Prof as Betreuer," +
				" s.StGr as Studiengruppe," +
				" co.Name as Firmenname," +
				" bericht as Bericht," +
				" zeugnis as Zeugnis," +
				" pr.NameID"
				+ " as email" +
				" FROM contract c JOIN student s ON c.MatrNr = s.MatrNr JOIN companies co ON c.compid = co.id JOIN profs pr ON pr.name = Prof" +
				" where co.ID in (" + sqlFilter +")");
		
		setModel(new Models.Mailing(query,"ID"));
		System.out.println(sqlFilter);
		}
	


		setView((view = new Views.Mailing(this,count)));
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
