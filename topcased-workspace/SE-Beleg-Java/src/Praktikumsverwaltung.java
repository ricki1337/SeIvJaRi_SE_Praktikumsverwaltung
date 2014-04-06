import Controller.*;
import Models.Datenbank.Database;

import javax.swing.*;


public class Praktikumsverwaltung extends JFrame{

	JDesktopPane InnerDesktop;
	
	
	public Praktikumsverwaltung(String dbHost){
		//temporär
		super("MainFrame");
		InnerDesktop = new JDesktopPane();
		setContentPane(InnerDesktop);
		
		//test
		Database db = Database.getInstance();
		db.connect("MySql", dbHost, 3306, "root", "", "seproject");
		
		
		//student controller starten...
		Controller c = new Student();
		
		
		
		c.display();
		InnerDesktop.add(c);
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
