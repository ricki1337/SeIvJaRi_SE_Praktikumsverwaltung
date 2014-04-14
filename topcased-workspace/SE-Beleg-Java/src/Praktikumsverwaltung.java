import Controller.*;
import Models.Datenbank.Database;

import javax.swing.*;


public class Praktikumsverwaltung extends JFrame{

	static JDesktopPane InnerDesktop;
	
	public static void addFrame(Controller controller){
		controller.display();
		InnerDesktop.add(controller.getDisplayedFrame());
	}
	
	public Praktikumsverwaltung(String dbHost){
		//temporär
		super("MainFrame");
		InnerDesktop = new JDesktopPane();
		setContentPane(InnerDesktop);
		
		//test
		Database db = Database.getInstance();
		db.connect("MySql", dbHost, 3306, "root", "", "seproject");
		
		//controller  müssen über eine funktion erstellt werden
		//	-> controller bekommen den maincontroller mit übergeben, um selbst neue fenster zu erzeugen
		
		//student controller starten...
		Controller c = new StudentList();
		
		
		
		c.display();
		InnerDesktop.add(c.getDisplayedFrame());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(800, 600);
		setVisible(true);
	}
	
	public static void main(String[] args){
		//args[0] enthält db-host
		//praktikumsverwaltung starten
		new Praktikumsverwaltung(args[0]);
		
		
		
		
	}
}
