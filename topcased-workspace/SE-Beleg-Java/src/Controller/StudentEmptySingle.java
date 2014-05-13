package Controller;

import java.awt.event.MouseEvent;

public class StudentEmptySingle extends SingleController{
	
	Views.StudentEmptySingle view;
	
	public StudentEmptySingle(){
		setModel(new Models.StudentSingle());
		setView((view = new Views.StudentEmptySingle(this)));
	}

	@Override
	public void display() {
		view.display();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(e.getComponent().getName()){
			case "save": 	model.insertIntoDatabase();
							view.clearAllFields();
							break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
