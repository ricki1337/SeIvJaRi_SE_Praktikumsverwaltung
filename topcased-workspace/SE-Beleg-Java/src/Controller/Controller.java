package Controller;

import javax.swing.*;
import Models.*;
import Views.*;

public abstract class Controller extends JInternalFrame {
	private View view;
	private Model model;
	
	public abstract void setView();
	
	public abstract void setModel();
	
	
	public Controller(){
		super();
		setModel();
		setView();
	}
	
	
	public View getView(){
		return view;
	}
	
	public Model getModel(){
		return model;
	}
	

}
