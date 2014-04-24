package Controller;

import java.awt.event.MouseListener;

import Models.Model;
import Views.ListView;

public abstract class SingleController extends Controller implements MouseListener{
	
	public SingleController(){}
	
	public SingleController(ListView view, Model model){
		super(view,model);
	}
	
	public abstract void display();
}
