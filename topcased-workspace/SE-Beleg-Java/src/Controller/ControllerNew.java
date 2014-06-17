package Controller;



import javax.swing.JInternalFrame;

import Models.Model;
import Views.ViewNew;

public abstract class ControllerNew {
	protected Model model = null;
	protected ViewNew view = null;
	
	public ControllerNew(){}
	
	public ControllerNew(ViewNew view, Model model){
		setModel(model);
		setView(view);
	}
	
	public void setView(ViewNew view){
		if(view == null)
			throw new IllegalArgumentException();
		this.view = view;
	}
	
	public void setModel(Model model){
		if(model == null)
			throw new IllegalArgumentException();
		this.model = model;
	}
	
	public Model getModel(){
		return this.model;
	}
	
	public JInternalFrame getDisplayedFrame(){
		if(view == null)
			throw new IllegalStateException();
		return this.view;
	}
	
	public void close(){
		if(model != null)
			model.modelClose();
		if(view != null)
			view.dispose();
	}
	
	public abstract void display();
	
}
