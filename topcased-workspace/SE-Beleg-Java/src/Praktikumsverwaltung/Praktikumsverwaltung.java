package Praktikumsverwaltung;


import java.beans.PropertyVetoException;

import Controller.*;
import Models.Datenbank.Database;
import Views.Dialog.DatabaseConnectionDialog;
import Views.GuiElemente.MenuBar;

import javax.swing.*;
import ConfigParser.Config;

public class Praktikumsverwaltung extends JFrame{

	static JDesktopPane InnerDesktop;
	
	public static void addFrameToForeground(Controller controller){
		controller.display();
		JInternalFrame newFrame = controller.getDisplayedFrame();
		InnerDesktop.add(newFrame);
		try {
			newFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public Praktikumsverwaltung(String dbHost){
		super("Praktikumsverwaltung");
		InnerDesktop = new JDesktopPane();
		setContentPane(InnerDesktop);
		

		
		DatabaseConnectionDialog Dialog = new DatabaseConnectionDialog(dbHost,this, true);
		Dialog.setLocationRelativeTo(null);
		Dialog.setVisible(true);
		
		
		
		
		MenuBar menu = new MenuBar();
		this.setJMenuBar(menu.getMenu());
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(1024, 768);
		setVisible(true);
	}
	
	public static void main(String[] args){
		//args[0] enthält db-host
		//praktikumsverwaltung starten
		new Praktikumsverwaltung(args[0]);
		
		
		
		
	}
}
