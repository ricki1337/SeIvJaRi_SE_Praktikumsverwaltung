package Praktikumsverwaltung;


import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import ConfigParser.Debug;
import Controller.Controller;
import Views.Dialog.DatabaseConnectionDialog;
import Views.GuiElemente.MenuBar;

/**
 * Hauptfenster der Anwendung<br>
 * Beinhaltet die main und ist für das Anzeigen von Views zuständig.
 */
public class Praktikumsverwaltung extends JFrame{

	static JDesktopPane InnerDesktop;
	static Praktikumsverwaltung praktikumsverwaltung ;
	
	/**
	 * Bringt eine View zur Anzeige im InnerDesktop.
	 * @param controller	Controller einer View.
	 */
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
	
	/**
	 * Liefert die Frame Repräsentation des Hauptfensters
	 * @return	Frame
	 */
	public static Frame getFrame(){
		return praktikumsverwaltung;
	}
	
	/**
	 * Hauptkonstruktor<br>
	 * Legt das Hauptfenster an und<br>
	 * Initialisiert die Datenbankverbindung
	 * @param dbHost	IP-Adresse oder DNS Name des Datenbankservers
	 */
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
			System.out.println("Error! Es wurde eine falsche Anzahl Parameter übergeben.");
			System.out.println("Parameter 1: Datenbankserver IP oder DNS Name - pflicht");
			System.out.println("Parameter 2: 'debug' - optional");
			System.exit(0);
		}
		//args[0] enthält db-host
		//args[1] enthält debug mode
		
		if(args.length == 2){
			if(args[1].toLowerCase().equals("debug"))
				Debug.setDebugMode(true);
		}
			
		Praktikumsverwaltung.praktikumsverwaltung = new Praktikumsverwaltung(args[0]);
		
	}
}
