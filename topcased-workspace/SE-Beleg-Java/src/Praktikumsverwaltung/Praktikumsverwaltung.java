package Praktikumsverwaltung;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import ConfigParser.Debug;
import Controller.ControllerNew;
import Views.Dialog.DatabaseConnectionDialog;
import Views.GuiElemente.MenuBar;

public class Praktikumsverwaltung extends JFrame{

	static JDesktopPane InnerDesktop;
	static Praktikumsverwaltung praktikumsverwaltung ;
	
	public static void addFrameToForeground(ControllerNew controller){
		controller.display();
		JInternalFrame newFrame = controller.getDisplayedFrame();
		InnerDesktop.add(newFrame);
		try {
			newFrame.setSelected(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	public static Praktikumsverwaltung getFrame(){return praktikumsverwaltung;}
	
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

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		
		setSize(width, height);
		setVisible(true);
	}
	
	public static void main(String[] args){
		

		if(args.length > 2 || args.length == 0){
			System.out.println("Error! Es wurde eine falsche Anzahl Parameter �bergeben.");
			System.exit(0);
		}
		//args[0] enth�lt db-host
		//args[1] enth�lt debug mode
		
		if(args.length == 2){
			if(args[1].equals("debug"))
				Debug.setDebugMode(true);
			System.out.println(Debug.isDebugMode());
		}
			
		praktikumsverwaltung = new Praktikumsverwaltung(args[0]);
		
	}
}
