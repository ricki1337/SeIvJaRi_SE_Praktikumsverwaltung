package Controller;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.event.DocumentListener;
import Models.Model;
import Views.ListView;

//public abstract class ListController extends Controller implements ListSelectionListener, MouseListener,InputMethodListener{
public abstract class ListController extends Controller implements MouseListener,DocumentListener, ActionListener{
	
	public ListController(){}
	
	public ListController(ListView view, Model model){
		super(view,model);
	}
	
	
	

	@Override
	public abstract void display();
	
	

}
