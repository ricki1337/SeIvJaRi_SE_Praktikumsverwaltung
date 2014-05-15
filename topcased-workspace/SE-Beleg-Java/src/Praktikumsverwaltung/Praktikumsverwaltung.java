package Praktikumsverwaltung;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.beans.PropertyVetoException;

import Controller.*;
import Views.Dialog.DatabaseConnectionDialog;
import Views.GuiElemente.MenuBar;

import javax.swing.*;
//import ConfigParser.Config;

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
		/*
		 * http://stackoverflow.com/questions/3680221/screen-resolution-java
		 * */
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		
		
		setSize(width, height);
		setVisible(true);
	}
	
	public static void main(String[] args){
		//args[0] enth�lt db-host
		//praktikumsverwaltung starten
		new Praktikumsverwaltung(args[0]);
		
	}
}
