package Controller;

import java.awt.event.ActionListener;

import javax.swing.*;
import Models.*;
import Views.*;

public abstract class Controller extends JInternalFrame implements ActionListener {
	protected Model model = null;
	protected View view = null;
	
	public Controller(){
		super();
	}
	
	public Controller(View view, Model model){
		this();
		
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
	
	public abstract void display();
}
