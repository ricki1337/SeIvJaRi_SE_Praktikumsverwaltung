package Controller;

import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Models.Model;
import Views.ListView;
import Views.View;

//public abstract class ListController extends Controller implements ListSelectionListener, MouseListener,InputMethodListener{
public abstract class ListController extends Controller implements ListSelectionListener, MouseListener,KeyListener{
	
	public ListController(){}
	
	public ListController(ListView view, Model model){
		super(view,model);
	}
	
	
	

	@Override
	public abstract void display();
	
	

}
