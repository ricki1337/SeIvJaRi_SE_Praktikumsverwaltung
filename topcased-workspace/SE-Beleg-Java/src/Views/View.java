package Views;

import javax.swing.*;
import Models.*;
import Controller.*;

public abstract class View implements UpdateView{

	private Model model;
	private Controller controller;
	
	private JInternalFrame frame;
	
	public View(Controller controller){
		this.controller = controller;
		frame = controller;
		model = controller.getModel();
		model.setView(this);
	}
	
	@Override
	public void modelHasChanged() {
		// TODO Auto-generated method stub
		model.getResult();
	}
	
}
