package Controller;



import javax.swing.JInternalFrame;

import Models.Model;
import Praktikumsverwaltung.Praktikumsverwaltung;
import Views.View;

/**
 * Controller Standardfunktionen.<br>
 * Muss von allen Controllern implementiert werden, da diese dem Hauptframe ({@link Praktikumsverwaltung}) hinzugefügt werden.
 */
public abstract class Controller {
	protected Model model = null;
	protected View view = null;
	
	/**
	 * Standardkonstruktor.<br>
	 * Stellt nur Variablen bereit.
	 */
	public Controller(){}
	
	/**
	 * Initialisiert den Controller und setzt das übergebene Model und die View.
	 * 
	 * @param view	Instanz von {@link View}.
	 * @param model	Instanz von {@link Model}.
	 */
	public Controller(View view, Model model){
		model.setView(view);
		view.setModel(model);
		setModel(model);
		setView(view);
		
	}
	
	/**
	 * Setzt die interne View mit einer Instanz von {@link View}.
	 * @param view	Instanz von {@link View}.
	 * @throws IllegalArgumentException
	 */
	public void setView(View view){
		if(view == null)
			throw new IllegalArgumentException();
		this.view = view;
	}
	
	/**
	 * Setzt das interne Model mit einer Instanz von {@link Model}.
	 * 
	 * @param model	Instanz von {@link Model}.
	 * @throws IllegalArgumentException
	 */
	public void setModel(Model model){
		if(model == null)
			throw new IllegalArgumentException();
		this.model = model;
	}
	
	/**
	 * Gibt das gesetzte interne {@link Model} zurück.
	 * @return	Intern gesetzte {@link Model} Instanz.
	 */
	public Model getModel(){
		return this.model;
	}
	
	/**
	 * Gibt die {@link JInternalFrame} Repräsentation der internen {@link View} zurück.
	 * @return	{@link JInternalFrame} Repräsentation der internen {@link View}.
	 * @throws 	IllegalStateException
	 */
	public JInternalFrame getDisplayedFrame(){
		if(view == null)
			throw new IllegalStateException();
		return this.view;
	}
	
	/**
	 * Schließt den Controller und beendet die Verbindung zum Model und zur View.<br>
	 * Model beendet die Verbindung zur Datenbank.<br>
	 * View wird geschlossen.	
	 */
	public void close(){
		if(model != null)
			model.modelClose();
		if(view != null)
			view.dispose();
	}
	
	/**
	 * Bringt die View zur Anzeige.<br>
	 * Wird der Controller bei {@link Praktikumsverwaltung} registriert, findet der Aufruf statt.
	 */
	public abstract void display();
	
}
