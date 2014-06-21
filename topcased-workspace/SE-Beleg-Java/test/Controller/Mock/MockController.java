package Controller.Mock;

import Controller.Controller;
import Models.Model;
import Views.View;

public class MockController extends Controller{

	public MockController(){
		super();
	}
	
	public MockController(View view, Model model){
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
