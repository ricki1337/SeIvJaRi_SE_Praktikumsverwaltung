package Controller;



import javax.swing.*;
import Models.*;
import Views.*;

public abstract class Controller {
	protected Model model = null;
	protected View view = null;
	
	public Controller(){}
	
	public Controller(View view, Model model){

		setModel(model);
		setView(view);
	}
	
	public void setView(View view){
		this.view = view;
	}
	
	public void setModel(Model model){
		this.model = model;
	}
	
	public Model getModel(){
		return this.model;
	}
	
	public JInternalFrame getDisplayedFrame(){
		return this.view;
	}
	
	public abstract void display();
	
}
