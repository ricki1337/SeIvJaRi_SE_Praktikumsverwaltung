package Controller.Mock;

import Controller.ControllerNew;
import Models.Model;
import Views.ViewNew;

public class MockController extends ControllerNew{

	public MockController(){
		super();
	}
	
	public MockController(ViewNew view, Model model){
		super(view,model);
	}
	
	@Override
	public Model getModel(){
		return new MockModel();
	}
	
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}

}
